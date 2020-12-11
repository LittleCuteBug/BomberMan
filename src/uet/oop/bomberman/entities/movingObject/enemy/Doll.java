package uet.oop.bomberman.entities.movingObject.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.movingObject.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Enemy {
    private static final Image[] spriteLeft= {Sprite.doll_left1.getFxImage(),Sprite.doll_left2.getFxImage(),Sprite.doll_left3.getFxImage()};
    private static final Image[] spriteRight= {Sprite.doll_right1.getFxImage(),Sprite.doll_right2.getFxImage(),Sprite.doll_right3.getFxImage()};
    private static final Image[] spriteDead = {Sprite.doll_dead.getFxImage(),Sprite.mob_dead1.getFxImage(),Sprite.mob_dead2.getFxImage(),Sprite.mob_dead3.getFxImage()};
    public Doll(double x, double y, Game game) {
        super(x, y, game, Doll.spriteRight[0], 90, 0, 0,0);
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
