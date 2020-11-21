package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObject.Balloom;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    private int direction;
    private static final Image[] sprite = {Sprite.explosion_horizontal_left_last.getFxImage(), Sprite.explosion_horizontal_right_last.getFxImage(),
                                            Sprite.explosion_vertical_top_last.getFxImage(), Sprite.explosion_vertical_down_last.getFxImage()};

    public Flame(int x, int y, int direction) {
        super(x, y, Sprite.bomb_exploded.getFxImage());
        this.direction = direction;
    }

    public void updateImage() {
        switch (direction){
            case 1:
                img = Flame.sprite[0];
                break;
            case 2:
                img = Flame.sprite[1];
                break;
            case 3:
                img = Flame.sprite[2];
                break;
            case 4:
                img = Flame.sprite[3];
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
