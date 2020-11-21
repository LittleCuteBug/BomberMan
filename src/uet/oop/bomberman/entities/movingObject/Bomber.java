package uet.oop.bomberman.entities.movingObject;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

public class Bomber extends MovingEntity {
    private int life;
    // Đoạn này t nghĩ là phải thêm sẵn mấy cái thuộc tính luôn, vd speed = 2.0 gì đấy.
    // Đọc ở đoạn chạy Game thì vẫn để Bomber(1.5, 2)

    public Bomber(double x, double y, Game game) {
        super(x, y, game, Sprite.player_right.getFxImage(), 0.5, 1, 0, 1);
    }
    // Khai báo này có vẻ không ổn lắm.

    private void updateAction(){
        if(Keyboard.up)
            super.moveUp();
        if(Keyboard.down)
            super.moveDown();
        if(Keyboard.left)
            super.moveLeft();
        if(Keyboard.right)
            super.moveRight();
        if(Keyboard.space)
            super.placeBomb();
    }

    private void updateImage(){
        imgStage = imgStage % 3;
        switch (direction){
            case UP:
                switch (imgStage)
                {
                    case 0:
                        img = Sprite.player_up.getFxImage();
                        break;
                    case 1:
                        img = Sprite.player_up_1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.player_up_2.getFxImage();
                        break;
                }
                break;
            case DOWN:
                switch (imgStage)
                {
                    case 0:
                        img = Sprite.player_down.getFxImage();
                        break;
                    case 1:
                        img = Sprite.player_down_1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.player_down_2.getFxImage();
                        break;
                }
                break;
            case LEFT:
                switch (imgStage){
                    case 0:
                        img = Sprite.player_left.getFxImage();
                        break;
                    case 1:
                        img = Sprite.player_left_1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.player_left_2.getFxImage();
                        break;
                }
                break;
            case RIGHT:
                switch (imgStage){
                    case 0:
                        img = Sprite.player_right.getFxImage();
                        break;
                    case 1:
                        img = Sprite.player_right_1.getFxImage();
                        break;
                    case 2:
                        img = Sprite.player_right_2.getFxImage();
                        break;
                }
                break;
        }
    }

    @Override
    public void update() {
        updateAction();
        updateImage();
    }
}
