package aufgabe.io;

import aufgabe.io.RegExReader;

import java.io.IOException;

public class TestRegExReader {
    public static void main(String[] args) throws IOException {
        RegExReader regExReader = new RegExReader(System.in, args[0]);
        // parse trough the entire file and check for mathces with the given regEx
        while (regExReader.readLine()!=null){}
    }
}
