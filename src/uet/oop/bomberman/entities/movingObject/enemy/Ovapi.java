package uet.oop.bomberman.entities.movingObject.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.movingObject.SmartEnemy;
import uet.oop.bomberman.graphics.Sprite;

public class Ovapi extends SmartEnemy {
    private static final Image[] spriteLeft= {Sprite.ovapi_left1.getFxImage(),Sprite.ovapi_left2.getFxImage(),Sprite.ovapi_left3.getFxImage()};
    private static final Image[] spriteRight= {Sprite.ovapi_right1.getFxImage(),Sprite.ovapi_right2.getFxImage(),Sprite.ovapi_right3.getFxImage()};
    private static final Image[] spriteDead = {Sprite.ovapi_dead.getFxImage(),Sprite.mob_dead1.getFxImage(),Sprite.mob_dead2.getFxImage(),Sprite.mob_dead3.getFxImage()};
    public Ovapi(double x, double y, Game game) {
        super(x, y, game, Ovapi.spriteRight[0], 80, 0, 0,4);
        this.setBrickPassUsed();
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
