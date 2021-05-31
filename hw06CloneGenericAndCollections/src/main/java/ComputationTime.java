import java.util.Collection;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;

public class ComputationTime {
    public static void main(String[] args){
        Collection[] collections = {
                new LinkedList<Integer>(),
                new ArrayList<Integer>(),
                new HashSet<Integer>()
        };

        for (int i = 0; i<collections.length; i++){
            testSpeed(collections[i], 10000, 4);
        }

    }

    /**
     * Tests the runtime of any Collection<Integer>
     * @param toBeTested
     * @param iterations
     * @param rounds
     */
    public static void testSpeed(Collection<Integer> toBeTested, int iterations, int rounds){
        long[] results = {0, 0, 0};
        for (int i = 0; i<rounds; i++){
            long start_add = System.nanoTime();
            for(int j = 0; j < iterations; j++){
                toBeTested.add(j);
            }
            long end_add = System.nanoTime();
            results[0] += end_add - start_add;


            long start_contains = System.nanoTime();
            for(int j = 0; j < iterations; j++){
                toBeTested.contains(j);
            }
            long end_contains = System.nanoTime();
            results[1] += end_contains - start_contains;


            long start_remove = System.nanoTime();
            for(int j = iterations-1; j >= 0; j--){
                toBeTested.remove(j);
            }
            long end_remove = System.nanoTime();
            results[2] += end_remove - start_remove;
        }


        float norm =  1 / (float) (rounds * iterations);
        String message = String.format(
                "\n-----------------------------------\nAdd:      %s\nContains: %s\nRemove:   %s\n----------------------------------- ",
                results[0] * norm,
                results[1] * norm,
                results[2] * norm
        );

        System.out.println(message);
    }
}
