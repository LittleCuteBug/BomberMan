package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class PortalBrick extends Brick{
    private static final Image itemBrick = Sprite.brick.getFxImage();
    private final Portal portal;
    private final Game game;
    public PortalBrick(int x, int y, Game game) {
        super(x, y);
        this.img = itemBrick;
        this.portal = new Portal(x,y,game);
        this.game = game;
    }

    @Override
    public void update() {
        updateImage();
        if(isRemoved()){
            game.setPortal(portal);
            remove();
        }
    }
}
