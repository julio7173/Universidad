// libraria para mostrar estadísticas
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Aplicacion {
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
            construir();
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

    private void construir() {

        // botones
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.decode("#15191d"));
        leftPanel.setLayout(new GridLayout(0, 2, 0, 10)); // Usamos FlowLayout y establecemos los márgenes a 0
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel color = new JLabel("Color");
        color.setOpaque(true);
        leftPanel.add(color);

        String[] opcionesColor = {"rojo", "verde", "azul", "rosa", "celeste"};
        JComboBox<String> coloresOpcion = new JComboBox<>(opcionesColor);
        leftPanel.add(coloresOpcion);

        JLabel envase = new JLabel("Envase");
        envase.setOpaque(true);
        leftPanel.add(envase);

        String[] opcionesEnvases = {"Cuadrado", "Circular", "Largo"};
        JComboBox<String> envasesOpcion = new JComboBox<>(opcionesEnvases);
        leftPanel.add(envasesOpcion);

        JLabel animacionLabel = new JLabel("Animacion");
        animacionLabel.setOpaque(true);
        leftPanel.add(animacionLabel);

        String[] opcionesAnimaciones = {"Aparecer", "Desvanecer", "Parpadear"};
        JComboBox<String> animacionesOpcion = new JComboBox<>(opcionesAnimaciones);
        leftPanel.add(animacionesOpcion);

        JButton showButton = new JButton("Mostrar");
        leftPanel.add(showButton);

        leftPanel.add(new JLabel());

        JButton saveButton = new JButton("Guardar");
        leftPanel.add(saveButton);

        for (int i = 0; i < 20 ; i++) {
            leftPanel.add(new JLabel());
        }

        // canvas derecha
        JPanel canvas = new JPanel();
        canvas.setBackground(Color.WHITE);

        // union de ambos
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, canvas);
        splitPane.setDividerLocation(0.5);
        splitPane.setEnabled(false);
        splitPane.setResizeWeight(0.15);
        splitPane.setDividerSize(0); // Establece el tamaño del divisor en cero

        canvasPanel.add(splitPane);
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.removeAll();
                String colorSeleccionado = (String) coloresOpcion.getSelectedItem();
                String envaseSeleccionado = (String) envasesOpcion.getSelectedItem();
                String animacionSeleccionada = (String) animacionesOpcion.getSelectedItem();
                Color color = obtenerColor(colorSeleccionado);
                Perfume nuevo = new Perfume(envaseSeleccionado, color);
                nuevo.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));
                canvas.add(nuevo);
                canvas.revalidate();
                canvas.repaint();
                System.out.println("Color seleccionado: " + colorSeleccionado);
                System.out.println("Envase seleccionado: " + envaseSeleccionado);
                System.out.println("Animación seleccionada: " + animacionSeleccionada);
            }
        });
    }
    public static Color obtenerColor(String colorSeleccionado) {
        switch (colorSeleccionado) {
            case "rojo":
                return Color.RED;
            case "verde":
                return Color.GREEN;
            case "azul":
                return Color.BLUE;
            case "rosa":
                return Color.PINK;
            case "celeste":
                return new Color(0, 191, 255); // Código de color RGB para celeste
            default:
                return Color.BLACK; // negro si no es ninguno de los anteriores
        }
    }
    private void imagenes() {

        JPanel lista = new JPanel();
        lista.setBackground(Color.WHITE);
        lista.setLayout(new GridBagLayout());

        searchButton.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel busqueda = new JPanel();
        busqueda.setBackground(Color.decode("#15191d"));
        busqueda.setLayout(new FlowLayout(FlowLayout.CENTER));
        busqueda.add(searchButton);
        busqueda.add(textField);

        JPanel informacion = new JPanel();
        informacion.setBackground(Color.decode("#15191d"));



        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre componentes

        // Agrega los Canvas al panel
        for (int i = 0; i < 29; i++) {
            Perfume canvas = new Perfume("circular", Perfume.randomColor());
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
        JSplitPane listaInformacion = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, informacion, scrollPane);
        listaInformacion.setDividerLocation(0.25);
        listaInformacion.setEnabled(false);
        listaInformacion.setResizeWeight(0.8);
        listaInformacion.setDividerSize(0);

        canvasPanel.add(busqueda, BorderLayout.NORTH);
        canvasPanel.add(listaInformacion, BorderLayout.CENTER);

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
class Perfume extends Canvas implements Serializable {
    private BufferedImage image;
    private Color color;
    private JPanel padre;
    private int clicks;

    public Perfume(JPanel padre) {
        this.padre = padre;
        this.color = randomColor();

        // Carga la imagen en un hilo separado
        Thread thread = new Thread(() -> {
            try {
                image = javax.imageio.ImageIO.read(new java.io.File("ControlVoz/imagenes/perfumeCuadrado.png"));
                // cuando la imagen cargue repintamos
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mostrarColor(color);
            }
        });
    }
    public Perfume(Perfume copiar) {
        this.padre = copiar.padre;
        this.color = copiar.color;
        this.image = copiar.image;
        this.clicks = copiar.clicks;
    }
    public Perfume(String forma, Color color){
        this.clicks = 0;
        this.padre = null;
        forma = forma.toLowerCase();
        this.color = color;
        String ruta = "";
        switch (forma) {
            case "cuadrado":
                ruta = "ControlVoz/imagenes/perfumeCuadrado.png";
                break;
            case "circular":
                ruta = "ControlVoz/imagenes/perfumeCircular.png";
                break;
            case "largo":
                ruta = "ControlVoz/imagenes/perfumeLargo.png";
                break;
            default:
                System.out.println("El tipo de perfume no existe");
                break;
        }
        if (!ruta.equals("")) {
            // cargamos la imagen en un hilo separado
            String finalRuta = ruta;
            Thread thread = new Thread(() -> {
                try {
                    File archivo = new File(finalRuta);
                    if (archivo.exists()) {
                        image = ImageIO.read(archivo);
                        repaint();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

    }
    private void mostrarColor(Color color) {
        if (padre != null) {
            // Crea un JLabel y establece su tamaño y color de fondo
            JLabel colorLabel = new JLabel();
            colorLabel.setText(color.toString());
            colorLabel.setOpaque(true);
            colorLabel.setBackground(color);

            padre.removeAll();
            Perfume nuevo = new Perfume(this);
            nuevo.setPreferredSize(new Dimension(250, 250));
            padre.add(nuevo);

            // Vuelve a dibujar el panel para reflejar los cambios
            padre.revalidate();
            padre.repaint();
        }
    }
    public static Color randomColor() {
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
    public static void guardarPerfumes(ArrayList<Perfume> perfumes) {
        try {
            FileOutputStream archivo = new FileOutputStream("ControlVoz/datos/perfumes.txt");
            ObjectOutputStream salida = new ObjectOutputStream(archivo);
            salida.writeObject(perfumes);
            salida.close();
            archivo.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
        }
    }
    public static ArrayList<Perfume> leerPerfumes() {
        ArrayList<Perfume> lista = new ArrayList<>();

        try {
            FileInputStream archivo = new FileInputStream("ControlVoz/datos/perfumes.txt");
            ObjectInputStream entrada = new ObjectInputStream(archivo);
            lista = (ArrayList<Perfume>) entrada.readObject();
            entrada.close();
            archivo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
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