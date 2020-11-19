package uet.oop.bomberman.entities.item;

import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item {
    public FlameItem(int x, int y) {
        super(x, y, Sprite.powerup_flames.getFxImage());
    }

    public void update() {
        // Ăn được thì kích hoạt tăng bomb.Flame += 1.
    }
}
