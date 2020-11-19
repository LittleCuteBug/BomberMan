package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class MovingEntity extends Entity {
    protected double speed;
    protected int bombMax;
    protected int bombCnt;
    protected int bombLength;

    protected MovingEntity(double x, double y, Image img, double speed, int bombMax, int bombCnt, int bombLength) {
        super(x, y, img);
        this.speed = speed;
        this.bombMax = bombMax;
        this.bombCnt = bombCnt;
        this.bombLength = bombLength;
    }

    protected void moveUp() {}

    protected void moveDown() {}

    protected void moveLeft() {}

    protected void moveRight() {}

    protected void placeBomb() {}
}
