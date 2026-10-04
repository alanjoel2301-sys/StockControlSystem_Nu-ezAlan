package com.tienda.stockcontrol.controlador;

/**
 *
 * @author Dell
 */
public class ControladorEstadisticas {

    private final com.tienda.stockcontrol.modelo.MovimientoStockDAO movimientoDAO =
            new com.tienda.stockcontrol.modelo.MovimientoStockDAO();
    private final com.tienda.stockcontrol.modelo.GraficoStock graficoStock =
            new com.tienda.stockcontrol.modelo.GraficoStock();
    private final com.tienda.stockcontrol.vista.FrmEstadisticasStock vista;

    public ControladorEstadisticas(com.tienda.stockcontrol.vista.FrmEstadisticasStock vista) {
        this.vista = vista;
    }

    public void cargarGraficos() {
        try {
            java.util.Map<String, java.util.Map<String, Integer>> totales = movimientoDAO.totalesPorMes(6);
            vista.agregarPestana("Entradas / Salidas por mes",
                    graficoStock.crearGraficoMovimientosPorMes(totales));

            java.util.Map<String, Integer> stockPorCategoria = movimientoDAO.stockPorCategoria();
            vista.agregarPestana("Stock por categoria",
                    graficoStock.crearGraficoStockPorCategoria(stockPorCategoria));

            java.util.Map<String, Integer> masVendidos = movimientoDAO.productosMasSalidas(10);
            vista.agregarPestana("Productos mas vendidos",
                    graficoStock.crearGraficoMasVendidos(masVendidos));
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }
}