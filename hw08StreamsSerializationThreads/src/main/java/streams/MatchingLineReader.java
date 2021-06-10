package streams;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchingLineReader extends LineNumberReader {
    /**
     * The pattern to be matched in the read lines.
     */
    private final Pattern pattern;

    /**
     * The number of matches in the last line.
     */
    private long matchesInLastLine;

    /**
     * Create a new line-numbering reader, using the default input-buffer
     * size which additionally matches a regular expression pattern p.
     *
     * @param  in
     *         A Reader object to provide the underlying stream
     * @param p the pattern to be matched in the lines.
     */
    public MatchingLineReader(Reader in, Pattern p) {
        super(in);
        this.pattern = p;
    }

    /**
     * Create a new line-numbering reader, using the default input-buffer
     * size which additionally matches a regular expression pattern p.
     *
     * @param  in
     *         A Input Stream object to provide the underlying stream
     * @param p the pattern to be matched in the lines.
     */
    public MatchingLineReader(InputStream in, Pattern p){
        super(new InputStreamReader(in));
        this.pattern = p;
    }

    /**
     * Read a line of text.  <a href="#lt">Line terminators</a> are compressed
     * into single newline ('\n') characters. The current line number is
     * incremented whenever a line terminator is read, or when the end of the
     * stream is reached and the last character in the stream is not a line
     * terminator.
     *
     * @return  A String containing the contents of the line, not including
     *          any <a href="#lt">line termination characters</a>, or
     *          {@code null} if the end of the stream has been reached
     *
     * @throws  IOException
     *          If an I/O error occurs
     */
    @Override
    public String readLine() throws IOException {
        String out = super.readLine();
        if (out == null) return null;

        // count the number of pattern matches.
        Matcher m = pattern.matcher(out);
        this.matchesInLastLine = m.results().count();

        return out;
    }

    /**
     * @return number of pattern matches in the last read line.
     */
    public long getAmountOfMatches(){
        return this.matchesInLastLine;
    }
}
