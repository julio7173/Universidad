import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

public class ejemplo extends ContenedorAbstracto {
    private Figura textoActual;
    public static void main(String[] args) {
        Contenedor gc = new Contenedor(new ejemplo(), 700, 500, 2, 20);
        gc.addFigura("jhon", new Cuadrado(60, 67, 50, Color.CYAN, 1, "jhon").setAnimacion(Animacion.DVD));
        ArrayList<Figura> figuras = new ArrayList<>();
        figuras.add(new Triangulo(50, 50, 50, Color.ORANGE, 1));
        figuras.add(new Cuadrado(50, 50, 100, Color.red, 1, "jhon1"));
        gc.addFigura(new FiguraCompuesta(figuras).setAnimacion(Animacion.DVD).setAnimable(true));
        gc.addFigura(new Triangulo(50, 68, 128, Color.ORANGE, 1).setAnimacion(Animacion.DVD).setAnimable(true));
        gc.start();
        gc.mostrarTodo();
    }
    @Override
    public void update(Contenedor gc, float dt) {
        if (gc.getInput().isButtonDown(MouseEvent.BUTTON3)) {
            //System.out.println(gc.getInput().getMouseX());
            for (Map.Entry<String, Figura> figura :gc.getFigurash().entrySet()) {
                String key = figura.getKey();
                Figura value = figura.getValue();
                if ((gc.getInput().getMouseX() >= value.getX()) && (gc.getInput().getMouseX() <= (value.getX()+value.getAncho()))
                    &&(gc.getInput().getMouseY() >= value.getY()) && (gc.getInput().getMouseY() <= (value.getY()+value.getAltura()))) {
                    if (textoActual != null) {
                        if (textoActual.isVisible()) {
                            textoActual.setVisible(false);
                        } else {
                            textoActual.setVisible(true);
                        }
                    } else {
                        textoActual = new Texto("pruebaaa", gc.getInput().getMouseX(), gc.getInput().getMouseY()).setAnimacion(Animacion.FOLLOW_MOUSE).setAnimable(true);
                        gc.addFigura(textoActual);
                    }
                    value.setAnimable(!value.animable);
                    System.out.println("Key: " + figura.getKey());
                }
                System.out.println("x: " +value.getX() + " y " + value.getY());

            }
        }
    }

    @Override
    public void render(Contenedor gc, Renderizador r) {
    }

}
