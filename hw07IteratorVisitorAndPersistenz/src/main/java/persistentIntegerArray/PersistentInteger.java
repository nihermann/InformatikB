package persistentIntegerArray;

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
     * @param values array which to write into the file
     * @param fileName filename under the which the new file is ougt to be created
     */
    public PersistentInteger(Integer[] values, String fileName){
        this.fileName = fileName;
        this.values = values;

        //create a new file if it already exists overwrite it
        File createdFile = new File(fileName);
        try {
            createdFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //create the randomAccessFile variable
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
     *
     * @param insertPos position of the element you want to
     * @return element at insertPos
     */
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

    /**
     *
     * @param insertPos position of the element that you want to change
     * @param newValue the new value of the element at the position
     */
    public void set(long insertPos, Integer newValue){
        try {
            if(insertPos<0||insertPos>length()){throw new ArrayIndexOutOfBoundsException();}
            this.file.seek(insertPos*4);
            file.writeInt(newValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return an Integer array of all integer elements in the file
     */
    public Integer[] readValues(){
        Integer[] fileValues = new Integer[length()];
        for(int i=0; i<length();i++){
            try {
                fileValues[i] = this.file.readInt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileValues;
    }

    /**
     *
     * @return the length of the file given by its elements (byte size devided by the byte size of one integer )
     */
    public int length(){
        try {
            return (int) new RandomAccessFile(fileName,"r").length()/4;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * closes the file
     */
    public void close(){
        try {
            this.file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
