package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    private Direction direction;
    private long placeTime;
    private static final long timeCountDown = 1000;

    private static final Image[] spriteLeft = {Sprite.explosion_horizontal_left_last.getFxImage(), Sprite.explosion_horizontal_left_last1.getFxImage(),
            Sprite.explosion_horizontal_left_last2.getFxImage()};

    private static final Image[] spriteRight = {Sprite.explosion_horizontal_right_last.getFxImage(), Sprite.explosion_horizontal_right_last1.getFxImage(),
            Sprite.explosion_horizontal_right_last2.getFxImage()};

    private static final Image[] spriteUp = {Sprite.explosion_vertical_top_last.getFxImage(), Sprite.explosion_vertical_top_last1.getFxImage(),
            Sprite.explosion_vertical_top_last2.getFxImage()};

    private static final Image[] spriteDown = {Sprite.explosion_vertical_down_last.getFxImage(), Sprite.explosion_vertical_down_last1.getFxImage(),
            Sprite.explosion_vertical_down_last2.getFxImage()};

    private static final Image[] spriteCenter = {Sprite.bomb_exploded.getFxImage(), Sprite.bomb_exploded1.getFxImage(), Sprite.bomb_exploded2.getFxImage()};

    private static final Image[] spriteHorizontal = {Sprite.explosion_horizontal.getFxImage(), Sprite.explosion_horizontal1.getFxImage(), Sprite.explosion_horizontal2.getFxImage()};

    private static final Image[] spriteVertical = {Sprite.explosion_vertical.getFxImage(),Sprite.explosion_vertical1.getFxImage(),Sprite.explosion_vertical2.getFxImage()};

    public Flame(int x, int y, long placeTime, Direction direction) {
        super(x, y, Sprite.bomb_exploded.getFxImage());
        this.direction = direction;
        this.placeTime = placeTime;
    }

    public void updateImage() {
        imgStage = (int)(System.currentTimeMillis()-placeTime)/300%3;
        switch (direction){
            case LEFT:
                img = Flame.spriteLeft[imgStage];
                break;
            case RIGHT:
                img = Flame.spriteRight[imgStage];
                break;
            case UP:
                img = Flame.spriteUp[imgStage];
                break;
            case DOWN:
                img = Flame.spriteDown[imgStage];
                break;
            case CENTER:
                img = Flame.spriteCenter[imgStage];
                break;
            case VERTICAL:
                img = Flame.spriteVertical[imgStage];
                break;
            case HORIZONTAL:
                img = Flame.spriteHorizontal[imgStage];
                break;
        }
    }

    public void updateAction() {
        if (placeTime + timeCountDown < System.currentTimeMillis()) {
            this.remove();
        }
    }

    public void update() {
        updateImage();
        if(!isRemoved()){
            updateAction();
        }
    }
}
