package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    private int direction;

    public Flame(int x, int y, int direction) {
        super(x, y, Sprite.bomb_exploded.getFxImage());
        this.direction = direction;
    }

    public void update() {
        // Nhận hiệu lệnh đếm ngược của Bomb (countDown)
        // Khi nổ thì sẽ toả ra explosion_horizontal_left, right, explosion_vertical_top, down.
        // Khi ăn được Flame item thì toả ra theo dấu cộng (5 - 5), còn không thì (3 - 3)
    }
}
