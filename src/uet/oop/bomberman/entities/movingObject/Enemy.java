package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Check;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Enemy extends MovingEntity {
    protected Enemy(double x, double y, Game game, Image img, long timeBetweenMove, int bombMax, int bombCnt, int bombLength) {
        super(x,y,game,img,timeBetweenMove,bombMax,bombCnt,bombLength);
    }

    protected void updateAction(){
        Random generator = new Random();
        int rand = generator.nextInt(4) + 1;
        switch (rand) {
            case 1:
                if(!canMoveDown() || direction!= Direction.DOWN)
                    moveUp();
                break;
            case 2:
                if(!canMoveUp()||direction!=Direction.UP)
                    moveDown();
                break;
            case 3:
                if(!canMoveRight()||direction!=Direction.RIGHT)
                    moveLeft();
                break;
            case 4:
                if(!canMoveLeft()||direction!=Direction.LEFT)
                    moveRight();
                break;
        }
    }

    protected void updateObject() {
        Entity entity = game.getBomber();
        if(!entity.isRemoved()) {
            if(Check.touchCheck(x,y,entity)) {
                entity.dead();
            }
        }
    }

}
