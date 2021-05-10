/**
 * @Author Nicolai Hermann, Michael Hüppe
 */
package Library;

public class BluRay extends LibraryItem {

    /**
     * The title of the BluRay
     */
    private final String title;
    /**
     * The name of the director of the movie.
     */
    private final String director;

    /**
     * Create an instance of a BlueRay with title and director.
     * @param title of the movie.
     * @param director of the movie.
     */
    public BluRay(String title, String director){
        super();
        this.title = title;
        this.director = director;
    }

    /**
     * Get a short description about the BluRay.
     * @return the description.
     */
    @Override
    public String getDescription() {
        return "The BlueRay " + title + " was directed by " + director;
    }

    /**
     * @return the title of the BluRay.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the name of the director.
     */
    public String getDirector() {
        return director;
    }
}
