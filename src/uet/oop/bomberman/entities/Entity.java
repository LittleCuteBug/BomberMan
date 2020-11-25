package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.movingObject.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.ViewPoint;

public abstract class Entity {
    protected double x;
    protected double y;
    protected Image img;
    protected int imgStage = 0;
    private boolean removed = false;
    private boolean dead = false;
    protected long deadLength = 150;
    protected long deadTime = 0;

    public Entity(double x, double y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        if( this instanceof MovingEntity ){
            deadLength = 500;
        } else {
            deadLength = 150;
        }
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x * Sprite.SCALED_SIZE + ViewPoint.ViewPointX, y * Sprite.SCALED_SIZE + ViewPoint.ViewPointY);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract void update();

    public boolean isRemoved() {
        if (dead) {
            removed = deadTime + deadLength < System.currentTimeMillis();
        }
        return removed;
    }
    public boolean isDead() {
        //System.out.println(dead);
        return dead;
    }
    public void dead() {
        deadTime = System.currentTimeMillis();
        dead = true;
    }
    public void remove() {
        removed = true;
    }
    public void reborn() {
        removed = false;
        dead = false;
    }
}
