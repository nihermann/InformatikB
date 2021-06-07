package persistentIntegerArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PersistentInteger {
    public String fileName;
    public RandomAccessFile file;
    private Integer[] values;

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
            this.file.seek(insertPos*4);
            file.writeInt(newValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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

    public int length(){
        try {
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
