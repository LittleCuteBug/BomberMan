package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

public class Bomber extends MovingEntity {
    private static final Image[] spriteUp = {Sprite.player_up.getFxImage(),Sprite.player_up_1.getFxImage(),Sprite.player_up_2.getFxImage()};
    private static final Image[] spriteDown = {Sprite.player_down.getFxImage(),Sprite.player_down_1.getFxImage(),Sprite.player_down_2.getFxImage()};
    private static final Image[] spriteLeft = {Sprite.player_left.getFxImage(),Sprite.player_left_1.getFxImage(),Sprite.player_left_2.getFxImage()};
    private static final Image[] spriteRight = {Sprite.player_right.getFxImage(),Sprite.player_right_1.getFxImage(),Sprite.player_right_2.getFxImage()};
    private static final Image[] spriteDead = {Sprite.player_dead1.getFxImage(),Sprite.player_dead2.getFxImage(),Sprite.player_dead3.getFxImage()};

    private int life;
    private long invincibleStart = System.currentTimeMillis();
    private long invincibleLength = 5000;

    public Bomber(double x, double y, Game game) {
        super(x, y, game, Bomber.spriteRight[0], 70, 5, 0, 1);
    }


    private void updateAction(){
        if(Keyboard.up)
            if(canMoveUp())
                moveUp();
        if(Keyboard.down)
            if(canMoveDown())
                moveDown();
        if(Keyboard.left)
            if(canMoveLeft())
                moveLeft();
        if(Keyboard.right)
            if(canMoveRight())
                moveRight();
        if(Keyboard.space)
            super.placeBomb();
    }
    private void updateImage(){
        if(isDead()) {
            imgStage = (int) ((System.currentTimeMillis() - deadTime) / (deadLength / 3) % 3);
            img = Bomber.spriteDead[imgStage];
        }
        else {
            imgStage = imgStage % 3;
            switch (direction) {
                case UP:
                    img = Bomber.spriteUp[imgStage];
                    break;
                case DOWN:
                    img = Bomber.spriteDown[imgStage];
                    break;
                case LEFT:
                    img = Bomber.spriteLeft[imgStage];
                    break;
                case RIGHT:
                    img = Bomber.spriteRight[imgStage];
                    break;
            }
        }
    }

    public boolean isInvincible() {
        return invincibleStart + invincibleLength > System.currentTimeMillis();
    }

    @Override
    public void dead() {
        if(!isInvincible()&&!isDead()) {
            //System.out.println("Dead");
            super.dead();
        }
    }

    public void reborn(){
        x=1;
        y=1;
        img = Bomber.spriteRight[0];
        direction = Direction.RIGHT;
        invincibleStart = System.currentTimeMillis();
        super.reborn();
    }
    @Override
    public void update() {
        if(!isDead()) {
            updateAction();
        }
        updateImage();
        if(isRemoved())
            remove();
    }
}
