import java.awt.*;

public class Rectangulo extends Figura {
    public Rectangulo(int altura, int ancho, int x, int y, Color color, int zIndex) {
        visible = true;
        this.altura = altura;
        this.ancho = ancho;
        this.X = x;
        this.Y = y;
        this.color = color;
        this.zIndex = zIndex;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillRect(X, Y, ancho, altura);
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
