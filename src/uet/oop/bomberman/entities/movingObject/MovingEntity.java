package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.entities.Check.touchCheck;

public abstract class MovingEntity extends Entity {
    protected long timeBetweenMove;
    protected long lastTimeMove;
    protected final double speed = 0.5;
    protected Direction direction = Direction.RIGHT;
    protected int bombMax;
    protected int bombCnt;
    protected int bombLength;
    protected Game game;

    protected MovingEntity(double x, double y, Game game, Image img, long timeBetweenMove, int bombMax, int bombCnt, int bombLength) {
        super(x, y, img);
        this.timeBetweenMove = timeBetweenMove;
        this.bombMax = bombMax;
        this.bombCnt = bombCnt;
        this.bombLength = bombLength;
        this.game = game;
        this.lastTimeMove = System.currentTimeMillis();
    }
    protected boolean canMove(double _x, double _y)
    {
        if((lastTimeMove+timeBetweenMove)>System.currentTimeMillis()) {
            return false;
        }
        for (Entity entity : game.getBrick()) {
            if (touchCheck(_x, _y, entity)) {
                return false;
            }
        }
        for (Entity entity : game.getWall()) {
            if (touchCheck(_x, _y, entity)) {
                return false;
            }
        }
        return true;
    }
    protected boolean canMoveUp() {
        return canMove(x,y-speed);
    }

    protected boolean canMoveDown() {
        return canMove(x,y+speed);
    }

    protected boolean canMoveLeft() {
        return canMove(x-speed,y);
    }

    protected boolean canMoveRight() {
        return canMove(x+speed,y);
    }

    protected void moveUp() {
        if (canMoveUp()) {
            y -= speed;
            lastTimeMove = System.currentTimeMillis();
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
            lastTimeMove = System.currentTimeMillis();
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
            lastTimeMove = System.currentTimeMillis();
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
            lastTimeMove = System.currentTimeMillis();
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
