package visitor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestVisitable {
    private MyList<Integer> getList(){
        MyList<Integer> list = new MyList<Integer>();
        for (int i = 10; 0<i; i--){
            list.add(i);
        }
        return list;
    }

    @Test
    public void testComplete(){
        MyList<Integer> l = getList();
        MyVisitor v = new MyVisitor();
        l.accept(v);
        assertEquals(10, v.numVisited);
    }

    @Test
    public void testIncomplete(){
        MyList<Integer> l = getList();
        VisitorWithBreak v = new VisitorWithBreak();
        l.accept(v);
        assertEquals(5, v.numVisited);
    }
}
