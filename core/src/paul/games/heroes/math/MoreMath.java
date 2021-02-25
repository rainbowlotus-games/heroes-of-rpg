package paul.games.heroes.math;

import java.util.Random;

public class MoreMath {
    public static double distanceBetween(int x1, int y1, int x2, int y2) {
        return java.lang.Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }
    public static double randomRange(double min, double max) {
        Random r = new Random();
        return min + r.nextDouble() * (max - min);
    }
    public static float randomRange(float min, float max) {
        Random r = new Random();
        return min + r.nextFloat() * (max - min);
    }
    public static int randomRange(int min, int max) {
        Random r = new Random();
        return min + r.nextInt() * (max - min);
    }
    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }
    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
    /**
     * Multiply val by max and then clamp between min and max.
     * @param val
     * @param min
     * @param max
     * @return
     */
    public static double multiClamp(double val, double min, double max) {
        return clamp(val*max, min, max);
    }
    public static boolean isValueBetween(double val, double left, double right) {
        return val >= left && val <= right;
    }
}
