package uet.oop.bomberman.entities.movingObject.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Check;
import uet.oop.bomberman.entities.movingObject.Enemy;
import uet.oop.bomberman.entities.movingObject.SmartEnemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Minvo extends SmartEnemy {
    private static final Image[] spriteLeft= {Sprite.minvo_left1.getFxImage(),Sprite.minvo_left2.getFxImage(),Sprite.minvo_left3.getFxImage()};
    private static final Image[] spriteRight= {Sprite.minvo_right1.getFxImage(),Sprite.minvo_right2.getFxImage(),Sprite.minvo_right3.getFxImage()};
    private static final Image[] spriteDead = {Sprite.minvo_dead.getFxImage(),Sprite.mob_dead1.getFxImage(),Sprite.mob_dead2.getFxImage(),Sprite.mob_dead3.getFxImage()};
    public Minvo(double x, double y, Game game) {
        super(x, y, game, Minvo.spriteRight[0], 110, 1,2,3);
        timeBetweenPlaceBomb = 1000;
        setBombPassUsed();
        setFlamePassUsed();
    }

    @Override
    protected void updateAction() {
        super.updateAction();
        Random random = new Random();
        if(random.nextDouble()<0.005|| Check.getInlineDistance(x,y,game.getBomber()) < 2) {
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
