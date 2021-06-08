import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PersistentInteger {
    public String fileName;
    public RandomAccessFile file;
    private Integer[] values;

    /**
     *
     * @param values
     * @param fileName
     */
    public PersistentInteger(Integer[] values, String fileName){
        this.fileName = fileName;
        this.values = values;

        File createdFile = new File(fileName);
        try {
            createdFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.file = new RandomAccessFile(createdFile,"rw");
            this.file.seek(0);
            for(Integer value: values){
                this.file.writeInt(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * To get access to a already existing file and its values.
     * @param fileName
     */
    public PersistentInteger(String fileName){
        this.fileName = fileName;

        File createdFile = new File(fileName);
        if(!createdFile.isFile()){
            throw new RuntimeException("If you want to access a file only by its name it has to already instantiated.");
        }

        try {
            this.file = new RandomAccessFile(fileName,"rw");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Integer get(long insertPos) {
        try {
            if(insertPos<0||insertPos>length()){throw new ArrayIndexOutOfBoundsException();}
            this.file.seek(insertPos*4);
            return file.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void set(long insertPos, Integer newValue){
        try {
            if(insertPos<0||insertPos>length()){throw new ArrayIndexOutOfBoundsException();}
            //seek also measures in bytes so you have to multiply by 4
            this.file.seek(insertPos*4);
            file.writeInt(newValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Integer[] readValues(){
        Integer[] fileValues = new Integer[length()];
        try {
            this.file.seek(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0; i<length();i++){
            try {
                fileValues[i] = this.file.readInt();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileValues;
    }

    public int length(){
        try {
            // devided by 4 because length measures the length of the file in bytes and in java integer are 32 bits meaning 4 bytes
            return (int) new RandomAccessFile(fileName,"r").length()/4;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void close(){
        try {
            this.file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
