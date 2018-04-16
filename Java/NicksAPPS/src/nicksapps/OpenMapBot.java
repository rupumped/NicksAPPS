package nicksapps;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OpenMapBot implements KeyListener {
    public static void main(String[] args) {
        while (true) {}
    }
    
    public void keyTyped(KeyEvent e) {
        System.out.println("KEY TYPED: " + e);
    }

    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        System.out.println("KEY PRESSED: " + e);
    }

    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent e) {
        System.out.println("KEY RELEASED: " + e);
    }
}