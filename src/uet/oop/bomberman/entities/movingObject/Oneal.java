package uet.oop.bomberman.entities.movingObject;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends MovingEntity {
    public Oneal(double x, double y, Game game) {
        super(x, y, game, Sprite.oneal_right1.getFxImage(), 0.5, 0, 0, 0);
    }

    public void update() {
        // dùng thuật toán gì đó để đưa Oneal đến với Bomber
        // cần dùng vị trí của Bomber?
    }
}
