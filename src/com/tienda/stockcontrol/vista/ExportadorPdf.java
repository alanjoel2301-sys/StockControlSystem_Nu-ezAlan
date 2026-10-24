
package com.tienda.stockcontrol.vista;

/**
 *
 * @author Dell
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public final class ExportadorPdf {

    // Hoja A4 en puntos; ESCALA = 2.78 equivale a unos 200 puntos por pulgada
    private static final double ANCHO = 595.28;
    private static final double ALTO = 841.89;
    private static final double MARGEN = 36;
    private static final double ESCALA = 2.78;
    private static final double ANCHO_UTIL = ANCHO - 2 * MARGEN;
    private static final double ALTO_CABECERA = 44;
    private static final double ALTO_PIE = 24;
    private static final double ALTO_CONTENIDO = ALTO - 2 * MARGEN - ALTO_CABECERA - ALTO_PIE;

    private static final FontRenderContext FRC = new FontRenderContext(null, true, true);
    private static final Font FUENTE = new Font(Font.SANS_SERIF, Font.PLAIN, 9);
    private static final Font NEGRITA = FUENTE.deriveFont(Font.BOLD);
    private static final Font TITULO = FUENTE.deriveFont(Font.BOLD, 15f);
    private static final Font PEQUENA = FUENTE.deriveFont(8f);
    private static final double ALTO_LINEA = 11;
    private static final double PAD_H = 5;
    private static final double PAD_V = 3.5;

    private static final Color TEXTO = new Color(0x222222);
    private static final Color SUAVE = new Color(0x777777);
    private static final Color LINEA = new Color(0xD0D5DD);
    private static final Color ACENTO = new Color(0x2F5D9E);
    private static final Color FILA_ALTERNA = new Color(0xF2F5FA);

    private ExportadorPdf() {
    }

    // ---------------------------------------------------------------
    //  Punto de entrada (lo llama el menu)
    // ---------------------------------------------------------------

    public static void exportarVentana(Component padre, JInternalFrame ventana) {
        if (ventana == null) {
            JOptionPane.showMessageDialog(padre, "Abra una ventana con una tabla o con gráficos para exportarla.",
                    "Exportar a PDF", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        List<ChartPanel> paneles = buscar(ventana, ChartPanel.class);
        List<JTable> tablas = buscar(ventana, JTable.class);
        if (paneles.isEmpty() && tablas.isEmpty()) {
            JOptionPane.showMessageDialog(padre, "Esta ventana no tiene una tabla ni gráficos para exportar.",
                    "Exportar a PDF", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Con graficos, la misma ventana de "Guardar como" ofrece exportar solo el actual
        JCheckBox soloActual = new JCheckBox("Solo el gráfico actual");
        File destino = pedirArchivo(padre, ventana.getTitle(), paneles.isEmpty() ? null : soloActual);
        if (destino == null) {
            return;
        }
        try {
            List<byte[]> paginas;
            if (paneles.isEmpty()) {
                paginas = paginasTabla(tablas.get(0), ventana.getTitle());
            } else {
                paginas = paginasGraficos(graficosElegidos(ventana, paneles, soloActual.isSelected()), ventana.getTitle());
            }
            escribirPdf(destino, paginas);
            abrir(padre, destino);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(padre, "No se pudo guardar el PDF: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static List<JFreeChart> graficosElegidos(JInternalFrame ventana, List<ChartPanel> todos, boolean soloActual) {
        List<ChartPanel> elegidos = todos;
        List<JTabbedPane> pestanas = buscar(ventana, JTabbedPane.class);
        if (soloActual && !pestanas.isEmpty() && pestanas.get(0).getSelectedComponent() != null) {
            List<ChartPanel> delTab = buscar(pestanas.get(0).getSelectedComponent(), ChartPanel.class);
            if (!delTab.isEmpty()) {
                elegidos = delTab;
            }
        }
        List<JFreeChart> graficos = new ArrayList<>();
        for (ChartPanel panel : elegidos) {
            graficos.add(panel.getChart());
        }
        return graficos;
    }

    // ---------------------------------------------------------------
    //  Tabla: tal como se ve en pantalla (filtros y orden incluidos)
    // ---------------------------------------------------------------

    private static List<byte[]> paginasTabla(JTable tabla, String titulo) throws IOException {
        int columnas = tabla.getColumnCount();
        int filas = tabla.getRowCount();

        String[] encabezados = new String[columnas];
        int[] alineacion = new int[columnas];
        String[][] celdas = new String[filas][columnas];
        double[] ancho = new double[columnas];
        double suma = 0;
        for (int c = 0; c < columnas; c++) {
            encabezados[c] = String.valueOf(tabla.getColumnName(c));
            alineacion[c] = JLabel.LEFT;
            ancho[c] = tabla.isShowing() ? tabla.getColumnModel().getColumn(c).getWidth()
                    : tabla.getColumnModel().getColumn(c).getPreferredWidth();
            suma += ancho[c];
        }
        for (int c = 0; c < columnas; c++) {
            ancho[c] = suma > 0 ? ANCHO_UTIL * ancho[c] / suma : ANCHO_UTIL / columnas;   // mismas proporciones que en pantalla
        }
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                Object valor = tabla.getValueAt(f, c);
                String texto = valor == null ? "" : valor.toString();
                try {
                    // El mismo renderer de la pantalla: respeta formatos y alineacion
                    Component render = tabla.prepareRenderer(tabla.getCellRenderer(f, c), f, c);
                    if (render instanceof JLabel) {
                        JLabel etiqueta = (JLabel) render;
                        texto = etiqueta.getText() == null ? "" : etiqueta.getText();
                        if (f == 0) {
                            alineacion[c] = etiqueta.getHorizontalAlignment();
                        }
                    }
                } catch (RuntimeException e) {
                    // si el renderer falla se usa el valor tal cual
                }
                celdas[f][c] = texto;
            }
        }

        // Textos largos: pasan a varias lineas dentro de la celda
        String[][] lineasEncabezado = new String[columnas][];
        double altoEncabezado = ALTO_LINEA;
        for (int c = 0; c < columnas; c++) {
            lineasEncabezado[c] = envolver(encabezados[c], ancho[c] - 2 * PAD_H, NEGRITA).toArray(new String[0]);
            altoEncabezado = Math.max(altoEncabezado, lineasEncabezado[c].length * ALTO_LINEA);
        }
        altoEncabezado += 2 * PAD_V;

        String[][][] lineas = new String[filas][columnas][];
        double[] altoFila = new double[filas];
        for (int f = 0; f < filas; f++) {
            int maximo = 1;
            for (int c = 0; c < columnas; c++) {
                lineas[f][c] = envolver(celdas[f][c], ancho[c] - 2 * PAD_H, FUENTE).toArray(new String[0]);
                maximo = Math.max(maximo, lineas[f][c].length);
            }
            altoFila[f] = maximo * ALTO_LINEA + 2 * PAD_V;
        }

        // Reparto de filas en hojas
        List<int[]> hojas = new ArrayList<>();      // {primera fila, ultima fila (exclusive)}
        int inicio = 0;
        double usado = 0;
        for (int f = 0; f < filas; f++) {
            if (usado + altoFila[f] > ALTO_CONTENIDO - altoEncabezado && f > inicio) {
                hojas.add(new int[]{inicio, f});
                inicio = f;
                usado = 0;
            }
            usado += altoFila[f];
        }
        hojas.add(new int[]{inicio, filas});

        String detalle = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "  -  " + filas + " registros";
        List<byte[]> paginas = new ArrayList<>();
        for (int h = 0; h < hojas.size(); h++) {
            BufferedImage imagen = nuevaPagina();
            Graphics2D g = lienzo(imagen);
            cabeceraYPie(g, titulo, detalle, h + 1, hojas.size());

            double y = MARGEN + ALTO_CABECERA;
            g.setColor(ACENTO);
            g.fill(new Rectangle2D.Double(MARGEN, y, ANCHO_UTIL, altoEncabezado));
            double x = MARGEN;
            for (int c = 0; c < columnas; c++) {
                dibujarCelda(g, lineasEncabezado[c], x, y, ancho[c], alineacion[c], NEGRITA, Color.WHITE);
                x += ancho[c];
            }
            y += altoEncabezado;

            for (int f = hojas.get(h)[0]; f < hojas.get(h)[1]; f++) {
                if ((f - hojas.get(h)[0]) % 2 == 1) {
                    g.setColor(FILA_ALTERNA);
                    g.fill(new Rectangle2D.Double(MARGEN, y, ANCHO_UTIL, altoFila[f]));
                }
                x = MARGEN;
                for (int c = 0; c < columnas; c++) {
                    dibujarCelda(g, lineas[f][c], x, y, ancho[c], alineacion[c], FUENTE, TEXTO);
                    x += ancho[c];
                }
                y += altoFila[f];
                g.setColor(LINEA);
                g.draw(new Rectangle2D.Double(MARGEN, y, ANCHO_UTIL, 0));
            }
            if (filas == 0) {
                g.setFont(FUENTE);
                g.setColor(SUAVE);
                g.drawString("No hay registros para mostrar.", (float) (MARGEN + PAD_H), (float) (y + ALTO_LINEA + PAD_V));
            }
            g.dispose();
            paginas.add(comprimir(imagen));
        }
        return paginas;
    }

    private static void dibujarCelda(Graphics2D g, String[] lineas, double x, double y, double anchoCelda,
            int alineacion, Font fuente, Color color) {
        g.setFont(fuente);
        g.setColor(color);
        double base = y + PAD_V + ALTO_LINEA - 2.5;
        for (String linea : lineas) {
            double w = ancho(linea, fuente);
            double posX = x + PAD_H;
            if (alineacion == JLabel.RIGHT || alineacion == JLabel.TRAILING) {
                posX = x + anchoCelda - PAD_H - w;
            } else if (alineacion == JLabel.CENTER) {
                posX = x + (anchoCelda - w) / 2;
            }
            g.drawString(linea, (float) posX, (float) base);
            base += ALTO_LINEA;
        }
    }

    /** Corta el texto en lineas que entren en el ancho dado (por palabras; si una palabra no entra, por letras). */
    private static List<String> envolver(String texto, double maximo, Font fuente) {
        List<String> lineas = new ArrayList<>();
        String actual = "";
        for (String palabra : texto.trim().split("\\s+")) {
            String candidata = actual.isEmpty() ? palabra : actual + " " + palabra;
            if (ancho(candidata, fuente) <= maximo) {
                actual = candidata;
                continue;
            }
            if (!actual.isEmpty()) {
                lineas.add(actual);
            }
            actual = palabra;
            while (ancho(actual, fuente) > maximo && actual.length() > 1) {
                int corte = actual.length() - 1;
                while (corte > 1 && ancho(actual.substring(0, corte), fuente) > maximo) {
                    corte--;
                }
                lineas.add(actual.substring(0, corte));
                actual = actual.substring(corte);
            }
        }
        lineas.add(actual);
        return lineas;
    }

    private static double ancho(String texto, Font fuente) {
        return fuente.getStringBounds(texto, FRC).getWidth();
    }

    // ---------------------------------------------------------------
    //  Graficos: siempre a color, varios por hoja
    // ---------------------------------------------------------------

    private static List<byte[]> paginasGraficos(List<JFreeChart> graficos, String titulo) throws IOException {
        int porPagina = graficos.size() <= 2 ? 2 : 3;
        double altoCelda = ALTO_CONTENIDO / porPagina;
        int total = (int) Math.ceil(graficos.size() / (double) porPagina);
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        List<byte[]> paginas = new ArrayList<>();
        for (int p = 0; p < total; p++) {
            BufferedImage imagen = nuevaPagina();
            Graphics2D g = lienzo(imagen);
            cabeceraYPie(g, titulo, fecha, p + 1, total);

            for (int i = 0; i < porPagina && p * porPagina + i < graficos.size(); i++) {
                JFreeChart grafico = graficos.get(p * porPagina + i);
                try {
                    grafico = (JFreeChart) grafico.clone();
                } catch (CloneNotSupportedException e) {
                    // se usa el original
                }
                // Aire arriba del eje de valores, para que no se corte el numero de la barra mas alta
                if (grafico.getPlot() instanceof org.jfree.chart.plot.CategoryPlot) {
                    ((org.jfree.chart.plot.CategoryPlot) grafico.getPlot()).getRangeAxis().setUpperMargin(0.15);
                }
                // El grafico se dibuja en un "lienzo" de 800 de ancho y se reduce a la hoja,
                // asi los textos quedan proporcionados
                double k = ANCHO_UTIL / 800;
                Graphics2D gc = (Graphics2D) g.create();
                gc.translate(MARGEN, MARGEN + ALTO_CABECERA + i * altoCelda);
                gc.scale(k, k);
                grafico.draw(gc, new Rectangle2D.Double(0, 0, 800, (altoCelda - 8) / k));
                gc.dispose();
            }
            g.dispose();
            paginas.add(comprimir(imagen));
        }
        return paginas;
    }

    // ---------------------------------------------------------------
    //  Paginas (imagenes) y cabecera / pie
    // ---------------------------------------------------------------

    private static BufferedImage nuevaPagina() {
        BufferedImage imagen = new BufferedImage((int) Math.round(ANCHO * ESCALA), (int) Math.round(ALTO * ESCALA),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = imagen.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, imagen.getWidth(), imagen.getHeight());
        g.dispose();
        return imagen;
    }

    private static Graphics2D lienzo(BufferedImage imagen) {
        Graphics2D g = imagen.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.scale(ESCALA, ESCALA);
        return g;
    }

    private static void cabeceraYPie(Graphics2D g, String titulo, String detalle, int pagina, int total) {
        g.setColor(TEXTO);
        g.setFont(TITULO);
        g.drawString(titulo, (float) MARGEN, (float) (MARGEN + 14));
        g.setColor(SUAVE);
        g.setFont(PEQUENA);
        g.drawString(detalle, (float) MARGEN, (float) (MARGEN + 27));
        g.setColor(LINEA);
        g.draw(new Rectangle2D.Double(MARGEN, MARGEN + ALTO_CABECERA - 8, ANCHO_UTIL, 0));

        double yLinea = ALTO - MARGEN - ALTO_PIE + 8;
        g.draw(new Rectangle2D.Double(MARGEN, yLinea, ANCHO_UTIL, 0));
        g.setColor(SUAVE);
        g.setFont(PEQUENA);
        g.drawString("Sistema de Control de Stock", (float) MARGEN, (float) (ALTO - MARGEN - 2));
        String numero = "Página " + pagina + " de " + total;
        g.drawString(numero, (float) (MARGEN + ANCHO_UTIL - ancho(numero, PEQUENA)), (float) (ALTO - MARGEN - 2));
    }

    // ---------------------------------------------------------------
    //  Archivo PDF: cada pagina es una imagen A4
    // ---------------------------------------------------------------

    private static byte[] comprimir(BufferedImage imagen) throws IOException {
        int ancho = imagen.getWidth();
        int[] fila = new int[ancho];
        byte[] bytes = new byte[ancho * 3];
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try (DeflaterOutputStream deflater = new DeflaterOutputStream(salida, new Deflater(Deflater.BEST_SPEED))) {
            for (int y = 0; y < imagen.getHeight(); y++) {
                imagen.getRGB(0, y, ancho, 1, fila, 0, ancho);
                for (int x = 0; x < ancho; x++) {
                    bytes[x * 3] = (byte) (fila[x] >> 16);
                    bytes[x * 3 + 1] = (byte) (fila[x] >> 8);
                    bytes[x * 3 + 2] = (byte) fila[x];
                }
                deflater.write(bytes);
            }
        }
        return salida.toByteArray();
    }

    /** Objetos: 1 catalogo, 2 paginas; despues, por cada hoja: imagen, contenido y pagina. */
    private static void escribirPdf(File destino, List<byte[]> paginas) throws IOException {
        int ancho = (int) Math.round(ANCHO * ESCALA);
        int alto = (int) Math.round(ALTO * ESCALA);
        int total = 2 + 3 * paginas.size();

        StringBuilder hijos = new StringBuilder();
        for (int i = 0; i < paginas.size(); i++) {
            hijos.append(5 + 3 * i).append(" 0 R ");
        }

        ByteArrayOutputStream pdf = new ByteArrayOutputStream();
        int[] posicion = new int[total + 1];
        pdf.write("%PDF-1.4\n".getBytes(StandardCharsets.ISO_8859_1));
        objeto(pdf, posicion, 1, "<< /Type /Catalog /Pages 2 0 R >>".getBytes(StandardCharsets.ISO_8859_1));
        objeto(pdf, posicion, 2, ("<< /Type /Pages /Kids [ " + hijos + "] /Count " + paginas.size() + " >>").getBytes(StandardCharsets.ISO_8859_1));
        for (int i = 0; i < paginas.size(); i++) {
            byte[] datos = paginas.get(i);
            ByteArrayOutputStream imagen = new ByteArrayOutputStream();
            imagen.write(("<< /Type /XObject /Subtype /Image /Width " + ancho + " /Height " + alto
                    + " /ColorSpace /DeviceRGB /BitsPerComponent 8 /Filter /FlateDecode /Length " + datos.length
                    + " >>\nstream\n").getBytes(StandardCharsets.ISO_8859_1));
            imagen.write(datos);
            imagen.write("\nendstream".getBytes(StandardCharsets.ISO_8859_1));
            objeto(pdf, posicion, 3 + 3 * i, imagen.toByteArray());

            String dibujo = "q " + ANCHO + " 0 0 " + ALTO + " 0 0 cm /Im0 Do Q";
            objeto(pdf, posicion, 4 + 3 * i, ("<< /Length " + dibujo.length() + " >>\nstream\n" + dibujo + "\nendstream")
                    .getBytes(StandardCharsets.ISO_8859_1));
            objeto(pdf, posicion, 5 + 3 * i, ("<< /Type /Page /Parent 2 0 R /MediaBox [0 0 " + ANCHO + " " + ALTO
                    + "] /Resources << /XObject << /Im0 " + (3 + 3 * i) + " 0 R >> >> /Contents " + (4 + 3 * i) + " 0 R >>")
                    .getBytes(StandardCharsets.ISO_8859_1));
        }

        int inicioXref = pdf.size();
        StringBuilder xref = new StringBuilder("xref\n0 " + (total + 1) + "\n0000000000 65535 f \n");
        for (int n = 1; n <= total; n++) {
            xref.append(String.format(java.util.Locale.ROOT, "%010d 00000 n \n", posicion[n]));
        }
        xref.append("trailer\n<< /Size ").append(total + 1).append(" /Root 1 0 R >>\nstartxref\n")
                .append(inicioXref).append("\n%%EOF\n");
        pdf.write(xref.toString().getBytes(StandardCharsets.ISO_8859_1));
        Files.write(destino.toPath(), pdf.toByteArray());
    }

    private static void objeto(ByteArrayOutputStream pdf, int[] posicion, int numero, byte[] cuerpo) throws IOException {
        posicion[numero] = pdf.size();
        pdf.write((numero + " 0 obj\n").getBytes(StandardCharsets.ISO_8859_1));
        pdf.write(cuerpo);
        pdf.write("\nendobj\n".getBytes(StandardCharsets.ISO_8859_1));
    }

    // ---------------------------------------------------------------
    //  "Guardar como" y utilidades
    // ---------------------------------------------------------------

    private static File pedirArchivo(Component padre, String titulo, JCheckBox opcion) {
        JFileChooser selector = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
        selector.setDialogTitle("Guardar como PDF");
        selector.setAcceptAllFileFilterUsed(false);
        selector.setFileFilter(new FileNameExtensionFilter("Documento PDF (*.pdf)", "pdf"));
        selector.setSelectedFile(new File(nombreSugerido(titulo)));
        if (opcion != null) {
            selector.setAccessory(opcion);
        }
        if (selector.showSaveDialog(padre) != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        File archivo = selector.getSelectedFile();
        if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
            archivo = new File(archivo.getParentFile(), archivo.getName() + ".pdf");
        }
        if (archivo.exists() && JOptionPane.showConfirmDialog(padre,
                "El archivo \"" + archivo.getName() + "\" ya existe.\n¿Desea reemplazarlo?",
                "Guardar como PDF", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION) {
            return null;
        }
        return archivo;
    }

    /** Ej.: "Consultar stock" -> "Consultar_stock_20261024_1530.pdf" */
    private static String nombreSugerido(String titulo) {
        String base = Normalizer.normalize(titulo == null ? "" : titulo, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").replaceAll("[^A-Za-z0-9]+", "_").replaceAll("^_+|_+$", "");
        if (base.isEmpty()) {
            base = "Documento";
        }
        return base + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm")) + ".pdf";
    }

    private static void abrir(Component padre, File archivo) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop.getDesktop().open(archivo);
                return;
            }
        } catch (IOException e) {
            // se avisa abajo con la ubicacion del archivo
        }
        JOptionPane.showMessageDialog(padre, "PDF guardado en:\n" + archivo.getAbsolutePath(),
                "Exportar a PDF", JOptionPane.INFORMATION_MESSAGE);
    }

    /** Busca, dentro de un componente, todos los componentes de cierto tipo (en orden). */
    private static <T extends Component> List<T> buscar(Component raiz, Class<T> tipo) {
        List<T> encontrados = new ArrayList<>();
        if (tipo.isInstance(raiz)) {
            encontrados.add(tipo.cast(raiz));
        }
        if (raiz instanceof Container) {
            for (Component hijo : ((Container) raiz).getComponents()) {
                encontrados.addAll(buscar(hijo, tipo));
            }
        }
        return encontrados;
    }
}