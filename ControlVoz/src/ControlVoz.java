import javax.imageio.ImageIO;
import javax.speech.*;
import javax.speech.recognition.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControlVoz extends ResultAdapter {
    private HashMap<String, String> numeros;
    private HashMap<String, Color> colores;

    private HashMap<String, Figura> figuras;
    Recognizer rec;
    Contenedor gc;
    private String pedido = "";
    private int tiempo;

    public ControlVoz(Recognizer rec, Contenedor gc, int delay) {
        this.tiempo = tiempo;
        tablaNumeros();
        tablaColores();

        this.rec = rec;
        this.gc = gc;
    }

    // Receives RESULT_ACCEPTED event: print it, clean up, exit
    public void resultAccepted(ResultEvent e) {
        Result r = (Result)(e.getSource());

        ResultToken tokens[] = r.getBestTokens();

        for (int i = 0; i < tokens.length; i++) {
            String palabra = tokens[i].getSpokenText();
            System.out.print(palabra);
            if (palabra.contains("exit")) {
                try {
                    rec.deallocate();
                } catch (EngineException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }

            if (palabra.contains("hide")){
                gc.ocultarTodo();
                break;
            }
            if (palabra.contains("mostrar")){
                gc.mostrarTodo();
                break;
            }
            if (palabra.contains("animar")){
                gc.animarTodos();
                break;
            }
            if (palabra.contains("detener")){
                gc.noAnimarTodos();
                break;
            }
            if (gc.getFigurash().get(palabra) != null) {
                gc.addFigura(gc.getFigurash().get(palabra));
                break;
            }
            if (pedido.equals("")) {
                verificarTokens();
            }
            if (numeros.get(palabra) != null) {
                pedido += numeros.get(palabra) + " ";
                System.out.print(numeros.get(palabra));
            } else {
                pedido += palabra + " ";
            }
        }
        System.out.println();
    }
    public void verificarTokens() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int pt = 50;
                int px = gc.getWidth()/2, py= gc.getHeight()/2;
                int pz = 1;
                int altura = 50;
                int ancho = 75;
                Animacion ani = null;
                Color color = Color.white;
                String nuevax = findX(pedido);
                String nuevay = findY(pedido);
                String nuevat = findTamaño(pedido);
                String nuevaAltura = findAltura(pedido);
                String nuevoAncho  = findAncho(pedido);

                String[] palabras = pedido.split("\\s+");
                for (int i = 0; i < palabras.length; i++) {
                    if (colores.get(palabras[i]) != null) {
                        color = colores.get(palabras[i]);
                        break;
                    }
                }
                for (int i = 0; i < palabras.length; i++) {
                    if (gc.getAnimaciones().get(palabras[i]) != null) {
                        ani = gc.getAnimaciones().get(palabras[i]);
                        break;
                    }
                }

                if (!nuevax.equals("")) {
                    px = Integer.parseInt(nuevax);
                }
                if (!nuevay.equals("")) {
                    py = Integer.parseInt(nuevay);
                }
                if (!nuevat.equals("")) {
                    int num = Integer.parseInt(nuevat);
                    if (num > 0) {
                        pt = num;
                    }
                }
                if (!nuevaAltura.equals("")) {
                    altura = Integer.parseInt(nuevaAltura);
                }
                if (!nuevoAncho.equals("")) {
                    ancho = Integer.parseInt(nuevoAncho);
                }
                System.out.println("Contenido de los tokens: " + pedido);
                pedido = pedido.toLowerCase();
                if (pedido.contains("add") || pedido.contains("agregar")) {
                    if(pedido.contains("cuadrado")) {
                        gc.addFigura(new Cuadrado(pt, px, py, color, pz).setAnimacion(ani));
                    }
                    if(pedido.contains("triangulo")) {
                        gc.addFigura(new Triangulo(pt, px, py, color, pz).setAnimacion(ani));
                    }
                    if(pedido.contains("circulo")) {
                        gc.addFigura(new Circulo(pt, px, py, color, pz).setAnimacion(ani));
                    }
                    if(pedido.contains("rectangulo")) {
                        gc.addFigura(new Rectangulo(altura, ancho, px, py, color, pz).setAnimacion(ani));
                    }
                    /*if(pedido.contains("poligono")) {
                        int[] xPoints = {100, 200, 300, 250, 150};  // coordenadas X de los puntos del polígono
                        int[] yPoints = {100, 150, 100, 50, 50};     // coordenadas Y de los puntos del polígono
                        gc.addFigura(new Poligono(xPoints, yPoints, px, py, color, pz).setAnimacion(ani));
                    }*/
                    if(pedido.contains("imagen")) {
                        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("train.png");
                        BufferedImage imagen = null;
                        try {
                            imagen = ImageIO.read(inputStream);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        gc.addFigura(new Imagen(imagen, px, py, pt).resize(125, 125).setAnimacion(ani));
                    }
                }
                pedido = "";
            }
        }, tiempo*1000);
    }
    private String findX(String texto) {
        String resx = "";
        String regex = ".*x\\s+(-?\\s?(\\d+\\s+)+).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        if (matcher.matches()) {
            String numero = matcher.group(1).replaceAll("\\s+", "");
            resx = numero;
        }
        return resx;
    }
    private String findY(String texto) {
        String resy = "";
        String regex = ".*y\\s+(-?\\s?(\\d+\\s+)+).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        if (matcher.matches()) {
            String numero = matcher.group(1).replaceAll("\\s+", "");
            resy = numero;
        }
        return resy;
    }
    private String findTamaño(String texto) {
        String res = "";
        String regex = ".*tamaño\\s+((\\d+\\s+)+).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        if (matcher.matches()) {
            String numero = matcher.group(1).replaceAll("\\s+", "");
            res = numero;
        }
        return res;
    }
    private String findAncho(String texto) {
        String res = "";
        String regex = ".*ancho\\s+((\\d+\\s+)+).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        if (matcher.matches()) {
            String numero = matcher.group(1).replaceAll("\\s+", "");
            res = numero;
        }
        return res;
    }
    private String findAltura(String texto) {
        String res = "";
        String regex = ".*altura\\s+((\\d+\\s+)+).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        if (matcher.matches()) {
            String numero = matcher.group(1).replaceAll("\\s+", "");
            res = numero;
        }
        return res;
    }
    private void tablaNumeros() {
        numeros = new HashMap<>();
        numeros.put("cero", "0");
        numeros.put("uno", "1");
        numeros.put("dos", "2");
        numeros.put("tres", "3");
        numeros.put("cuatro", "4");
        numeros.put("cinco", "5");
        numeros.put("seis", "6");
        numeros.put("siete", "7");
        numeros.put("ocho", "8");
        numeros.put("nueve", "9");
    }
    private void tablaColores() {
        colores = new HashMap<>();
        colores.put("rojo", Color.RED);
        colores.put("verde", Color.GREEN);
        colores.put("azul", Color.BLUE);
        colores.put("amarillo", Color.YELLOW);
        colores.put("blanco", Color.WHITE);
        colores.put("negro", Color.BLACK);
        colores.put("cyan", Color.CYAN);
    }


}