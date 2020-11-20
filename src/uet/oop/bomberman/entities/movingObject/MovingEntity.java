package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.entities.Check.touchCheck;

public abstract class MovingEntity extends Entity {
    protected double speed;
    protected Direction direction = Direction.RIGHT;
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
            if (touchCheck(x, y-speed, entity)) {
                return false;
            }
        }
        for (Entity entity : game.getWall()) {
            if (touchCheck(x, y-speed, entity)) {
                return false;
            }
        }
        return true;
    }

    protected boolean canMoveDown() {
        for (Entity entity : game.getBrick()) {
            if (touchCheck(x, y+speed, entity)) {
                return false;
            }
        }
        for (Entity entity : game.getWall()) {
            if (touchCheck(x, y+speed, entity)) {
                return false;
            }
        }
        return true;
    }

    protected boolean canMoveLeft() {
        for (Entity entity : game.getBrick()) {
            if (touchCheck(x-speed, y, entity)) {
                return false;
            }
        }
        for (Entity entity : game.getWall()) {
            if (touchCheck(x-speed, y, entity)) {
                return false;
            }
        }
        return true;
    }

    protected boolean canMoveRight() {
        for (Entity entity : game.getBrick()) {
            if (touchCheck(x+speed, y, entity)) {
                return false;
            }
        }
        for (Entity entity : game.getWall()) {
            if (touchCheck(x+speed, y, entity)) {
                return false;
            }
        }
        return true;
    }

    protected void moveUp() {
        if (canMoveUp()) {
            y -= speed;
            if(direction == Direction.UP) {
                imgStage++;
            } else {
                direction = Direction.UP;
                imgStage = 0;
            }
        }
    }

    protected void moveDown() {
        if (canMoveDown()) {
            y += speed;
            if(direction == Direction.DOWN){
                imgStage++;
            }else{
                direction = Direction.DOWN;
                imgStage = 0;
            }

        }
    }

    protected void moveLeft() {
        if (canMoveLeft()) {
            x -= speed;
            if(direction == Direction.LEFT)
            {
                imgStage++;
            } else {
                direction = Direction.LEFT;
                imgStage = 0;
            }
        }
    }

    protected void moveRight() {
        if (canMoveRight()) {
            x += speed;
            if(direction == Direction.RIGHT) {
                imgStage++;
            } else {
                direction = Direction.RIGHT;
                imgStage = 0;
            }
        }
    }

    protected void placeBomb() {}
}
