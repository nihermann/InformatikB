/**
 * @Author Michael Hüppe, Nicolai Hermann.
 */
package Geometry;

public class Point extends Geometry implements Comparable<Geometry>{

    private final double[] coordinates;
    /**
     * Create a new Geometry. Every Geometry must have a dimension
     * of at least 2. A Point can be created with a double of coordinates or
     * two doubles (representing a Point2D)
     *
     * @throws RuntimeException if the number of dimensions dimension
     *                          is lesser than 2.
     */
    public Point(double[] coordinates) {
        super(coordinates.length);
        this.coordinates = coordinates;
    }

    /**
     * @return the Coordinates array
     */
    public double[] getCoordinates() {
        return coordinates;
    }

    /**
     * The volume of a Point is 0
     * @return 0
     */
    @Override
    public double volume() {
        return 0;
    }

    /**
     * @param points Array of Points two compare to
     * @param max Boolean to return either the maximum or the Minimum
     */
    public static Point getExtreme(Point[] points, boolean max){
        double[] extreme = new double[points[0].dimensions()];

        for (int i = 0; i < points.length-1; i++){
            // for each coordinate in each given point calculate the extreme over all points
            double[] current = points[i].getCoordinates();

            for (int j = 0; j < current.length; j++){
                if (max){
                    if (i == 0){ extreme[j] = current[j]; }
                    extreme[j] = Math.max(extreme[j], current[j]);
                }
                else{
                    if (i == 0){ extreme[j] = current[j]; }
                    extreme[j] = Math.min(extreme[j], current[j]);
                }
            }
        }
        return new Point(extreme);
    }

    /**
     * @param other another Geometry which is supposed to be encapsulate with this point
     * @return another volume which encapsulates both the point and the other Geometry
     */
    @Override
    public Geometry encapsulate(Geometry other) {
        if (this.incompatibleDimensions(other)){
            return null;
        }

        if (other instanceof Point p){
            // if there are only two points these have to be the new corners
            return new Volume(this, p);
        }

        if (other instanceof Volume v){
            // get the two extreme points (minimum and maximum and make them the corner points of the new volume
            Point[] points = new Point[]{this, v.getP1(), v.getP2()};
            return new Volume(
                    getExtreme(points,false),
                    getExtreme(points,true)
            );
        }
        return null;
    }

    @Override
    public int compareTo(Geometry o) {
        return (int) (this.volume() - o.volume());
    }
}
