import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Imagen extends Rectangulo{
    private BufferedImage imagen;
    public Imagen(String ruta, int x, int y, int zIndex, String identificador) {
        super(0, 0, x, y, Color.black, zIndex, identificador);
        imagen = null;
        this.identificador = "Imagen"+ new Random().nextInt(100000);
        try {
            imagen = ImageIO.read(new File(ruta));
            super.setAltura(imagen.getHeight());
            super.setAncho(imagen.getWidth());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Imagen(BufferedImage imagen, int px, int py, int pt, String identificador) {
        super(0, 0, px, py, Color.black, pt, identificador);
        this.imagen = imagen;
        super.setAltura(imagen.getHeight());
        super.setAncho(imagen.getWidth());
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(imagen, X, Y, this.ancho, this.altura,  null);
    }
    public Imagen resizes(int x, int y) {
        super.setAncho(x);
        super.setAltura(y);
        return this;
    }
}
