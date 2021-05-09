import java.nio.channels.Pipe;
import java.sql.SQLSyntaxErrorException;
import java.util.Arrays;

public class Point extends Geometry implements Comparable{

    private double[] coordinates;
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

    public Point(double x, double y) {
        super(2);
        double[] coordinates = new double[2];
        coordinates[0] = x;
        coordinates[1] = y;
        this.coordinates = coordinates;
    }


    // getter for the Coordinates array
    public double[] getCoordinates() {
        return coordinates;
    }

    // The volume of a Point is 0
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
            double[] next = points[i+1].getCoordinates();

            for (int j = 0; j < current.length; j++){
                if (max){
                    if (i == 0){ extreme[j] = Math.max(current[j], next[j]); }
                    extreme[j] = Math.max(extreme[j],Math.max(current[j], next[j]));
                }
                else{
                    if (i == 0){ extreme[j] = Math.min(current[j], next[j]); }
                    extreme[j] = Math.min(extreme[j],Math.min(current[j], next[j]));
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
        if (!this.sameDimensions(other)){ return null;}
        else if (this.getClass() == other.getClass()){
            // if there are only two points these have to be the new corners
            return new Volume(this, (Point) other);
        }else if (other instanceof Volume){
//            get the two extreme points (minimum and maximum and make them the corner points of the new volume
            Point[] points = new Point[]{this,((Volume) other).getP1(),((Volume) other).getP2()};
            return new Volume(getExtreme(points,false), getExtreme(points,true));
        }
        return null;
    }

    /**
     * @param geom geometry which is to be compared with this instance
     * @return the difference of the volume from the given point and the other instance
     */
    @Override
    public double compareTo(Geometry geom) {
        return this.volume()-geom.volume();
    }

    /**
     * @param o to be compared object (if not point return false)
     * @return check if the other object is a Point and if it is checks if their coordinates are the same
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Point){
            return Arrays.equals(this.getCoordinates(), ((Point) o).getCoordinates());
        }
        return false;
    }
}
