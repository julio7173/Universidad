import javax.speech.Central;
import javax.speech.EngineModeDesc;
import javax.speech.recognition.Recognizer;
import javax.speech.recognition.RuleGrammar;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Contenedor implements Runnable {
    private Thread thread;
    private Ventana ventana;
    private Renderizador renderizador;
    private Input input;
    private ContenedorAbstracto game;
    private ArrayList<Figura> figuras;

    private boolean running;
    private final double update_cap = 1.0/60.0;
    private int width, height;
    private float scale;
    private String tittle = "Graficos";
    private int delay;
    private HashMap<String, Animacion> animaciones;
    private HashMap<String, Figura> figurash;
    public Contenedor(ContenedorAbstracto game, int width, int height, float scale, int delay) {
        this.width = width;
        this.delay = delay;
        this.height = height;
        this.scale = scale;
        this.game = game;

        this.figuras = new ArrayList<>();
        tablaFiguras();
        tablaAnimaciones();
    }
    public void start() {
        ventana = new Ventana(this);
        renderizador = new Renderizador(this);
        input = new Input(this);
        thread = new Thread(this);
        thread.start();
        escuchar();
        for (Map.Entry<String, Figura> entrada: figurash.entrySet()) {
            String clave = entrada.getKey();
            cargarGramatica(clave);
        }
        for (Map.Entry<String, Animacion> entrada: animaciones.entrySet()) {
            String clave = entrada.getKey();
            cargarGramatica(clave);
        }
    }
    public void stop() {

    }
    public void run() {
        running = true;

        boolean render = false;
        double firstTime = 0;
        double lastTime  = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while (running) {
            render = false;
            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while (unprocessedTime >= update_cap) {
                unprocessedTime -= update_cap;
                render = true;
                //update window
                animarTodo();
                game.update(this, (float) update_cap);
                input.update();
                //contador de fps
                if(frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    //System.out.println("FPS: " + fps);
                }
            }
            if (render) {
                dibujarTodo();
                renderizador.clear();
                game.render(this, renderizador);
                dibujarTodo();
                //render window
                ventana.update();

                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        dispose();
    }
    public void dispose() {

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Ventana getVentana() {
        return ventana;
    }

    public Input getInput() {
        return input;
    }

    public void dibujarTodo() {
        for (Figura figura: figuras) {
            if (figura.isVisible()) {
                figura.dibujar(renderizador.getG());
            }
        }
    }
    public void animarTodo() {
        for (Figura figura: figuras) {
            if (figura.isAnimable() && figura.isVisible()) {
                figura.animar(this);
            }
        }
    }
    public void addFigura(Figura figura) {
        figuras.add(figura);
        Collections.sort(figuras);
    }
    public void addFigura(String name, Figura figura) {
        figurash.put(name, figura);
        addFigura(figura);
    }
    private Recognizer rec;

    public void escuchar() {
        try {
            // configuracion del reconocedor
            rec = Central.createRecognizer(new EngineModeDesc(Locale.ENGLISH));
            File archivo = new File("./gramatica.txt");
            BufferedReader gramatica;
            if (!archivo.exists()) {
                OutputStream outputStream = new FileOutputStream(archivo);
                InputStream copia = getClass().getClassLoader().getResourceAsStream("Gramatica.txt");
                byte[] buffer = new byte[1024];
                int bytesRead;
                // Lee del InputStream y escribe en el OutputStream
                while ((bytesRead = copia.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                // Cierra el InputStream y el OutputStream
                copia.close();
                outputStream.close();
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Gramatica.txt");
                InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                gramatica = new BufferedReader(streamReader);
            } else {
                gramatica = new BufferedReader(new FileReader("./gramatica.txt"));
            }
            rec.allocate();
            RuleGrammar gram = rec.loadJSGF(gramatica);
            gram.setEnabled(true);

            // agregamos un listener al reconocedor
            rec.addResultListener(new ControlVoz(rec, this, delay));
            // inicia el reconocedor

            rec.commitChanges();
            rec.requestFocus();
            rec.resume();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void cargarGramatica(String nuevaFigura){
        try {
            rec.pause();
            File archivo = new File("./gramatica.txt");
            FileWriter fw = new FileWriter(archivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.format("\n\npublic <figuras> = (%s);", nuevaFigura.toLowerCase().replaceAll("\\s+", "")));
            bw.close();
            BufferedReader gramatica = new BufferedReader(new FileReader("./gramatica.txt"));
            rec.loadJSGF(gramatica);
            rec.commitChanges();
            System.out.println("Recargado");
            rec.requestFocus();
            rec.resume();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ocultarTodo() {
        for (Figura figura: figuras) {
            figura.setVisible(false);
        }
    }
    public void mostrarTodo() {
        for (Figura figura: figuras) {
            figura.setVisible(true);
        }
    }
    public void animarTodos() {
        for (Figura figura: figuras) {
            figura.setAnimable(true);
        }
    }
    public void noAnimarTodos() {
        for (Figura figura: figuras) {
            figura.setAnimable(false);
        }
    }
    private void tablaFiguras() {
        figurash = new HashMap<>();
    }
    public void addFiguraCustom(String nombre, Figura figura) {
        figurash.put(nombre, figura);
    }

    public HashMap<String, Figura> getFigurash() {
        return figurash;
    }
    private void tablaAnimaciones() {
        animaciones = new HashMap<>();
        animaciones.put("fly", Animacion.DVD);
    }
    public void addAnimacionCustom(String nombre, Animacion animacion) {
        animaciones.put(nombre, animacion);
    }

    public HashMap<String, Animacion> getAnimaciones() {
        return animaciones;
    }
}
