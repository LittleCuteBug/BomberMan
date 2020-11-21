package uet.oop.bomberman.entities.movingObject;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.util.Random;

public class Balloom extends MovingEntity {
    public Balloom(double x, double y, Game game) {
        super(x, y, game, Sprite.balloom_right1.getFxImage(), 0.5, 0, 0,0);
    }

    public void update() {
        updateAction();
        updateImage();
    }

    private void updateAction(){
        Random generator = new Random();
        int rand = generator.nextInt(4) + 1;
        switch (rand) {
            case 1:
                if(!canMoveDown()||direction!=Direction.DOWN)
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

    private void updateImage(){
        imgStage = imgStage%3;
        switch (direction){
            case UP:
                img = Sprite.balloom_left1.getFxImage();
                break;
            case DOWN:
                img = Sprite.balloom_left1.getFxImage();
                break;
            case LEFT:
                switch (imgStage) {
                    case 0:
                        img = Sprite.balloom_left1.getFxImage();
                        break;
                    case 1:
                        img = Sprite.balloom_left2.getFxImage();
                        break;
                    case 2:
                        img = Sprite.balloom_left3.getFxImage();
                        break;
                }
                break;
            case RIGHT:
                switch (imgStage){
                    case 0:
                        img = Sprite.balloom_right1.getFxImage();
                        break;
                    case 1:
                        img = Sprite.balloom_right2.getFxImage();
                        break;
                    case 2:
                        img = Sprite.balloom_right3.getFxImage();
                        break;
                }
                break;
        }
    }
}
