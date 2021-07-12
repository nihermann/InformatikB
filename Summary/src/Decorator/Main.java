package Decorator;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try(BreakLineWriter breakLineWriter = new BreakLineWriter(new FileWriter("src/Decorator/test.txt"))){
            breakLineWriter.write("Hello");
            breakLineWriter.write("du");
            breakLineWriter.write("du bussy liebhaber");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
