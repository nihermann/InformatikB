package antRace;

import antRace.AntField.Field;

import java.util.Arrays;

/**
 * An {@code Ant} is created at a specific position of an {@link AntField} with
 * an initial {@code stepCount}. When running an Ant, it will lookup the values
 * on the current and all surrounding {@link Field}
 * (Moore-neighborhood) instances and test if the position is free, i.e. has a
 * value of {@code 0}, or if the value is greater than the {@code stepCount} of
 * this Ant. For both cases, the Ant will set the value of the {@code Field} at
 * the visited position to its own {@code stepCount+1}. After an {@code Ant} has
 * successfully visited one field, it will create new {@code Ant} instances with
 * an incremented {@code stepCount} to visit the other available {@code Field}
 * elements. The Ant will run until it finds no more {@code Field} elements in
 * its neighborhood to be altered.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 *
 */
public class Ant implements Runnable {

   AntField fields;
   int x;
   int y;
   int stepCount;
   boolean moved = false;
   /**
    *
    * @param fields
    *           the {@code AntField} on which this {@code Ant} operates
    * @param x
    *           x-axis value of the starting position
    * @param y
    *           y-axis value of the starting position
    * @param stepCount
    *           initial stepCount of this {@code Ant}.
    *
    * @throws IllegalArgumentException
    *            If the {@code Field} at position {@code x,y} does not exist, or
    *            if its value is < 0
    */
   public Ant(AntField fields, int x, int y, int stepCount) {
         this.fields = fields;
         this.x = x;
         this.y = y;
         this.stepCount = stepCount;

         Field start = fields.getField(x,y);
         if(start == null){
             throw new IllegalArgumentException("The field for the given starting position does not exist.");
         }
         start.setValue(stepCount);

   }

    public void run() {
        boolean moved;
        do {
            moved = false;
            // saving the origin of the ant to avoid misplacement after moving.
            int x = this.x, y = this.y;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    // No need to look at our current position.
                    if (i == 0 && j == 0) continue;

                    // fields are returned by reference so we can safely lock it after checking for null
                    // since we are not reading any data so far. This will only happen while synchronized.
                    Field field = this.fields.getField(x + i, y + j);
                    if ( field == null ) continue;
                    if (isValid(field, moved)) {
                        // if field is valid to visit we move to it or deploy another ant to this field.
                        if (!moved) {
                            moveTo(x + i, y + j, field);
                            moved = true;
                        } else cloneAndMove(x + i, y + j);
                    }
                    }
                }
        } while (moved);
    }

    /**
     * Ant will move to the specified field at pos x, y and increase its step counter by 1.
     * @param x x pos of field.
     * @param y y pos of field.
     * @param field the associated {@link Field} at pos x, y.
     */
    private void moveTo(int x, int y, Field field) {
        field.setValue(++this.stepCount);
        this.x = x;
        this.y = y;
    }

    /**
     * Clone this ant to the pos x, y with the same step count.
     * (intended to be called after ant has moved and increased its step count via {@code moveTo}.)
     * @param x x pos of new field.
     * @param y y pos of new field.
     */
    private void cloneAndMove(int x, int y) {
        // assume that ant has already moved so we do not need to increment the step counter again.
        Thread companion = new Thread(new Ant(this.fields, x, y, this.stepCount));
        try {
            companion.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        companion.start();

    }

    /**
     * Checks if a {@link Field} is valid for moving.
     * @param successor the {@link Field} in question.
     * @param hasMoved whether the ant has already moved in this iteration or not.
     * @return true if validity is given, else false.
     */
    private boolean isValid(Field successor, boolean hasMoved){
        int i = hasMoved? 0 : 1; // if ant has moved its step count was already increased, so we don't want to increment again.
        return successor != null && (successor.getValue() > this.stepCount +i || successor.getValue() == 0);
    }

}
