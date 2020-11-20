package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.entities.Check.touchCheck;

public abstract class MovingEntity extends Entity {
    protected double speed;
    protected int bombMax;
    protected int bombCnt;
    protected int bombLength;
    protected Game game;

    protected MovingEntity(double x, double y, Game game, Image img, double speed, int bombMax, int bombCnt, int bombLength) {
        super(x, y, img);
        this.speed = speed;
        this.bombMax = bombMax;
        this.bombCnt = bombCnt;
        this.bombLength = bombLength;
        this.game = game;
    }

    protected boolean canMoveUp() {
        for (Entity entity : game.getBrick()) {
            if (touchCheck(x, y, entity)) {
                return false;
            }
        }
        for (Entity entity : game.getWall()) {
            if (touchCheck(x, y, entity)) {
                return false;
            }
        }
        return true;
    }

    protected boolean canMoveDown() {
        return true;
    }

    protected boolean canMoveLeft() {
        return true;
    }

    protected boolean canMoveRight() {
        return true;
    }

    protected void moveUp() {
        if (canMoveUp()) {
            x += speed;
        }
    }

    protected void moveDown() {
        if (canMoveDown()) {
            x -= speed;
        }
    }

    protected void moveLeft() {
        if (canMoveLeft()) {
            y -= speed;
        }
    }

    protected void moveRight() {
        if (canMoveRight()) {
            y += speed;
        }
    }

    protected void placeBomb() {}
}
