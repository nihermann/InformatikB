package Library;

public class BluRay extends LibraryItem {

    private final String title;
    private final String director;

    public BluRay(String title, String director){
        super();
        this.title = title;
        this.director = director;
    }

    @Override
    public String getDescription() {
        return "The BlueRay " + title + " was directed by " + director;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }
}
