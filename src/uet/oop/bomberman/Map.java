package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.item.*;
import uet.oop.bomberman.entities.movingObject.Bomber;
import uet.oop.bomberman.entities.movingObject.MovingEntity;
import uet.oop.bomberman.entities.movingObject.enemy.*;
import uet.oop.bomberman.entities.tile.Brick;
import uet.oop.bomberman.entities.tile.ItemBrick;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.sounds.Sound;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Map {
    public static void clearMap(Game game){
        game.getEnemy().clear();
        game.getItem().clear();
        game.getWall().clear();
        game.getBrick().clear();
        game.getBomb().clear();
        game.getFlame().clear();
    }
    public static void loadMap(String filePath, Game game){
        try {
            clearMap(game);
            Scanner reader = new Scanner(new File(filePath));
            int level = reader.nextInt();
            int HEIGHT = reader.nextInt();
            int WIDTH = reader.nextInt();
            game.setLevel(level);
            game.setHEIGHT(HEIGHT);
            game.setWIDTH(WIDTH);
            reader.nextLine();
            List<MovingEntity> enemy = game.getEnemy();
            List<Entity> wall = game.getWall();
            List<Entity> brick = game.getBrick();
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
                            portal = new Portal(i,j, game);
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
                        case '3':
                            enemy.add(new Kondoria(i, j, game));
                            break;
                        case 'b':
                            brick.add(new ItemBrick(i,j,new BombItem(i,j,game),game));
                            break;
                        case 'f':
                            brick.add(new ItemBrick(i,j,new FlameItem(i,j,game),game));
                            break;
                        case 's':
                            brick.add(new ItemBrick(i,j,new SpeedItem(i,j,game),game));
                            break;
                        case 'd':
                            brick.add(new ItemBrick(i,j,new BombPassItem(i, j, game),game));
                            break;
                        case 'e':
                            brick.add(new ItemBrick(i,j,new FlamePassItem(i, j, game),game));
                            break;
                        case 'g':
                            brick.add(new ItemBrick(i,j,new WallPassItem(i, j, game),game));
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createMap(Game game) {
        clearMap(game);
        int WIDTH = 20;
        int HEIGHT = 20;
        game.setWIDTH(WIDTH);
        game.setHEIGHT(HEIGHT);
        List<Entity> wall = game.getWall();
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
        game.setPortal(new Portal(WIDTH-2,HEIGHT-2, game));
    }
    public static void initGame(Game game){
        int WIDTH = 20;
        int HEIGHT = 10;
        game.setWIDTH(WIDTH);
        game.setHEIGHT(HEIGHT);
        game.setLevel(0);
        game.setBomber(new Bomber(1,1,game));
        genMap(game);
    }

    public static void nextLevel(Game game){
        int WIDTH = game.getWIDTH()+2;
        int HEIGHT = game.getHEIGHT()+2;
        game.setWIDTH(WIDTH);
        game.setHEIGHT(HEIGHT);
        game.increaseLevel();
        genMap(game);
    }
    public static void genMap(Game game) {
        clearMap(game);
        int WIDTH = game.getWIDTH();
        int HEIGHT = game.getHEIGHT();
        List<Entity> wall = game.getWall();
        List<Entity> brick = game.getBrick();
        boolean[][] cellUsed = new boolean[WIDTH][HEIGHT];
        game.getBomber().reborn();
        cellUsed[1][1] = true;
        cellUsed[1][2] = true;
        cellUsed[2][1] = true;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if(!cellUsed[i][j]){
                    if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1 || (i%2 == 0 && j%2 == 0 )){
                        wall.add(new Wall(i,j));
                        cellUsed[i][j]=true;
                    } else {
                        double randValue = Math.random();
                        if (randValue < 0.4) {
                            brick.add(new Brick(i, j));
                            cellUsed[i][j]=true;
                        }
                    }

                }
            }
        }
        while (true){
            Random random = new Random();
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            if(!cellUsed[x][y]) {
                game.setPortal(new Portal(x, y,game));
                cellUsed[x][y] = true;
                break;
            }
        }
        int cntItem = 0;
        int maxItem = game.getLevel()/3+2;
        while (cntItem<maxItem) {
            Random random = new Random();
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            if (!cellUsed[x][y]) {
                Item item;
                int type = random.nextInt(3);
                if(game.getLevel()>=5)
                    type = random.nextInt(6);
                switch (type){
                    case 0:
                        item = new BombItem(x,y,game);
                        break;
                    case 1:
                        item = new FlameItem(x,y,game);
                        break;
                    case 2:
                        item = new SpeedItem(x,y,game);
                        break;
                    case 3:
                        item = new BombPassItem(x,y,game);
                        break;
                    case 4:
                        item = new FlamePassItem(x,y,game);
                        break;
                    default:
                        item = new WallPassItem(x,y,game);
                }
                brick.add(new ItemBrick(x,y,item,game));
                cntItem++;
                cellUsed[x][y]=true;
            }
        }
        List<MovingEntity> enemy = game.getEnemy();
        int cntEnemy = 0;
        int maxEnemy = game.getLevel()*2+3;
        while (cntEnemy<maxEnemy) {
            Random random = new Random();
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            if (!cellUsed[x][y]) {
                int type = random.nextInt(Math.min(game.getLevel()+1,6));
                switch (type){
                    case 0:
                        enemy.add(new Balloom(x,y,game));
                        break;
                    case 1:
                        enemy.add(new Oneal(x,y,game));
                        break;
                    case 2:
                        enemy.add(new Doll(x,y, game));
                        break;
                    case 3:
                        enemy.add(new Minvo(x,y, game));
                        break;
                    case 4:
                        enemy.add(new Kondoria(x,y, game));
                        break;
                    case 5:
                        enemy.add(new Ovapi(x,y, game));
                        break;
                }
                cntEnemy++;
                cellUsed[x][y]=true;
            }
        }
        Sound.STAGE_COMPLETE_SOUND.stop();
        Sound.STAGE_THEME_SOUND.stop();
        Sound.ENDING_SOUND.stop();
        Sound.STAGE_START_SOUND.play();
    }
}
