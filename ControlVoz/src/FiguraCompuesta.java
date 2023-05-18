import java.awt.*;
import java.util.ArrayList;

public class FiguraCompuesta extends Figura{
    ArrayList<Figura> figuras;

    public FiguraCompuesta(ArrayList<Figura> figuras) {
        this.visible = true;
        int totalX = 0;
        int totalY = 0;

        // Sumar las coordenadas x e y de cada figura
        for (Figura figura : figuras) {
            totalX += figura.getX();
            totalY += figura.getY();
        }
        this.X = totalX/figuras.size();
        this.Y = totalY/figuras.size();
        this.figuras = figuras;
    }

    @Override
    public void dibujar(Graphics g) {
        for (Figura figura: figuras) {
            figura.dibujar(g);
        }
    }

    @Override
    public void setX(int x) {
        int vieja = this.X;
        int distancia = Math.abs(vieja - x);
        this.X = x;
        for (Figura figura: figuras) {
            if (x > vieja) {
                figura.setX(figura.getX()+distancia);
            } else if (x< vieja) {
                figura.setX(figura.getX()-distancia);
            }
        }
    }

    @Override
    public void setY(int y) {
        int vieja = this.Y;
        int distancia = Math.abs(vieja - y);
        this.Y = y;
        for (Figura figura: figuras) {
            if (y > vieja) {
                figura.setY(figura.getY()+distancia);
            } else if (y< vieja) {
                figura.setY(figura.getY()-distancia);
            }
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        for (Figura figura: figuras) {
            figura.setVisible(visible);
        }
    }

    @Override
    public Figura setAnimable(boolean animable) {
        super.setAnimable(animable);
        for (Figura figura: figuras) {
            figura.setVisible(animable);
        }
        return this;
    }

    @Override
    public int getAncho() {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (Figura figura : figuras) {
            int x = figura.getX();
            int ancho = figura.getAncho();
            if (x < minX) {
                minX = x;
            }
            if (x + ancho > maxX) {
                maxX = x + ancho;
            }
        }
        int anchoTotal = maxX - minX;
        return anchoTotal;
    }

    @Override
    public int getAltura() {
        int maxY = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        for (Figura figura : figuras) {
            int altura = figura.getAltura();
            int y = figura.getY();
            int suma = altura + y;
            if (suma > maxY) {
                maxY = suma;
            }
            if (y < minY) {
                minY = y;
            }
        }
        return maxY - minY;
    }

}
