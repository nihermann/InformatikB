package Decorator;

import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        String regex = args[0];
        try(var reader = new RegexReader(new InputStreamReader(System.in), regex)){
            String s;
            while((s = reader.readLine()) != null){
                if(reader.getAmountOfMatches() > 0){
                    System.out.println(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
