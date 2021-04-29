/**
 * @Author Nicolai Hermann, Michael Hüppe
 */
public class Arena {
    /**
     * The radius of the arena.
     */
    private double radius = 1.5;

    /**
     * Tracks if a (x, y) coordinate lies inside the arena and if so in which sector.
     * @param x double - x coordinate.
     * @param y double - y coordinate.
     * @return int - -1 if the coordinates are outside of the arena, else the number of the sector in which the coordinates lye.
     */
    public int getArea(double x, double y){
        // if the distance to the origin is higher than the radius of the arena, the Tribute is no longer in the arena.
        double distanceToOrigin = Math.sqrt(x*x + y*y);
        if (distanceToOrigin > this.radius){
            return -1;
        }
        // In which sector is the tribute?
        // First we want to know the angel to the Y-Axis.
        double angelToY = Math.acos(y/distanceToOrigin);
        // convert it to degree and round it as we are later casting to int and dont want a false
        // classification if the angel e.g. was 29.9999 due to imprecision of double.
        angelToY = Math.round(Math.toDegrees(angelToY));

        // extract the sign of x to know if the angel is to the right or left of the Y-Axis.
        angelToY *= x < 0 ? -1 : 1;
        // if x is negative we would get a negative angel which is invalid. If x is positive the added 360 will cancel out.
        int degree_norm = (int) ((360 + angelToY) % 360);
        // divide by 12 + 1 to count through with 1-12 and not 0-11.
        return (degree_norm/30) + 1;
    }
}
