import java.io.*;
import java.util.ArrayList;

public class Scoreboard {
    private static Scoreboard self;

    private final ArrayList<ScoreboardEntry> highscores;

    private final String path = "src/scoreboard.ser";

    private Scoreboard(){
        ArrayList<ScoreboardEntry> tempHighscores;
        try (var fis = new FileInputStream(path);
             var dec = new ObjectInputStream(fis)
        ){
            tempHighscores = (ArrayList<ScoreboardEntry>) dec.readObject();
        } catch (IOException | ClassNotFoundException e){
            tempHighscores = new ArrayList<>();
        }

        highscores = tempHighscores;
    }

    public static Scoreboard init(){
        if (self==null) self = new Scoreboard();
        return self;
    }


    public ScoreboardEntry getHighscore(){
        ScoreboardEntry best = new ScoreboardEntry("", Double.MAX_VALUE);
        for(ScoreboardEntry e: highscores){
            if(best.time() > e.time()) best = e;
        }
        if(best.time() == Double.MAX_VALUE) return null;
        return best;
    }

    public void registerEntry(String name, double time){
        ScoreboardEntry best = getHighscore();

        if (best != null && best.time() < time) return;

        highscores.add(new ScoreboardEntry(name, time));
        try (var fos = new FileOutputStream(path);
             var enc = new ObjectOutputStream(fos)) {
            enc.writeObject(highscores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
