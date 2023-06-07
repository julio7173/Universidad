import java.awt.*;

public class Texto extends Figura{
    private String texto;
    private Color background;
    @Override
    public void dibujar(Graphics g) {
        g.setColor(this.background);

        int rectangleWidth = calculateRectangleWidth(g, this.texto);
        int rectangleHeight = calculateRectangleHeight(g, this.texto);
        this.altura = rectangleHeight;
        this.ancho = rectangleWidth;
        int x = this.X;
        int y = this.Y;

        g.fillRect(x, y, rectangleWidth, rectangleHeight);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(this.texto);
        int textHeight = fontMetrics.getHeight();

        g.drawString(this.texto, x + (rectangleWidth - textWidth) / 2, y + (rectangleHeight  - textHeight) / 2 + fontMetrics.getAscent());
    }

    public Texto(String texto, int x, int y) {
        this.X = x;
        this.Y = y;
        this.texto = texto;
        this.visible = true;
        this.color = Color.black;
        this.background = Color.white;
        this.altura = 0;
        this.ancho = 0;
        this.zIndex = Integer.MAX_VALUE;
    }
    public void invertir() {
        Color copiaColor = this.color;
        this.color = this.background;
        this.background = copiaColor;
    }
    private int calculateRectangleWidth(Graphics g, String text) {
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        int rectangleWidth = textWidth;
        return rectangleWidth;
    }
    private int calculateRectangleHeight(Graphics g, String text) {
        FontMetrics fontMetrics = g.getFontMetrics();
        int textHeight = fontMetrics.getHeight();
        int rectangleHeight = textHeight;
        return rectangleHeight;
    }
}