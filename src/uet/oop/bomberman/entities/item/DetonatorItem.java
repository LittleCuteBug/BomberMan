package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class DetonatorItem extends Item {
    private static final Image img = Sprite.powerup_detonator.getFxImage();

    public DetonatorItem(int x, int y, Game game) {
        super(x, y, img, game);
    }

    @Override
    protected void itemUsed() {

    }
}
