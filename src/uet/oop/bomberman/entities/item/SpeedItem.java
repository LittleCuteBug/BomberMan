package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {
    public SpeedItem(int x, int y) {
        super(x, y, Sprite.powerup_speed.getFxImage());
    }

    public void update() {
        // Ăn được thì tăng speed của Bomber gấp đôi.
    }
}
