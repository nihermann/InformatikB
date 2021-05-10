/**
 * @Author Nicolai Hermann, Michael Hüppe.
 */
package Library;

public abstract class LibraryItem {
    /**
     * Boolean whether the item is borrowed or not.
     */
    private boolean isBorrowed;

    /**
     * @return truth value whether the item is borrowed or not.
     */
    public boolean isBorrowed(){ return this.isBorrowed; }

    /**
     * Sets the item to be borrowed or returned.
     * @param isBorrowed whether the item is borrowed or not.
     */
    public void setBorrowed(boolean isBorrowed){ this.isBorrowed = isBorrowed; }

    /**
     * @return a brief description about the item.
     */
    public abstract String getDescription();
}
