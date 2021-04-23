/**
 *
 */

import Test.Test;

public class TestArena {
    public static double[] getXY(int sector, int offSetInDegree){
        double[] xy = new double[2];
        double offset = Math.toRadians(offSetInDegree);
        double pos = ((double) (sector-1) /12 * 2 * Math.PI + offset);
        xy[0] = Math.sin(pos);
        xy[1] = Math.cos(pos);
        return xy;
    }
    public static void returnMinusOneIfOutsideTheArena(){
        Arena a = new Arena();
        int result = a.getArea(-2d, -1d);
        Test.shouldBeTrue(result == -1, "Outside the arena should be -1, received " + result);
    }

    public static void shouldBeInCorrectSector(){
        Arena a = new Arena();

        for (int sector = 1; sector <= 12; sector++){

            double[] xy = getXY(sector, 15);
            //System.out.print(" x: " + String.format("%.02f", xy[0]) + " y: " + String.format("%.02f", xy[1]) + " pred: ");
            int result = a.getArea(xy[0], xy[1]);
            Test.shouldBeTrue(
                    result == sector,
                    "Should be in sector " + sector + " but claimed to be in sector " + result + "  " + sector * 30
            );
        }
    }

    public static void testAll(){
        returnMinusOneIfOutsideTheArena();
        shouldBeInCorrectSector();
        System.out.println("Number of errors: " + Test.getErrorCount());
    }

    public static void main(String[] args){
        TestArena.testAll();
        /*for (int i = 1; i < 13; i++){
            double[] xy = getXY(i, 15);
            System.out.println("i: " + i + " x: " + String.format("%.02f", xy[0]) + " y: " + String.format("%.02f", xy[1]));
        }

        for (double r = 0; r < 6.3d; r += 2*Math.PI/12){
            while (r > Math.PI){

            }
            double x = Math.sin(r);
            double x_after = Math.asin(x);
            System.out.println("R: " + r + " BEFORE: " + x + " AFTER: " + x_after);
        }*/

    }
}
