package aufgabe.io;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.TimerTask;

public class FileThread extends TimerTask {
    private File file;
    private long oldSize;

    /**
     * Constructor for FileThread initializing the oldSize (referenced file size to check wheter or not the size has
     * changed)
     * @param file
     */
    public FileThread(File file){
        this.file = file;
        oldSize = getSize();
    }

    /**
     * Calculates the size of the calles file (recursively for dictionaries)
     * @param file
     * @return
     */
    private long size(File file){
        long length = 0;
        // base case, it is a file - return the length.
        if(file.isFile()) {
            return length + file.length();
            // recursive case, if it is an directory we want to check its content.
        } else if(file.isDirectory()){
            for (File subfile : file.listFiles()){
                if(subfile == null) continue;
                length += size(subfile);
            }
        }
        // if file was neither a file nor a directory we will return 0
        // or if we found files we return their sum of lengths.
        return length;
    }

    /**
     * @return the size of the file
     */
    public long getSize(){
        return size(this.file);
    }

    /**
     * Overrides the run method of the TimerTask
     * Notify the user if the file size has changed
     */
    @Override
    public void run() {
        long current = getSize();
        if(current != oldSize){
            // Print a notification when the file has been changed
            System.out.println("The size of the File was changed. The old size was: " + oldSize + " the new size is: " + current +".");
            oldSize = current;
            // make notification sound to alert when the file has been changed
            Toolkit.getDefaultToolkit().beep();
        }
    }

}
