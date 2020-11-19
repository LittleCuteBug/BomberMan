package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Entity {
    public Portal (int x, int y)
    {
        super(x,y, Sprite.portal.getFxImage());
    }


    @Override
    public void update() {

    }
}
