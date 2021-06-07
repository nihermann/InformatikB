package persistentIntegerArray;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class TestPIntArray {
    private String path = "src/test/java/persistentIntegerArray/myIntegerArray_";
    @Test
    public void testInitWithArray(){
        Integer[] ints = {1,2,3,4,5,6,7,8,9,10};
        PIntArray array = new PIntArray(path + "test1.xml", ints);
        File f = new File(path);
        array.close();

        assertTrue(f.exists());
    }

    @Test
    public void testInitFromName(){
        Integer[] ints = {1,2,3,4,5,6,7,8,9,10};
        PIntArray array = new PIntArray(path + "test2.xml", ints);
        array.close();
        PIntArray array2 = new PIntArray(path + "test2.xml");

        assertArrayEquals(new Integer[]{1,2,3,4,5,6,7,8,9,10}, array2.get());
        array2.close();
    }

    @Test
    public void testChange(){
        Integer[] ints = {1,2,3,4,5,6,7,8,9,10};
        PIntArray array = null;
        try {
            array = new PIntArray(path + "test3.xml", ints);

            for (int i = 0; i<array.length; i++){
                if(i %2 != 0){
                    array.set(i, 0);
                }
            }
            assertArrayEquals(new Integer[]{1,0,3,0,5,0,7,0,9,0}, array.get());
        } catch(Exception e){
            e.printStackTrace();
            fail();
        } finally {
            if(array != null) {
                array.close();
            }
        }
    }
}
