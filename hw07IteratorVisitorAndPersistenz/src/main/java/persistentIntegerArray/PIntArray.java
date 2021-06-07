package persistentIntegerArray;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class PIntArray {
    private Integer[] arr;
    private final String name;

    private XMLDecoder in;
    private XMLEncoder out;

    public final int length;

    public PIntArray(String name, Integer[] array){
        this.name = name;
        this.arr = array;
        this.length = array.length;

        this.write();
    }

    public PIntArray(String name){
        this.name = name;
        this.read();
        length = this.arr.length;
    }

    private void write(){
        try {
            if(this.out == null) {
                FileOutputStream fos = new FileOutputStream(this.name);
                this.out = new XMLEncoder(fos);
            }
            if(new File(this.name).delete()){
                System.out.println("deleted");
            }
            this.out.writeObject(this.arr.clone());
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void read(){
        try {
            if (this.in == null) {
                FileInputStream fis = new FileInputStream(name);
                this.in = new XMLDecoder(fis);
            }
            this.arr = (Integer[]) this.in.readObject();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public Integer[] get(){
        this.read();
        return this.arr;
    }

    public Integer get(int index){
        this.read();
        return this.arr[index];
    }

    public void set(int index, Integer value){
        this.arr[index] = value;
        this.write();
    }

    public void close() {
        if(this.in != null){
//            try{
            this.in.close();
//            } catch(IOException e){
//                e.printStackTrace();
//            }
        }
        if(this.out != null){
//            try{
            this.out.close();
//            } catch(IOException e){
//                e.printStackTrace();
//            }
        }
    }

}
