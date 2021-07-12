package Interfaces;


public class Quadrat implements Drawable {

    @Override
    public void draw(int radius) {
        for( int i = 0; i<=radius; i++){
            for(int j = 0; j<=radius; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
