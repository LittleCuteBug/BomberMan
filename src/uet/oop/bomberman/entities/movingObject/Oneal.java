package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends MovingEntity {
    public Oneal(double x, double y) {
        super(x, y, Sprite.oneal_right1.getFxImage(), 0.5, 0, 0, 0);
    }

    public void update() {
        // dùng thuật toán gì đó để đưa Oneal đến với Bomber
        // cần dùng vị trí của Bomber?
    }
}
