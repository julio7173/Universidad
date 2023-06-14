import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class ListaScrollablePanel extends JPanel {

    private JPanel contentPane;
    public JScrollPane scrollPane;
    private JPanel canvasPanel;

    public ListaScrollablePanel() {
        setLayout(new BorderLayout());

        canvasPanel = new JPanel();
        canvasPanel.setLayout(new GridBagLayout());
        canvasPanel.setBackground(new Color(21, 25, 29));


        scrollPane = new JScrollPane(canvasPanel);
        canvasPanel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {

            }
        });
        add(scrollPane, BorderLayout.CENTER);
        List<Figura> perfumes = Figura.cargarLista("./perfumes.txt");
        System.out.println(perfumes.size());
        for (int i = 0; i < perfumes.size(); i++) {
            Imagenes canvas = new Imagenes();
            canvas.setPreferredSize(new Dimension(250, 250));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = (i % 3) + 1; // selecionar columna
            gbc.gridy = i / 3; // selecionar fila
            gbc.weightx = 1.0; // espacio horizontal
            gbc.weighty = 1.0; // espacio vertical
            gbc.fill = GridBagConstraints.CENTER;
            gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre los elementos
            canvasPanel.add(canvas, gbc);
        }

    }

    private class Imagenes extends JPanel {
        private Color color;

        public Imagenes() {
            color = getRandomColor();

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    color = getRandomColor();
                    repaint();
                }
            });
        }

        private Color getRandomColor() {
            int r = (int) (Math.random() * 256);
            int g = (int) (Math.random() * 256);
            int b = (int) (Math.random() * 256);
            return new Color(r, g, b);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int size = Math.min(getWidth(), getHeight());

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            g2d.fillRect(0, 0, size, size);
        }
    }

    private class CustomScrollBar extends JScrollBar {

        public CustomScrollBar(int orientation) {
            super(orientation);
            setUI(new CustomScrollBarUI());
        }

        private class CustomScrollBarUI extends BasicScrollBarUI {

            @Override
            public Dimension getPreferredSize(JComponent c) {
                if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                    return new Dimension(10, super.getPreferredSize(c).height);
                } else {
                    return new Dimension(super.getPreferredSize(c).width, 10);
                }
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createEmptyButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createEmptyButton();
            }

            private JButton createEmptyButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        }
    }
}
