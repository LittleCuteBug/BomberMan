package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item {
    public FlameItem(int x, int y, Game game) {
        super(x, y, Sprite.powerup_flames.getFxImage(), game);
    }

    @Override
    protected void itemUsed() {

    }
}
