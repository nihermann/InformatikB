package model.visitorInterfaces;

public interface Visitor<E> {
    public boolean visit(E o);
}
