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
                if ((int) (Math.abs(x - entity.getX()) - 1) < flameLeft) {
                    flameLeft = (int) (Math.abs(x - entity.getX()) - 1);
                }
            }
        }
        return flameLeft;
    }

    public int flameToRight(int x, int y) {
        int flameRight = this.bombLength;

        for (Entity entity : game.getWall()) {
            if (entity.getX() > x && entity.getY() == y) {
                if ((int) (Math.abs(x - entity.getX()) - 1) < flameRight) {
                    flameRight = (int) (Math.abs(x - entity.getX()) - 1);
                }
            }
        }
        return flameRight;
    }

    public int flameToTop(int x, int y) {
        int flameUp = this.bombLength;

        for (Entity entity : game.getWall()) {
            if (entity.getX() == x && entity.getY() < y) {
                if ((int) (Math.abs(y - entity.getY()) - 1) < flameUp) {
                    flameUp = (int) (Math.abs(y - entity.getY()) - 1);
                }
            }
        }
        return flameUp;
    }

    public int flameToBottom(int x, int y) {
        int flameDown = this.bombLength;

        for (Entity entity : game.getWall()) {
            if (entity.getX() == x && entity.getY() > y) {
                if ((int) (Math.abs(y - entity.getY()) - 1) < flameDown) {
                    flameDown = (int) (Math.abs(y - entity.getY()) - 1);
                }
            }
        }
        return flameDown;
    }

    /*public void checkLeft(int x, int y) {
        for (Entity entity : game.getWall()) {
            if (entity.getX() > 0 && entity.getX() <= x && entity.getX() >= x - 1 && entity.getY() == y) {
                System.out.println(entity.getX() + " " + entity.getY());
            }
        }
    }*/

    public void explode(){
        owner.decreaseBombCnt();
        long flamePlaceTime = System.currentTimeMillis();
        game.getFlame().add(new Flame((int) x, (int) y,flamePlaceTime, Direction.CENTER, game));
//        System.out.println(flameToLeft((int) x, (int) y));
        for (int i = 0; i < flameToLeft((int) x, (int) y); ++i) {
            game.getFlame().add(new Flame((int) x - i - 1, (int) y,flamePlaceTime, Direction.LEFT, game));
        }
//        System.out.println("flameRight = " + flameToRight((int) x, (int) y));
//        System.out.println("flameUp = " + flameToTop((int) x, (int) y));
//        System.out.println("flameDown = " + flameToBottom((int) x, (int) y));
//        game.getFlame().add(new Flame((int) x - 1, (int) y,flamePlaceTime, Direction.LEFT, game));
        for (int i = 0; i < flameToRight((int) x, (int) y); ++i) {
            game.getFlame().add(new Flame((int) x + i + 1, (int) y,flamePlaceTime, Direction.RIGHT, game));
        }
        for (int i = 0; i < flameToTop((int) x, (int) y); ++i) {
            game.getFlame().add(new Flame((int) x, (int) y - i - 1,flamePlaceTime, Direction.UP, game));
        }
        for (int i = 0; i < flameToBottom((int) x, (int) y); ++i) {
            game.getFlame().add(new Flame((int) x, (int) y + i + 1,flamePlaceTime, Direction.DOWN, game));
        }
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
