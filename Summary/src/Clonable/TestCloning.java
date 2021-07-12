package Clonable;

public class TestCloning {
    public static void main(String[] args) throws CloneNotSupportedException {
        ToClone toClone = new ToClone("Hallo", "Welt");

        ToClone toClone1 = toClone.clone();

        assert toClone != toClone1: "Die referenzen sind nicht ungleich";
        assert toClone.getClass() == toClone1.getClass() : "Die Klassen namen müssen gleich sein";
        assert toClone.equals(toClone1) : "The copy should be equal to the original.";

    }
}
