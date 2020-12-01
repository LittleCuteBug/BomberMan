package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item {
    private static final Image img = Sprite.powerup_flames.getFxImage();

    public FlameItem(int x, int y, Game game) {
        super(x, y, img, game);
    }

    @Override
    protected void itemUsed() {
        game.getBomber().increaseBombLength();
//        System.out.println("done flame item");
    }
}
