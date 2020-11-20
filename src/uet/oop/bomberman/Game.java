package uet.oop.bomberman;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.item.BombItem;
import uet.oop.bomberman.entities.item.FlameItem;
import uet.oop.bomberman.entities.item.SpeedItem;
import uet.oop.bomberman.entities.movingObject.Balloom;
import uet.oop.bomberman.entities.movingObject.Bomber;
import uet.oop.bomberman.entities.movingObject.Oneal;
import uet.oop.bomberman.entities.tile.Brick;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private int WIDTH = 31;
    private int HEIGHT = 13;
    private int level;

    private List<Entity> enemy = new ArrayList<>();
    private List<Entity> item = new ArrayList<>();
    private List<Entity> wall = new ArrayList<>();
    private List<Entity> brick = new ArrayList<>();
    private Bomber bomber;
    private Portal portal;

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public List<Entity> getEnemy() {
        return enemy;
    }

    public List<Entity> getItem() {
        return item;
    }

    public List<Entity> getWall() {
        return wall;
    }

    public List<Entity> getBrick() {
        return brick;
    }

    public List<Entity> getGlass() {
        return glass;
    }

    public Bomber getBomber() {
        return bomber;
    }

    public Portal getPortal() {
        return portal;
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j);
                    wall.add(object);
                }
            }
        }
        bomber = new Bomber(1,1);
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
            reader.nextLine();
            for(int j=0;j<HEIGHT;j++)
            {
                String line = reader.nextLine();
                System.out.println(line);
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
                            bomber = new Bomber(i,j);
                            break;
                        case '1':
                            enemy.add(new Balloom(i,j));
                            break;
                        case '2':
                            enemy.add(new Oneal(i,j));
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

    public void update() {
        //wall.forEach(Entity::update);
        brick.forEach(Entity::update);
        item.forEach(Entity::update);
        portal.update();
        bomber.update();
        enemy.forEach(Entity::update);
    }

    public void render(GraphicsContext gc) {
        wall.forEach(g -> g.render(gc));
        brick.forEach(g -> g.render(gc));
        item.forEach(g -> g.render(gc));
        portal.render(gc);
        bomber.render(gc);
        enemy.forEach(g -> g.render(gc));
    }
}
