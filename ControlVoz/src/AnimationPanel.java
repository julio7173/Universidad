import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class AnimationPanel extends JPanel {
    private BufferedImage spriteSheet;
    private int rows;
    private int columns;
    private int currentFrame;
    private int frameWidth;
    private int frameHeight;
    private int startSpriteIndex;
    private int endSpriteIndex;
    private int totalFrames; // Número total de cuadros en la animación
    private int frameCount; // Contador de cuadros reproducidos
    private int animationDuration; // Duración total de la animación en milisegundos
    private long startTime; // Tiempo de inicio de la animación
    private boolean isAnimating; // Indica si la animación está en curso

    public AnimationPanel(BufferedImage spriteSheet, int rows, int columns, int startSpriteIndex, int endSpriteIndex, int duration) {
        this.spriteSheet = spriteSheet;
        this.rows = rows;
        this.columns = columns;
        this.currentFrame = startSpriteIndex-1;
        this.frameWidth = spriteSheet.getWidth() / columns;
        this.frameHeight = spriteSheet.getHeight() / rows;
        this.startSpriteIndex = startSpriteIndex;
        this.endSpriteIndex = endSpriteIndex;
        this.totalFrames = endSpriteIndex - startSpriteIndex + 2;
        this.frameCount = 0;
        this.animationDuration = duration;
        this.isAnimating = false;

        setPreferredSize(new Dimension(500, 500));
    }

    public void startAnimation() {
        if (!isAnimating) {
            isAnimating = true;
            startTime = System.currentTimeMillis();
            Thread animationThread = new Thread(() -> {
                while (frameCount < totalFrames) {
                    nextFrame();
                    try {
                        Thread.sleep(animationDuration / totalFrames);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(System.currentTimeMillis() - startTime);
                isAnimating = false;
            });
            animationThread.start();
        }
    }

    public void nextFrame() {
        currentFrame++;
        if (currentFrame > endSpriteIndex) {
            currentFrame = startSpriteIndex;
        }
        frameCount++;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println(currentFrame);

        int currentRow = currentFrame / columns;
        int currentColumn = currentFrame % columns;
        // Mostramos la parte de la imagen que corresponde al sprite
        g.drawImage(spriteSheet, 0, 0, getWidth(), getHeight(),
                currentColumn * frameWidth, currentRow * frameHeight,
                (currentColumn + 1) * frameWidth, (currentRow + 1) * frameHeight, null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        String rutaSprite = "ControlVoz/imagenes/pikachu.png";

        BufferedImage spriteSheet = null;
        try {
            spriteSheet = ImageIO.read(new File(rutaSprite));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int startSpriteIndex = 1; // Índice de inicio del sprite
        int endSpriteIndex = 61; // Índice de fin del sprite
        int animationDuration = 2000; // Duración total de la animación en milisegundos (ejemplo: 5000 ms = 5 segundos)

        AnimationPanel animationPanel = new AnimationPanel(spriteSheet, 14, 10, startSpriteIndex, endSpriteIndex, animationDuration);
        frame.add(animationPanel);

        frame.setVisible(true);

        // Iniciar la animación cuando el frame sea visible
        animationPanel.startAnimation();
    }
}
