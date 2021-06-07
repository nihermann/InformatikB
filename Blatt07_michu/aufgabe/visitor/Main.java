package visitor;

public class Main {
    public static void main(String[] args) {
        visitAndPrint();
        visitStopAfterNegative();
    }

    public static void visitAndPrint(){
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

    public static void visitStopAfterNegative(){

        Visitor<Integer> MyListVisitor = new Visitor<Integer>(){
            private int counter;
            public boolean visit(Integer o) {
                System.out.println(o);
                counter++;
                return o>=0;
            }

        };


        MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(2);
        list.add(-3);
        list.add(4);
        list.add(5);
        list.accept(MyListVisitor);
    }

}
