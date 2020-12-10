package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.Check;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sounds.Sound;

public class Portal extends Entity {
    protected final Game game;
    public Portal (int x, int y, Game game)
    {
        super(x,y, Sprite.portal.getFxImage());
        this.game = game;
    }

    @Override
    public void update() {
        if (game.getEnemy().size() == 0) {
            if (Sound.ENDING_SOUND.isNotPlay()) {
                Sound.ENDING_SOUND.play();
                Sound.STAGE_THEME_SOUND.stop();
                System.out.println("start exit");
            }
            if (Check.overlapCheck(x,y,game.getBomber())) {
                Sound.ENDING_SOUND.stop();
                Sound.STAGE_COMPLETE_SOUND.play();
                Map.nextLevel(game);
            }
        }
    }
}
