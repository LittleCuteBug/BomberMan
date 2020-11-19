package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends MovingEntity {
    public Balloom(double x, double y) {
        super(x, y, Sprite.balloom_right1.getFxImage(), 0.3, 0, 0,0);
    }

    public void update() {
        // random di chuyển đến khi nào chạm wall hoặc brick thì quay lại?
    }
}
