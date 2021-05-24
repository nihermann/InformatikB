package utilList;


class MyListTest {
    int error =0;
    public static void main(String[] args) {
        MyListTest listTest = new MyListTest();
        listTest.testCopy();
    }

    public void testCopy(){
        this.error++;
        MyList<Float> original = new MyList<Float>();

        MyList<Float> copy = original.clone();
        original.add((float) 3.3);
        original.add((float) 12);
        original.add((float) 4.1);
        System.out.println(original != copy);
        System.out.println(original.getClass() == copy.getClass());
        System.out.println(original.equals(copy));

        System.out.println(original.toString());
        System.out.println(copy.toString());


    }
}