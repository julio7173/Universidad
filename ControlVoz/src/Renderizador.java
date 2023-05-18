import java.awt.*;
import java.awt.image.WritableRaster;

public class Renderizador {
    private int pW, pH;
    private int[] p;
    private WritableRaster raster;
    private Graphics2D g;

    public Renderizador(Contenedor gc) {
        pW = gc.getWidth();
        pH = gc.getHeight();
        g = gc.getVentana().getImage().createGraphics();
        raster = gc.getVentana().getImage().getRaster();
        p = new int[raster.getWidth() * raster.getHeight() * raster.getNumBands()];
    }

    public void clear() {
        for (int i = 0; i < p.length; i++) {
            p[i] = 0;
        }
        raster.setPixels(0, 0, raster.getWidth(), raster.getHeight(), p);
    }

    public Graphics2D getG() {
        return g;
    }
}
