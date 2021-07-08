package Serialization;

import java.io.Serializable;

/**
 * Serializable class.
 */
public class Persistent implements Serializable {
    static final long serialVersionUID = 42L;
    private transient int notIncluded;
    private String included;

    // ADD JAVADOC!!!
    public Persistent(int notIncluded, String included) {
        this.notIncluded = notIncluded;
        this.included = included;
    }


    @Override
    public String toString() {
        return "Persistent{" +
                "notIncluded=" + notIncluded +
                ", included='" + included + '\'' +
                '}';
    }
}
