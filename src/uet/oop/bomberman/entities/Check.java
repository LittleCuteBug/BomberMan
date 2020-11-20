package uet.oop.bomberman.entities;

public class Check {
    public static boolean touchCheck(double x, double y, Entity entity) {
        if (Math.abs(x - entity.getX()) < 1) {
            return false;
        }
        if (Math.abs(y - entity.getY()) < 1) {
            return false;
        }
        return true;
    }
}
