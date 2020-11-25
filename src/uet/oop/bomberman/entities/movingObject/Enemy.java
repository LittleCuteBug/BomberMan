package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Check;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObject.enemy.Oneal;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.entities.Check.touchCheck;

public abstract class Enemy extends MovingEntity {
    protected Enemy(double x, double y, Game game, Image img, long timeBetweenMove, int bombMax, int bombCnt, int bombLength) {
        super(x,y,game,img,timeBetweenMove,bombMax,bombCnt,bombLength);
    }

    protected boolean canMove(double _x, double _y){
        for(Entity entity : game.getEnemy()) {
            if (entity!=this && touchCheck(_x, _y, entity)) {
                return false;
            }
        }
        return super.canMove(_x,_y);
    }
    protected void updateAction(){
        Random generator = new Random();
        Direction rand = Direction.values()[generator.nextInt(4)];
        switch (rand) {
            case UP:
                if(!canMoveDown() || direction!= Direction.DOWN)
                    if(canMoveUp())
                        moveUp();
                break;
            case DOWN:
                if(!canMoveUp()||direction!=Direction.UP)
                    if(canMoveDown())
                        moveDown();
                break;
            case RIGHT:
                if(!canMoveRight()||direction!=Direction.RIGHT)
                    if(canMoveLeft())
                        moveLeft();
                break;
            case LEFT:
                if(!canMoveLeft()||direction!=Direction.LEFT)
                    if(canMoveRight())
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
    abstract protected Image getSpriteLeft(int imgStage);
    abstract protected Image getSpriteRight(int imgStage);
    abstract protected Image getSpriteDead(int imgStage);

    protected void updateImage(){
        if(isDead()){
            imgStage =  (int) ((System.currentTimeMillis() - deadTime) / (deadLength / 4) % 4);
            img = getSpriteDead(imgStage);
        } else {
            imgStage = imgStage % 3;
            switch (direction){
                case LEFT:
                case DOWN:
                    img = getSpriteLeft(imgStage);
                    break;
                case RIGHT:
                case UP:
                    img = getSpriteRight(imgStage);
                    break;
            }
        }
    }
    
    @Override
    public void update() {
        updateImage();
        if (!isDead()) {
            updateAction();
            updateObject();
        }
        if(isRemoved()){
            remove();
        }
    }
}
