import javax.swing.*;
import java.awt.*;

public class prueba extends JPanel {
    private int x = 0;
    private int y = 0;
    private int destinoX;
    private int destinoY;
    private int deltaX;
    private int deltaY;
    private int tiempo;
    private boolean animando;
    private JTextField textField;
    public prueba() {
        animando = false;
        this.deltaX = 1;
        this.deltaY = 1;
        setLayout(new BorderLayout());
        textField = new JTextField();
        add(textField, BorderLayout.NORTH);
    }
    public void MovimientoAnimado(int destinoX, int destinoY, int tiempo) {
        this.destinoX = destinoX;
        this.destinoY = destinoY;
        this.x = getWidth()/2;
        this.y = getHeight()/2;
        this.destinoX = destinoX;
        this.destinoY = destinoY;
        this.tiempo = tiempo;
    }

    private void mover() {
        if (x != destinoX || y != destinoY) {
            if (x < destinoX) {
                x++;
            } else if (x > destinoX) {
                x--;
            }

            if (y < destinoY) {
                y++;
            } else if (y > destinoY) {
                y--;
            }
            repaint();
        }
    }
    public void moveTriangle() {
        x += deltaX;
        y += deltaY;

        // Detectar colisiones con los bordes del panel
        if (x <= 0 || x + 50 >= getWidth()) {
            deltaX *= -1;
        }

        if (y <= 0 || y + 50 >= getHeight()) {
            deltaY *= -1;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar el triángulo en la posición actual
        g.setColor(Color.RED);
        g.fillPolygon(new int[]{x, x + 25, x + 50}, new int[]{y + 50, y, y + 50}, 3);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Triángulo DVD Animation");
        prueba triangle = new prueba();
        triangle.MovimientoAnimado(triangle.x +200, triangle.y+200, 10);
        frame.add(triangle);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            triangle.mover();
            triangle.repaint();
            try {
                Thread.sleep(30); // Retraso para una animación suave
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
