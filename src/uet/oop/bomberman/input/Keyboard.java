package uet.oop.bomberman.input;
import javafx.scene.input.KeyEvent;
public class Keyboard {
    public static boolean up, down, left, right, space;

    public static void keyPressed(KeyEvent e) {
        switch (e.getCode())
        {
            case UP:
                up = true;
                break;
            case DOWN:
                down = true;
                break;
            case LEFT:
                left = true;
                break;
            case RIGHT:
                right = true;
                break;
            case SPACE:
                space = true;
                break;
        }
    }

    public static void keyReleased(KeyEvent e) {
        switch (e.getCode()) {
          case UP:
            up = false;
            break;
          case DOWN:
            down = false;
            break;
          case LEFT:
            left = false;
            break;
          case RIGHT:
            right = false;
            break;
          case SPACE:
            space = false;
            break;
        }
    }
}
