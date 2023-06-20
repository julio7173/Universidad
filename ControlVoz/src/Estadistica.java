import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Estadistica extends JPanel {
    public Estadistica(ArrayList<Perfume> perfumes) {
        setLayout(new GridLayout(2, 2));

        // Calcular las frecuencias de cada atributo de un perfume
        Map<String, Integer> frecuenciaAnimaciones = calcularFrecuencias(perfumes, "animacion");
        Map<String, Integer> frecuenciaColores = calcularFrecuencias(perfumes, "color");
        Map<String, Integer> frecuenciaFormas = calcularFrecuencias(perfumes, "forma");
        Map<String, Integer> frecuenciaClicks = calcularFrecuenciasNumericas(perfumes, "clicks");

        // Crear datasets con las frecuencias
        DefaultCategoryDataset datasetAnimaciones = crearDataset(frecuenciaAnimaciones);
        DefaultCategoryDataset datasetColores = crearDataset(frecuenciaColores);
        DefaultCategoryDataset datasetFormas = crearDataset(frecuenciaFormas);
        DefaultCategoryDataset datasetClicks = crearDataset(frecuenciaClicks);

        // Crear gráficos de barras
        JFreeChart chartAnimaciones = crearChart(datasetAnimaciones, "Animaciones Usadas", "Animaciones", "Cantidad");
        JFreeChart chartColores = crearChart(datasetColores, "Colores Usados", "Colores", "Cantidad");
        JFreeChart chartFormas = crearChart(datasetFormas, "Formas Usadas", "Formas", "Cantidad");
        JFreeChart chartClicks = crearChart(datasetClicks, "Clicks", "Cantidad", "Cantidad");

        // Crear paneles de gráficos
        ChartPanel chartPanelAnimaciones = new ChartPanel(chartAnimaciones);
        ChartPanel chartPanelColores = new ChartPanel(chartColores);
        ChartPanel chartPanelFormas = new ChartPanel(chartFormas);
        ChartPanel chartPanelClicks = new ChartPanel(chartClicks);

        // Agregar paneles de gráficos al panel Estadistica
        add(chartPanelAnimaciones);
        add(chartPanelColores);
        add(chartPanelFormas);
        add(chartPanelClicks);
    }

    // calculamos estadisticas de atributos de perfumes
    private Map<String, Integer> calcularFrecuencias(ArrayList<Perfume> perfumes, String atributo) {
        Map<String, Integer> frecuencias = new HashMap<>();

        for (Perfume perfume : perfumes) {
            String valor = null;

            switch (atributo) {
                case "animacion":
                    valor = perfume.getAnimacion();
                    break;
                case "color":
                    valor = obtenerNombreColor(perfume.getColor());
                    break;
                case "forma":
                    valor = perfume.getForma();
                    break;
            }

            frecuencias.put(valor, frecuencias.getOrDefault(valor, 0) + 1);
        }

        return frecuencias;
    }

    // calculamos los clicks de perfumes
    private Map<String, Integer> calcularFrecuenciasNumericas(ArrayList<Perfume> perfumes, String atributo) {
        Map<String, Integer> frecuencias = new HashMap<>();
        if (atributo.equals("clicks")) {
            PriorityQueue<Perfume> colaPrioridad = new PriorityQueue<>();
            // agregamos todos los perfumes a la cola de prioridad
            colaPrioridad.addAll(perfumes);
            int current = 0;
            int limite = 3;
            while (!colaPrioridad.isEmpty() && current < limite) {
                Perfume actual = colaPrioridad.poll();
                frecuencias.put(String.valueOf(actual.getClass()), actual.getClicks());
                current++;
            }
        }
        return frecuencias;
    }

    // Crear el dataset para un gráfico de barras
    private DefaultCategoryDataset crearDataset(Map<String, Integer> frecuencias) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : frecuencias.entrySet()) {
            dataset.setValue(entry.getValue(), "Cantidad", entry.getKey());
        }

        return dataset;
    }

    // Crear el dataset para un gráfico de barras con valores numéricos
    private DefaultCategoryDataset crearDatasetNumericos(Map<Integer, Integer> frecuencias) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<Integer, Integer> entry : frecuencias.entrySet()) {
            dataset.setValue(entry.getValue(), "Cantidad", entry.getKey());
        }

        return dataset;
    }

    // Crear el gráfico de barras
    private JFreeChart crearChart(DefaultCategoryDataset dataset, String titulo, String etiquetaX, String etiquetaY) {
        JFreeChart chart = ChartFactory.createBarChart(
                titulo,
                etiquetaX,
                etiquetaY,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        return chart;
    }
    public static String obtenerNombreColor(Color color) {
        if (color.equals(Color.RED)) {
            return "rojo";
        } else if (color.equals(Color.GREEN)) {
            return "verde";
        } else if (color.equals(Color.BLUE)) {
            return "azul";
        } else if (color.equals(Color.PINK)) {
            return "rosa";
        } else if (color.equals(new Color(0, 191, 255))) {
            return "celeste";
        } else if (color.equals(Color.YELLOW)) {
            return "amarillo";
        } else if (color.equals(Color.ORANGE)) {
            return "naranja";
        } else if (color.equals(new Color(148, 0, 211))) {
            return "violeta";
        } else if (color.equals(Color.GRAY)) {
            return "gris";
        } else if (color.equals(new Color(139, 69, 19))) {
            return "marrón";
        } else if (color.equals(new Color(64, 224, 208))) {
            return "turquesa";
        } else if (color.equals(Color.BLACK)) {
            return "negro";
        } else if (color.equals(Color.WHITE)) {
            return "blanco";
        } else if (color.equals(new Color(128, 0, 128))) {
            return "morado";
        } else if (color.equals(new Color(255, 215, 0))) {
            return "dorado";
        } else if (color.equals(new Color(192, 192, 192))) {
            return "plateado";
        } else if (color.equals(new Color(0, 255, 255))) {
            return "cian";
        } else if (color.equals(new Color(250, 128, 114))) {
            return "salmon";
        } else if (color.equals(new Color(50, 205, 50))) {
            return "verde lima";
        } else if (color.equals(new Color(169, 169, 169))) {
            return "gris oscuro";
        } else if (color.equals(new Color(0, 0, 128))) {
            return "azul marino";
        } else {
            return "desconocido";
        }
    }

}
