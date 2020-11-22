package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Check;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Oneal extends MovingEntity {
    private static final Image[] spriteLeft= {Sprite.oneal_left1.getFxImage(),Sprite.oneal_right2.getFxImage(),Sprite.oneal_left3.getFxImage()};
    private static final Image[] spriteRight= {Sprite.oneal_right1.getFxImage(),Sprite.oneal_right2.getFxImage(),Sprite.oneal_right3.getFxImage()};

    public Oneal(double x, double y, Game game) {
        super(x, y, game, Oneal.spriteRight[0], 100, 0, 0,0);
    }

    private void updateImage(){
        imgStage = imgStage % 3;
        switch (direction){
            case LEFT:
            case DOWN:
                img = Oneal.spriteLeft[imgStage];
                break;
            case RIGHT:
            case UP:
                img = Oneal.spriteRight[imgStage];
                break;
        }
    }

    private void updateAction(){
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

    public void updateObject() {
        Entity entity = game.getBomber();
        if(!entity.isRemoved()) {
            if(Check.touchCheck(x,y,entity)) {
                entity.remove();
            }
        }
    }

    public void update() {
        // dùng thuật toán gì đó để đưa Oneal đến với Bomber
        // cần dùng vị trí của Bomber?
        updateImage();
        if (!isRemoved()) {
            updateAction();
            updateObject();
        }
    }
}
