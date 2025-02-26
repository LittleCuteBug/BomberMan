package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Check;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.movingObject.Bomber;
import uet.oop.bomberman.entities.movingObject.Enemy;
import uet.oop.bomberman.entities.movingObject.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    private final Direction direction;
    private final long placeTime;
    private final Game game;
    private final MovingEntity owner;

    private static final long timeCountDown = 600;

    private static final Image[] spriteLeft = {Sprite.explosion_horizontal_left_last.getFxImage(), Sprite.explosion_horizontal_left_last1.getFxImage(),
            Sprite.explosion_horizontal_left_last2.getFxImage()};

    private static final Image[] spriteRight = {Sprite.explosion_horizontal_right_last.getFxImage(), Sprite.explosion_horizontal_right_last1.getFxImage(),
            Sprite.explosion_horizontal_right_last2.getFxImage()};

    private static final Image[] spriteUp = {Sprite.explosion_vertical_top_last.getFxImage(), Sprite.explosion_vertical_top_last1.getFxImage(),
            Sprite.explosion_vertical_top_last2.getFxImage()};

    private static final Image[] spriteDown = {Sprite.explosion_vertical_down_last.getFxImage(), Sprite.explosion_vertical_down_last1.getFxImage(),
            Sprite.explosion_vertical_down_last2.getFxImage()};

    private static final Image[] spriteCenter = {Sprite.bomb_exploded.getFxImage(), Sprite.bomb_exploded1.getFxImage(), Sprite.bomb_exploded2.getFxImage()};

    private static final Image[] spriteHorizontal = {Sprite.explosion_horizontal.getFxImage(), Sprite.explosion_horizontal1.getFxImage(), Sprite.explosion_horizontal2.getFxImage()};

    private static final Image[] spriteVertical = {Sprite.explosion_vertical.getFxImage(),Sprite.explosion_vertical1.getFxImage(),Sprite.explosion_vertical2.getFxImage()};

    public Flame(int x, int y, long placeTime, Direction direction, Game game, MovingEntity owner) {
        super(x, y, Sprite.bomb_exploded.getFxImage());
        this.direction = direction;
        this.placeTime = placeTime;
        this.game = game;
        this.owner = owner;
    }

    public MovingEntity getOwner() {
        return owner;
    }

    public void updateImage() {
        imgStage = (int)((System.currentTimeMillis()-placeTime)/(timeCountDown/3)%3);
        switch (direction){
            case LEFT:
                img = Flame.spriteLeft[imgStage];
                break;
            case RIGHT:
                img = Flame.spriteRight[imgStage];
                break;
            case UP:
                img = Flame.spriteUp[imgStage];
                break;
            case DOWN:
                img = Flame.spriteDown[imgStage];
                break;
            case CENTER:
                img = Flame.spriteCenter[imgStage];
                break;
            case VERTICAL:
                img = Flame.spriteVertical[imgStage];
                break;
            case HORIZONTAL:
                img = Flame.spriteHorizontal[imgStage];
                break;
        }
    }

    public void updateAction() {
        if (placeTime + timeCountDown < System.currentTimeMillis()) {
            this.remove();
        }
    }

    public void updateBomb() {
        for (Bomb bomb:game.getBomb()) {
            if(!bomb.isRemoved()) {
                if(Check.touchCheck(x,y,bomb)) {
                    bomb.setExplode();
                }
            }
        }
    }

    public void updateObject() {
        for(Entity entity:game.getBrick()) {
            if(!entity.isDead()) {
                if(Check.touchCheck(x,y,entity)) {
                    entity.dead();
                }
            }
        }
        if(owner instanceof Bomber)
            for(Entity entity:game.getEnemy()) {
                if(!entity.isDead()) {
                    if(Check.touchCheck(x,y,entity)) {
                        entity.dead();
                    }
                }
            }

        Bomber bomber = game.getBomber();
        if(!bomber.isDead()) {
            if(Check.touchCheck(x,y,bomber)  && !(bomber.isFlamePassUsed() && owner==bomber)) {
                bomber.dead();
            }
        }
    }
    public void update() {
        updateImage();
        if(!isRemoved()){
            updateBomb();
            updateObject();
            updateAction();
        }

    }
}
