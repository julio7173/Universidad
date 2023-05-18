import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener {
    private Contenedor gc;

    private final int numKeys = 256;
    private boolean keys[] = new boolean[numKeys];
    private boolean keysLast[] = new boolean[numKeys];

    private final int numButtons = 5;
    private boolean buttons[] = new boolean[numButtons];
    private boolean buttonsLast[] = new boolean[numButtons];

    private int mouseX, mouseY;

    public Input(Contenedor gc) {
        this.gc = gc;
        mouseX = 0;
        mouseY = 0;

        gc.getVentana().getCanvas().addKeyListener(this);
        gc.getVentana().getCanvas().addMouseListener(this);
        gc.getVentana().getCanvas().addMouseMotionListener(this);
    }

    public void update() {
        for (int i = 0; i < numKeys; i++) {
            keysLast[i] = keys[i];
        }
        for (int i = 0; i < numButtons; i++) {
            buttonsLast[i] = buttons[i];
        }
    }

    public boolean isKey(int keyCode) {
        return keys[keyCode];
    }
    public boolean isKeyUp(int keyCode) {
        return !keys[keyCode] && keysLast[keyCode];
    }
    public boolean isKeyDown(int keyCode) {
        return keys[keyCode] && !keysLast[keyCode];
    }

    public boolean isButton(int button) {
        return buttons[button];
    }
    public boolean isButtonUp(int button) {
        return !buttons[button] && buttonsLast[button];
    }
    public boolean isButtonDown(int button) {
        return buttons[button] && !buttonsLast[button];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = (int) (e.getX()/gc.getScale());
        mouseY = (int) (e.getY()/gc.getScale());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int) (e.getX()/gc.getScale());
        mouseY = (int) (e.getY()/gc.getScale());
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
