package uet.oop.bomberman.entities.movingObject.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.movingObject.SmartEnemy;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends SmartEnemy {
    private static final Image[] spriteLeft= {Sprite.oneal_left1.getFxImage(),Sprite.oneal_left2.getFxImage(),Sprite.oneal_left3.getFxImage()};
    private static final Image[] spriteRight= {Sprite.oneal_right1.getFxImage(),Sprite.oneal_right2.getFxImage(),Sprite.oneal_right3.getFxImage()};
    private static final Image[] spriteDead = {Sprite.oneal_dead.getFxImage(),Sprite.mob_dead1.getFxImage(),Sprite.mob_dead2.getFxImage(),Sprite.mob_dead3.getFxImage()};
    public Oneal(double x, double y, Game game) {
        super(x, y, game, Oneal.spriteRight[0], 80, 0, 0,0,5);
    }

    protected Image getSpriteLeft(int imgStage){
        return spriteLeft[imgStage];
    }
    protected Image getSpriteRight(int imgStage){
        return spriteRight[imgStage];
    }
    protected Image getSpriteDead(int imgStage){
        return spriteDead[imgStage];
    }

}
