package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class DetonatorItem extends Item {
    public DetonatorItem(int x, int y, Game game) {
        super(x, y, Sprite.powerup_detonator.getFxImage(),game);
    }

    @Override
    protected void itemUsed() {

    }
}
