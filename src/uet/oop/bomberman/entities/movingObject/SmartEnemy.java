package uet.oop.bomberman.entities.movingObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Check;
import uet.oop.bomberman.entities.Direction;
import uet.oop.bomberman.entities.Entity;

public abstract class SmartEnemy extends Enemy{
    protected int radios;
    protected SmartEnemy(double x, double y, Game game, Image img, long timeBetweenMove, int bombMax, int bombLength, int radios) {
        super(x, y, game, img, timeBetweenMove, bombMax, bombLength);
        this.radios = radios;
    }

    abstract protected Image getSpriteLeft(int imgStage);
    abstract protected Image getSpriteRight(int imgStage);
    abstract protected Image getSpriteDead(int imgStage);

    private int[][] getMap(int radios) {
        int[][] map = new int[radios*2+1][radios*2+1];
        if(!brickPassUsed) {
            for (Entity entity: game.getBrick()) {
                if(Check.getDistance(x,y,entity)<=radios){
                    map[(int) (radios+entity.getY()-y)][(int) (radios+entity.getX()-x)] = 1;
                }
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


    private Direction smartMove(){
        if(Check.getDistance(x,y,game.getBomber())>radios)
            return null;
        if(x!=Math.round(x)||y!=Math.round(y)){
            return direction;
        }

        int[][] map = getMap(radios);

        int bomberX = (int) (game.getBomber().getX()-x+radios);
        int bomberY = (int) (game.getBomber().getY()-y+radios);

        return FindPath.getDirection(map,radios,radios,radios,bomberX,bomberY);
    }

    @Override
    protected void updateAction() {
        Direction _direction = smartMove();
        if(_direction != null)
            timeBetweenMove -= 20;
        if(!move(_direction)) {
            super.updateAction();
        }
        if(_direction != null)
            timeBetweenMove += 20;
    }

}
