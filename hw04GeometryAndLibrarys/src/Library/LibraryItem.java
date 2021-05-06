package Library;

public abstract class LibraryItem {
    private boolean isBorrowed;

    public boolean isBorrowed(){ return this.isBorrowed; }
    public void setBorrowed(boolean isBorrowed){ this.isBorrowed = isBorrowed; }
    public abstract String getDescription();
}
