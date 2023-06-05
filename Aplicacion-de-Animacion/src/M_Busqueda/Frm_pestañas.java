/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package M_Busqueda;


// Estas son las importaciones de las clases y paquetes necesarios para el programa
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

// Esta es la clase principal que extiende de JFrame y crea una ventana con pestañas
public class Frm_pestañas extends JFrame {
    
    // Estos son los atributos de la clase
    
    HashMap<String, Integer> mapa; // Un mapa que almacena los nombres y las frecuencias de los perfumes

    /**
     * Creates new form Frm_pestañas
     */
    // Este es el constructor de la clase
    public Frm_pestañas() {
        initComponents(); // Este método inicializa los componentes de la ventana
        setLocationRelativeTo(null); // Este método establece la ubicación de la ventana en el centro de la pantalla
        
        // Estos métodos asignan una imagen a cada botón de perfume usando una ruta relativa
        SetImageButton(per1, "src/Imagenes-Perfumes/PCi1.png");
        SetImageButton(per2, "src/Imagenes-Perfumes/PCi2.png");
        SetImageButton(per3, "src/Imagenes-Perfumes/PCu1.png");
        SetImageButton(per4, "src/Imagenes-Perfumes/PCu2.png");
        
         // Este bloque inicializa el mapa con los nombres y las frecuencias de los perfumes
        mapa = new HashMap();
        mapa.put("PCi1", 0);
        mapa.put("PCi2", 0);
        mapa.put("PCu1", 0);
        mapa.put("PCu2", 0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Este método inicializa los componentes de la ventana usando el editor gráfico de NetBeans
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane(); // Un componente que permite crear pestañas
        jScrollPane1 = new javax.swing.JScrollPane(); // Un componente que permite añadir barras de desplazamiento a otro componente
        jPanel1 = new javax.swing.JPanel(); // Un componente que permite agrupar otros componentes en un contenedor
        catalogo = new javax.swing.JButton(); // Un componente que representa un botón que se puede pulsar
        jLabel5 = new javax.swing.JLabel(); // Un componente que representa una etiqueta que puede mostrar texto o imágenes
        per1 = new javax.swing.JButton(); // Otro botón para el perfume 1
        per2 = new javax.swing.JButton(); // Otro botón para el perfume 2
        per3 = new javax.swing.JButton(); // Otro botón para el perfume 3
        per4 = new javax.swing.JButton(); // Otro botón para el perfume 4
        jScrollPane2 = new javax.swing.JScrollPane(); // Otro componente que permite añadir barras de desplazamiento a otro componente
        jPanel2 = new javax.swing.JPanel(); // Otro componente que permite agrupar otros componentes en un contenedor
        graficar = new javax.swing.JButton(); // Otro componente que representa un botón que se puede pulsar

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // Establece que al cerrar la ventana se termine el programa

        jPanel1.setBackground(new java.awt.Color(255, 102, 102)); // Establece el color de fondo del panel 1

        catalogo.setBackground(new java.awt.Color(153, 153, 255)); // Establece el color de fondo del botón catálogo
        catalogo.setFont(new java.awt.Font("DialogInput", 3, 24)); // NOI18N // Establece la fuente del botón catálogo
        catalogo.setText("B U S C A R"); // Establece el texto del botón catálogo
        catalogo.addActionListener(new java.awt.event.ActionListener() { // Añade un oyente de acción al botón catálogo
            public void actionPerformed(java.awt.event.ActionEvent evt) { // Este método se ejecuta cuando se hace clic en el botón catálogo
                catalogoActionPerformed(evt); // Llama a otro método que abre un selector de archivos
            }
        });

        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null)); // Establece el borde de la etiqueta 5

        per1.addActionListener(new java.awt.event.ActionListener() { // Añade un oyente de acción al botón perfume 1
            public void actionPerformed(java.awt.event.ActionEvent evt) { // Este método se ejecuta cuando se hace clic en el botón perfume 1
                per1ActionPerformed(evt); // Llama a otro método que incrementa la frecuencia del perfume 1 en el mapa
            }
        });

        per2.addActionListener(new java.awt.event.ActionListener() { // Añade un oyente de acción al botón perfume 2
            public void actionPerformed(java.awt.event.ActionEvent evt) { // Este método se ejecuta cuando se hace clic en el botón perfume 2
                per2ActionPerformed(evt); // Llama a otro método que incrementa la frecuencia del perfume 2 en el mapa
            }
        });

        per3.addActionListener(new java.awt.event.ActionListener() { // Añade un oyente de acción al botón perfume 3
            public void actionPerformed(java.awt.event.ActionEvent evt) { // Este método se ejecuta cuando se hace clic en el botón perfume 3
                per3ActionPerformed(evt); // Llama a otro método que incrementa la frecuencia del perfume 3 en el mapa
            }
        });

        per4.addActionListener(new java.awt.event.ActionListener() { // Añade un oyente de acción al botón perfume 4
            public void actionPerformed(java.awt.event.ActionEvent evt) { // Este método se ejecuta cuando se hace clic en el botón perfume 4
                per4ActionPerformed(evt); // Llama a otro método que incrementa la frecuencia del perfume 4 en el mapa
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1); // Crea un objeto GroupLayout para el panel 1
        jPanel1.setLayout(jPanel1Layout); // Establece el objeto GroupLayout como el administrador de diseño del panel 1
        jPanel1Layout.setHorizontalGroup( // Define el diseño horizontal del panel 1
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Crea un grupo paralelo horizontal que contiene todos los componentes del panel 1
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup() // Añade al grupo paralelo un subgrupo secuencial horizontal que contiene los botones de perfume 1 y 2
                .addGap(55, 55, 55) // Añade un espacio de 55 píxeles al principio del subgrupo secuencial
                .addComponent(per1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE) // Añade el botón perfume 1 al subgrupo secuencial con un tamaño preferido de 250 píxeles
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE) // Añade un espacio entre el botón perfume 1 y el botón perfume 2 que puede variar según el tamaño del contenedor
                .addComponent(per2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE) // Añade el botón perfume 2 al subgrupo secuencial con un tamaño preferido de 250 píxeles
                .addGap(134, 134, 134)) // Añade un espacio de 134 píxeles al final del subgrupo secuencial
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup() // Añade al grupo paralelo otro subgrupo secuencial horizontal que contiene el botón catálogo y la etiqueta 5
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Añade un espacio al principio del subgrupo secuencial que puede variar según el tamaño del contenedor
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Añade al subgrupo secuencial un subgrupo paralelo horizontal que contiene el botón catálogo y la etiqueta 5
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup() // Añade al subgrupo paralelo otro subgrupo secuencial horizontal que contiene solo el botón catálogo
                        .addComponent(catalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE) // Añade el botón catálogo al subgrupo secuencial con un tamaño preferido de 500 píxeles
                        .addGap(165, 165, 165)) // Añade un espacio de 165 píxeles al final del subgrupo secuencial
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup() // Añade al subgrupo paralelo otro subgrupo secuencial horizontal que contiene solo la etiqueta 5
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE) // Añade la etiqueta 5 al subgrupo secuencial con un tamaño preferido de 338 píxeles
                        .addGap(240, 240, 240)))) // Añade un espacio de 240 píxeles al final del subgrupo secuencial
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup() // Añade al grupo paralelo otro subgrupo secuencial horizontal que contiene los botones de perfume 3 y 4
                .addGap(93, 93, 93) // Añade un espacio de 93 píxeles al principio del subgrupo secuencial
                .addComponent(per3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE) // Añade el botón perfume 3 al subgrupo secuencial con un tamaño preferido de 250 píxeles
                .addGap(75, 75, 75) // Añade un espacio de 75 píxeles entre el botón perfume 3 y el botón perfume 4
                .addComponent(per4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE) // Añade el botón perfume 4 al subgrupo secuencial con un tamaño preferido de 250 píxeles
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)) // Añade un espacio al final del subgrupo secuencial que puede variar según el tamaño del contenedor
        );
        jPanel1Layout.setVerticalGroup( // Define el diseño vertical del panel 1
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Crea un grupo paralelo vertical que contiene todos los componentes del panel 1
            .addGroup(jPanel1Layout.createSequentialGroup() // Añade al grupo paralelo un subgrupo secuencial vertical que contiene todos los componentes del panel 1
                .addGap(58, 58, 58) // Añade un espacio de 58 píxeles al principio del subgrupo secuencial
                .addComponent(catalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE) // Añade el botón catálogo al subgrupo secuencial con un tamaño preferido de 50 píxeles
                .addGap(55, 55, 55) // Añade un espacio de 55 píxeles entre el botón catálogo y la etiqueta 5
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE) // Añade la etiqueta 5 al subgrupo secuencial con un tamaño preferido de 322 píxeles
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE) // Añade un espacio entre la etiqueta 5 y los botones de perfume que puede variar según el tamaño del contenedor
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Añade al subgrupo secuencial un subgrupo paralelo vertical que contiene los botones de perfume 1 y 2
                    .addComponent(per2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE) // Añade el botón perfume 2 al subgrupo paralelo con un tamaño preferido de 300 píxeles
                    .addComponent(per1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)) // Añade el botón perfume 1 al subgrupo paralelo con un tamaño preferido de 300 píxeles
                .addGap(32, 32, 32) // Añade un espacio de 32 píxeles entre los botones de perfume y los botones de perfume
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Añade al subgrupo secuencial otro subgrupo paralelo vertical que contiene los botones de perfume 3 y 4
                    .addComponent(per4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE) // Añade el botón perfume 4 al subgrupo paralelo con un tamaño preferido de 300 píxeles
                    .addComponent(per3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)) // Añade el botón perfume 3 al subgrupo paralelo con un tamaño preferido de 300 píxeles
                .addGap(24, 24, 24)) // Añade un espacio de 24 píxeles al final del subgrupo secuencial
        );

        jScrollPane1.setViewportView(jPanel1); // Establece el panel 1 como el componente que se muestra dentro del componente jScrollPane1

        jTabbedPane1.addTab("CATÁLOGO", jScrollPane1); // Añade el componente jScrollPane1 al componente jTabbedPane1 con el título "CATÁLOGO"

        graficar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N // Establece la fuente del botón graficar
        graficar.setText("GRAFICAR"); // Establece el texto del botón graficar
        graficar.addActionListener(new java.awt.event.ActionListener() { // Añade un oyente de acción al botón graficar
            public void actionPerformed(java.awt.event.ActionEvent evt) { // Este método se ejecuta cuando se hace clic en el botón graficar
                graficarActionPerformed(evt); // Llama a otro método que crea un gráfico de pastel con los datos del mapa
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2); // Crea un objeto GroupLayout para el panel 2
        jPanel2.setLayout(jPanel2Layout); // Establece el objeto GroupLayout como el administrador de diseño del panel 2
        jPanel2Layout.setHorizontalGroup( // Define el diseño horizontal del panel 2
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Crea un grupo paralelo horizontal que contiene todos los componentes del panel 2
            .addGroup(jPanel2Layout.createSequentialGroup() // Añade al grupo paralelo un subgrupo secuencial horizontal que contiene solo el botón graficar
                .addGap(356, 356, 356) // Añade un espacio de 356 píxeles al principio del subgrupo secuencial
                .addComponent(graficar) // Añade el botón graficar al subgrupo secuencial
                .addContainerGap(369, Short.MAX_VALUE)) // Añade un espacio al final del subgrupo secuencial que puede variar según el tamaño del contenedor
        );
        jPanel2Layout.setVerticalGroup( // Define el diseño vertical del panel 2
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Crea un grupo paralelo vertical que contiene todos los componentes del panel 2
            .addGroup(jPanel2Layout.createSequentialGroup() // Añade al grupo paralelo un subgrupo secuencial vertical que contiene solo el botón graficar
                .addGap(215, 215, 215) // Añade un espacio de 215 píxeles al principio del subgrupo secuencial
                .addComponent(graficar) // Añade el botón graficar al subgrupo secuencial
                .addContainerGap(232, Short.MAX_VALUE)) // Añade un espacio al final del subgrupo secuencial que puede variar según el tamaño del contenedor
        );

        jScrollPane2.setViewportView(jPanel2); // Establece el panel 2 como el componente que se muestra dentro del componente jScrollPane2

        jTabbedPane1.addTab("ESTADÍSTICA", jScrollPane2); // Añade el componente jScrollPane2 al componente jTabbedPane1 con el título "ESTADÍSTICA"

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane()); // Crea un objeto GroupLayout para el contenedor principal de la ventana
        getContentPane().setLayout(layout); // Establece el objeto GroupLayout como el administrador de diseño del contenedor principal de la ventana
        layout.setHorizontalGroup( // Define el diseño horizontal del contenedor principal de la ventana
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Crea un grupo paralelo horizontal que contiene todos los componentes del contenedor principal de la ventana
            .addGroup(layout.createSequentialGroup() // Añade al grupo paralelo un subgrupo secuencial horizontal que contiene solo el componente jTabbedPane1
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE) // Añade el componente jTabbedPane1 al subgrupo secuencial con un tamaño preferido de 800 píxeles
                .addGap(0, 0, Short.MAX_VALUE)) // Añade un espacio al final del subgrupo secuencial que puede variar según el tamaño del contenedor
        );
        layout.setVerticalGroup( // Define el diseño vertical del contenedor principal de la ventana
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // Crea un grupo paralelo vertical que contiene todos los componentes del contenedor principal de la ventana
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE) // Añade el componente jTabbedPane1 al grupo paralelo con un tamaño preferido de 500 píxeles
        );

        pack(); // Empaqueta la ventana para que se ajuste al tamaño preferido de sus componentes
    }// </editor-fold>//GEN-END:initComponents

    private void catalogoActionPerformed(java.awt.event.ActionEvent evt) {                                                                                                            
        String ruta = ""; // Declara una variable de tipo String para almacenar la ruta del archivo elegido
        JFileChooser catalogo = new JFileChooser(); // Crea un objeto JFileChooser para mostrar el selector de archivos
        FileNameExtensionFilter filtrado = new FileNameExtensionFilter("png", "png"); // Crea un objeto FileNameExtensionFilter para filtrar los archivos con extensión png
        catalogo.setFileFilter(filtrado); // Establece el objeto FileNameExtensionFilter como el filtro de archivos del objeto JFileChooser
        
        int respuesta = catalogo.showOpenDialog(this); // Muestra el selector de archivos y guarda la respuesta del usuario en una variable de tipo int
        
        if(respuesta == JFileChooser.APPROVE_OPTION){ // Si el usuario aprueba la selección del archivo
            ruta = catalogo.getSelectedFile().getPath(); // Obtiene la ruta del archivo seleccionado y la guarda en la variable ruta
            Image per = new ImageIcon(ruta).getImage(); // Crea un objeto Image a partir de la ruta del archivo
            ImageIcon perIco = new ImageIcon(per.getScaledInstance(jLabel5.getWidth(), jLabel5.getHeight(), Image.SCALE_SMOOTH)); // Crea un objeto ImageIcon a partir del objeto Image y lo escala al tamaño de la etiqueta 5
            jLabel5.setIcon(perIco); // Establece el objeto ImageIcon como el icono de la etiqueta 5
        }
        
        String nombre = catalogo.getSelectedFile().getName(); // Obtiene el nombre del archivo seleccionado y lo guarda en una variable de tipo String
        nombre = nombre.replace(".png", ""); // Elimina la extension '.png' del nombre
        if(mapa.containsKey(nombre)){ // Comprobar si el mapa contiene la clave con el nombre de la imagen
            int valor = mapa.get(nombre); // Obtiene el valor asociado a esa clave y lo guarda en una variable de tipo int
            valor ++; // Incrementa el valor en uno
            mapa.put(nombre, valor); // Actualiza el valor asociado a esa clave en el mapa
            pastel2.setValue(nombre, valor); // Actualiza el valor del conjunto de datos para ese nombre
            gra2.repaint(); // Repinta el panel gra2 para que se actualice la gráfica
        }
    }                                        

    int contador1 = 0; // Declara una variable de tipo int para contar las veces que se hace clic en el botón perfume 1
    int contador2 = 0; // Declara una variable de tipo int para contar las veces que se hace clic en el botón perfume 2
    int contador3 = 0; // Declara una variable de tipo int para contar las veces que se hace clic en el botón perfume 3
    int contador4 = 0; // Declara una variable de tipo int para contar las veces que se hace clic en el botón perfume 4
    
    DefaultPieDataset pastel1 = new DefaultPieDataset(); // Crea un objeto DefaultPieDataset para almacenar los datos del primer gráfico de pastel
    ChartPanel gra1; // Crea un objeto ChartPanel
    JFreeChart p1; // Crea un objeto JFreeChart
    
    DefaultPieDataset pastel2 = new DefaultPieDataset(); // Crea un objeto DefaultPieDataset para almacenar los datos del segundo gráfico de pastel
    JFreeChart p2; // Crea un objeto JFreeChart
    ChartPanel gra2; // Crea un objeto ChartPanel
    
    
    private void per1ActionPerformed(java.awt.event.ActionEvent evt) { // Este método se ejecuta cuando se hace clic en el botón perfume 1
        
        contador1++; // Incrementa el contador1 en uno
        pastel1.setValue("per1", contador1); // actualizar el valor de per1 en el conjunto de datos
        gra1.repaint(); // repintar el panel gra1 para que se actualice la gráfica
    }                                    

    private void per2ActionPerformed(java.awt.event.ActionEvent evt) { // Este método se ejecuta cuando se hace clic en el botón perfume 2                                                                                                           
        contador2++; // Incrementa el contador2 en uno
        pastel1.setValue("per2", contador2); // actualizar el valor de per1 en el conjunto de datos
        gra1.repaint(); // repintar el panel gra1 para que se actualice la gráfica
    }                                    

    private void per3ActionPerformed(java.awt.event.ActionEvent evt) { // Este método se ejecuta cuando se hace clic en el botón perfume 3                                                                                                         
        contador3++; // Incrementa el contador3 en uno
        pastel1.setValue("per3", contador3); // actualizar el valor de per1 en el conjunto de datos
        gra1.repaint(); // repintar el panel gra1 para que se actualice la gráfica
    }                                    

    private void per4ActionPerformed(java.awt.event.ActionEvent evt) { // Este método se ejecuta cuando se hace clic en el botón perfume 4                                                                                                         
        contador4++; // Incrementa el contador4 en uno
        pastel1.setValue("per4", contador4); // actualizar el valor de per1 en el conjunto de datos
        gra1.repaint(); // repintar el panel gra1 para que se actualice la gráfica
    }                                    

    private void graficarActionPerformed(java.awt.event.ActionEvent evt) { // Este método se ejecuta cuando se hace clic en el botón graficar                                                                                                            
        pastel1.setValue("per1", contador1); // Añade el valor del contador1 con la etiqueta "per1" al conjunto de datos
        pastel1.setValue("per2", contador2); // Añade el valor del contador2 con la etiqueta "per2" al conjunto de datos
        pastel1.setValue("per3", contador3); // Añade el valor del contador3 con la etiqueta "per3" al conjunto de datos
        pastel1.setValue("per4", contador4); // Añade el valor del contador4 con la etiqueta "per4" al conjunto de datos

        p1 = ChartFactory.createPieChart( // Crea un objeto JFreeChart que representa el primer gráfico de pastel usando una fábrica de gráficos
                "La más usada", // El título del gráfico
                pastel1, // El conjunto de datos del gráfico
                true, // Si se muestra o no la leyenda del gráfico
                true, // Si se muestra o no las herramientas del gráfico
                false // Si se genera o no URLs para los elementos del gráfico
        );
        
        gra1 = new ChartPanel(p1); // Crea un objeto ChartPanel que contiene el primer gráfico de pastel
        gra1.setMouseWheelEnabled(true); // Habilita el uso de la rueda del ratón para hacer zoom en el gráfico
        gra1.setPreferredSize(new Dimension(400,200)); // Establece el tamaño preferido del panel del gráfico

        jPanel2.setLayout(new BorderLayout()); // Establece el diseño del panel 2 como un BorderLayout
        jPanel2.add(gra1, BorderLayout.NORTH); // Añade el panel del primer gráfico al panel 2 en la posición norte

        pack(); // Empaqueta la ventana para que se ajuste al tamaño preferido de sus componentes
        repaint(); // Repinta la ventana para reflejar los cambios
         
        
        pastel2.setValue("PCi1", mapa.get("PCi1")); // Añade el valor asociado a la clave "PCi1" en el mapa con la etiqueta "PCi1" al conjunto de datos
        pastel2.setValue("PCi2", mapa.get("PCi2")); // Añade el valor asociado a la clave "PCi2" en el mapa con la etiqueta "PCi2" al conjunto de datos
        pastel2.setValue("PCu1", mapa.get("PCu1")); // Añade el valor asociado a la clave "PCu1" en el mapa con la etiqueta "PCu1" al conjunto de datos
        pastel2.setValue("PCu2", mapa.get("PCu2")); // Añade el valor asociado a la clave "PCu2" en el mapa con la etiqueta "PCu2" al conjunto de datos
        
        p2 = ChartFactory.createPieChart( // Crea un objeto JFreeChart que representa el segundo gráfico de pastel usando una fábrica de gráficos
                "La más usada", // El título del gráfico
                pastel2, // El conjunto de datos del gráfico
                true, // Si se muestra o no la leyenda del gráfico
                true, // Si se muestra o no las herramientas del gráfico
                false // Si se genera o no URLs para los elementos del gráfico
        );
        
        gra2 = new ChartPanel(p2); // Crea un objeto ChartPanel que contiene el segundo gráfico de pastel
        gra2.setMouseWheelEnabled(true); // Habilita el uso de la rueda del ratón para hacer zoom en el gráfico
        gra2.setPreferredSize(new Dimension(400,200)); // Establece el tamaño preferido del panel del gráfico

        jPanel2.setLayout(new BorderLayout()); // Establece el diseño del panel 2 como un BorderLayout
        jPanel2.add(gra2, BorderLayout.SOUTH); // Añade el panel del segundo gráfico al panel 2 en la posición sur

        pack(); // Empaqueta la ventana para que se ajuste al tamaño preferido de sus componentes
        repaint(); // Repinta la ventana para reflejar los cambios
    }                                        
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) { // El método principal de la aplicación
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try { 
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) { // Recorre los aspectos visuales instalados en el sistema
                if ("Nimbus".equals(info.getName())) { // Si encuentra el aspecto visual Nimbus
                    javax.swing.UIManager.setLookAndFeel(info.getClassName()); // Establece el aspecto visual Nimbus para la aplicación
                    break; // Rompe el bucle
                }
            }
        } catch (ClassNotFoundException ex) { // Si ocurre una excepción de clase no encontrada
            java.util.logging.Logger.getLogger(Frm_pestañas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); // Registra el error en el logger
        } catch (InstantiationException ex) { // Si ocurre una excepción de instancia no creada
            java.util.logging.Logger.getLogger(Frm_pestañas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); // Registra el error en el logger
        } catch (IllegalAccessException ex) { // Si ocurre una excepción de acceso ilegal
            java.util.logging.Logger.getLogger(Frm_pestañas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); // Registra el error en el logger
        } catch (javax.swing.UnsupportedLookAndFeelException ex) { // Si ocurre una excepción de aspecto visual no soportado
            java.util.logging.Logger.getLogger(Frm_pestañas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); // Registra el error en el logger
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() { // Invoca al hilo de eventos para ejecutar una tarea
            public void run() { // La tarea a ejecutar es la siguiente
                new Frm_pestañas().setVisible(true); // Crea un objeto Frm_pestañas y lo hace visible
            }
        });
    }

    private void SetImageLabel(JLabel labelName, String root){ // Este método recibe como parámetros una etiqueta y una ruta de una imagen
        ImageIcon image = new ImageIcon(root); // Crea un objeto ImageIcon a partir de la ruta de la imagen
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(),labelName.getHeight(), Image.SCALE_DEFAULT)); // Crea otro objeto ImageIcon a partir del objeto anterior, pero escalando la imagen al tamaño de la etiqueta
        labelName.setIcon(icon); // Establece el objeto ImageIcon como el icono de la etiqueta
        this.repaint(); // Repinta la ventana para reflejar los cambios
    }
    
    private void SetImageButton(JButton buttonName, String root){ // Este método recibe como parámetros un botón y una ruta de una imagen
        ImageIcon image = new ImageIcon(root); // Crea un objeto ImageIcon a partir de la ruta de la imagen
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(buttonName.getWidth(),buttonName.getHeight(), Image.SCALE_DEFAULT)); // Crea otro objeto ImageIcon a partir del objeto anterior, pero escalando la imagen al tamaño del botón
        buttonName.setIcon(icon); // Establece el objeto ImageIcon como el icono del botón
        this.repaint(); // Repinta la ventana para reflejar los cambios
    }
    
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton catalogo; // Declara un botón llamado catálogo
    private javax.swing.JButton graficar; // Declara un botón llamado graficar
    private javax.swing.JLabel jLabel5; // Declara una etiqueta llamada jLabel5
    private javax.swing.JPanel jPanel1; // Declara un panel llamado jPanel1
    private javax.swing.JPanel jPanel2; // Declara un panel llamado jPanel2
    private javax.swing.JScrollPane jScrollPane1; // Declara un componente que permite desplazarse por el panel 1
    private javax.swing.JScrollPane jScrollPane2; // Declara un componente que permite desplazarse por el panel 2
    private javax.swing.JTabbedPane jTabbedPane1; // Declara un componente que permite cambiar entre diferentes paneles usando pestañas
    private javax.swing.JButton per1; // Declara un botón llamado per1
    private javax.swing.JButton per2; // Declara un botón llamado per2
    private javax.swing.JButton per3; // Declara un botón llamado per3
    private javax.swing.JButton per4; // Declara un botón llamado per4
    // End of variables declaration//GEN-END:variables
}
