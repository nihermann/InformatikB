package Serialisierung;

import java.io.Serializable;

public class Fußballspieler implements Serializable {
    private static long serialVersionUID = 1L;


    private final int alter;
    private transient final int geld;
    private final String name;

    public Fußballspieler(int alter, int geld, String name){
        this.alter = alter;
        this.geld = geld;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fußballspieler{" +
                "alter=" + alter +
                ", geld=" + geld +
                ", name='" + name + '\'' +
                '}';
    }
}
