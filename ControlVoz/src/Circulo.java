import java.awt.*;

public class Circulo extends Figura {
    public Circulo(int radio, int x, int y, Color color, int zIndex) {
        visible = true;
        this.radio = radio;
        this.color = color;
        this.X = x;
        this.Y = y;
        this.zIndex = zIndex;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillOval(X-radio, Y-radio, radio*2, radio*2);
    }
    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public int getRadio() {
        return radio;
    }

    @Override
    public int getAncho() {
        return radio*2;
    }

    @Override
    public int getAltura() {
        return radio*2;
    }
}
