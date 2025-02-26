package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.sounds.Sound;

import static uet.oop.bomberman.entities.Check.touchCheck;

public abstract class MovingEntity extends Entity {
    protected long timeBetweenMove;
    protected long lastTimeMove;
    protected long timeBetweenPlaceBomb = 300;
    protected long lastTimePlaceBomb;
    protected double speed = 0.25;
    protected Direction direction = Direction.RIGHT;
    protected int bombMax;
    protected int bombCnt;
    protected int bombLength;
    protected boolean bombPassUsed = false;
    protected boolean flamePassUsed = false;
    protected boolean brickPassUsed = false;
    protected Game game;

    protected MovingEntity(double x, double y, Game game, Image img, long timeBetweenMove, int bombMax, int bombLength) {
        super(x, y, img);
        this.timeBetweenMove = timeBetweenMove;
        this.bombMax = bombMax;
        this.bombCnt = 0;
        this.bombLength = bombLength;
        this.game = game;
        this.lastTimeMove = System.currentTimeMillis();
        this.lastTimePlaceBomb = System.currentTimeMillis();
    }

    public boolean isFlamePassUsed() {
        return flamePassUsed;
    }

    public void setBombPassUsed() {
        bombPassUsed = true;
    }

    public void setFlamePassUsed() {
        flamePassUsed = true;
    }

    public void setBrickPassUsed() {
        brickPassUsed = true;
    }

    protected boolean canMove(double _x, double _y)
    {
        if((lastTimeMove+timeBetweenMove)>System.currentTimeMillis()) {
            return false;
        }
        for (Flame flame: game.getFlame()){
            if (touchCheck(_x, _y, flame)
                    && (!flamePassUsed || (this instanceof Enemy && flame.getOwner() instanceof Bomber))
                    && !(this instanceof Enemy && flame.getOwner() instanceof Enemy)
            ) {
                return false;
            }
        }
        for (Bomb bomb: game.getBomb()){
            if(bomb.isByPass()||bomb.getOwner()!=this) {
                if (touchCheck(_x, _y, bomb) &&
                    (!bombPassUsed || (this instanceof Enemy && bomb.getOwner() instanceof Bomber)
                            || (this instanceof Bomber && bomb.getOwner() instanceof Enemy)))
                {
                        return false;
                }
            }
        }
        for (Entity entity : game.getBrick()) {
            if (touchCheck(_x, _y, entity) && !brickPassUsed) {
                return false;
            }
        }
        for (Entity entity : game.getWall()) {
            if (touchCheck(_x, _y, entity)) {
                return false;
            }
        }
        return true;
    }

    public int getBombLength() {
        return bombLength;
    }

    public void increaseBombLength() {
        bombLength++;
    }

    public void increaseSpeed() {
        //if(timeBetweenMove>50)
        timeBetweenMove = Math.max(40, (long) (timeBetweenMove/1.1));
        //System.out.println(timeBetweenMove);
    }

    protected boolean canMoveUp() {
        return canMove(x,y-speed);
    }

    protected boolean canMoveDown() {
        return canMove(x,y+speed);
    }

    protected boolean canMoveLeft() {
        return canMove(x-speed,y);
    }

    protected boolean canMoveRight() {
        return canMove(x+speed,y);
    }

    protected void moveUp() {
        y -= speed;
        lastTimeMove = System.currentTimeMillis();
        if(direction == Direction.UP) {
            imgStage++;
        } else {
            direction = Direction.UP;
            imgStage = 0;
        }
    }

    protected void moveDown() {
        y += speed;
        lastTimeMove = System.currentTimeMillis();
        if(direction == Direction.DOWN){
            imgStage++;
        }else{
            direction = Direction.DOWN;
            imgStage = 0;
        }
    }

    protected void moveLeft() {
        x -= speed;
        lastTimeMove = System.currentTimeMillis();
        if(direction == Direction.LEFT)
        {
            imgStage++;
        } else {
            direction = Direction.LEFT;
            imgStage = 0;
        }
    }

    protected void moveRight() {
        x += speed;
        lastTimeMove = System.currentTimeMillis();
        if(direction == Direction.RIGHT) {
            imgStage++;
        } else {
            direction = Direction.RIGHT;
            imgStage = 0;
        }
    }

    public void increaseBombMax() {
        bombMax++;
    }

    public void increaseBombCnt() {
        bombCnt++;
    }
    public void decreaseBombCnt() {
        bombCnt--;
    }

    protected boolean canPlaceBomb(double _x, double _y)
    {
        if (bombCnt >= bombMax || lastTimePlaceBomb + timeBetweenPlaceBomb > System.currentTimeMillis())
            return false;
        for (Entity entity : game.getBrick()) {
            if (touchCheck(_x, _y, entity)) {
                return false;
            }
        }
        Bomber bomber = game.getBomber();
        if(bomber!= this && touchCheck(_x,_y,bomber))
            return false;

        for (Entity entity : game.getEnemy()) {
            if(entity!=this)
            {
                if (touchCheck(_x, _y, entity)) {
                    return false;
                }
            }
        }
        for (Entity entity : game.getBomb() ) {
            if (touchCheck(_x, _y, entity)) {
                return false;
            }
        }
        for (Entity entity : game.getFlame() ) {
            if (touchCheck(_x, _y, entity)) {
                return false;
            }
        }
        return true;
    }
    protected void placeBomb() {
        if (canPlaceBomb(Math.round(x), Math.round(y))) {
            lastTimePlaceBomb = System.currentTimeMillis();
            new Sound(Sound.PLACE_BOMB, false).play();
            game.getBomb().add(new Bomb((int) Math.round(x), (int) Math.round(y), this,this.game));
        }
    }

}
