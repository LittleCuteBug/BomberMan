package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.graphics.Sprite;

public class ItemBrick extends Brick {
    private static final Image itemBrick = Sprite.powerup_brick.getFxImage();
    private Item item;
    private Game game;
    public ItemBrick(int x, int y, Item item, Game game) {
        super(x, y);
        this.img = itemBrick;
        this.item = item;
        this.game = game;
    }

    @Override
    public void update() {
        updateImage();
        if(isRemoved()){
            game.getItem().add(item);
            remove();
        }
    }
}
