package Testing.Geometry;
import Geometry.*;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Testing all extensions of Geometry
 * @Author Nicolai Hermann, Michael Hüppe
 */
public class TestGeometry {
    /**
     * Returns a new instance of a Rectangle.
     * @param x1: first point.
     * @param y1: first point.
     * @param x2: second point.
     * @param y2: second point.
     * @return rectangle.
     */
    public Rectangle getRectangle(double x1, double y1, double x2, double y2){
        return new Rectangle(
                new Point2D(x1, y1),
                new Point2D(x2, y2)
        );
    }

    /**
     * Returns a new instance of a Volume.
     * @param a: Coords of Point one.
     * @param b: Coords of Point two.
     * @return Volume.
     * @throws Exception
     */
    private Volume getVolume(double[] a, double[] b) throws Exception {
        return new Volume(
                new Point(a),
                new Point(b)
        );
    }

    /**
     * Makes an array out of many doubles.
     * @param d doubles
     * @return array containing all d's
     */
    private double[] doubles(double... d){
        return d;
    }


    /**
     * Testing that all exceptions are thrown when they should.
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public void testVolumeDimException() throws Exception {
        Volume v = getVolume(doubles(1,2), doubles(1,2,3));
    }

    /**
     * Testing Geometry.volume for all extensions.
     */
    @Test
    public void testVolumePoint2D(){
        Point2D p = new Point2D(0, 0);

        assertEquals(0, p.volume(), 0.0001);
    }

    @Test
    public void testVolumeRectangle(){
        Rectangle r = getRectangle(0,0,2,2);

        assertEquals(4, r.volume(), 0.0001);
    }

    @Test
    public void testVolumePoint(){
        Point p = new Point(doubles(0, 0, 0));

        assertEquals(0, p.volume(), 0.0001);
    }

    @Test
    public void testVolumeVolume() throws Exception {
        Volume v = getVolume(
                doubles(0, -1, 0),
                doubles(2, 3, 4)
        );

        assertEquals(32, v.volume(), 0.0001);
    }

    /**
     * Testing Geometry.minAxis and Geometry.maxAxis for all Geometry extensions.
     */
    @Test
    public void testMinMaxAxisPoint2D(){
        Point2D p = new Point2D(0, 0);

        assertArrayEquals(doubles(0, 0), p.minAxis().getXY(), 0.0001);
        assertArrayEquals(doubles(0, 0), p.maxAxis().getXY(), 0.0001);
    }

    @Test
    public void testMinMaxAxisRectangle(){
        Rectangle r = getRectangle( -2, -3, 1, 10);

        assertArrayEquals(doubles(-2, -3), r.minAxis().getXY(), 0.0001);
        assertArrayEquals(doubles(1, 10), r.maxAxis().getXY(), 0.0001);

    }

    @Test
    public void testMinMaxAxisPoint(){
        Point p = new Point(doubles(1,2,3));

        assertArrayEquals(doubles(1,2,3), p.minAxis().getPosition(), 0.0001);
        assertArrayEquals(doubles(1,2,3), p.maxAxis().getPosition(), 0.0001);
    }

    @Test
    public void testMinMaxAxisVolume() throws Exception {
        Volume v = getVolume(
                doubles(1, -2, 3),
                doubles(10, -8, -2)
        );
        assertArrayEquals(doubles(1, -8, -2), v.minAxis().getPosition(), 0.0001);
        assertArrayEquals(doubles(10, -2, 3), v.maxAxis().getPosition(), 0.0001);
    }

    /**
     * Test Geometry.encapsulate for all Geometry extensions.
     */

    @Test
    public void testEncapsulateInvalidDimsPoint2D() throws Exception {
        Point2D p0 = new Point2D(2,2);
        Point p = new Point(doubles(3, 3, 3));

        assertNull(p0.encapsulate(p));
    }

    @Test
    public void testEncapsulateInvalidDimsRectangle() throws Exception {
        Rectangle r = getRectangle(2,2,2,2);
        Point p = new Point(doubles(3, 3, 3));

        assertNull(r.encapsulate(p));
    }

    @Test
    public void testEncapsulateInvalidDimsPoint() throws Exception {
        Point p0 = new Point(doubles(4, 4, 4, 4));
        Point p = new Point(doubles(3, 3, 3));

        assertNull(p0.encapsulate(p));
    }

    @Test
    public void testEncapsulateInvalidDimsVolume() throws Exception {
        Volume v = getVolume(doubles(4, 4, 4, 4), doubles(0, 0, 0, 0));
        Point p = new Point(doubles(3, 3, 3));

        assertNull(v.encapsulate(p));
    }

    private void testCorners(double[] lowerLeft, double[] upperRight, Geometry g){
        if(g instanceof Rectangle r){
            assertArrayEquals(lowerLeft, r.minAxis().getXY(), 0.0001);
            assertArrayEquals(upperRight, r.maxAxis().getXY(), 0.0001);
        } else {
            Volume v = (Volume) g;
            assertArrayEquals(lowerLeft, v.minAxis().getPosition(), 0.0001);
            assertArrayEquals(upperRight, v.maxAxis().getPosition(), 0.0001);
        }
    }


    @Test
    public void testEncapsulatePoint2DRectangle() throws Exception {
        Point2D tester = new Point2D(0, 0);

        Point2D p = new Point2D(-2, 2);

        // two points.
        testCorners(doubles(-2, 0), doubles(0, 2),
                    tester.encapsulate(p));
        testCorners(doubles(-2, 0), doubles(0, 2),
                p.encapsulate(tester));

        Combinations2D(tester);
    }

    @Test
    public void testEncapsulateRectangleRectangle() throws Exception {
        Rectangle tester = getRectangle(0,0, 0.00001, 0.00001);
        Combinations2D(tester);
    }

    @Test
    public void testEncapsulate2DWith3DPoint() throws Exception {
        Point tester = new Point(doubles(0, 0));
        Point2D p = new Point2D(-2, 2);

        // two points.
        testCorners(doubles(-2, 0), doubles(0, 2),
                tester.encapsulate(p));
        testCorners(doubles(-2, 0), doubles(0, 2),
                p.encapsulate(tester));
        Combinations2D(tester);
    }

    @Test
    public void testEncapsulate2DWith3DVolume() throws Exception {
        Volume tester = getVolume(doubles(0, 0), doubles(0.00001, 0.00001));
        Combinations2D(tester);
    }

    private void Combinations2D(Geometry tester) throws Exception {
        // right upper + reversed.
        Rectangle rightUpper = getRectangle(1, 2, 2, 1); // specified lu and rl.
        testCorners(doubles(0, 0), doubles(2, 2),
                    tester.encapsulate(rightUpper));
        testCorners(doubles(0, 0), doubles(2, 2),
                rightUpper.encapsulate(tester));

        // right upper and lower + reversed.
        Rectangle rightUpperAndLower = getRectangle(1, 1, 2,-1); // specified lu and rl.
        testCorners(doubles(0, -1), doubles(2, 1),
                    tester.encapsulate(rightUpperAndLower));
        testCorners(doubles(0, -1), doubles(2, 1),
                rightUpperAndLower.encapsulate(tester));

        // lower left and right + reversed.
        Rectangle lowerLeftAndRight = getRectangle(-1, -2, 1, -1); // specified ll and ur.
        testCorners(doubles(-1, -2), doubles(1, 0),
                    tester.encapsulate(lowerLeftAndRight));
        testCorners(doubles(-1, -2), doubles(1, 0),
                lowerLeftAndRight.encapsulate(tester));

        // outer and inner inclusion.
        Rectangle outer = getRectangle(-10,-10, 10, 10);
        testCorners(doubles(-10,-10), doubles(10,10),
                    tester.encapsulate(outer));
        testCorners(doubles(-10,-10), doubles(10,10),
                    outer.encapsulate(tester)); //outer might now be inner
    }


    /**
     * 3D / N-Dim
     */
    @Test
    public void testEncapsulatePointRectangle() throws Exception {
        Point tester = new Point(doubles(0, 0, 0));

        Point p = new Point(doubles(-2, 2, -2));

        // two points.
        testCorners(doubles(-2, 0, -2), doubles(0, 2, 0),
                tester.encapsulate(p));
        testCorners(doubles(-2, 0, -2), doubles(0, 2, 0),
                p.encapsulate(tester));

        Combinations3D(tester);
    }

    @Test
    public void testEncapsulateVolumeVolume() throws Exception {
        Volume tester = getVolume(doubles(0, 0, 0), doubles(0.00001, 0.00001, 0.00001));
        Combinations3D(tester);
    }


    private void Combinations3D(Geometry tester) throws Exception {
        // right upper + reversed.
        Volume rightUpper = getVolume(doubles(1, 2, 0), doubles(2, 1, 2)); // specified lu and rl.
        testCorners(doubles(0, 0, 0), doubles(2, 2, 2),
                tester.encapsulate(rightUpper));
        testCorners(doubles(0, 0, 0), doubles(2, 2, 2),
                rightUpper.encapsulate(tester));

        // right upper and lower + reversed.
        Volume rightUpperAndLower = getVolume(doubles(1, 1, -1), doubles(2, -1, 1)); // specified lu and rl.
        testCorners(doubles(0, -1, -1), doubles(2, 1, 1),
                tester.encapsulate(rightUpperAndLower));
        testCorners(doubles(0, -1, -1), doubles(2, 1, 1),
                rightUpperAndLower.encapsulate(tester));

        // lower left and right + reversed.
        Volume lowerLeftAndRight = getVolume(doubles(-1, -2, 0), doubles(1, -1, 7.3)); // specified ll and ur.
        testCorners(doubles(-1, -2, 0), doubles(1, 0, 7.3),
                tester.encapsulate(lowerLeftAndRight));
        testCorners(doubles(-1, -2, 0), doubles(1, 0, 7.3),
                lowerLeftAndRight.encapsulate(tester));

        // outer and inner inclusion.
        Volume outer = getVolume(doubles(-10,-10, -10), doubles(10, 10, 10));
        testCorners(doubles(-10, -10, -10), doubles(10, 10, 10),
                tester.encapsulate(outer));
        testCorners(doubles(-10, -10, -10), doubles(10, 10, 10),
                outer.encapsulate(tester)); //outer might now be inner
    }

}
