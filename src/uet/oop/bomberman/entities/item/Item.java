package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.sounds.Sound;

import static uet.oop.bomberman.entities.Check.overlapCheck;

public abstract class Item extends Entity {
    protected final Game game;
    public Item(int x, int y, Image img, Game game) {
        super (x, y, img);
        this.game = game;
    }

    protected abstract void itemUsed();

    public void update() {
        if (overlapCheck(x, y, game.getBomber())) {
            itemUsed();
            Sound.ITEM_USED_SOUND.play();
            this.remove();
        }

    }
}
