package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class ItemBrick extends Brick {
    private static final Image itemBrick = Sprite.powerup_brick.getFxImage();

    public ItemBrick(int x, int y) {
        super(x, y);
        this.img = itemBrick;
    }
}
