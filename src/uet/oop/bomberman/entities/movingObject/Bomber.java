package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;
import uet.oop.bomberman.sounds.Sound;

public class Bomber extends MovingEntity {
    private static final Image[] spriteUp = {Sprite.player_up.getFxImage(),Sprite.player_up_1.getFxImage(),Sprite.player_up_2.getFxImage()};
    private static final Image[] spriteDown = {Sprite.player_down.getFxImage(),Sprite.player_down_1.getFxImage(),Sprite.player_down_2.getFxImage()};
    private static final Image[] spriteLeft = {Sprite.player_left.getFxImage(),Sprite.player_left_1.getFxImage(),Sprite.player_left_2.getFxImage()};
    private static final Image[] spriteRight = {Sprite.player_right.getFxImage(),Sprite.player_right_1.getFxImage(),Sprite.player_right_2.getFxImage()};
    private static final Image[] spriteDead = {Sprite.player_dead1.getFxImage(),Sprite.player_dead2.getFxImage(),Sprite.player_dead3.getFxImage()};

    private int life;
    private long invincibleStart = System.currentTimeMillis();
    private final long invincibleLength = 1000;

    public Bomber(double x, double y, Game game) {
        super(x, y, game, Bomber.spriteRight[0], 70, 2, 1);
    }

    protected boolean canMoveUpRight() {
        return canMove(x+speed,y-speed);
    }
    protected boolean canMoveUpLeft() {
        return canMove(x-speed,y-speed);
    }
    protected boolean canMoveDownRight() {
        return canMove(x+speed,y+speed);
    }
    protected boolean canMoveDownLeft() {
        return canMove(x-speed,y+speed);
    }

    private void updateAction(){
        if(Keyboard.up) {
            if (canMoveUp())
                moveUp();
            else if (canMoveRight()&&canMoveUpRight()){
                moveRight();
                moveUp();
            } else if (canMoveLeft()&&canMoveUpLeft()){
                moveLeft();
                moveUp();
            }
        }
        if(Keyboard.down) {
            if (canMoveDown())
                moveDown();
            else if (canMoveRight()&&canMoveDownRight()){
                moveRight();
                moveDown();
            } else if (canMoveLeft()&&canMoveDownLeft()){
                moveLeft();
                moveDown();
            }
        }
        if(Keyboard.left) {
            if (canMoveLeft())
                moveLeft();
            else if (canMoveDown()&&canMoveDownLeft()){
                moveDown();
                moveLeft();
            } else if (canMoveUp()&&canMoveUpLeft()){
                moveUp();
                moveLeft();
            }
        }
        if(Keyboard.right) {
            if (canMoveRight())
                moveRight();
            else if (canMoveDown()&&canMoveDownRight()){
                moveDown();
                moveRight();
            } else if (canMoveUp()&&canMoveUpRight()){
                moveUp();
                moveRight();
            }
        }
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
            Sound.LIFE_LOST_SOUND.play();
            super.dead();
        }
    }

    public void reborn(){
        x=1;
        y=1;
        img = Bomber.spriteRight[0];
        bombCnt = 0;
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
