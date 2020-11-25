package uet.oop.bomberman;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.item.BombItem;
import uet.oop.bomberman.entities.item.FlameItem;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.entities.item.SpeedItem;
import uet.oop.bomberman.entities.movingObject.enemy.Balloom;
import uet.oop.bomberman.entities.movingObject.Bomber;
import uet.oop.bomberman.entities.movingObject.MovingEntity;
import uet.oop.bomberman.entities.movingObject.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Brick;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.ViewPoint;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private int WIDTH;
    private int HEIGHT;

    private double gameHEIGHT;
    private double gameWIGHT;

    private int level;

    private List<MovingEntity> enemy = new ArrayList<>();
    private List<Item> item = new ArrayList<>();
    private List<Entity> wall = new ArrayList<>();
    private List<Entity> brick = new ArrayList<>();
    private List<Bomb> bomb = new ArrayList<>();
    private List<Flame> flame = new ArrayList<>();
    
    private Bomber bomber;
    private Portal portal;

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public List<MovingEntity> getEnemy() {
        return enemy;
    }

    public List<Item> getItem() {
        return item;
    }

    public List<Entity> getWall() {
        return wall;
    }

    public List<Entity> getBrick() {
        return brick;
    }

    public List<Bomb> getBomb() {
        return bomb;
    }

    public List<Flame> getFlame() {
        return flame;
    }

    public Bomber getBomber() {
        return bomber;
    }

    public Portal getPortal() {
        return portal;
    }

    public void createMap() {
        WIDTH = 20;
        HEIGHT = 20;
        gameHEIGHT = HEIGHT*Sprite.SCALED_SIZE;
        gameWIGHT = WIDTH*Sprite.SCALED_SIZE;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j);
                    wall.add(object);
                }
            }
        }
        bomber = new Bomber(1,1,this);
        portal = new Portal(WIDTH-2,HEIGHT-2);
    }

    void loadMap(String filePath){
        enemy.clear();
        item.clear();
        wall.clear();
        brick.clear();
        try {
            Scanner reader = new Scanner(new File(filePath));
            level = reader.nextInt();
            HEIGHT = reader.nextInt();
            WIDTH = reader.nextInt();
            gameHEIGHT = HEIGHT*Sprite.SCALED_SIZE;
            gameWIGHT = WIDTH*Sprite.SCALED_SIZE;
            reader.nextLine();
            for(int j=0;j<HEIGHT;j++)
            {
                String line = reader.nextLine();
                //System.out.println(line);
                for(int i = 0;i<WIDTH;i++)
                {
                    char c = line.charAt(i);
                    switch (c) {
                        case '#':
                            wall.add(new Wall(i,j));
                            break;
                        case '*':
                            brick.add(new Brick(i,j));
                            break;
                        case 'x':
                            portal = new Portal(i,j);
                            break;
                        case 'p':
                            bomber = new Bomber(i,j,this);
                            break;
                        case '1':
                            enemy.add(new Balloom(i,j,this));
                            break;
                        case '2':
                            enemy.add(new Oneal(i,j,this));
                            break;
                        case 'b':
                            item.add(new BombItem(i,j));
                            break;
                        case 'f':
                            item.add(new FlameItem(i,j));
                            break;
                        case 's':
                            item.add(new SpeedItem(i,j));
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void remove(){
        bomb.removeIf(Entity::isRemoved);
        flame.removeIf(Entity::isRemoved);
        brick.removeIf(Entity::isRemoved);
        enemy.removeIf(Entity::isRemoved);
        if(bomber.isRemoved())
            bomber.reborn();
    }
    public void updateViewPoint(double x, double y, double stageWidth, double stageHeight)
    {
        double borderHEIGHT = stageHeight/6;
        double borderWIGHT = stageWidth/6;
        if(x>-ViewPoint.ViewPointX+stageWidth-borderWIGHT-Sprite.SCALED_SIZE)
            ViewPoint.ViewPointX = stageWidth-x-borderWIGHT-Sprite.SCALED_SIZE;
        if(x>gameWIGHT-borderWIGHT-Sprite.SCALED_SIZE)
            ViewPoint.ViewPointX = stageWidth-gameWIGHT;

        if(x<-ViewPoint.ViewPointX+borderWIGHT)
            ViewPoint.ViewPointX = borderWIGHT-x;
        if(x<borderWIGHT)
            ViewPoint.ViewPointX = 0;

        if(y>-ViewPoint.ViewPointY+stageHeight- borderHEIGHT - Sprite.SCALED_SIZE) {
            ViewPoint.ViewPointY = stageHeight - y - borderHEIGHT - Sprite.SCALED_SIZE;
        }
        if(y>gameHEIGHT-borderHEIGHT - Sprite.SCALED_SIZE)
            ViewPoint.ViewPointY = stageHeight-gameHEIGHT;
        if(y<-ViewPoint.ViewPointY+borderHEIGHT)
            ViewPoint.ViewPointY = borderHEIGHT-y;
        if(y<borderHEIGHT)
            ViewPoint.ViewPointY = 0;
    }
    public void update(double stageWidth, double stageHeight) {
        //wall.forEach(Entity::update);
        brick.forEach(Entity::update);
        item.forEach(Entity::update);
        portal.update();
        bomber.update();
        enemy.forEach(Entity::update);
        bomb.forEach(Entity::update);
        flame.forEach(Entity::update);
        remove();
        updateViewPoint(bomber.getX()*Sprite.SCALED_SIZE, bomber.getY()*Sprite.SCALED_SIZE,stageWidth,stageHeight);
    }

    public void render(GraphicsContext gc) {
        wall.forEach(g -> g.render(gc));
        brick.forEach(g -> g.render(gc));
        item.forEach(g -> g.render(gc));
        portal.render(gc);
        flame.forEach(g -> g.render(gc));
        bomb.forEach(g->g.render(gc));
        bomber.render(gc);
        enemy.forEach(g -> g.render(gc));
    }
}
