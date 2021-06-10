package threads;

import java.io.File;
import java.util.TimerTask;

public class CheckSize extends TimerTask {
    /**
     * The tracked file or directory.
     */
    private final File trackMe;

    /**
     * The size of trackMe since the last checkup.
     */
    private long lastSize = 0;

    /**
     * A TimerTask which monitors the memory usage of a file or directory with all its content.
     * @param file the file or directory to monitor.
     */
    public CheckSize(File file) {
        this.trackMe = file;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        long size = size(this.trackMe);
        if (size != this.lastSize){ // only act on change.
            System.out.println("Size has changed from " + this.lastSize + " to " + size + "!");
            this.lastSize = size;
        }
    }

    /**
     * Recursively calculates the size of a directory or file.
     * @param file file or directory.
     * @return memory usage of file.
     */
    private long size(File file){
        long length = 0;
        // base case, it is a file - return the length.
        if(file.isFile()) {
            return length + file.length();
        // recursive case, if it is an directory we want to check its content.
        } else if(file.isDirectory()){
            for (File f : file.listFiles()){
                if(f == null) continue;
                length += size(f);
            }
        }
        // if file was neither a file nor a directory we will return 0
        // or if we found files we return their sum of lengths.
        return length;
    }
}
