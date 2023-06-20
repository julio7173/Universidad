// libraria para mostrar estadísticas

import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineModeDesc;
import javax.speech.recognition.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aplicacion {
    public static void main(String[] args) {

        CanvasFrame frame = new CanvasFrame();
        frame.initRecognizer();
        frame.run();
    }
}
class CanvasFrame extends JFrame {
    private Recognizer recognizer;
    private ArrayList<Perfume> perfumes;
    private JPanel canvasPanel;
    private Perfume seleccionado;
    private boolean contruir;

    public CanvasFrame() {
        // leemos los perfumes guardados en datos
        perfumes = Perfume.leerPerfumes();
        System.out.println(perfumes);
        setTitle("Crear");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);



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
            buscar();
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
        buscar();
    }

    private void construir() {
        contruir = true;
        canvasPanel.removeAll();
        Perfume[] nuevo = new Perfume[1];

        // botones
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.decode("#15191d"));
        leftPanel.setLayout(new GridLayout(0, 2, 0, 10)); // Usamos FlowLayout y establecemos los márgenes a 0
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel nombreLabel = new JLabel("Nombre");
        nombreLabel.setOpaque(true);
        leftPanel.add(nombreLabel);

        JTextField nombreTexto = new JTextField("perfume");
        nombreTexto.setFocusable(false);
        nombreTexto.setOpaque(true);
        nombreTexto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nombreTexto.setFocusable(true);
                nombreTexto.requestFocus();
            }
        });
        nombreTexto.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nombreTexto.getText().isEmpty()) {
                    nombreTexto.setText("perfume");
                }
            }
        });
        nombreTexto.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String input = nombreTexto.getText();
                    if (input.isEmpty()) {
                        // el texto es 1 si no se ingresa nada
                        nombreTexto.setText("perfume");
                    }

                    // quitamos el focus del text field
                    nombreTexto.setFocusable(false);
                    leftPanel.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        leftPanel.add(nombreTexto);


        JLabel mililitrosLabel = new JLabel("Mililitros");
        mililitrosLabel.setOpaque(true);
        leftPanel.add(mililitrosLabel);

        JTextField mililitrosTexto = new JTextField("100");
        mililitrosTexto.setFocusable(false);
        mililitrosTexto.setOpaque(true);
        mililitrosTexto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mililitrosTexto.setFocusable(true);
                mililitrosTexto.requestFocus();
            }
        });
        mililitrosTexto.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (mililitrosTexto.getText().isEmpty()) {
                    mililitrosTexto.setText("100");
                } else {
                    if (Integer.parseInt(mililitrosTexto.getText()) <= 0) {
                        // numero tiene que ser mayor a 0
                        mililitrosTexto.setText("100");
                    }
                }
            }
        });
        mililitrosTexto.addKeyListener(new KeyListener() {
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
                    String input = nombreTexto.getText();
                    if (input.isEmpty()) {
                        // el texto es 1 si no se ingresa nada
                        nombreTexto.setText("100");
                    }

                    // quitamos el focus del text field
                    nombreTexto.setFocusable(false);
                    leftPanel.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        leftPanel.add(mililitrosTexto);


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
                "Secuencia"
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
                } else if (!nombreTexto.getBounds().contains(e.getPoint())) {
                    //panel.requestFocusInWindow(); // Establecer el foco en el panel al hacer clic en cualquier lugar excepto en el campo de texto
                    nombreTexto.setFocusable(false);
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
                nuevo[0] = new Perfume(envaseSeleccionado, color, Integer.parseInt(tiempoTexto.getText()), animacionSeleccionada);
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

        // guardamos el nuevo perfume, si se uso el boton de mostrar
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nuevo[0] != null) {
                    nuevo[0].stopAnimation();
                    nuevo[0].setNombre(nombreTexto.getText());
                    nuevo[0].setMililitros(mililitrosTexto.getText());
                    perfumes.add(nuevo[0]);
                    Perfume.guardarPerfumes(perfumes);
                    System.out.println("agregando...");
                    nuevo[0] = null;
                    canvas.removeAll();
                    canvas.revalidate();
                    canvas.repaint();
                }
            }
        });
        animarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nuevo[0] != null) {
                    if (nuevo[0].isAnimable()) {
                        nuevo[0].setDuracion(Integer.parseInt(tiempoTexto.getText()));
                        nuevo[0].setAnimacion((String) animacionesOpcion.getSelectedItem());
                        nuevo[0].play();
                    } else {
                        nuevo[0].stopAnimation();
                    }
                }
            }
        });
        canvasPanel.revalidate();
        canvasPanel.repaint();
    }
    private void buscar() {
        contruir = false;
        canvasPanel.removeAll();

        JButton searchButton = new JButton("Buscar");
        searchButton.setBackground(new Color(16, 36, 112));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusable(false);
        searchButton.setSelected(false);

        JTextField textField = new JTextField(50);

        searchButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // simulamos enter en el textfield
                textField.dispatchEvent(new KeyEvent(textField, KeyEvent.KEY_PRESSED, 0,0 , KeyEvent.VK_ENTER));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        // panel de texto superior
        JPanel busqueda = new JPanel();
        busqueda.setBackground(Color.decode("#15191d"));
        busqueda.setLayout(new FlowLayout(FlowLayout.CENTER));
        busqueda.add(searchButton);
        busqueda.add(textField);
            // panel de informacion
        JPanel informacion = new JPanel();
        informacion.setBackground(Color.decode("#15191d"));
        informacion.setLayout(new BoxLayout(informacion, BoxLayout.Y_AXIS));

        JPanel dibujado = new JPanel() {};

        dibujado.setBackground(Color.WHITE);
        informacion.add(dibujado);

        JPanel detalles = new JPanel();
        detalles.setBackground(Color.decode("#15191d"));
        detalles.setLayout(new GridLayout(0, 1, 0, 2));

        JLabel nombreLabel = new JLabel("Nombre: ");
        nombreLabel.setForeground(Color.WHITE);
        nombreLabel.setBackground(new Color(255, 255, 255, 25));
        nombreLabel.setOpaque(true);
        detalles.add(nombreLabel);

        JLabel colorLabel = new JLabel("Color: ");
        colorLabel.setForeground(Color.WHITE);
        colorLabel.setBackground(new Color(255, 255, 255, 25));
        colorLabel.setOpaque(true);
        detalles.add(colorLabel);

        JLabel animacionLabel = new JLabel("Animacion: ");
        animacionLabel.setForeground(Color.WHITE);
        animacionLabel.setBackground(new Color(255, 255, 255, 25));
        animacionLabel.setOpaque(true);
        detalles.add(animacionLabel);

        JLabel contenidoLabel = new JLabel("Contenido: ");
        contenidoLabel.setForeground(Color.WHITE);
        contenidoLabel.setBackground(new Color(255, 255, 255, 25));
        contenidoLabel.setOpaque(true);
        detalles.add(contenidoLabel);

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setForeground(Color.WHITE);
        eliminarButton.setBackground(new Color(119, 17, 17));
        eliminarButton.setFocusable(false);
        eliminarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (seleccionado!= null) {
                    dibujado.removeAll();
                    dibujado.revalidate();
                    dibujado.repaint();
                    perfumes.remove(seleccionado);
                    Perfume.guardarPerfumes(perfumes);
                    seleccionado = null;
                    buscar();

                }
            }
        });

        detalles.add(eliminarButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, dibujado, detalles);
        splitPane.setDividerLocation(0.5); // Establecer la ubicación del divisor al 50%
        splitPane.setEnabled(false);
        splitPane.setResizeWeight(0.75);
        splitPane.setDividerSize(0); // Establece el tamaño del divisor en cero

        informacion.add(splitPane);


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
            perfume.setPadre(padre);
            perfume.loadImage();
            perfume.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    seleccionado = perfume;
                    dibujado.removeAll();
                    perfume.aumentarClicks();
                    System.out.println(perfume.getClicks());
                    Perfume copia = new Perfume(perfume);
                    copia.setPreferredSize(new Dimension(dibujado.getWidth(), dibujado.getHeight()));
                    copia.setPadre(dibujado);
                    animacionLabel.setText("Animacion: " + perfume.getAnimacion());
                    colorLabel.setText("Color: " + obtenerNombreColor(perfume.getColor()));
                    contenidoLabel.setText("Contenido: " + perfume.getMililitros() + " ml");
                    nombreLabel.setText("Nombre: " + perfume.getNombre());
                    detalles.repaint();
                    dibujado.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (copia.isAnimable()){
                                copia.play();
                            } else {
                                copia.stopAnimation();
                            }

                        }
                    });
                    copia.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            dibujado.dispatchEvent(e);
                        }
                    });
                    dibujado.add(copia);

                    Perfume.guardarPerfumes(perfumes);
                    dibujado.revalidate();
                    dibujado.repaint();
                }
            });

            // Configura la restricción del GridBagLayout para mantener la forma cuadrada
            gbc.gridx = i % 3; // Columna
            gbc.gridy = i / 3; // Fila
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;

            padre.setPreferredSize(new Dimension(200, 200));
            perfume.setPreferredSize(new Dimension(200, 200));
            padre.add(perfume);
            lista.add(padre, gbc);
            //perfume.startAnimation();
        }


        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.getVerticalScrollBar().setUnitIncrement(64);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(64);
        JSplitPane listaInformacion = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, informacion, scrollPane);
        listaInformacion.setDividerLocation(450);
        listaInformacion.setEnabled(false);
        listaInformacion.setResizeWeight(0);
        listaInformacion.setDividerSize(0);

        canvasPanel.add(busqueda, BorderLayout.NORTH);
        canvasPanel.add(listaInformacion, BorderLayout.CENTER);
        canvasPanel.revalidate();
        canvasPanel.repaint();
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    dibujado.removeAll();
                    seleccionado = null;
                    lista.removeAll();
                    String input = textField.getText();
                    Pattern pattern = Pattern.compile(input);
                    int i = 0;
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre componentes
                    for (Perfume perfume : perfumes) {
                        Matcher matcher = pattern.matcher(perfume.getNombre());
                        if (matcher.find()) {
                            System.out.println("pueba: " + perfume.getNombre());
                            JPanel padre = new JPanel();
                            padre.setBackground(Color.WHITE);
                            // cargamos la imagen, al serializar se guardo la ruta de la imagen, no se puede
                            // serializar BufferedImage
                            perfume.setPadre(padre);
                            perfume.loadImage();
                            perfume.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    seleccionado = perfume;
                                    dibujado.removeAll();
                                    perfume.aumentarClicks();
                                    System.out.println(perfume.getClicks());
                                    Perfume copia = new Perfume(perfume);
                                    copia.setPreferredSize(new Dimension(dibujado.getWidth(), dibujado.getHeight()));
                                    copia.setPadre(dibujado);
                                    animacionLabel.setText("Animacion: " + perfume.getAnimacion());
                                    colorLabel.setText("Color: " + obtenerNombreColor(perfume.getColor()));
                                    contenidoLabel.setText("Contenido: " + perfume.getMililitros() + " ml");
                                    nombreLabel.setText("Nombre: " + perfume.getNombre());
                                    detalles.repaint();
                                    dibujado.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            if (copia.isAnimable()){
                                                copia.play();
                                            } else {
                                                copia.stopAnimation();
                                            }

                                        }
                                    });
                                    copia.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            dibujado.dispatchEvent(e);
                                        }
                                    });
                                    dibujado.add(copia);

                                    Perfume.guardarPerfumes(perfumes);
                                    dibujado.revalidate();
                                    dibujado.repaint();
                                }
                            });

                            // Configura la restricción del GridBagLayout para mantener la forma cuadrada
                            gbc.gridx = i % 3; // Columna
                            gbc.gridy = i / 3; // Fila
                            gbc.weightx = 1.0;
                            gbc.weighty = 1.0;
                            gbc.fill = GridBagConstraints.BOTH;

                            padre.setPreferredSize(new Dimension(200, 200));
                            perfume.setPreferredSize(new Dimension(200, 200));
                            padre.add(perfume);
                            lista.add(padre, gbc);
                            i++;
                        }
                    }
                    dibujado.repaint();
                    lista.repaint();
                    lista.revalidate();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
    }

    private void estadisticas() {
        contruir = false;
        canvasPanel.add(new Estadistica(perfumes));
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
    public void initRecognizer() {
        try {
            // Inicializar el reconocedor

            recognizer = Central.createRecognizer(new EngineModeDesc(Locale.ENGLISH));
            File archivo = new File("ControlVoz/datos/gramatica.txt");

            BufferedReader gramatica;
            FileInputStream fileInputStream = new FileInputStream(archivo);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            gramatica = new BufferedReader(inputStreamReader);

            recognizer.allocate();
            RuleGrammar gram = recognizer.loadJSGF(gramatica);
            gram.setEnabled(true);

            recognizer.addResultListener(new ResultListener() {
                @Override
                public void audioReleased(ResultEvent resultEvent) {

                }

                @Override
                public void grammarFinalized(ResultEvent resultEvent) {

                }

                @Override
                public void resultAccepted(ResultEvent event) {
                    if (contruir) {
                        try {
                            Result r = (Result) (event.getSource());

                            ResultToken tokens[] = r.getBestTokens();

                            for (int i = 0; i < tokens.length; i++) {
                                String palabra = tokens[i].getSpokenText();
                                //System.out.println(palabra);
                                if (palabra.contains("exit")) {
                                    try {
                                        recognizer.deallocate();
                                    } catch (EngineException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    System.exit(0);
                                }
                                if (palabra.contains("show")) {
                                    System.out.println("mostrar");

                                }else if (palabra.contains("save")) {
                                    System.out.println("guardar");
                                }else if (palabra.contains("play")) {
                                    System.out.println("animar");
                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void resultCreated(ResultEvent resultEvent) {

                }

                @Override
                public void resultRejected(ResultEvent resultEvent) {

                }

                @Override
                public void resultUpdated(ResultEvent resultEvent) {

                }

                @Override
                public void trainingInfoReleased(ResultEvent resultEvent) {

                }
            });


            // Inicia el reconocedor
            recognizer.commitChanges();
            recognizer.requestFocus();
            recognizer.resume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }
}