package File;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileHandling {
    public static void main(String[] args)  {
        String sep = File.separator;
        String path = "src"+sep+"File"+sep+"text.txt";
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
//            accessFile.writeChars("Baka");
            char[] read = new char[(int)accessFile.length()/2];
            accessFile.seek(0);
            for (int i = 0; i < (int) accessFile.length()/2; i++){
                accessFile.seek(i);
                read[i] = accessFile.readChar();
                System.out.println("File was read from and the char was: " + read[i]);
            }
            String hello = String.valueOf(read);
            System.out.println((int)accessFile.length()/2);
            System.out.println(hello);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
