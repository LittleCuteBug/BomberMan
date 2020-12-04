package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Entity {
    protected final Game game;
    public Portal (int x, int y, Game game)
    {
        super(x,y, Sprite.portal.getFxImage());
        this.game = game;
    }

    @Override
    public void update() {
        if (game.getEnemy().size() == 0 && game.getBomber().getX() == x && game.getBomber().getY() == y) {
            Map.loadMap("res/levels/Level1_1.txt",game);
        }
    }
}
