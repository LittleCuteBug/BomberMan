package uet.oop.bomberman.entities.movingObject;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends MovingEntity {
    private int life;
    // Đoạn này t nghĩ là phải thêm sẵn mấy cái thuộc tính luôn, vd speed = 2.0 gì đấy.
    // Đọc ở đoạn chạy Game thì vẫn để Bomber(1.5, 2)

    public Bomber(double x, double y) {
        super(x, y, Sprite.player_right.getFxImage(), 0.5, 1, 0, 1);
    }
    // Khai báo này có vẻ không ổn lắm.

    @Override
    public void update() {

    }
}
