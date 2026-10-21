
package com.tienda.stockcontrol.modelo;

/**
 *
 * @author Dell
 */
public class GraficoStock {

    // Paleta pensada para verse bien tanto sobre fondo claro como oscuro
    private static final java.awt.Color COLOR_ENTRADAS = new java.awt.Color(0x2EBD85);
    private static final java.awt.Color COLOR_SALIDAS = new java.awt.Color(0xF0654F);
    private static final java.awt.Color[] PALETA = {
        new java.awt.Color(0x4C8DF6), new java.awt.Color(0x26A69A),
        new java.awt.Color(0xF5A623), new java.awt.Color(0x9B6BF2),
        new java.awt.Color(0xEF6C5A), new java.awt.Color(0x66BB6A),
        new java.awt.Color(0xEC6FA8), new java.awt.Color(0x78909C)
    };

    private static final java.util.Locale LOCALE_AR = java.util.Locale.forLanguageTag("es-AR");

    // ---------------------------------------------------------------
    //  Creacion de los graficos
    // ---------------------------------------------------------------

    public javax.swing.JPanel crearGraficoMovimientosPorMes(
            java.util.Map<String, java.util.Map<String, Integer>> totales) {
        org.jfree.data.category.DefaultCategoryDataset dataset = new org.jfree.data.category.DefaultCategoryDataset();
        for (java.util.Map.Entry<String, java.util.Map<String, Integer>> entry : totales.entrySet()) {
            String periodo = formatearPeriodo(entry.getKey());
            java.util.Map<String, Integer> tipos = entry.getValue();
            dataset.addValue(tipos.getOrDefault("ENTRADA", 0), "Entradas", periodo);
            dataset.addValue(tipos.getOrDefault("SALIDA", 0), "Salidas", periodo);
        }

        org.jfree.chart.JFreeChart chart = org.jfree.chart.ChartFactory.createBarChart(
                "Movimientos de stock por mes (últimos 6 meses)",
                null, "Unidades",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true, true, false);

        return envolverEnPanel(chart);
    }

    public javax.swing.JPanel crearGraficoStockPorCategoria(java.util.Map<String, Integer> stockPorCategoria) {
        org.jfree.data.general.DefaultPieDataset<String> dataset = new org.jfree.data.general.DefaultPieDataset<>();
        for (java.util.Map.Entry<String, Integer> entry : stockPorCategoria.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        org.jfree.chart.JFreeChart chart = org.jfree.chart.ChartFactory.createRingChart(
                "Distribución del stock actual por categoría",
                dataset,
                true, true, false);

        return envolverEnPanel(chart);
    }

    public javax.swing.JPanel crearGraficoMasVendidos(java.util.Map<String, Integer> masVendidos) {
        org.jfree.data.category.DefaultCategoryDataset dataset = new org.jfree.data.category.DefaultCategoryDataset();
        for (java.util.Map.Entry<String, Integer> entry : masVendidos.entrySet()) {
            dataset.addValue(entry.getValue(), "Unidades vendidas", entry.getKey());
        }

        org.jfree.chart.JFreeChart chart = org.jfree.chart.ChartFactory.createBarChart(
                "Top 10 productos con más salidas",
                null, "Unidades",
                dataset,
                org.jfree.chart.plot.PlotOrientation.HORIZONTAL,
                false, true, false);

        return envolverEnPanel(chart);
    }

    private javax.swing.JPanel envolverEnPanel(org.jfree.chart.JFreeChart chart) {
        estilizar(chart);

        // Si el usuario cambia el tema con la ventana abierta, Swing llama a
        // updateUI() en todos los componentes: aprovechamos para reaplicar los colores.
        org.jfree.chart.ChartPanel chartPanel = new org.jfree.chart.ChartPanel(chart) {
            @Override
            public void updateUI() {
                super.updateUI();
                org.jfree.chart.JFreeChart actual = getChart();
                if (actual != null) {
                    estilizar(actual);
                }
            }
        };
        chartPanel.setPopupMenu(null);
        chartPanel.setMouseWheelEnabled(false);
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        // Sin estos limites el grafico se "estira" (y se ve borroso) al maximizar la ventana
        chartPanel.setMinimumDrawWidth(0);
        chartPanel.setMinimumDrawHeight(0);
        chartPanel.setMaximumDrawWidth(Integer.MAX_VALUE);
        chartPanel.setMaximumDrawHeight(Integer.MAX_VALUE);

        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panel.add(chartPanel, java.awt.BorderLayout.CENTER);
        return panel;
    }

    // ---------------------------------------------------------------
    //  Estilo
    // ---------------------------------------------------------------

    private static void estilizar(org.jfree.chart.JFreeChart chart) {
        java.awt.Color fondo = colorUI("Panel.background", java.awt.Color.WHITE);
        java.awt.Color texto = colorUI("Label.foreground", java.awt.Color.DARK_GRAY);
        java.awt.Color textoSuave = mezclar(fondo, texto, 0.70f);
        java.awt.Color grilla = mezclar(fondo, texto, 0.15f);

        java.awt.Font base = javax.swing.UIManager.getFont("Label.font");
        if (base == null) {
            base = new java.awt.Font(java.awt.Font.SANS_SERIF, java.awt.Font.PLAIN, 12);
        }
        java.awt.Font fuenteTitulo = base.deriveFont(java.awt.Font.BOLD, base.getSize2D() + 4f);
        java.awt.Font fuenteTexto = base.deriveFont(java.awt.Font.PLAIN, base.getSize2D());

        chart.setBackgroundPaint(fondo);
        chart.setBorderVisible(false);
        chart.setAntiAlias(true);

        org.jfree.chart.title.TextTitle titulo = chart.getTitle();
        if (titulo != null) {
            titulo.setFont(fuenteTitulo);
            titulo.setPaint(texto);
            titulo.setHorizontalAlignment(org.jfree.chart.ui.HorizontalAlignment.LEFT);
            titulo.setPadding(6, 8, 10, 8);
        }

        org.jfree.chart.title.LegendTitle leyenda = chart.getLegend();
        if (leyenda != null) {
            leyenda.setBackgroundPaint(fondo);
            leyenda.setItemFont(fuenteTexto);
            leyenda.setItemPaint(texto);
            leyenda.setFrame(org.jfree.chart.block.BlockBorder.NONE);
        }

        org.jfree.chart.plot.Plot plot = chart.getPlot();
        plot.setBackgroundPaint(fondo);
        plot.setOutlineVisible(false);
        plot.setNoDataMessage("No hay datos para mostrar");
        plot.setNoDataMessageFont(fuenteTexto);
        plot.setNoDataMessagePaint(textoSuave);

        if (plot instanceof org.jfree.chart.plot.CategoryPlot) {
            estilizarBarras((org.jfree.chart.plot.CategoryPlot) plot, texto, textoSuave, grilla, fuenteTexto);
        } else if (plot instanceof org.jfree.chart.plot.PiePlot) {
            estilizarTorta((org.jfree.chart.plot.PiePlot<?>) plot, leyenda, texto, fuenteTitulo, fuenteTexto);
        }
    }

    private static void estilizarBarras(org.jfree.chart.plot.CategoryPlot plot,
            java.awt.Color texto, java.awt.Color textoSuave, java.awt.Color grilla, java.awt.Font fuente) {
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(grilla);
        plot.setRangeGridlineStroke(new java.awt.BasicStroke(1f));

        org.jfree.chart.axis.CategoryAxis ejeCategorias = plot.getDomainAxis();
        ejeCategorias.setAxisLineVisible(false);
        ejeCategorias.setTickMarksVisible(false);
        ejeCategorias.setTickLabelFont(fuente);
        ejeCategorias.setTickLabelPaint(texto);
        ejeCategorias.setCategoryMargin(0.25);
        if (plot.getOrientation() == org.jfree.chart.plot.PlotOrientation.HORIZONTAL) {
            // Nombres largos de producto: se les deja hasta el 40% del ancho
            ejeCategorias.setMaximumCategoryLabelWidthRatio(0.4f);
        }
        plot.setRangeAxisLocation(org.jfree.chart.axis.AxisLocation.BOTTOM_OR_LEFT);

        org.jfree.chart.axis.ValueAxis ejeValores = plot.getRangeAxis();
        ejeValores.setAxisLineVisible(false);
        ejeValores.setTickMarksVisible(false);
        ejeValores.setTickLabelFont(fuente);
        ejeValores.setTickLabelPaint(textoSuave);
        ejeValores.setLabelFont(fuente);
        ejeValores.setLabelPaint(textoSuave);
        if (ejeValores instanceof org.jfree.chart.axis.NumberAxis) {
            ((org.jfree.chart.axis.NumberAxis) ejeValores)
                    .setStandardTickUnits(org.jfree.chart.axis.NumberAxis.createIntegerTickUnits());
        }

        org.jfree.chart.renderer.category.BarRenderer renderer =
                (org.jfree.chart.renderer.category.BarRenderer) plot.getRenderer();
        renderer.setBarPainter(new BarraRedondeada());
        renderer.setShadowVisible(false);
        renderer.setDrawBarOutline(false);
        renderer.setItemMargin(0.08);
        renderer.setMaximumBarWidth(0.12);

        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelFont(fuente);
        renderer.setDefaultItemLabelPaint(texto);
        renderer.setDefaultItemLabelGenerator(new org.jfree.chart.labels.StandardCategoryItemLabelGenerator() {
            @Override
            public String generateLabel(org.jfree.data.category.CategoryDataset dataset, int row, int column) {
                Number valor = dataset.getValue(row, column);
                if (valor == null || valor.intValue() == 0) {
                    return null;
                }
                return String.format(LOCALE_AR, "%,d", valor.intValue());
            }
        });

        org.jfree.data.category.CategoryDataset datos = plot.getDataset();
        for (int i = 0; i < datos.getRowCount(); i++) {
            Comparable<?> serie = datos.getRowKey(i);
            java.awt.Color color;
            if ("Entradas".equals(serie)) {
                color = COLOR_ENTRADAS;
            } else if ("Salidas".equals(serie)) {
                color = COLOR_SALIDAS;
            } else {
                color = PALETA[i % PALETA.length];
            }
            renderer.setSeriesPaint(i, color);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void estilizarTorta(org.jfree.chart.plot.PiePlot<?> plotGenerico,
            org.jfree.chart.title.LegendTitle leyenda, java.awt.Color texto,
            java.awt.Font fuenteTitulo, java.awt.Font fuenteTexto) {
        org.jfree.chart.plot.PiePlot plot = plotGenerico;
        plot.setShadowPaint(null);
        plot.setSectionOutlinesVisible(false);
        plot.setLabelGenerator(null);
        plot.setInteriorGap(0.04);
        plot.setLegendLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
                "{0}  ({2})",
                new java.text.DecimalFormat("#,##0"),
                new java.text.DecimalFormat("0%")));

        int i = 0;
        for (Object clave : plot.getDataset().getKeys()) {
            plot.setSectionPaint((Comparable) clave, PALETA[i % PALETA.length]);
            i++;
        }

        if (leyenda != null) {
            leyenda.setPosition(org.jfree.chart.ui.RectangleEdge.RIGHT);
        }

        if (plot instanceof org.jfree.chart.plot.RingPlot) {
            org.jfree.chart.plot.RingPlot anillo = (org.jfree.chart.plot.RingPlot) plot;
            anillo.setSectionDepth(0.38);
            anillo.setSeparatorsVisible(false);
            double total = org.jfree.data.general.DatasetUtils.calculatePieDatasetTotal(plot.getDataset());
            anillo.setCenterTextMode(org.jfree.chart.plot.CenterTextMode.FIXED);
            anillo.setCenterText("Total: " + String.format(LOCALE_AR, "%,d", (long) total));
            anillo.setCenterTextFont(fuenteTitulo);
            anillo.setCenterTextColor(texto);
        }
    }

    // ---------------------------------------------------------------
    //  Utilidades
    // ---------------------------------------------------------------

    /** Convierte "2026-05" en "May 2026". Si no puede, devuelve el texto original. */
    private static String formatearPeriodo(String periodo) {
        try {
            java.time.YearMonth ym = java.time.YearMonth.parse(periodo);
            String texto = ym.format(java.time.format.DateTimeFormatter.ofPattern("MMM yyyy", LOCALE_AR));
            return texto.substring(0, 1).toUpperCase(LOCALE_AR) + texto.substring(1);
        } catch (java.time.format.DateTimeParseException e) {
            return periodo;
        }
    }

    private static java.awt.Color colorUI(String clave, java.awt.Color porDefecto) {
        java.awt.Color c = javax.swing.UIManager.getColor(clave);
        return c != null ? c : porDefecto;
    }

    /** Mezcla dos colores: t = 0 devuelve "a", t = 1 devuelve "b". */
    private static java.awt.Color mezclar(java.awt.Color a, java.awt.Color b, float t) {
        float inv = 1f - t;
        return new java.awt.Color(
                Math.round(a.getRed() * inv + b.getRed() * t),
                Math.round(a.getGreen() * inv + b.getGreen() * t),
                Math.round(a.getBlue() * inv + b.getBlue() * t));
    }

    /** Pintor de barras con esquinas redondeadas del lado opuesto a la base. */
    private static class BarraRedondeada implements org.jfree.chart.renderer.category.BarPainter {

        @Override
        public void paintBar(java.awt.Graphics2D g2, org.jfree.chart.renderer.category.BarRenderer renderer,
                int row, int column, java.awt.geom.RectangularShape barra,
                org.jfree.chart.ui.RectangleEdge base) {
            java.awt.Paint pintura = renderer.getItemPaint(row, column);
            double x = barra.getX();
            double y = barra.getY();
            double ancho = barra.getWidth();
            double alto = barra.getHeight();
            double arco = Math.min(12, Math.min(ancho, alto));

            // Se dibuja un rectangulo redondeado que se pasa de la base y se recorta,
            // asi solo quedan redondeadas las esquinas del extremo libre de la barra.
            java.awt.geom.RoundRectangle2D forma;
            if (base == org.jfree.chart.ui.RectangleEdge.BOTTOM) {
                forma = new java.awt.geom.RoundRectangle2D.Double(x, y, ancho, alto + arco, arco, arco);
            } else if (base == org.jfree.chart.ui.RectangleEdge.TOP) {
                forma = new java.awt.geom.RoundRectangle2D.Double(x, y - arco, ancho, alto + arco, arco, arco);
            } else if (base == org.jfree.chart.ui.RectangleEdge.LEFT) {
                forma = new java.awt.geom.RoundRectangle2D.Double(x - arco, y, ancho + arco, alto, arco, arco);
            } else {
                forma = new java.awt.geom.RoundRectangle2D.Double(x, y, ancho + arco, alto, arco, arco);
            }

            java.awt.Shape clipOriginal = g2.getClip();
            g2.clip(barra);
            g2.setPaint(pintura);
            g2.fill(forma);
            g2.setClip(clipOriginal);
        }

        @Override
        public void paintBarShadow(java.awt.Graphics2D g2, org.jfree.chart.renderer.category.BarRenderer renderer,
                int row, int column, java.awt.geom.RectangularShape barra,
                org.jfree.chart.ui.RectangleEdge base, boolean pegarSombra) {
            // Sin sombra
        }
    }
}