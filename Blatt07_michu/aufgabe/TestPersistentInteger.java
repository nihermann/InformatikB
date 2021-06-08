import java.util.Arrays;

public class TestPersistentInteger {
    public static void main(String[] args) {
        testCreate();
        testReadValues();
        testGet();
        testSet();
    }

    public static void testCreate(){
        Integer[] original = new Integer[]{1,2,3,4,5};
        PersistentInteger persistentInteger = new PersistentInteger(original, "IntegerTest.txt");
        PersistentInteger persistentIntegerOnlyName = new PersistentInteger("IntegerTest.txt");

        Integer[] fileValues = persistentInteger.readValues();
        Integer[] fileValuesOnlyName = persistentIntegerOnlyName.readValues();
        // check whether or not the file values are the same as the original written ones
        assert Arrays.equals(original,fileValues): "The Values were not properly read.";
        assert Arrays.equals(original,fileValuesOnlyName): "The Values were not properly read.";
    }

    public static void testReadValues(){
        Integer[] original = new Integer[]{1,2,3,4,5};
        PersistentInteger persistentInteger = new PersistentInteger(original, "IntegerTest.txt");
        Integer[] fileValues = persistentInteger.readValues();
        // check whether or not the file values are the same as the original written ones
        assert Arrays.equals(original,fileValues): "The Values were not properly read.";
    }

    public static void testGet(){
        Integer[] original = new Integer[]{1,2,3,4,5};
        PersistentInteger persistentInteger = new PersistentInteger(original, "IntegerTest.txt");
        // check whether or not the file values are the same as the original written ones
        for(int i= 0; i<original.length;i++){
            assert original[i] == persistentInteger.get(i) :"The values of the file were not properly obtained";
        }

        try {
            persistentInteger.get(-1);
            assert false:"The get method should throw an Array Out of Bounds Exception for too negative/large indeces.";
        }catch (ArrayIndexOutOfBoundsException e){
        }
    }

    public static void testSet(){
        Integer[] original = new Integer[]{1,2,3,4,5};
        PersistentInteger persistentInteger = new PersistentInteger(original, "IntegerTest.txt");
        persistentInteger.set(0,6);
        original[0]=6;
        Integer[] fileValues = persistentInteger.readValues();
        // check whether or not the file values are the same as the original written ones
        assert Arrays.equals(original,fileValues): "The Values were not properly set.";

        try {
            persistentInteger.set(7,6);
            assert false:"The set method should throw an Array Out of Bounds Exception for too large/negative indeces.";
        }catch (ArrayIndexOutOfBoundsException e){
        }
    }
}
