package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sounds.Sound;

public class FlamePassItem extends Item {
    private static final Image img = Sprite.powerup_flamepass.getFxImage();
    public FlamePassItem(int x, int y, Game game) {
        super(x, y, img, game);
    }

    @Override
    protected void itemUsed() {
        Sound.INVINCIBILITY_SOUND.play();
        game.getBomber().setFlamePassUsed();
    }
}
