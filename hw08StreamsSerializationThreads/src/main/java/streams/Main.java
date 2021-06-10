package streams;

import java.io.IOException;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){
        // get pattern from console
        Pattern pattern = Pattern.compile(args[0]);

        try(MatchingLineReader reader = new MatchingLineReader(System.in, pattern)){
            while(true){
                String line = reader.readLine();
                if (line == null) break;

                // when pattern matched more than once, we want to output the line onto the console.
                if (reader.getAmountOfMatches() > 0){
                    System.out.println(
                            reader.getAmountOfMatches() + " matches found in line " +
                            reader.getLineNumber() + ": " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
