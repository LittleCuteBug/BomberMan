package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.entities.movingObject.Bomber;
import uet.oop.bomberman.graphics.Sprite;

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
        if( this instanceof Bomber){
            deadLength = 300;
        } else {
            deadLength = 150;
        }
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
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
            removed = dead & (deadTime + deadLength < System.currentTimeMillis());
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
