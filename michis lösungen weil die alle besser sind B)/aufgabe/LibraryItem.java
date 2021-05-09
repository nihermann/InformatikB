public class LibraryItem {

    private boolean isBorrowed;
    private Library library;


    public LibraryItem() {
    }

    public boolean isBorrowed(){
        return this.isBorrowed;
    }

    public void setBorrowed(boolean isBorrowed){
        this.isBorrowed = isBorrowed;
    }

    public String getDescription(){return null;}

}
