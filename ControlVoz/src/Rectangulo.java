import java.awt.*;

public class Rectangulo extends Figura {
    public Rectangulo(int altura, int ancho, int x, int y, Color color, int zIndex, String identificador) {
        visible = true;
        this.altura = altura;
        this.ancho = ancho;
        this.X = x;
        this.Y = y;
        this.color = getRandomColor();
        this.zIndex = zIndex;
        this.identificador = identificador;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    private Color getRandomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }
    @Override
    public void dibujar(Graphics g) {

    }

    @Override
    public int getAncho() {
        return  ancho;
    }
    @Override
    public int getAltura() {
        return  altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
}
