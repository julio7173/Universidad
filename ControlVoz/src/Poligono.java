import java.awt.*;

public class Poligono extends Figura {
    public Poligono(int[] puntosX, int[] puntosY, int x, int y, Color color, int zIndex) {
        if (puntosX.length != puntosY.length) {
            throw new RuntimeException("los dos arrays deben de ser del mismo tamaño");
        }
        this.color = color;
        this.zIndex = zIndex;
        visible = true;
        this.puntosX = puntosX;
        this.puntosY = puntosY;
        this.X = x;
        this.Y = y;
    }

    @Override
    public void dibujar(Graphics g) {
        Polygon polygon = new Polygon(puntosX, puntosY, puntosX.length);
        g.setColor(color);
        g.fillPolygon(polygon);
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

}
