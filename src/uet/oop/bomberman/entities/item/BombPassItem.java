package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class BombPassItem extends Item {
    public BombPassItem(int x, int y, Game game) {
        super(x, y, Sprite.powerup_bombpass.getFxImage(),game);
    }

    @Override
    protected void itemUsed() {
        game.getBomber().setBombPassUsed(true);
    }
}
