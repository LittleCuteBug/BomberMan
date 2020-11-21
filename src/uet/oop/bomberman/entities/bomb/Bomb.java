package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Check;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObject.Balloom;
import uet.oop.bomberman.entities.movingObject.Direction;
import uet.oop.bomberman.entities.movingObject.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private static final Image[] spriteBomb = {Sprite.bomb.getFxImage(), Sprite.bomb_1.getFxImage(), Sprite.bomb_2.getFxImage()};
    private long placeTime;
    private static final long timeCountDown = 1000;
    private Game game;
    private int bombLength;
    private MovingEntity owner;
    private boolean byPass = false;

    public Bomb(int x, int y, MovingEntity owner, Game game) {
        super(x, y, Sprite.bomb.getFxImage());
        this.placeTime = System.currentTimeMillis();
        this.owner = owner;
        this.game = game;
        this.bombLength = owner.getBombLength();
        owner.increaseBombCnt();
    }
    private void updateImage(){
        imgStage = imgStage % 3;
        switch (imgStage){
            case 1:
                img = Bomb.spriteBomb[imgStage];
                break;
            case 2:
                img = Bomb.spriteBomb[imgStage];
                break;
            case 3:
                img = Bomb.spriteBomb[imgStage];
                break;
        }
    }

    private void explore(){
        if (placeTime + timeCountDown < System.currentTimeMillis()) {
            owner.decreaseBombCnt();
            game.getFlame().add(new Flame((int) x, (int) y, Direction.CENTER));
            game.getFlame().add(new Flame((int) x + 1, (int) y, Direction.RIGHT));
            game.getFlame().add(new Flame((int) x - 1, (int) y, Direction.LEFT));
            game.getFlame().add(new Flame((int) x, (int) y + 1, Direction.UP));
            game.getFlame().add(new Flame((int) x, (int) y - 1, Direction.DOWN));
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
