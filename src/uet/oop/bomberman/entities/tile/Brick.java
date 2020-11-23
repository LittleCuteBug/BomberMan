package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObject.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    private static final Image spriteBrick = Sprite.brick.getFxImage();
    private static final Image[] spriteExplode = {Sprite.brick_exploded.getFxImage(),Sprite.brick_exploded1.getFxImage(),
    Sprite.brick_exploded2.getFxImage()};
    public Brick(int x, int y) {
        super(x, y, Brick.spriteBrick);
    }

    protected void updateImage(){
        if(isDead()){
            imgStage = (int) ((System.currentTimeMillis() - deadTime) / (deadLength / 3) % 3);
            img = Brick.spriteExplode[imgStage];
        }
    }

    public void update() {
        updateImage();
        if(isRemoved())
            remove();
    }
}
