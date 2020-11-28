package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class FlamePassItem extends Item {
    public FlamePassItem(int x, int y, Game game) {
        super(x, y, Sprite.powerup_flamepass.getFxImage(),game);
    }

    @Override
    protected void itemUsed() {

    }
}
