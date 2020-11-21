package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.lang.ref.SoftReference;
import java.util.Random;

public class Balloom extends MovingEntity {
    private static final Image[] spriteLeft= {Sprite.balloom_left1.getFxImage(),Sprite.balloom_left2.getFxImage(),Sprite.balloom_left3.getFxImage()};
    private static final Image[] spriteRight= {Sprite.balloom_right1.getFxImage(),Sprite.balloom_right2.getFxImage(),Sprite.balloom_right3.getFxImage()};

    public Balloom(double x, double y, Game game) {
        super(x, y, game, Balloom.spriteRight[0], 80, 0, 0,0);
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
            case LEFT:
            case DOWN:
                img = Balloom.spriteLeft[imgStage];
                break;
            case RIGHT:
            case UP:
                img = Balloom.spriteRight[imgStage];
                break;
        }
    }
}
