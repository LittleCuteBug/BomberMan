package uet.oop.bomberman.entities;

public class Check {
    public static boolean touchCheck(double x, double y, Entity entity) {
        return (Math.abs(x - entity.getX()) < 1) && (Math.abs(y - entity.getY()) < 1);
    }

    public static boolean overlapCheck(double x, double y, Entity entity) {
        return (Math.abs(x - entity.getX()) <0.3) && (Math.abs(y - entity.getY()) < 0.3);
    }

    public static double getManhattanDistance(double x, double y, Entity entity) {
        return Math.abs(x - entity.getX()) + Math.abs(y - entity.getY());
    }

    public static double getDistance(double x, double y, Entity entity) {
        return Math.max(Math.abs(x - entity.getX()) , Math.abs(y - entity.getY()));
    }

    public static double getInlineDistance(double x, double y, Entity entity) {
        if(Math.abs(x - entity.getX())<0.5)
            return Math.abs(y - entity.getY());
        if(Math.abs(y - entity.getY())<0.5)
            return Math.abs(x - entity.getX());
        return 1000;
    }
}
