package Library;

public class Book extends LibraryItem {
    private final String title;
    private final String author;

    public Book(String title, String author){
        super();
        this.title = title;
        this.author = author;
    }

    @Override
    public String getDescription() {
        return "The book " + title + " was written by " + author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
