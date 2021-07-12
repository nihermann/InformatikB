package Decorator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class BreakLineWriter extends BufferedWriter {
    public BreakLineWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str) throws IOException {
        super.write(str);
        super.write(System.lineSeparator());

    }
}
