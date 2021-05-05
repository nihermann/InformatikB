package Geometry;

public class Point extends Geometry implements INdim, Comparable<Geometry> {
    public double[] getPosition() {
        return position;
    }

    private final double[] position;

    /**
     * Create a new Geometry. Every Geometry must have a <code>dimension</code>
     * of at least 2.
     *
     * @param position array of coordinates of the n-dimensional point.
     * @throws RuntimeException if the number of dimensions <code>dimension</code>
     *                          is lesser than 2.
     */
    public Point(double[] position) {
        super(position.length);
        this.position = position;
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
        if (other == null || other.dimensions() != super.dimensions()) {
            return null;
        }
        if (!(other instanceof INdim)){
            return other.encapsulate(new Point2D(this.position[0], this.position[1]));
        }

        Volume v_min = new Volume((Point) other.minAxis(), this);
        Volume v_max = new Volume((Point) other.maxAxis(), this);

        return new Volume(v_min.minAxis(), v_max.maxAxis());
    }

    @Override
    public Point minAxis() {
        return new Point(this.position);
    }

    @Override
    public Point maxAxis() {
        return new Point(this.position);
    }

    @Override
    public int compareTo(Geometry o) {
        return (int) (this.volume() - o.volume());
    }
}
