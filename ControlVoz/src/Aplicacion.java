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
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Aplicacion {
    public static void main(String[] args) {
        CanvasFrame frame = new CanvasFrame();
        frame.run();
    }
}
class CanvasFrame extends JFrame {
    private ArrayList<Perfume> perfumes;
    private JPanel canvasPanel;
    private JButton searchButton;
    JTextField textField;

    public CanvasFrame() {
        // leemos los perfumes guardados en datos
        perfumes = Perfume.leerPerfumes();
        System.out.println(perfumes);
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
        Perfume[] nuevo = new Perfume[1];

        // botones
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.decode("#15191d"));
        leftPanel.setLayout(new GridLayout(0, 2, 0, 10)); // Usamos FlowLayout y establecemos los márgenes a 0
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel color = new JLabel("Color");
        color.setOpaque(true);
        leftPanel.add(color);

        String[] opcionesColor = {
                "rojo",
                "verde",
                "azul",
                "rosa",
                "celeste",
                "amarillo",
                "naranja",
                "violeta",
                "gris",
                "marrón",
                "turquesa",
                "negro",
                "blanco",
                "morado",
                "dorado",
                "plateado",
                "cian",
                "salmon",
                "verde lima",
                "gris oscuro",
                "azul marino"
        };
        JComboBox<String> coloresOpcion = new JComboBox<>(opcionesColor);
        leftPanel.add(coloresOpcion);

        JLabel envase = new JLabel("Envase");
        envase.setOpaque(true);
        leftPanel.add(envase);

        String[] opcionesEnvases = {
                "Cuadrado",
                "Circular",
                "Largo"
        };
        JComboBox<String> envasesOpcion = new JComboBox<>(opcionesEnvases);
        leftPanel.add(envasesOpcion);

        JLabel animacionLabel = new JLabel("Animacion");
        animacionLabel.setOpaque(true);
        leftPanel.add(animacionLabel);

        String[] opcionesAnimaciones = {
                "Aparecer",
                "Desvanecer",
                "Parpadear"
        };
        JComboBox<String> animacionesOpcion = new JComboBox<>(opcionesAnimaciones);
        leftPanel.add(animacionesOpcion);

        JLabel timeLabel = new JLabel("Tiempo (milisegundos)");
        timeLabel.setOpaque(true);
        leftPanel.add(timeLabel);

        JTextField tiempoTexto = new JTextField();
        leftPanel.add(tiempoTexto);

        tiempoTexto.setText("2000"); // Valor inicial
        tiempoTexto.setFocusable(false); // Desactivar la capacidad de obtener el foco al inicio del programa
        Font boldFont = new Font(tiempoTexto.getFont().getName(), Font.BOLD, 14);
        tiempoTexto.setFont(boldFont);

        tiempoTexto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tiempoTexto.setFocusable(true);
                tiempoTexto.requestFocusInWindow();
            }
        });
        tiempoTexto.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Ignorar caracteres no numéricos o el número 0
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String input = tiempoTexto.getText();
                    if (input.isEmpty()) {
                        // el texto es 1 si no se ingresa nada
                        tiempoTexto.setText("1000");
                    } else {
                        if (Integer.parseInt(tiempoTexto.getText()) == 0) {
                            // el texto es 1 si no se ingresa nada
                            tiempoTexto.setText("1000");
                        }
                    }

                    // quitamos el focus del text field
                    tiempoTexto.setFocusable(false);
                    requestFocusInWindow();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        leftPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!tiempoTexto.getBounds().contains(e.getPoint())) {
                    //panel.requestFocusInWindow(); // Establecer el foco en el panel al hacer clic en cualquier lugar excepto en el campo de texto
                    tiempoTexto.setFocusable(false);
                    leftPanel.requestFocus();
                }
            }
        });

        tiempoTexto.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tiempoTexto.getText().isEmpty()) {
                    tiempoTexto.setText("2000");
                } else {
                    if (Integer.parseInt(tiempoTexto.getText()) == 0) {
                        // el texto es 1 si no se ingresa nada
                        tiempoTexto.setText("2000");
                    }
                }
            }
        });

        JButton showButton = new JButton("Mostrar");
        leftPanel.add(showButton);

        leftPanel.add(new JLabel());

        JButton saveButton = new JButton("Guardar");
        leftPanel.add(saveButton);

        leftPanel.add(new JLabel());

        JButton animarButton = new JButton("Animar");
        leftPanel.add(animarButton);

        leftPanel.add(new JLabel());

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
        // muestra un nuevo perfume con los valores dados
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.removeAll();
                String colorSeleccionado = (String) coloresOpcion.getSelectedItem();
                String envaseSeleccionado = (String) envasesOpcion.getSelectedItem();
                String animacionSeleccionada = (String) animacionesOpcion.getSelectedItem();
                Color color = obtenerColor(colorSeleccionado);
                nuevo[0] = new Perfume(envaseSeleccionado, color, Math.min(canvas.getWidth(), canvas.getHeight()));
                nuevo[0].setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));
                nuevo[0].setPadre(canvas);
                canvas.add(nuevo[0]);
                //nuevo[0].startAnimation();
                canvas.setBackground(Color.WHITE);
                canvas.revalidate();
                canvas.repaint();
                //System.out.println(Perfume.leerPerfumes());
            }
        });

        // guardamos el nuevo perfume, si se mostro con el boton mostrar
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nuevo[0] != null) {
                    perfumes.add(nuevo[0]);
                    Perfume.guardarPerfumes(perfumes);
                    System.out.println("agregando...");
                }
            }
        });
        animarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nuevo[0] != null) {
                    if (nuevo[0].isAnimable()) {
                        nuevo[0].startAnimation(Integer.parseInt(tiempoTexto.getText()));
                    } else {
                        nuevo[0].stopAnimation();
                    }
                }
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
            case "amarillo":
                return Color.YELLOW;
            case "naranja":
                return Color.ORANGE;
            case "violeta":
                return new Color(148, 0, 211); // Código de color RGB para violeta
            case "gris":
                return Color.GRAY;
            case "marrón":
                return new Color(139, 69, 19); // Código de color RGB para marrón
            case "turquesa":
                return new Color(64, 224, 208); // Código de color RGB para turquesa
            case "negro":
                return Color.BLACK;
            case "blanco":
                return Color.WHITE;
            case "morado":
                return new Color(128, 0, 128); // Código de color RGB para morado
            case "dorado":
                return new Color(255, 215, 0); // Código de color RGB para dorado
            case "plateado":
                return new Color(192, 192, 192); // Código de color RGB para plateado
            case "cian":
                return new Color(0, 255, 255); // Código de color RGB para cian
            case "salmon":
                return new Color(250, 128, 114); // Código de color RGB para salmón
            case "verde lima":
                return new Color(50, 205, 50); // Código de color RGB para verde lima
            case "gris oscuro":
                return new Color(169, 169, 169); // Código de color RGB para gris oscuro
            case "azul marino":
                return new Color(0, 0, 128); // Código de color RGB para azul marino
            default:
                return Color.BLACK; // negro si no es ninguno de los anteriores
        }
    }
    private void imagenes() {

        searchButton.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel busqueda = new JPanel();
        busqueda.setBackground(Color.decode("#15191d"));
        busqueda.setLayout(new FlowLayout(FlowLayout.CENTER));
        busqueda.add(searchButton);
        busqueda.add(textField);

        JPanel informacion = new JPanel();
        informacion.setBackground(Color.decode("#15191d"));

        // lista para buscados
        JPanel lista = new JPanel();
        lista.setBackground(Color.WHITE);
        lista.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre componentes

        // Agrega los Canvas al panel
        for (int i = 0; i < perfumes.size(); i++) {
            JPanel padre = new JPanel();
            padre.setBackground(Color.WHITE);
            Perfume perfume = perfumes.get(i);
            // cargamos la imagen, al serializar se guardo la ruta de la imagen, no se puede
            // serializar BufferedImage
            perfume.setPreferredSize(new Dimension(200, 200)); // Tamaño deseado de cada Canvas
            perfume.setPadre(padre);
            perfume.loadImage();
            perfume.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    perfume.aumentarClicks();
                    System.out.println(perfume.getClicks());
                    Perfume.guardarPerfumes(perfumes);
                }
            });

            // Configura la restricción del GridBagLayout para mantener la forma cuadrada
            gbc.gridx = i % 3; // Columna
            gbc.gridy = i / 3; // Fila
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;

            padre.setPreferredSize(new Dimension(200, 200));
            padre.add(perfume);
            lista.add(padre, gbc);
            //perfume.startAnimation();
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
class Perfume extends JPanel implements Serializable {
    // image no se guarda al momento de serializar
    private transient BufferedImage image;
    private Color color;
    private JPanel padre;
    private int clicks;
    private String ruta;
    private Timer timer;
    private boolean animando;
    private int originalSize;
    private Dimension originalDimension;

    public Perfume(Perfume copiar) {
        this.padre = copiar.padre;
        this.color = copiar.color;
        this.image = copiar.image;
        this.clicks = copiar.clicks;
        this.ruta = copiar.ruta;
    }
    public Perfume(String forma, Color color, int size){

        this.originalSize = size;
        this.animando = false;
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
        this.ruta = ruta;
        loadImage();
    }

    public void setPadre(JPanel padre) {
        this.padre = padre;
    }

    public void loadImage() {
        if (this.ruta != null && !this.ruta.equals("")) {
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
            g.setColor(padre.getBackground());
            g.fillRect(0,0, getWidth(), getHeight());
            g.setColor(color);
            g.fillRect(x, y, canvasSize, canvasSize);
            // Dibuja la imagen en el panel
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
    public void startAnimation(int animationDuration) {
        if (!animando) {
            originalDimension = new Dimension(getWidth(), getHeight());
            animando = true;
            // Crea el temporizador para la animación
            int totalFrames = animationDuration / 16; // Aproximadamente 60 cuadros por segundo
            int targetSize = Math.min(padre.getWidth(), padre.getHeight());

            int centerX = padre.getWidth() / 2; // Coordenada X del centro del panel padre
            int centerY = padre.getHeight() / 2; // Coordenada Y del centro del panel padre

            long startTime = System.currentTimeMillis(); // Obtiene el tiempo de inicio de la animación
            timer = new Timer(16, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long elapsedTime = System.currentTimeMillis() - startTime; // Calcula el tiempo transcurrido

                    if (elapsedTime < animationDuration) {
                        float progress = (float) elapsedTime / animationDuration; // Calcula el progreso de la animación
                        int newSize = (int) (progress * targetSize);
                        int newWidth = newSize;
                        int newHeight = newSize;

                        // Calcula las coordenadas del origen del componente animado
                        int startX = centerX - (newWidth / 2);
                        int startY = centerY - (newHeight / 2);

                        // Actualiza el tamaño y la ubicación del componente en cada cuadro
                        setSize(new Dimension(newWidth, newHeight));
                        setLocation(startX, startY);

                        // Redibuja el Canvas para reflejar los cambios
                        repaint();
                    } else {
                        // Detiene el temporizador una vez que se alcanza la duración total
                        timer.stop();
                        animando = false;
                    }
                }
            });

            // Inicia la animación
            timer.start();
        }
    }
    public void disappearAnimation(int animationDuration) {
        if (!animando) {
            animando = true;
            // Crea el temporizador para la animación
            int totalFrames = animationDuration / 16; // Aproximadamente 60 cuadros por segundo
            int targetSize = 0;

            int centerX = padre.getWidth() / 2; // Coordenada X del centro del panel padre
            int centerY = padre.getHeight() / 2; // Coordenada Y del centro del panel padre

            long startTime = System.currentTimeMillis(); // Obtiene el tiempo de inicio de la animación
            timer = new Timer(16, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long elapsedTime = System.currentTimeMillis() - startTime; // Calcula el tiempo transcurrido

                    if (elapsedTime < animationDuration) {
                        float progress = 1 - ((float) elapsedTime / animationDuration); // Calcula el progreso de la animación (decreciente)
                        int newSize = (int) (progress * targetSize);
                        int newWidth = newSize;
                        int newHeight = newSize;

                        // Calcula las coordenadas del origen del componente animado
                        int startX = centerX - (newWidth / 2);
                        int startY = centerY - (newHeight / 2);

                        // Actualiza el tamaño y la ubicación del componente en cada cuadro
                        setSize(new Dimension(newWidth, newHeight));
                        setLocation(startX, startY);

                        // Redibuja el Canvas para reflejar los cambios
                        repaint();
                    } else {
                        // Detiene el temporizador una vez que se alcanza la duración total
                        timer.stop();
                        animando = false;
                    }
                }
            });

            // Inicia la animación
            timer.start();
        }
    }

    public void stopAnimation() {
        if (timer != null && timer.isRunning()) {

            timer.stop();
            animando = false;
            setSize(originalDimension);
            setLocation(0, 0);
        }
    }
    public boolean isAnimable() {
        return !animando;
    }
    public void aumentarClicks() {
        clicks++;
    }

    public int getClicks() {
        return clicks;
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