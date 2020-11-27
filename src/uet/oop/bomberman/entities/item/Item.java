package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.entities.Check.touchCheck;

public abstract class Item extends Entity {
    protected final Game game;
    public Item(int x, int y, Image img, Game game) {
        super (x, y, img);
        this.game = game;
    }

    protected abstract void itemUsed();

    public void update() {
        // kiểm tra nếu chạm bomber thì gọi hàm itemUsed rồi remove
        if (touchCheck(x, y, game.getBomber())) {
            itemUsed();
            this.remove();
        }

    }
}
