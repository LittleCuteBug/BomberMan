package uet.oop.bomberman.entities.movingObject;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.util.Random;

public class Balloom extends MovingEntity {
    public Balloom(double x, double y, Game game) {
        super(x, y, game, Sprite.balloom_right1.getFxImage(), 0.3, 0, 0,0);
    }

    public void update() {
        updateAction();
        updateImage();
    }

    private void updateAction(){
        Random generator = new Random();
        int rand = generator.nextInt(4) + 1;
        System.out.println(rand);
        switch (rand) {
            case 1:
                if (canMoveUp()) {
                    moveUp();
                    System.out.println("done up");
                } /*else if (canMoveDown()) {
                    moveDown();
                } else if (canMoveLeft()) {
                    moveLeft();
                } else if (canMoveRight()) {
                    moveRight();
                }*/
            case 2:
                if (canMoveDown()) {
                    moveDown();
                    System.out.println("done down");
                } /*else if (canMoveLeft()) {
                    moveLeft();
                } else if (canMoveRight()) {
                    moveRight();
                } else if (canMoveUp()) {
                    moveUp();
                }*/
            case 3:
                if (canMoveLeft()) {
                    moveLeft();
                    System.out.println("done left");
                }
                /*else if (canMoveRight()) {
                    moveRight();
                } else if (canMoveUp()) {
                    moveUp();
                } else if (canMoveDown()) {
                    moveDown();
                }*/
            case 4:
                if (canMoveRight()) {
                    moveRight();
                    System.out.println("done right");
                } /*else if (canMoveUp()) {
                    moveUp();
                } else if (canMoveDown()) {
                    moveDown();
                } else if (canMoveLeft()) {
                    moveLeft();
                }*/
        }
    }

    private void updateImage(){
        imgStage = imgStage % 3;
        Random generator = new Random();
        int rand = generator.nextInt(4) + 1;
        System.out.println("img " + rand);
        switch (rand){
            case 1:
                img = Sprite.balloom_left1.getFxImage();
                break;
            case 2:
                img = Sprite.balloom_left1.getFxImage();
                break;
            case 3:
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
            case 4:
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
