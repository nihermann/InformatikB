package Geometry;

public class Volume extends Geometry implements INdim, Comparable<Geometry> {

    private final Point a;
    private final Point b;

    /**
     * Create a new Geometry rectangle from two n-Dim points.
     * @param a
     * @param b
     */
    public Volume(Point a, Point b) throws Exception {
        super(a.dimensions());
        if (a.dimensions() != b.dimensions()){
            throw new Exception("Both points must have an equal dimensionality.");
        }
        this.a = a;
        this.b = b;
    }

    @Override
    public double volume() {
        double[] a_pos = this.a.getPosition();
        double[] b_pos = this.b.getPosition();
        double volume = 1;
        for (int i = 0; i < a.dimensions(); i++){
            volume *= Math.abs(a_pos[i] - b_pos[i]);
        }
        return volume;
    }

    @Override
    public Geometry encapsulate(Geometry other) throws Exception{
        if (other == null || other.dimensions() != super.dimensions()) {
            return null;
        }
        if (!(other instanceof INdim)){
            return other.encapsulate(new Rectangle(
                    new Point2D(this.a.getPosition()[0], this.a.getPosition()[1]),
                    new Point2D(this.b.getPosition()[0], this.b.getPosition()[1])
            ));
        }
        Volume v_min = new Volume((Point) other.minAxis(), this.minAxis());
        Volume v_max = new Volume((Point) other.maxAxis(), this.maxAxis());

        return new Volume(v_min.minAxis(), v_max.maxAxis());
    }

    @Override
    public Point minAxis() {
        double[] min = new double[this.a.dimensions()];
        double[] a = this.a.getPosition();
        double[] b = this.b.getPosition();
        for (int i = 0; i<this.a.dimensions(); i++){
            min[i] = Math.min(a[i], b[i]);
        }
        return new Point(min);
    }

    @Override
    public Point maxAxis() {
        double[] max = new double[this.a.dimensions()];
        double[] a = this.a.getPosition();
        double[] b = this.b.getPosition();
        for (int i = 0; i<this.a.dimensions(); i++){
            max[i] = Math.max(a[i], b[i]);
        }
        return new Point(max);
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    @Override
    public int compareTo(Geometry o) {
        return (int) (this.volume() - o.volume());
    }
}
