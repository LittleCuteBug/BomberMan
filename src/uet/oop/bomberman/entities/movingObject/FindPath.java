package uet.oop.bomberman.entities.movingObject;
import uet.oop.bomberman.entities.Direction;
import java.util.LinkedList;
import java.util.Queue;

public class FindPath {
    private static class Pair{
        public int fi; public int se;

        public Pair(int fi, int se) {
            this.fi = fi;
            this.se = se;
        }
    }
    private static final int[] tx = {1,-1,0,0};
    private static final int[] ty = {0,0,1,-1};

    public static Direction getDirection(int[][] map, int radios, int xStart, int yStart, int xEnd, int yEnd){
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.add(new Pair(yStart,xStart));
        map[yStart][xStart] = 1;
        while (!queue.isEmpty())
        {
            Pair pair = queue.remove();
            for(int i=0;i<4;i++) {
                if(pair.fi + ty[i]>=0&&pair.fi + ty[i]<=radios*2&&pair.se + tx[i]>=0&&pair.se + tx[i]<=radios*2){
                    if (map[pair.fi + ty[i]][pair.se + tx[i]] == 0) {
                        map[pair.fi + ty[i]][pair.se + tx[i]] = i+2;
                        queue.add(new Pair(pair.fi + ty[i], pair.se + tx[i]));
                    }
                }
            }
        }

        if(map[yEnd][xEnd]==0)
            return null;

        queue.add(new Pair(yEnd,xEnd));
        while(!queue.isEmpty()){
            Pair pair = queue.remove();
            int i = map[pair.fi][pair.se]-2;
            if(i<0) break;
            queue.add(new Pair(pair.fi - ty[i],pair.se - tx[i]));
            map[pair.fi][pair.se] = 6;
        }

        if(xStart>0&&map[yStart][xStart-1]==6) {
            return Direction.LEFT;
        }
        if(xStart<radios*2&&map[yStart][xStart+1]==6) {
            return Direction.RIGHT;
        }
        if(yStart>0&&map[yStart-1][xStart]==6) {
            return Direction.UP;
        }
        if(yStart<radios*2&&map[yStart+1][xStart]==6) {
            return Direction.DOWN;
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
