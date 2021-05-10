/**
 * @Author Nicolai Hermann, Michael Hüppe.
 */
package Library;

public class Book extends LibraryItem {
    /**
     * Title of the book.
     */
    private final String title;
    /**
     * Name of the books author.
     */
    private final String author;

    /**
     * Creates an instance of a book containing its title and the authors name.
     * @param title of the book.
     * @param author of the book.
     */
    public Book(String title, String author){
        super();
        this.title = title;
        this.author = author;
    }

    /**
     * @return a short description of the book.
     */
    @Override
    public String getDescription() {
        return "The book " + title + " was written by " + author;
    }

    /**
     * @return the title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the name of the books author.
     */
    public String getAuthor() {
        return author;
    }
}
