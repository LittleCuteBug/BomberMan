package uet.oop.bomberman.entities.movingObject;
import uet.oop.bomberman.entities.Direction;
import java.util.LinkedList;
import java.util.Queue;

public class FindPath {
    private static class Pair{
        public int x; public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static final int[] tx = {1,-1,0,0};
    private static final int[] ty = {0,0,1,-1};

    public static Direction getDirection(int[][] map, int radios, int yStart, int xStart, int yEnd, int xEnd){
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.add(new Pair(xStart,yStart));
        map[xStart][yStart] = 1;
        while (!queue.isEmpty())
        {
            Pair pair = queue.remove();
            for(int i=0;i<4;i++) {
                if(pair.x + tx[i]>=0&&pair.x + tx[i]<=radios*2&&pair.y + ty[i]>=0&&pair.y + ty[i]<=radios*2){
                    if (map[pair.x + tx[i]][pair.y + ty[i]] == 0) {
                        map[pair.x + tx[i]][pair.y + ty[i]] = i+2;
                        queue.add(new Pair(pair.x + tx[i], pair.y + ty[i]));
                    }
                }
            }
        }

        if(map[xEnd][yEnd]==0)
            return null;

        queue.add(new Pair(xEnd,yEnd));
        while(!queue.isEmpty()){
            Pair pair = queue.remove();
            int i = map[pair.x][pair.y]-2;
            if(i<0) break;
            queue.add(new Pair(pair.x - tx[i],pair.y - ty[i]));
            map[pair.x][pair.y] = 6;
        }

        if(xStart>0&&map[xStart-1][yStart]==6) {
            return Direction.UP;
        }
        if(xStart<radios*2&&map[xStart+1][yStart]==6) {
            return Direction.DOWN;
        }
        if(yStart>0&&map[xStart][yStart-1]==6) {
            return Direction.LEFT;
        }
        if(yStart<radios*2&&map[xStart][yStart+1]==6) {
            return Direction.RIGHT;
        }
        return null;
    }

    public static void main(String[] args) {
        int[][] map = {
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,1,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0}
        };

        FindPath.getDirection(map,3,3,3,0,0);
    }
}
