package uet.oop.bomberman.entities.movingObject;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {

    public Bomber(double x, double y) {
        super( x, y, Sprite.player_right.getFxImage());
    }

    @Override
    public void update() {

    }
}
