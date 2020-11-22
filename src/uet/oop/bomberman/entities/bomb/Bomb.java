package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Check;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.movingObject.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private static final Image[] spriteBomb = {Sprite.bomb.getFxImage(), Sprite.bomb_1.getFxImage(), Sprite.bomb_2.getFxImage()};
    private long placeTime;
    private static final long timeCountDown = 3000;
    private Game game;
    private int bombLength;
    private MovingEntity owner;
    private boolean byPass = false;

    public Bomb(int x, int y, MovingEntity owner, Game game) {
        super(x, y, Bomb.spriteBomb[0]);
        this.placeTime = System.currentTimeMillis();
        this.owner = owner;
        this.game = game;
        this.bombLength = owner.getBombLength();
        owner.increaseBombCnt();

    }
    private void updateImage(){
        imgStage = (int)(System.currentTimeMillis()-placeTime)/80%3;
        img = Bomb.spriteBomb[imgStage];
    }

    private void explore(){
        if (placeTime + timeCountDown < System.currentTimeMillis()) {
            owner.decreaseBombCnt();
            long flamePlaceTime = placeTime+timeCountDown;
            game.getFlame().add(new Flame((int) x, (int) y,flamePlaceTime, Direction.CENTER));
            game.getFlame().add(new Flame((int) x + 1, (int) y,flamePlaceTime, Direction.RIGHT));
            game.getFlame().add(new Flame((int) x - 1, (int) y,flamePlaceTime, Direction.LEFT));
            game.getFlame().add(new Flame((int) x, (int) y - 1,flamePlaceTime, Direction.UP));
            game.getFlame().add(new Flame((int) x, (int) y + 1,flamePlaceTime, Direction.DOWN));
            this.remove();
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
        if(!isRemoved())
            explore();
    }
}
