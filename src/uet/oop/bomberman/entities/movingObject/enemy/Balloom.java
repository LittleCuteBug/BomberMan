package uet.oop.bomberman.entities.movingObject.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.movingObject.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy {
    private static final Image[] spriteLeft= {Sprite.balloom_left1.getFxImage(),Sprite.balloom_left2.getFxImage(),Sprite.balloom_left3.getFxImage()};
    private static final Image[] spriteRight= {Sprite.balloom_right1.getFxImage(),Sprite.balloom_right2.getFxImage(),Sprite.balloom_right3.getFxImage()};
    private static final Image spriteDead= Sprite.balloom_dead.getFxImage();
    public Balloom(double x, double y, Game game) {
        super(x, y, game, Balloom.spriteRight[0], 150, 0, 0,0);
    }

    private void updateImage(){
        if(isDead()) {
            img = Balloom.spriteDead;
        } else {
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

    public void update() {
        updateImage();
        if (!isDead()) {
            updateAction();
            updateObject();
        }
        if(isRemoved())
            remove();
    }
}
