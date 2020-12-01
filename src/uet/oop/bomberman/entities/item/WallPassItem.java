package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class WallPassItem extends Item {
    public WallPassItem(int x, int y, Game game) {
        super(x, y, Sprite.powerup_wallpass.getFxImage(),game);
    }

    @Override
    protected void itemUsed() {
        game.getBomber().setWallPassUsed(true);
    }
}
