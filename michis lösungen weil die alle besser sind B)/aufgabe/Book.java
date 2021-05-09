public class Book extends LibraryItem{

    private String title;
    private String author;


    public Book(String title, String author) {
        super();
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return title+" by "+author;
    }
}
