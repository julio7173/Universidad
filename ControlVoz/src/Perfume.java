import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Perfume extends JPanel implements Serializable {
    // BufferedImage no se guardaran al momento de serializar
    private transient BufferedImage image, spriteSheet;
    private Color color;
    private JPanel padre;
    private int clicks;
    private String rutaImagen, rutaSprite;
    private transient Thread animationThread;
    private boolean animando;
    private Dimension originalDimension;
    private String animacion;
    private int duracion;

    private int columnas;
    private int filas;
    private int currentFrame;
    private int spriteAltura;
    private int spriteAncho;
    private int spriteInicio;
    private int spriteFinal;
    private int spriteFrameTotal;
    private int spriteFrameActual;
    private boolean spriteAnimando;

    public Perfume(String forma, Color color, int duracion, String animacion){
        this.rutaSprite = null;
        this.spriteSheet = null;
        this.spriteAnimando = false;
        this.columnas = 0;
        this.filas = 0;
        this.currentFrame = 0;
        this.spriteAltura = 0;
        this.spriteAncho = 0;
        this.spriteInicio = 0;
        this.spriteFinal = 0;
        this.spriteFrameTotal = 0;
        this.spriteFrameActual = 0;

        this.animacion = animacion.toLowerCase();
        this.duracion = duracion;
        this.animando = false;
        this.clicks = 0;
        this.padre = null;
        forma = forma.toLowerCase();
        this.color = color;
        String ruta = "";
        switch (forma) {
            case "cuadrado":
                ruta = "ControlVoz/imagenes/perfumeCuadrado.png";
                break;
            case "circular":
                ruta = "ControlVoz/imagenes/perfumeCircular.png";
                break;
            case "largo":
                ruta = "ControlVoz/imagenes/perfumeLargo.png";
                break;
            default:
                System.out.println("El tipo de perfume no existe");
                break;
        }
        this.rutaImagen = ruta;
        loadImage();
    }

    public void setPadre(JPanel padre) {
        this.padre = padre;
    }

    public void loadImage() {
        // cargamos las imagenes, si no estan cargadas, y si tienen una ruta valida
        if (image == null) {
            if (this.rutaImagen != null && !this.rutaImagen.isEmpty()) {
                // cargamos la imagen en un hilo separado
                String finalRuta = rutaImagen;
                Thread thread = new Thread(() -> {
                    try {
                        File archivo = new File(finalRuta);
                        if (archivo.exists()) {
                            image = ImageIO.read(archivo);
                            repaint();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }
        }
        if (spriteSheet == null) {
            if (this.rutaSprite != null && !this.rutaSprite.isEmpty()) {
                // cargamos el sprite en un hilo
                String finalRuta = rutaSprite;
                Thread thread = new Thread(() -> {
                    try {
                        File archivo = new File(finalRuta);
                        if (archivo.exists()) {
                            spriteSheet = ImageIO.read(archivo);
                            this.spriteAncho = spriteSheet.getWidth() / columnas;
                            this.spriteAltura = spriteSheet.getHeight() / filas;
                            repaint();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }
        }
    }
    public void cargarSprite(String ruta, int columnas, int filas, int spriteInicio, int spriteFinal) {
        this.rutaSprite = ruta;
        this.filas = filas;
        this.columnas = columnas;
        this.currentFrame = spriteInicio;

        this.spriteInicio = spriteInicio;
        this.spriteFinal = spriteFinal;
        this.spriteFrameTotal = spriteFinal - spriteInicio + 1;
        this.spriteFrameActual = 0;
        loadImage();

    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (spriteSheet != null && animando && spriteAnimando) {
            int currentRow = currentFrame / columnas;
            int currentColumn = currentFrame % columnas;
            int canvasSize = Math.min(getWidth(), getHeight());
            g.setColor(padre.getBackground());
            g.fillRect(0,0, getWidth(), getHeight());

            g.drawImage(spriteSheet, (padre.getWidth()/2)-(canvasSize/2), 0, (padre.getWidth()/2)+(canvasSize/2), canvasSize,
                    currentColumn * spriteAncho, currentRow * spriteAltura,
                    (currentColumn + 1) * spriteAncho, (currentRow + 1) * spriteAltura, null);
        } else if (image != null) {

            // Calcula el tamaño y posición para mantener la forma cuadrada
            int canvasSize = Math.min(getWidth(), getHeight());
            int x = (getWidth() - canvasSize) / 2;
            int y = (getHeight() - canvasSize) / 2;
            g.setColor(padre.getBackground());
            g.fillRect(0,0, getWidth(), getHeight());
            g.setColor(color);
            g.fillRect(x, y, canvasSize, canvasSize);
            // Dibuja la imagen en el panel
            g.drawImage(image, x, y, canvasSize, canvasSize, this);
        }

    }
    public static void guardarPerfumes(ArrayList<Perfume> perfumes) {
        try {
            FileOutputStream archivo = new FileOutputStream("ControlVoz/datos/perfumes.txt");
            ObjectOutputStream salida = new ObjectOutputStream(archivo);
            salida.writeObject(perfumes);
            salida.close();
            archivo.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Perfume> leerPerfumes() {
        ArrayList<Perfume> lista = new ArrayList<>();

        try {
            FileInputStream archivo = new FileInputStream("ControlVoz/datos/perfumes.txt");
            ObjectInputStream entrada = new ObjectInputStream(archivo);
            lista = (ArrayList<Perfume>) entrada.readObject();
            entrada.close();
            archivo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    public void play() {

        if (duracion > 0) {
            switch (animacion) {
                case "aparecer":
                    startAnimation(duracion);
                case "desvanecer":
                    System.out.println("des");
                    disappearAnimation(duracion);
                case "secuencia":
                    cargarSprite("ControlVoz/imagenes/pikachu.png", 10, 14, 1, 61);
                    spriteAnimation(duracion);
            }
        }
    }
    public void startAnimation(int animationDuration) {
        if (!animando) {
            originalDimension = new Dimension(getWidth(), getHeight());
            animando = true;

            int totalFrames = animationDuration / 16;
            int targetSize = Math.min(padre.getWidth(), padre.getHeight());
            int centerX = padre.getWidth() / 2;
            int centerY = padre.getHeight() / 2;

            long startTime = System.currentTimeMillis();

            animationThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    long elapsedTime = 0;

                    while (elapsedTime < animationDuration) {
                        elapsedTime = System.currentTimeMillis() - startTime;
                        float progress = (float) elapsedTime / animationDuration;
                        int newSize = (int) (progress * targetSize);
                        int newWidth = newSize;
                        int newHeight = newSize;
                        int startX = centerX - (newWidth / 2);
                        int startY = centerY - (newHeight / 2);

                        setSize(new Dimension(newWidth, newHeight));
                        setLocation(startX, startY);

                        repaint();

                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            animando = false;
                            return;
                        }
                    }

                    setSize(originalDimension);
                    setLocation(0, 0);

                    animando = false;
                    repaint();
                }
            });

            animationThread.start();
        }
    }

    public void disappearAnimation(int animationDuration) {
        if (!animando) {
            originalDimension = new Dimension(getWidth(), getHeight());
            animando = true;

            int totalFrames = animationDuration / 16;
            int targetSize = 0;
            int centerX = padre.getWidth() / 2;
            int centerY = padre.getHeight() / 2;

            long startTime = System.currentTimeMillis();

            animationThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    long elapsedTime = 0;

                    while (elapsedTime < animationDuration) {
                        elapsedTime = System.currentTimeMillis() - startTime;
                        int currentSize = Math.min(getWidth(), getHeight());
                        float progress = (float) (((float) elapsedTime / animationDuration) * 0.25);
                        int newSize = (int) (currentSize - (currentSize * progress));
                        int newWidth = newSize;
                        int newHeight = newSize;
                        int startX = centerX - (newWidth / 2);
                        int startY = centerY - (newHeight / 2);

                        setSize(new Dimension(newWidth, newHeight));
                        setLocation(startX, startY);

                        repaint();

                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt(); // Restaurar la bandera de interrupción
                            animando = false; // Establecer isAnimating a false
                            return;
                        }
                    }

                    // finalizamos la animacion
                    setSize(originalDimension);
                    setLocation(0, 0);

                    animando = false;
                    repaint();
                }
            });

            animationThread.start();
        }
    }

    public void spriteAnimation(int animationDuration) {
        if (!animando) {
            spriteAnimando = true;
            originalDimension = new Dimension(getWidth(), getHeight());
            animando = true;
            long startTime = System.currentTimeMillis();
            animationThread = new Thread(() -> {
                while (spriteFrameActual < spriteFrameTotal) {
                    nextFrame();
                    try {
                        Thread.sleep(animationDuration / spriteFrameTotal);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        animando = false;
                        spriteAnimando = false;
                        return;
                    }
                }
                System.out.println(System.currentTimeMillis() - startTime);
                animando = false;
                spriteAnimando = false;
                repaint();
            });
            animationThread.start();
        }
    }
    public void nextFrame() {
        currentFrame++;
        if (currentFrame > spriteFinal) {
            currentFrame = spriteInicio;
        }
        spriteFrameActual++;
        repaint();
    }

    public void stopAnimation() {
        if (animationThread != null && animationThread.isAlive()) {
            animationThread.interrupt();
            animationThread = null;
            animando = false;
            setSize(originalDimension);
            setLocation(0, 0);
            repaint();
        }
    }
    public boolean isAnimable() {
        return !animando;
    }
    public void aumentarClicks() {
        clicks++;
    }

    public int getClicks() {
        return clicks;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setAnimacion(String animacion) {
        this.animacion = animacion.toLowerCase();
    }
}