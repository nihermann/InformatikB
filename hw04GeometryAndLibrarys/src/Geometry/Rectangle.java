/**
 * @Author Michael Hüppe, Nicolai Hermann.
 */
package Geometry;

public class Rectangle extends Volume {
    /**
     * A rectangle is a 2 dimensional Volume
     *
     * @param p1,p2 Point2D representing the corners of the rectangle
     */
    public Rectangle(Point2D p1, Point2D p2) {
        super(p1, p2);
    }
}
