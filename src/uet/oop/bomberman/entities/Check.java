package uet.oop.bomberman.entities;

public class Check {
    public static boolean touchCheck(double x, double y, Entity entity) {
        return (Math.abs(x - entity.getX()) < 1) && (Math.abs(y - entity.getY()) < 1);
    }

    public static boolean overlapCheck(double x, double y, Entity entity) {
        return (Math.abs(x - entity.getX()) <0.3) && (Math.abs(y - entity.getY()) < 0.3);
    }
}
