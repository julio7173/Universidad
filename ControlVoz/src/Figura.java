import java.awt.*;

public abstract class Figura implements Comparable<Figura>{
    protected Color color;
    protected int X;
    protected int Y;
    protected int zIndex;
    protected int radio;
    protected int ancho;
    protected int altura;
    protected int lado;
    protected boolean visible;
    protected Animacion animacion;
    protected boolean animable;
    protected String identificador;
    protected int velocidadX = 2;
    protected int velocidadY = 2;


    public Figura setAnimable(boolean animable) {
        this.animable = animable;
        return this;
    }
    public String getIdentificador() {
        return identificador;
    }

    public boolean isAnimable() {
        return animable;
    }

    protected int puntosX[];
    protected int puntosY[];

    public Figura setAnimacion(Animacion animacion) {
        this.animacion = animacion;
        return this;
    }
    public void animar(Contenedor contenedor) {
        if (animacion != null) {
            animacion.animar(this, contenedor);
        }
    }
    public abstract void dibujar(Graphics g);
    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    };

    public int getzIndex() {
        return zIndex;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getRadio() {
        throw new Error("Solo el circulo tiene radio");
    }

    public int getAncho() {
        throw new Error("Solo un rectangulo tiene ancho");
    }

    public int getAltura() {
        throw new Error("Solo un rectangulo tiene altura");
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(int velocidadX) {
        this.velocidadX = velocidadX;
    }

    public int getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(int velocidadY) {
        this.velocidadY = velocidadY;
    }

    public int getLado() {
        throw new Error("Solo un triangulo tiene lado");
    }

    @Override
    public int compareTo(Figura figura) {
        return Integer.compare(this.zIndex, figura.getzIndex());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

