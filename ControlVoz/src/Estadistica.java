import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class Estadistica extends JPanel {
    public Estadistica() {
        setLayout(new BorderLayout());


        // Crear datos de ejemplo
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(120, "Categoría 1", "Elemento 1");
        dataset.setValue(240, "Categoría 1", "Elemento 2");
        dataset.setValue(180, "Categoría 1", "Elemento 3");
        dataset.setValue(90, "Categoría 2", "Elemento 1");
        dataset.setValue(160, "Categoría 2", "Elemento 2");
        dataset.setValue(210, "Categoría 2", "Elemento 3");

        // Crear gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Animaciones Usadas",
                "Categorías",
                "Valores",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Crear panel de gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

}