package uet.oop.bomberman.entities.movingObject.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.movingObject.SmartEnemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Pontan extends SmartEnemy {
    private static final Image[] spriteLeft= {Sprite.pontan_left1.getFxImage(),Sprite.pontan_left2.getFxImage(),Sprite.pontan_left3.getFxImage()};
    private static final Image[] spriteRight= {Sprite.pontan_right1.getFxImage(),Sprite.pontan_right2.getFxImage(),Sprite.pontan_right3.getFxImage()};
    private static final Image[] spriteDead = {Sprite.pontan_dead.getFxImage(),Sprite.mob_dead1.getFxImage(),Sprite.mob_dead2.getFxImage(),Sprite.mob_dead3.getFxImage()};
    public Pontan(double x, double y, Game game) {
        super(x, y, game, Pontan.spriteRight[0], 80, 2, 2,4);
        timeBetweenPlaceBomb = 1000;
        setBombPassUsed();
        setFlamePassUsed();
        setBrickPassUsed();
    }

    @Override
    protected void updateAction() {
        super.updateAction();
        Random random = new Random();
        if(random.nextDouble()<0.01) {
            placeBomb();
        }
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
