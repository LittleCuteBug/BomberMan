package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObject.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private int countDown;
    private MovingEntity owner;

    public Bomb(int x, int y, int countDown, MovingEntity owner) {
        super(x, y, Sprite.bomb.getFxImage());
        this.countDown = countDown;
        this.owner = owner;
    }

    public void update() {
        // Khi nào Bomber đặt thì sẽ có 1 bom đặt xuống ở grass
        // Khi nào Bomb nổ thì Flame toả ra 4 hướng.
        // Khi chưa ăn BombItem thì bomb nổ mới được đặt tiếp (check countDown).
        // Ăn bombItem thì bomb nổ rồi trong thời gian countDown > 0 vẫn cho đặt max 1 bomb nữa.
    }
}
