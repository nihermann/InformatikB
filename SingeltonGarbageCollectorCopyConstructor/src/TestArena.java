/**
 * Test the functionalities of Arena.java
 *
 * @Author Nicolai Hermann, Michael Hüppe
 */

import Test.Test;

public class TestArena {
    /**
     * Starting form the Y-Axis it calculates a point on the unit circle in 30 Degree steps, given by the value of sector.
     *
     * @param sector         int - in which sector the coordinates should be. Counting starts at x=0, y=1
     * @param offSetInDegree int - an offset between 0 and 29 Degree.
     * @return double array containing the x and y coordinates of the point.
     */
    public static double[] getXY(int sector, int offSetInDegree) {
        if (!(0 <= offSetInDegree && offSetInDegree < 30)) {
            // if off set would shift into another sector it will not be used.
            offSetInDegree = 0;
        }
        double[] xy = new double[2];
        double offset = Math.toRadians(offSetInDegree);
        // 2pi would be one whole cycle on the circle. We just divide this by 12 for the sectors
        // and multiply by the specified sector-1 as we count the mathematical 0th sector as 1.
        double pos = ((double) (sector - 1) / 12 * 2 * Math.PI + offset);
        xy[0] = Math.sin(pos);
        xy[1] = Math.cos(pos);
        return xy;
    }

    public static void returnMinusOneIfOutsideTheArena() {
        Arena a = new Arena();

        int result = a.getArea(-2d, -1d);

        Test.shouldBeTrue(result == -1, "Outside the arena should be -1, received " + result);
    }

    public static void shouldBeInCorrectSector() {
        Arena a = new Arena();

        // check every sector for validity.
        for (int sector = 1; sector <= 12; sector++) {

            double[] xy = getXY(sector, 15);
            int result = a.getArea(xy[0], xy[1]);

            Test.shouldBeTrue(
                    result == sector,
                    "Should be in sector " + sector + " but claimed to be in sector " + result
            );
        }
    }

    public static void testAll() {
        returnMinusOneIfOutsideTheArena();
        shouldBeInCorrectSector();
        System.out.println("Number of errors: " + Test.getErrorCount());
    }

    public static void main(String[] args) {
        TestArena.testAll();

    }
}
