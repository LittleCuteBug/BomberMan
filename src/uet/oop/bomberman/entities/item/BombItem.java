package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {
    public BombItem(int x, int y, Game game) {
        super(x, y, Sprite.powerup_bombs.getFxImage(),game);
    }

    @Override
    protected void itemUsed() {
        game.getBomber().increaseBombMax();
    }
}
