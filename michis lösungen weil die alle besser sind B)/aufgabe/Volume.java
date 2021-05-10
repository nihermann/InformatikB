public class Volume extends Geometry implements Comparable<Geometry>{
    private Point p1;
    private Point p2;

    /**
     * Create a new Volume extends Geometry. Every Geometry must have a dimension
     * of at least 2. A Volume can be created with two Poins or Point2Ds.
     *
     * @throws RuntimeException if the number of dimensions dimension
     *                          is lesser than 2.
     */
    public Volume(Point p1, Point p2) {
        super(p1.dimensions());
        this.p1 = p1;
        this.p2 = p2;

    }

    public Volume(Point2D p1, Point2D p2) {
        super(p1.dimensions());
        this.p1 = p1;
        this.p2 = p2;

    }

    /**
     * Getter for the two Points of the volume.
     */
    public Point getP1(){return this.p1;}

    public Point getP2(){return this.p2;}

    /**
     * Calculating the volume of a Volume.
     */
    @Override
    public double volume() {
        double[] coordinates_p1 = p1.getCoordinates();
        double[] coordinates_p2 = p2.getCoordinates();

        double volume = 1;
        for (int i = 0; i < p1.dimensions(); i++){
            volume *= Math.abs(coordinates_p1[i] - coordinates_p2[i]);
        }
        return volume;
    }

    /**
     * @param other another Geometry which is supposed to be encapsulate with this Volume
     * @return another volume which encapsulates both this Volume and the other Geometry
     */
    @Override
    public Geometry encapsulate(Geometry other) {
        if (!this.sameDimensions(other)){ return null;}
        else if (this.getClass() == other.getClass()){
            Point[] points = new Point[]{this.getP1(), this.getP2(),((Volume) other).getP1(),((Volume) other).getP2()};
            return new Volume(Point.getExtreme(points,false), Point.getExtreme(points,true));
        }else{return other.encapsulate(this);}
    }

    @Override
    public int compareTo(Geometry o) {
        return (int) (this.volume() - o.volume());
    }
}
