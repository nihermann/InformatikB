package IOOps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOTests {
    public static void main(String[] args) {
        File test = new File("src/IOOps/moveMe.txt");
        File dest = new File("src/IOOps/test/moveMe.txt");
        System.out.println(test.renameTo(dest));

        try(var writer = Files.newOutputStream(Path.of("src/IOOps/test/jojojo.txt"))){
           writer.write("I'm so cool".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(Files.writeString(Path.of("src/IOOps/moveMe1.txt"), "aösfdaweoij"));
    }
}
