package SerialisierungKlausur;

import java.io.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1){
            throw new RuntimeException("You have to specify one parameter either w or r");
        }


        String input = args[0];
        Random rand = new Random(); //i
        if(input.equals("w")){
            try(ObjectOutputStream oof = new ObjectOutputStream(new FileOutputStream("src/SerialisierungKlausur/students.ser"))) {
                Student[] students = new Student[5];
                for(int i = 0; i < 5; i++){
                    students[i] = new Student(NameGenerator.generateName(), rand.nextInt(Integer.MAX_VALUE));
                }
                oof.writeObject(students);
                oof.flush();
                System.out.println("Alles paletti");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Student[] students = null;
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/SerialisierungKlausur/students.ser"))) {
                // Create serialized stream object
                //read out
                System.out.println("Ich konnte streamen");

                students =(Student[]) ois.readObject();

                System.out.println("Ich konnte lesen");

                for(Student student : students) {
                    System.out.println(student.toString());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else if (input.equals("r")){
            Student[] students = null;
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/SerialisierungKlausur/students.ser"))) {
                // Create serialized stream object
                //read out
                System.out.println("Ich konnte streamen");

                students =(Student[]) ois.readObject();

                System.out.println("Ich konnte lesen");

                for(Student student : students) {
                    System.out.println(student.toString());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            throw new RuntimeException("The two possible parameters are either r to read or w to write");
        }

    }

}
