package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {
    public SpeedItem(int x, int y, Game game) {
        super(x, y, Sprite.powerup_speed.getFxImage(),game);
    }

    @Override
    protected void itemUsed() {

    }
}
