package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {
    public BombItem(int x, int y) {
        super(x, y, Sprite.powerup_bombs.getFxImage());
    }

    public void update() {
        // Kích hoạt thì tăng bomb.Boml
    }
}
