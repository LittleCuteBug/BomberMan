package uet.oop.bomberman.entities.movingObject.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Check;
import uet.oop.bomberman.entities.movingObject.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Pass extends Enemy {
    private static final Image[] spriteLeft= {Sprite.pass_left1.getFxImage(),Sprite.pass_left2.getFxImage(),Sprite.pass_left3.getFxImage()};
    private static final Image[] spriteRight= {Sprite.pass_right1.getFxImage(),Sprite.pass_right2.getFxImage(),Sprite.pass_right3.getFxImage()};
    private static final Image[] spriteDead = {Sprite.pass_dead.getFxImage(),Sprite.mob_dead1.getFxImage(),Sprite.mob_dead2.getFxImage(),Sprite.mob_dead3.getFxImage()};
    public Pass(double x, double y, Game game) {
        super(x, y, game, Pass.spriteRight[0], 110, 2,1);
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
