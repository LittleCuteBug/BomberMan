package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {

    public Wall(double x, double y) {
        super(x, y, Sprite.wall.getFxImage());
    }

    @Override
    public void update() {

    }
}
