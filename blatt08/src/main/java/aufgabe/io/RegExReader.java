package aufgabe.io;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExReader extends LineNumberReader {

    private int amountOfMatches;
    private Pattern regEX;

    /**
     *
     * @param in Reader for the file
     * @param regEX string representing the regEx to search for the file
     * @throws FileNotFoundException
     */
    public RegExReader(Reader in, String regEX) throws FileNotFoundException {
        super(in);
        this.regEX = Pattern.compile(regEX);
    }

    /**
     * @param file InputStream which gets the file
     */
    public RegExReader(InputStream file, String regEX) throws FileNotFoundException {
        this(new InputStreamReader(file),regEX);
    }

    /**
     * @param filename String representing the file
     */
    public RegExReader(String filename, String regEX) throws FileNotFoundException {
        this(new FileReader(filename),regEX);
    }


    /**
     * Overwrites the readline from the LineNumberReader and adds a print statement which is called if there were matches
     * to the given RegEx and if so prints the respective line number.
     *
     * @return current Line
     * @throws IOException
     */
    @Override
    public String readLine() throws IOException {
        String line = super.readLine();
        if(line == null){
            return null;
        }
        // get the number of matches of the RegEx in the current line
        this.amountOfMatches = (int) regEX.matcher(line).results().count();

        // if there were matches found print the line and the amount of matches found
        if(amountOfMatches>0){
            System.out.println( getLineNumber() + "| " + line + " Matches: " + this.amountOfMatches );
        }
        // return the current line as a String
        return line;
    }

    @Override
    /**
     * @return the current Line number inherited from LineNubmerReader
     */
    public int getLineNumber() {
        return super.getLineNumber();
    }

    /**
     *
     * @return the amount of matches from the last called line
     */
    public int getAmountOfMatches(){
        return amountOfMatches;
    }
}
