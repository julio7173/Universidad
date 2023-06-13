import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class VentanaMenu extends JFrame {
    private JPanel panelContenido;
    private JButton botonCreacion;
    private JButton botonBuscador;
    private JButton botonEstadisticas;
    private ListaScrollablePanel lista;
    private JScrollPane scrollPane;

    public VentanaMenu() {
        // Configuración de la ventana principal
        setTitle("Ventana con Menú");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);

        // Creación del menú
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));

        botonCreacion = new JButton("Creación");
        botonBuscador = new JButton("Buscador");
        botonEstadisticas = new JButton("Estadísticas");

        botonCreacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarContenido("Creación");
            }
        });

        botonBuscador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarContenido("Buscador");
            }
        });

        botonEstadisticas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarContenido("Estadísticas");
            }
        });

        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(botonCreacion);
        menuBar.add(botonBuscador);
        menuBar.add(botonEstadisticas);
        menuBar.add(Box.createHorizontalGlue());

        setJMenuBar(menuBar);

        panelContenido = new JPanel();
        panelContenido.setLayout(new BorderLayout());

        scrollPane = new JScrollPane(panelContenido);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        getContentPane().add(scrollPane);
    }

    private void cambiarContenido(String opcion) {
        panelContenido.removeAll();

        if (opcion.equals("Creación")) {
            panelContenido.add(new CustomLabel(), BorderLayout.CENTER);
        } else if (opcion.equals("Buscador")) {
            lista = new ListaScrollablePanel();
            panelContenido.add(lista, BorderLayout.CENTER);
        } else if (opcion.equals("Estadísticas")) {
            panelContenido.add(new EstadisticasPanel(), BorderLayout.CENTER);
        }

        panelContenido.revalidate();
        panelContenido.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaMenu().setVisible(true);
            }
        });
    }

    private class CustomLabel extends JLabel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int width = getWidth();
            int height = getHeight();
            int diameter = Math.min(width, height) - 20;
            int x = (width - diameter) / 2;
            int y = (height - diameter) / 2;

            g.setColor(Color.RED);
            g.fillOval(x, y, diameter, diameter);
        }
    }

    private class CustomLabelTriangulo extends JLabel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int width = getWidth();
            int height = getHeight();
            int[] xPoints = {width / 2, width / 4, (3 * width) / 4};
            int[] yPoints = {height / 4, (3 * height) / 4, (3 * height) / 4};

            g.setColor(Color.BLUE);
            g.fillPolygon(xPoints, yPoints, 3);
        }
    }

    private class EstadisticasPanel extends JPanel {
        public EstadisticasPanel() {
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
                    "Gráfico de Barras",
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
}
