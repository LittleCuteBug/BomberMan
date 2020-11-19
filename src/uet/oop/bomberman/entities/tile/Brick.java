package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    public Brick(int x, int y) {
        super(x, y, Sprite.brick.getFxImage());
    }

    public void update() {}
}
