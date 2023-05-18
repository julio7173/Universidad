import java.awt.*;

public class Triangulo extends Figura {
    public Triangulo(int lado, int x, int y, Color color, int zIndex) {
        this.X = x;
        this.Y = y;
        this.lado = lado;
        visible = true;
        puntosY = new int[3];
        puntosX = new int[3];
        puntosX[0] = X;  // vértice inferior
        puntosY[0] = Y;
        puntosX[1] = X - (lado / 2);  // vértice izquierdo
        puntosY[1] = Y + lado;
        puntosX[2] = X + (lado / 2);  // vértice derecho
        puntosY[2] = Y + lado;
        this.color = color;
        this.zIndex = zIndex;
    }

    @Override
    public void dibujar(Graphics g) {
        puntosX[0] = X;  // vértice inferior
        puntosY[0] = Y;
        puntosX[1] = X - (lado / 2);  // vértice izquierdo
        puntosY[1] = Y + lado;
        puntosX[2] = X + (lado / 2);  // vértice derecho
        puntosY[2] = Y + lado;
        Polygon polygon = new Polygon(puntosX, puntosY, puntosX.length);
        g.setColor(color);
        g.fillPolygon(polygon);
    }
    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public int getLado() {
        return lado;
    }

    @Override
    public int getAncho() {
        return lado;
    }

    @Override
    public int getAltura() {
        return lado;
    }

    @Override
    public void setX(int x) {
        this.X = x;
    }

    @Override
    public void setY(int y) {
        this.Y = y;
    }
}
