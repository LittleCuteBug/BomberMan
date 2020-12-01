package uet.oop.bomberman;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.item.*;
import uet.oop.bomberman.entities.movingObject.Bomber;
import uet.oop.bomberman.entities.movingObject.MovingEntity;
import uet.oop.bomberman.entities.movingObject.enemy.Balloom;
import uet.oop.bomberman.entities.movingObject.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Brick;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Map {
    public static void loadMap(String filePath, Game game){
        try {
            Scanner reader = new Scanner(new File(filePath));
            int level = reader.nextInt();
            int HEIGHT = reader.nextInt();
            int WIDTH = reader.nextInt();
            game.setLevel(level);
            game.setHEIGHT(HEIGHT);
            game.setWIDTH(WIDTH);
            reader.nextLine();

            List<MovingEntity> enemy = game.getEnemy();
            List<Item> item = game.getItem();
            List<Entity> wall = game.getWall();
            List<Entity> brick = game.getBrick();
            enemy.clear();
            item.clear();
            wall.clear();
            brick.clear();
            Bomber bomber;
            Portal portal;

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
                            game.setPortal(portal);
                            break;
                        case 'p':
                            bomber = new Bomber(i,j,game);
                            game.setBomber(bomber);
                            break;
                        case '1':
                            enemy.add(new Balloom(i,j,game));
                            break;
                        case '2':
                            enemy.add(new Oneal(i,j,game));
                            break;
                        case 'b':
                            item.add(new BombItem(i,j,game));
                            break;
                        case 'f':
                            item.add(new FlameItem(i,j,game));
                            break;
                        case 's':
                            item.add(new SpeedItem(i,j,game));
                            break;
                        case 'd':
                            item.add(new BombPassItem(i, j, game));
                            break;
                        case 'e':
                            item.add(new FlamePassItem(i, j, game));
                            break;
                        case 'g':
                            item.add(new WallPassItem(i, j, game));
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createMap(Game game) {
        int WIDTH = 20;
        int HEIGHT = 20;
        game.setWIDTH(WIDTH);
        game.setHEIGHT(HEIGHT);
        List<Entity> wall = game.getWall();
        wall.clear();
        game.getEnemy().clear();
        game.getItem().clear();
        game.getBrick().clear();

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j);
                    wall.add(object);
                }
            }
        }
        game.setBomber(new Bomber(1,1,game));
        game.setPortal(new Portal(WIDTH-2,HEIGHT-2));
    }

}
