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
    // Este método inicializa los componentes de la ventana usando el editor gráfico de NetBeans
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        catalogo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        per1 = new javax.swing.JButton();
        per2 = new javax.swing.JButton();
        per3 = new javax.swing.JButton();
        per4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        graficar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        catalogo.setBackground(new java.awt.Color(153, 153, 255));
        catalogo.setFont(new java.awt.Font("DialogInput", 3, 24)); // NOI18N
        catalogo.setText("B U S C A R");
        catalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catalogoActionPerformed(evt);
            }
        });

        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null));

        per1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                per1ActionPerformed(evt);
            }
        });

        per2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                per2ActionPerformed(evt);
            }
        });

        per3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                per3ActionPerformed(evt);
            }
        });

        per4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                per4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(per1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(per2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(catalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(165, 165, 165))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(240, 240, 240))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(per3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(per4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(catalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(per2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(per1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(per4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(per3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jScrollPane1.setViewportView(jPanel1);

        jTabbedPane1.addTab("CATÁLOGO", jScrollPane1);

        graficar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        graficar.setText("GRAFICAR");
        graficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graficarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(356, 356, 356)
                .addComponent(graficar)
                .addContainerGap(369, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(graficar)
                .addContainerGap(232, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        jTabbedPane1.addTab("ESTADÍSTICA", jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void catalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catalogoActionPerformed
        String ruta = "";
        JFileChooser catalogo = new JFileChooser();
        FileNameExtensionFilter filtrado = new FileNameExtensionFilter("png", "png");
        catalogo.setFileFilter(filtrado);
        
        int respuesta = catalogo.showOpenDialog(this);
        
        if(respuesta == JFileChooser.APPROVE_OPTION){
            ruta = catalogo.getSelectedFile().getPath();
            Image per = new ImageIcon(ruta).getImage();
            ImageIcon perIco = new ImageIcon(per.getScaledInstance(jLabel5.getWidth(), jLabel5.getHeight(), Image.SCALE_SMOOTH));
            jLabel5.setIcon(perIco);
        }
        
        String nombre = catalogo.getSelectedFile().getName();
        if(mapa.containsKey(nombre)){
            int valor = mapa.get(nombre);
            valor ++;
            mapa.put(nombre, valor);
        }
    }//GEN-LAST:event_catalogoActionPerformed

    int contador1 = 0;
    int contador2 = 0;
    int contador3 = 0;
    int contador4 = 0;
    
    
    private void per1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_per1ActionPerformed
        contador1++;
    }//GEN-LAST:event_per1ActionPerformed

    private void per2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_per2ActionPerformed
        contador2++;
    }//GEN-LAST:event_per2ActionPerformed

    private void per3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_per3ActionPerformed
        contador3++;
    }//GEN-LAST:event_per3ActionPerformed

    private void per4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_per4ActionPerformed
        contador4++;
    }//GEN-LAST:event_per4ActionPerformed

    private void graficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graficarActionPerformed
        DefaultPieDataset pastel1 = new DefaultPieDataset();
        pastel1.setValue("per1", contador1);
        pastel1.setValue("per2", contador2);
        pastel1.setValue("per3", contador3);
        pastel1.setValue("per4", contador4);

        JFreeChart p1 = ChartFactory.createPieChart(
                "La más usada",
                pastel1,
                true,
                true,
                false
        );
        
        ChartPanel gra1 = new ChartPanel(p1);
        gra1.setMouseWheelEnabled(true);
        gra1.setPreferredSize(new Dimension(400,200));

        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(gra1, BorderLayout.NORTH);

        pack();
        repaint();
        
        
        DefaultPieDataset pastel2 = new DefaultPieDataset();
        pastel2.setValue("PCi1", mapa.get("PCi1"));
        pastel2.setValue("PCi2", mapa.get("PCi2"));
        pastel2.setValue("PCu1", mapa.get("PCu1"));
        pastel2.setValue("PCu2", mapa.get("PCu2"));

        JFreeChart p2 = ChartFactory.createPieChart(
                "La más usada",
                pastel2,
                true,
                true,
                false
        );
        
        ChartPanel gra2 = new ChartPanel(p2);
        gra2.setMouseWheelEnabled(true);
        gra2.setPreferredSize(new Dimension(400,200));

        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(gra2, BorderLayout.SOUTH);

        pack();
        repaint();
    }//GEN-LAST:event_graficarActionPerformed
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frm_pestañas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_pestañas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_pestañas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_pestañas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_pestañas().setVisible(true);
            }
        });
    }

    private void SetImageLabel(JLabel labelName, String root){
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(),labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
        this.repaint();
    }
    
    private void SetImageButton(JButton buttonName, String root){
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(buttonName.getWidth(),buttonName.getHeight(), Image.SCALE_DEFAULT));
        buttonName.setIcon(icon);
        this.repaint();
    }
    
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton catalogo;
    private javax.swing.JButton graficar;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton per1;
    private javax.swing.JButton per2;
    private javax.swing.JButton per3;
    private javax.swing.JButton per4;
    // End of variables declaration//GEN-END:variables
}
