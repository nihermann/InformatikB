package Geometry;

public class Point2D extends Geometry{


    private final double y;
    private final double x;

    /**
     * Create a new Geometry Point 2D.
     * @param x
     * @param y
     */
    public Point2D(double x, double y) {
        super(2);
        this.x = x;
        this.y = y;
    }

    /**
     * A point does not have a volume.
     * @return 0
     */
    @Override
    public double volume() {
        return 0;
    }

    @Override
    public Geometry encapsulate(Geometry other) throws Exception {
        if (other == null || other.dimensions() != 2) {
            return null;
        }

        if (other instanceof INdim){
            return other.encapsulate(new Point(new double[]{this.x, this.y}));
        }

        Rectangle r_min = new Rectangle((Point2D) other.minAxis(),this);
        Rectangle r_max = new Rectangle((Point2D) other.maxAxis(), this);

        return new Rectangle(r_min.minAxis(), r_max.maxAxis());
    }

    @Override
    public Point2D minAxis() {
        return this;
    }

    @Override
    public Point2D maxAxis() {
        return this;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double[] getXY(){ return new double[]{this.x, this.y};}
}
