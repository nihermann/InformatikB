package Decorator;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexReader extends LineNumberReader {
    private final Pattern regexPatter;
    private long matches;

    /**
     * Create a new line-numbering reader, using the default input-buffer
     * size.
     *
     * @param in A Reader object to provide the underlying stream
     */
    public RegexReader(Reader in, String regex) {
        super(in);
        regexPatter = Pattern.compile(regex);

    }

    /**
     * Read a line of text.  Whenever a <a href="#lt">line terminator</a> is
     * read the current line number is incremented.
     *
     * @return A String containing the contents of the line, not including
     * any <a href="#lt">line termination characters</a>, or
     * {@code null} if the end of the stream has been reached
     * @throws IOException If an I/O error occurs
     */
    @Override
    public String readLine() throws IOException {
        String line = super.readLine();

        if(line != null) {
            Matcher m = regexPatter.matcher(line);
            matches = m.results().count();
        }

        return line;
    }

    /**
     * @return the number of regex matches from the most recently read line.
     */
    public long getAmountOfMatches(){
        return matches;
    }
}
