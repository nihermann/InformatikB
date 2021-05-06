package Testing.Library;

import Library.*;
import Library.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestLibrary {
    @Test
    public void testInsertion(){
        Library l = new Library();
        Book book = new Book("Faust", "Goethe");
        BluRay bluray = new BluRay("StarWars", "George Lucas");
        l.addItem(book);
        l.addItem(bluray);
    }

    @Test
    public void testSearchMultiple(){
        Library l = new Library();
        Book book = new Book("Faust", "Goethe");
        BluRay bluray = new BluRay("StarWars", "George Lucas");
        l.addItem(book);
        l.addItem(bluray);
        List matches = l.search("by");
        assertEquals(book.getDescription(), ((LibraryItem) matches.elem()).getDescription());
        matches.delete();
        assertEquals(bluray.getDescription(), ((LibraryItem) matches.elem()).getDescription());
    }

    @Test
    public void testSpecificSearch(){
        Library l = new Library();
        Book book = new Book("Faust", "Goethe");
        BluRay bluray = new BluRay("StarWars", "George Lucas");
        l.addItem(book);
        l.addItem(bluray);
        List match = l.search("George");
        assertEquals(bluray.getDescription(), ((LibraryItem) match.elem()).getDescription());
    }

    @Test
    public void testNotFound(){
        Library l = new Library();
        // with an empty library.
        assertNull(l.search("President"));

        // with a non-empty library.
        BluRay bluray = new BluRay("StarWars", "George Lucas");
        l.addItem(bluray);
        assertNull(l.search("President"));
    }

    @Test
    public void testDeletion(){
        Library l = new Library();
        Book book = new Book("Faust", "Goethe");
        BluRay bluray = new BluRay("StarWars", "George Lucas");
        l.addItem(book);
        l.addItem(bluray);

        l.deleteItem(book);
        List matches = l.search("Faust");
        assertNull(matches);
    }

    @Test
    public void testSideEffects(){
        Library l = new Library();
        Book book = new Book("Faust", "Goethe");
        BluRay bluray = new BluRay("StarWars", "George Lucas");
        l.addItem(book);
        l.addItem(bluray);

        // what ever?!
    }
}
