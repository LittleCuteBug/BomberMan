package uet.oop.bomberman.entities.movingObject.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.movingObject.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemy {
    private static final Image[] spriteLeft= {Sprite.kondoria_left1.getFxImage(),Sprite.kondoria_left2.getFxImage(),Sprite.kondoria_left3.getFxImage()};
    private static final Image[] spriteRight= {Sprite.kondoria_right1.getFxImage(),Sprite.kondoria_right2.getFxImage(),Sprite.kondoria_right3.getFxImage()};
    private static final Image[] spriteDead = {Sprite.kondoria_dead.getFxImage(),Sprite.mob_dead1.getFxImage(),Sprite.mob_dead2.getFxImage(),Sprite.mob_dead3.getFxImage()};
    public Kondoria(double x, double y, Game game) {
        super(x, y, game, Kondoria.spriteRight[0], 100, 0, 0,0);
        this.setBrickPassUsed();
        this.setBombPassUsed();
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