import java.lang.constant.Constable;

public class BluRay extends LibraryItem{

    private String title;
    private String director;

    public BluRay(String title, String director){
        super();
        this.title = title;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }
    
    public String getDescription(){
        return title+" by "+ director;
    }

}
