package Geometry;

public class Rectangle extends Geometry implements Comparable<Geometry> {

    private final Point2D a;
    private final Point2D b;

    /**
     * Create a new Geometry rectangle from two 2D points.
     * @param a
     * @param b
     */
    public Rectangle(Point2D a, Point2D b) {
        super(2);
        this.a = a;
        this.b = b;
    }

    /**
     * The volume of the rectangle.
     * @return volume
     */
    @Override
    public double volume() {
        return Math.abs(a.getX() - b.getX()) * Math.abs(a.getY() - b.getY());
    }

    @Override
    public Geometry encapsulate(Geometry other) throws Exception {
        if (other == null || other.dimensions() != super.dimensions()) {
            return null;
        }
        if (other instanceof INdim){
            return other.encapsulate(new Volume(
                    new Point(this.a.getXY()),
                    new Point(this.b.getXY())
            ));
        }

        Rectangle r_min = new Rectangle((Point2D) other.minAxis(), this.minAxis());
        Rectangle r_max = new Rectangle((Point2D) other.maxAxis(), this.maxAxis());
        return new Rectangle(r_min.minAxis(), r_max.maxAxis());
    }

    @Override
    public Point2D minAxis() {
        return new Point2D(
                Math.min(this.a.getX(), this.b.getX()),
                Math.min(this.a.getY(), this.b.getY())
        );
    }

    @Override
    public Point2D maxAxis() {
        return new Point2D(
                Math.max(this.a.getX(), this.b.getX()),
                Math.max(this.a.getY(), this.b.getY())
        );
    }

    @Override
    public int compareTo(Geometry o) {
        return (int) (this.volume() - o.volume());
    }
}
