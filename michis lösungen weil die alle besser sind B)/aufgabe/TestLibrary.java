import util.List;

public class TestLibrary {
    public static Test test = new Test();

    public static void main(String[] args) {
        testSearch();
        test.printError();
    }

    public static void testSearch(){
        Library library = new Library();
        Book book = new Book("Call me by your name", "Andre Aciman");
        Book book1 = new Book("Harry Potter", "Allen Rickman");
        Book book2 = new Book("Lord of the Rings", "George");

        library.addItem(book);
        library.addItem(book1);
        library.addItem(book2);

        List results = library.search("Lord of the Rings");
        System.out.println(results.elem());


    }
}
