package visitor;

public class MyVisitor implements Visitor<Integer> {
    public int numVisited = 0;
    /**
     * Called by the method {@link Visitable#accept(Visitor)} for every element it
     * visits. The visiting can be stopped by returning <code>false</code>.
     *
     * @param o the element that has just been visited by
     *          {@link Visitable#accept(Visitor)}
     * @return <code>true</code> if the visit should be continued until every
     * element in a {@link Visitable} has been visited once, else
     * <code>false</code>
     */
    @Override
    public boolean visit(Integer o) {
        this.numVisited++;
        return true;
    }
}
