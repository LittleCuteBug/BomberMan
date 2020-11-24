package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Check;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.movingObject.MovingEntity;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.graphics.Sprite;

import static java.lang.Math.abs;
import static uet.oop.bomberman.entities.Check.touchCheck;

public class Bomb extends Entity {
    private static final Image[] spriteBomb = {Sprite.bomb.getFxImage(), Sprite.bomb_1.getFxImage(), Sprite.bomb_2.getFxImage()};
    private long placeTime;
    private static final long timeCountDown = 3000;
    private Game game;
    private int bombLength;
    private MovingEntity owner;
    private boolean byPass = false;
    private boolean isExplode = false;

    public Bomb(int x, int y, MovingEntity owner, Game game) {
        super(x, y, Bomb.spriteBomb[0]);
        this.placeTime = System.currentTimeMillis();
        this.owner = owner;
        this.game = game;
//        this.bombLength = owner.getBombLength();
        this.bombLength = 2;
        owner.increaseBombCnt();

    }
    private void updateImage(){
        imgStage = (int)(System.currentTimeMillis()-placeTime)/80%3;
        img = Bomb.spriteBomb[imgStage];
    }

    public int flameToLeft(int x, int y) {
        int flameLeft = this.bombLength;

        for (Entity entity : game.getWall()) {
            if (entity.getX() < x && entity.getY() == y) {
                flameLeft = Math.min((int)(Math.abs(x - entity.getX()) - 1), flameLeft);
            }
        }
        for (Entity entity : game.getBrick()) {
            if (entity.getX() < x && entity.getY() == y) {
                flameLeft = Math.min((int)(Math.abs(x - entity.getX())), flameLeft);
            }
        }
        return flameLeft;
    }

    public int flameToRight(int x, int y) {
        int flameRight = this.bombLength;

        for (Entity entity : game.getWall()) {
            if (entity.getX() > x && entity.getY() == y) {
                flameRight = Math.min((int)(Math.abs(x - entity.getX()) - 1), flameRight);
            }
        }
        for (Entity entity : game.getBrick()) {
            if (entity.getX() > x && entity.getY() == y) {
                flameRight = Math.min((int)(Math.abs(x - entity.getX())), flameRight);
            }
        }
        return flameRight;
    }

    public int flameToTop(int x, int y) {
        int flameUp = this.bombLength;

        for (Entity entity : game.getWall()) {
            if (entity.getX() == x && entity.getY() < y) {
                flameUp = Math.min((int)(Math.abs(y - entity.getY()) - 1), flameUp);
            }
        }
        for (Entity entity : game.getBrick()) {
            if (entity.getX() == x && entity.getY() < y) {
                flameUp = Math.min((int)(Math.abs(y - entity.getY())), flameUp);
            }
        }
        return flameUp;
    }

    public int flameToBottom(int x, int y) {
        int flameDown = this.bombLength;

        for (Entity entity : game.getWall()) {
            if (entity.getX() == x && entity.getY() > y) {
                flameDown = Math.min((int)(Math.abs(y - entity.getY()) - 1), flameDown);
            }
        }
        for (Entity entity : game.getBrick()) {
            if (entity.getX() == x && entity.getY() > y) {
                flameDown = Math.min((int)(Math.abs(y - entity.getY())), flameDown);
            }
        }
        return flameDown;
    }

    public void flameOfBomb() {
        long flamePlaceTime = System.currentTimeMillis();
        int _x = (int) x;
        int _y = (int) y;
        game.getFlame().add(new Flame(_x, _y,flamePlaceTime, Direction.CENTER, game));

        int fLeft = flameToLeft(_x, _y);
        if (fLeft >= 1) {
            for (int i = 1; i < fLeft; ++i) {
                game.getFlame().add(new Flame(_x - i, _y, flamePlaceTime, Direction.HORIZONTAL, game));
            }
            game.getFlame().add(new Flame(_x - fLeft, _y, flamePlaceTime, Direction.LEFT, game));
        }

        int fRight = flameToRight(_x, _y);
        if (fRight >= 1) {
            for (int i = 1; i < fRight; ++i) {
                game.getFlame().add(new Flame(_x + i, _y, flamePlaceTime, Direction.HORIZONTAL, game));
            }
            game.getFlame().add(new Flame(_x + fRight, _y, flamePlaceTime, Direction.RIGHT, game));
        }

        int fUp = flameToTop(_x, _y);
        if (fUp >= 1) {
            for (int i = 1; i < fUp; ++i) {
                game.getFlame().add(new Flame(_x, _y - i, flamePlaceTime, Direction.VERTICAL, game));
            }
            game.getFlame().add(new Flame(_x, _y - fUp, flamePlaceTime, Direction.UP, game));
        }

        int fDown = flameToBottom(_x, _y);
        if (fDown >= 1) {
            for (int i = 1; i < fDown; ++i) {
                game.getFlame().add(new Flame(_x, _y + i, flamePlaceTime, Direction.VERTICAL, game));
            }
            game.getFlame().add(new Flame(_x, _y + fDown, flamePlaceTime, Direction.DOWN, game));
        }
    }

    public void explode(){
        owner.decreaseBombCnt();
        flameOfBomb();
        this.remove();
    }

    public boolean isByPass() {
        return byPass;
    }

    public MovingEntity getOwner() {
        return owner;
    }

    public void setExplode() {
        isExplode = true;
    }

    public void update() {
        if (!touchCheck(x,y,owner))
            byPass = true;
        updateImage();
        if (!isRemoved()) {
            if (placeTime + timeCountDown < System.currentTimeMillis()||isExplode) {
                explode();
            }
        }
    }
}
