package uet.oop.bomberman.entities.movingObject.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.movingObject.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    private static final Image[] spriteLeft= {Sprite.oneal_left1.getFxImage(),Sprite.oneal_right2.getFxImage(),Sprite.oneal_left3.getFxImage()};
    private static final Image[] spriteRight= {Sprite.oneal_right1.getFxImage(),Sprite.oneal_right2.getFxImage(),Sprite.oneal_right3.getFxImage()};
    private static final Image spriteDead = Sprite.oneal_dead.getFxImage();
    public Oneal(double x, double y, Game game) {
        super(x, y, game, Oneal.spriteRight[0], 100, 0, 0,0);
    }

    private void updateImage(){
        if(isDead()){
            img = Oneal.spriteDead;
        } else {
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
    }

    public void update() {
        // dùng thuật toán gì đó để đưa Oneal đến với Bomber
        // cần dùng vị trí của Bomber?
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
