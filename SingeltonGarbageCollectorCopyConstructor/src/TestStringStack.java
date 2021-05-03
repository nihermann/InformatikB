/**
 * Tests the Copy constructor of StringStack.java
 * @Author Nicolai Hermann, Michael Hüppe
 */

import Test.Test;

public class TestStringStack {
    public static void copyConstructorShouldBeDeep() {
        StringStack ss = new StringStack();
        ss.push("1");

        // copy of ss
        StringStack copy = new StringStack(ss);
        // add something to ss which should not appear to be in the copy.
        ss.push("2");
        System.out.println(ss.peek());
        System.out.println(copy.peek());
        Test.shouldBeTrue(!ss.peek().equals(copy.peek()), "Deep copy was unsuccessful, received same head when it should not.");
    }

    public static void testAll() {
        copyConstructorShouldBeDeep();
        System.out.println("Number of errors: " + Test.getErrorCount());
    }

    public static void main(String[] args) {
        TestStringStack.testAll();
    }
}
