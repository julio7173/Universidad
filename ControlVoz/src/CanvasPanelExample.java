import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.lang.model.type.NoType;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CanvasPanelExample {
    public static void main(String[] args) {
        CanvasFrame frame = new CanvasFrame();
        frame.run();
    }
}
class CanvasFrame extends JFrame {
    private JPanel canvasPanel;
    private JButton searchButton;
    JTextField textField;

    public CanvasFrame() {
        setTitle("Crear");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);

        searchButton = new JButton("Buscar");
        textField = new JTextField(50);
        // Crea el menú
        JMenuBar menuBar = new JMenuBar();

        // Crea el menú "Archivo"
        JMenu menuArchivo = new JMenu("Menu");
        menuBar.add(menuArchivo);

        // Crea los elementos del menú "Archivo"
        JMenuItem menuItemConstruir = new JMenuItem("Construir");
        JMenuItem menuItemBuscar = new JMenuItem("Buscar");
        JMenuItem menuItemEstadisticas = new JMenuItem("Estadísticas");

        // Agrega los elementos al menú "Archivo"
        menuArchivo.add(menuItemConstruir);
        menuArchivo.add(menuItemBuscar);
        menuArchivo.add(menuItemEstadisticas);

        // Establece la barra de menú en el marco
        setJMenuBar(menuBar);

        // Crea un JPanel que actuará como contenedor para los Canvas
        canvasPanel = new JPanel();
        canvasPanel.setBackground(Color.PINK);

        menuItemConstruir.addActionListener(e -> {
            canvasPanel.removeAll();
            canvasPanel.add(new JLabel("Construir"));
            canvasPanel.revalidate();
            canvasPanel.repaint();
        });

        menuItemBuscar.addActionListener(e -> {
            canvasPanel.removeAll();
            imagenes();
            canvasPanel.revalidate();
            canvasPanel.repaint();
        });

        menuItemEstadisticas.addActionListener(e -> {
            canvasPanel.removeAll();
            estadisticas();
            canvasPanel.revalidate();
            canvasPanel.repaint();
        });

        // Crea un JScrollPane y agrega el panel contenedor
        //JScrollPane scrollPane = new JScrollPane(canvasPanel);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        canvasPanel.setLayout(new BorderLayout());
        getContentPane().add(canvasPanel);
        imagenes();
    }
    private void imagenes() {
        JPanel lista = new JPanel();
        lista.setBackground(Color.WHITE);
        lista.setLayout(new GridBagLayout());

        searchButton.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel busqueda = new JPanel();
        busqueda.setBackground(Color.decode("#15191d"));
        busqueda.setLayout(new FlowLayout(FlowLayout.CENTER));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre componentes

        // Agrega los Canvas al panel
        for (int i = 0; i < 9; i++) {
            ImageCanvas canvas = new ImageCanvas(); // Utiliza la clase ImageCanvas personalizada
            canvas.setPreferredSize(new Dimension(200, 200)); // Tamaño deseado de cada Canvas

            // Configura la restricción del GridBagLayout para mantener la forma cuadrada
            gbc.gridx = i % 3; // Columna
            gbc.gridy = i / 3; // Fila
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;

            lista.add(canvas, gbc);
        }
        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.getVerticalScrollBar().setUnitIncrement(64);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(64);

        busqueda.add(searchButton);
        busqueda.add(textField);
        canvasPanel.add(busqueda, BorderLayout.NORTH);
        canvasPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void estadisticas() {
        canvasPanel.add(new EstadisticasPanel());
    }

    public void run() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }
}
class ImageCanvas extends Canvas {
    private BufferedImage image;
    private Color color;

    public ImageCanvas() {
        color = randomColor();

        // Carga la imagen en un hilo separado
        Thread thread = new Thread(() -> {
            try {
                image = javax.imageio.ImageIO.read(new java.io.File("ControlVoz/imagenes/perfumeCuadrado.png"));
                SwingUtilities.invokeLater(this::repaint); // Actualiza el Canvas en el hilo de despacho de eventos de Swing
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                color = randomColor(); // Cambia el color al hacer clic
                repaint(); // Vuelve a dibujar el Canvas
            }
        });
    }
    private Color randomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image != null) {
            // Calcula el tamaño y posición para mantener la forma cuadrada
            int canvasSize = Math.min(getWidth(), getHeight());
            int x = (getWidth() - canvasSize) / 2;
            int y = (getHeight() - canvasSize) / 2;
            g.setColor(color); // Puedes elegir el color que desees
            g.fillRect(x, y, canvasSize, canvasSize);
            // Dibuja la imagen en el Canvas
            g.drawImage(image, x, y, canvasSize, canvasSize, this);
        }
    }
}
class EstadisticasPanel extends JPanel {
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