package Clonable;

import java.util.Objects;

public class ToClone implements Cloneable{
    String test1;
    String test2;

    public ToClone(String test1, String test2){
        this.test1 = test1;
        this.test2 = test2;
    }

    @Override
    protected ToClone clone() throws CloneNotSupportedException {
        return new ToClone(test1,test2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToClone toClone = (ToClone) o;
        return Objects.equals(test1, toClone.test1) && Objects.equals(test2, toClone.test2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(test1, test2);
    }
}
