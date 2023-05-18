import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Imagen extends Rectangulo{
    private BufferedImage imagen;
    public Imagen(String ruta, int x, int y, int zIndex) {
        super(0, 0, x, y, Color.black, zIndex);
        imagen = null;
        try {
            imagen = ImageIO.read(new File(ruta));
            super.setAltura(imagen.getHeight());
            super.setAncho(imagen.getWidth());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Imagen(BufferedImage imagen, int px, int py, int pt) {
        super(0, 0, px, py, Color.black, pt);
        this.imagen = imagen;
        super.setAltura(imagen.getHeight());
        super.setAncho(imagen.getWidth());
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(imagen, X, Y, this.ancho, this.altura,  null);
    }
    public Imagen resize(int x, int y) {
        super.setAncho(x);
        super.setAltura(y);
        return this;
    }
}
