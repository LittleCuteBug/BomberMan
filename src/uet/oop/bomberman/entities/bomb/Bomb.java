package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Check;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObject.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private long placeTime;
    private static final long timeCountDown = 5000;
    private Game game;
    private int bombLength;
    private MovingEntity owner;
    private boolean byPass = false;

    public Bomb(int x, int y, MovingEntity owner, Game game) {
        super(x, y, Sprite.bomb.getFxImage());
        this.placeTime = System.currentTimeMillis();
        this.owner = owner;
        this.game = game;
        this.bombLength = 2;
        owner.increaseBombCnt();
    }
    private void updateImage(){

    }
    private void explore(){
        if (placeTime + timeCountDown < System.currentTimeMillis()) {
            owner.decreaseBombCnt();
            game.getFlame().add(new Flame((int) x, (int) y, 0));
            isRemoved = true;
        }
    }

    public boolean isByPass() {
        return byPass;
    }

    public MovingEntity getOwner() {
        return owner;
    }

    public void update() {
        if (!Check.touchCheck(x,y,owner))
            byPass = true;
        updateImage();
        if(!isRemoved)
            explore();
        // Khi nào Bomber đặt thì sẽ có 1 bom đặt xuống ở grass
        // Khi nào Bomb nổ thì Flame toả ra 4 hướng.
        // Khi chưa ăn BombItem thì bomb nổ mới được đặt tiếp (check countDown).
        // Ăn bombItem thì bomb nổ rồi trong thời gian countDown > 0 vẫn cho đặt max 1 bomb nữa.
    }
}
