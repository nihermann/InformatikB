package model.visitorInterfaces;

public interface Visitable <E>{
    public void accept(Visitor<E> v);
}
