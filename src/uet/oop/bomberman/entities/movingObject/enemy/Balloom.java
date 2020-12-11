package uet.oop.bomberman.entities.movingObject.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.movingObject.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy {
    private static final Image[] spriteLeft= {Sprite.balloom_left1.getFxImage(),Sprite.balloom_left2.getFxImage(),Sprite.balloom_left3.getFxImage()};
    private static final Image[] spriteRight= {Sprite.balloom_right1.getFxImage(),Sprite.balloom_right2.getFxImage(),Sprite.balloom_right3.getFxImage()};
    private static final Image[] spriteDead = {Sprite.balloom_dead.getFxImage(),Sprite.mob_dead1.getFxImage(),Sprite.mob_dead2.getFxImage(),Sprite.mob_dead3.getFxImage()};
    public Balloom(double x, double y, Game game) {
        super(x, y, game, Balloom.spriteRight[0], 90, 0, 0,0);
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
