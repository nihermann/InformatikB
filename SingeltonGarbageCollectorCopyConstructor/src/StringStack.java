

/**
 * A Stack that holds Strings. Works after the LIFO (Last in first out)
 * principle.
 *
 * @author Lars Huning
 *
 */
public class StringStack {

    public StringStack() {
        this.first = null;
    }

    /**
     *
     * @param s
     */
    public StringStack(StringStack s){
        this.first = s.first;
        /*
        this();
        StringStack revertedStack = this.revertStack(s);
        while(!revertedStack.empty()){
            s.push(revertedStack.peek());
            this.push(revertedStack.pop());
        }*/
    }

//    /**
//     *
//     * @param ss
//     * @return
//     */
//    private StringStack revertStack(StringStack ss){
//        StringStack new_ss = new StringStack();
//        while(!ss.empty()){
//            String s = ss.pop();
//            new_ss.push(s);
//        }
//        return new_ss;
//    }


    /**
     * The first entry of the stack
     */
    private StringStackEntry first;

    /**
     * Tests, whether this StringStack is empty or not.
     *
     * @return true if this StringStack is empty, else false
     */
    public boolean empty() {
        return this.first == null;
    }

    /**
     * Returns the first element in the stack. Throws Exception when stack is
     * empty
     *
     * @return First element or null if stack is empty
     * @throws RuntimeException
     *            if stack is empty.
     */
    public String peek() {
        if (!this.empty()) {
            return first.getString();
        } else {
            System.out.println("peek() called when stack was empty");
            System.out.println("Terminating program");
            System.exit(-1);

            //never reached, but necessary for compilation. Once we reach chapter 4
            //of the lecture, the exit statement should be replaced with a
            //runtime exception to avoid this issue
            return "";
        }
    }

    /**
     * Deletes the first element in the stack and returns it. Throws Exception
     * when stack is empty
     *
     * @return first element in the stack
     * @throws RuntimeException
     *            if stack is empty.
     */
    public String pop() {
        if (!this.empty()) {
            String ret = first.getString();
            this.first = this.first.getNext();
            return ret;
        } else {
            System.out.println("pop() called when stack was empty");
            System.out.println("Terminating program");
            System.exit(-1);

            //never reached, but necessary for compilation. Once we reach chapter 4
            //of the lecture, the exit statement should be replaced with a
            //runtime exception to avoid this issue
            return "";
        }
    }

    /**
     * Puts the String s on the stack.
     *
     * @param s String to be added.
     */
    public void push(String s) {
        if (this.empty()) {
            first = new StringStackEntry(s, null);
        } else {
            first = new StringStackEntry(s, first);
        }
    }

}
