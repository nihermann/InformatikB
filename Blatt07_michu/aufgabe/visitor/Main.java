package visitor;

public class Main {
    public static void main(String[] args) {
        visitAndPrintAnonymous();
        visitStopAfterNegativeAnonymous();
        testComplete();
        testIncomplete();

    }

    private static MyList<Integer> getList(){
        MyList<Integer> list = new MyList<Integer>();
        for (int i = 10; 0<i; i--){
            list.add(i);
        }
        return list;
    }

    public static void testComplete(){
        MyList<Integer> l = getList();
        MyVisitor v = new MyVisitor();
        l.accept(v);
        assert v.numVisited == 10: "The visitor didn't visit all of the elements";
    }

    public static void testIncomplete(){
        MyList<Integer> l = getList();
        VisitorWithBreak v = new VisitorWithBreak();
        l.accept(v);
        assert v.numVisited != 10: "The visitor didn't correctyl stopped visiting the elements";
    }

    public static void visitAndPrintAnonymous(){
        Visitor<Integer> MyListVisitor = new Visitor<Integer>(){
            public boolean visit(Integer o) {
                System.out.println(o);
                return true;
            }
        };

        MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.accept(MyListVisitor);
    }

    public static void visitStopAfterNegativeAnonymous(){

        Visitor<Integer> MyListVisitor = new Visitor<Integer>(){
            private int counter;
            public boolean visit(Integer o) {
                System.out.println(o);
                counter++;
                return o>=0;
            }

        };

        System.out.println("With negative check");
        MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(2);
        list.add(-3);
        list.add(4);
        list.add(5);
        list.accept(MyListVisitor);
    }

}
