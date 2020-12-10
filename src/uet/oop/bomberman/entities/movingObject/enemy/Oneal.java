package uet.oop.bomberman.entities.movingObject.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Check;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObject.Enemy;
import uet.oop.bomberman.entities.movingObject.FindPath;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    private static final Image[] spriteLeft= {Sprite.oneal_left1.getFxImage(),Sprite.oneal_left2.getFxImage(),Sprite.oneal_left3.getFxImage()};
    private static final Image[] spriteRight= {Sprite.oneal_right1.getFxImage(),Sprite.oneal_right2.getFxImage(),Sprite.oneal_right3.getFxImage()};
    private static final Image[] spriteDead = {Sprite.oneal_dead.getFxImage(),Sprite.mob_dead1.getFxImage(),Sprite.mob_dead2.getFxImage(),Sprite.mob_dead3.getFxImage()};
    public Oneal(double x, double y, Game game) {
        super(x, y, game, Oneal.spriteRight[0], 100, 0, 0,0);
    }

    private int[][] getMap(int radios) {
        int[][] map = new int[radios*2+1][radios*2+1];
        for (Entity entity: game.getBrick()) {
            if(Check.getDistance(x,y,entity)<=radios){
                map[(int) (radios+entity.getY()-y)][(int) (radios+entity.getX()-x)] = 1;
            }
        }
        for (Entity entity: game.getWall()) {
            if(Check.getDistance(x,y,entity)<=radios){
                map[(int) (radios+entity.getY()-y)][(int) (radios+entity.getX()-x)] = 1;
            }
        }
        for (Entity entity: game.getBomb()) {
            if(Check.getDistance(x,y,entity)<=radios){
                map[(int) (radios+entity.getY()-y)][(int) (radios+entity.getX()-x)] = 1;
            }
        }
        for (Entity entity: game.getFlame()) {
            if(Check.getDistance(x,y,entity)<=radios){
                map[(int) (radios+entity.getY()-y)][(int) (radios+entity.getX()-x)] = 1;
            }
        }
        return map;
    }

    private void continueMove(){
        switch (direction) {
            case UP:
                if(canMoveUp()) {
                    //System.out.println("#UP");
                    moveUp();
                }
                break;
            case DOWN:
                if(canMoveDown()) {
                    //System.out.println("#DOWN");
                    moveDown();
                }
                break;
            case RIGHT:
                if (canMoveRight()) {
                    //System.out.println("#RIGHT");
                    moveRight();
                }
                break;
            case LEFT:
                if (canMoveLeft()) {
                    //System.out.println("#LEFT");
                    moveLeft();
                }
                break;
        }
    }

    protected boolean smartMove(){
        int radios = 5;

        if(Check.getDistance(x,y,game.getBomber())>radios)
            return true;
        if(x!=Math.round(x)||y!=Math.round(y)){
            continueMove();
            return false;
        }

        int[][] map = getMap(radios);

        int bomberX = (int) (game.getBomber().getX()-x+radios);
        int bomberY = (int) (game.getBomber().getY()-y+radios);

        Direction _direction = FindPath.getDirection(map,radios,radios,radios,bomberX,bomberY);
        if(_direction==null) {
            return true;
        }
        direction = _direction;
        continueMove();
        return false;
    }

    @Override
    protected void updateAction() {
        if (smartMove()) {
            super.updateAction();
        }
    }

    protected Image getSpriteLeft(int imgStage){
        return spriteLeft[imgStage];
    }
    protected Image getSpriteRight(int imgStage){
        return spriteRight[imgStage];
    }
    protected Image getSpriteDead(int imgStage){
        return spriteDead[imgStage];
    }

}
