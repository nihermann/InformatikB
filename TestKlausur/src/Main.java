import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1 || (!args[0].equals("r") && !args[0].equals("w"))){
            throw new IllegalArgumentException("Illegal arguments. Provide 'r' or 'w'");
        }

        if (args[0].equals("w")){
            Student students[] = {new Student("asasdd", 10),
                    new Student("asaaffd", 130),
                    new Student("aasdfsasd", 103),
                    new Student("asearhd", 140),
                    new Student("asasdvad", 150)
            };
            try(var writer = new ObjectOutputStream(new FileOutputStream("Studenten.ser"))){

                for (Student s: students){
                    writer.writeObject(s);
                }
                writer.flush();


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try(var reader = new ObjectInputStream(new FileInputStream("Studenten.ser"))) {
                while (true) { // until reader throws EOF Exception.
                    Student s = (Student) reader.readObject();
                    System.out.println(s);
                }
            }catch(EOFException e){
                // no more students in file.
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
