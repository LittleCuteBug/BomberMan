package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObject.Balloom;
import uet.oop.bomberman.entities.movingObject.Direction;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    private Direction direction;
    private static final Image[] spriteLeft = {Sprite.explosion_horizontal_left_last.getFxImage(), Sprite.explosion_horizontal_left_last1.getFxImage(),
            Sprite.explosion_horizontal_left_last2.getFxImage(), Sprite.explosion_horizontal_left_last1.getFxImage(),
            Sprite.explosion_horizontal_left_last.getFxImage()};
    private static final Image[] spriteRight = {Sprite.explosion_horizontal_right_last.getFxImage(), Sprite.explosion_horizontal_right_last1.getFxImage(),
            Sprite.explosion_horizontal_right_last2.getFxImage(), Sprite.explosion_horizontal_right_last1.getFxImage(),
            Sprite.explosion_horizontal_right_last.getFxImage()};
    private static final Image[] spriteUp = {Sprite.explosion_vertical_top_last.getFxImage(), Sprite.explosion_vertical_top_last1.getFxImage(),
            Sprite.explosion_vertical_top_last2.getFxImage(), Sprite.explosion_vertical_top_last1.getFxImage(),
            Sprite.explosion_vertical_top_last.getFxImage()};
    private static final Image[] spriteDown = {Sprite.explosion_vertical_down_last.getFxImage(), Sprite.explosion_vertical_down_last1.getFxImage(),
            Sprite.explosion_vertical_down_last2.getFxImage(), Sprite.explosion_vertical_down_last1.getFxImage(),
            Sprite.explosion_vertical_down_last.getFxImage()};

    public Flame(int x, int y, Direction direction) {
        super(x, y, Sprite.bomb_exploded.getFxImage());
        this.direction = direction;
    }

    public void updateImage() {
        imgStage = imgStage % 5;
        switch (direction){
            case LEFT:
                img = Flame.spriteLeft[0];
                break;
            case RIGHT:
                img = Flame.spriteRight[1];
                break;
            case DOWN:
                img = Flame.spriteDown[2];
                break;
            case UP:
                img = Flame.spriteUp[3];
                break;
        }
    }

    public void updateAction() {

    }

    public void update() {
        updateImage();
        // Nhận hiệu lệnh đếm ngược của Bomb (countDown)
        // Khi nổ thì sẽ toả ra explosion_horizontal_left, right, explosion_vertical_top, down.
        // Khi ăn được Flame item thì toả ra theo dấu cộng (5 - 5), còn không thì (3 - 3)
    }
}
