
package com.tienda.stockcontrol.modelo;

/**
 *
 * @author Dell
 */
public class GraficoStock {
    public javax.swing.JPanel crearGraficoMovimientosPorMes(
            java.util.Map<String, java.util.Map<String, Integer>> totales) {
        org.jfree.data.category.DefaultCategoryDataset dataset = new org.jfree.data.category.DefaultCategoryDataset();
        for (java.util.Map.Entry<String, java.util.Map<String, Integer>> entry : totales.entrySet()) {
            String periodo = entry.getKey();
            java.util.Map<String, Integer> tipos = entry.getValue();
            dataset.addValue(tipos.getOrDefault("ENTRADA", 0), "Entradas", periodo);
            dataset.addValue(tipos.getOrDefault("SALIDA", 0), "Salidas", periodo);
        }

        org.jfree.chart.JFreeChart chart = org.jfree.chart.ChartFactory.createBarChart(
                "Movimientos de stock por mes (ultimos 6 meses)",
                "Mes", "Unidades",
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

        org.jfree.chart.JFreeChart chart = org.jfree.chart.ChartFactory.createPieChart(
                "Distribucion del stock actual por categoria",
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
                "Top 10 productos con mas salidas",
                "Producto", "Unidades",
                dataset,
                org.jfree.chart.plot.PlotOrientation.HORIZONTAL,
                false, true, false);

        return envolverEnPanel(chart);
    }

    private javax.swing.JPanel envolverEnPanel(org.jfree.chart.JFreeChart chart) {
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridLayout(1, 1));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panel.add(new org.jfree.chart.ChartPanel(chart));
        return panel;
    }
}
