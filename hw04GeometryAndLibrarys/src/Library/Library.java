/**
 * @Author Nicolai Hermann, Michael Hüppe.
 */
package Library;

import Library.util.List;

public class Library {
    /**
     * List contain all library items.
     */
    private List inventory = new List();

    /**
     * Add an item to the Library.
     * @param item the item to be added.
     */
    public void addItem(LibraryItem item){
        inventory.add(item);
    }

    /**
     * Removes an item from the Library inventory.
     * @param item to be removed.
     */
    public void deleteItem(LibraryItem item){
        // we can only delete if there is something to delete.
        if(inventory.empty()){
            return;
        }
        // iterate through the list.
        inventory.reset();
        while(!inventory.endpos()){
            LibraryItem i = (LibraryItem) inventory.elem();
            // if the descriptions equals we will delete the item.
            if(i.getDescription().equals(item.getDescription())){
                inventory.delete();
                return;
            }
            inventory.advance();
        }
    }

    /**
     * Searches through the library items.
     * @param text keyword for the search.
     * @return List of all matches.
     */
    public List search(String text){
        // if there is nothing in the inventory there is nothing to find.
        if(inventory.empty()){
            return null;
        }
        // resulting list to collect all matches.
        List matches = new List();

        inventory.reset();
        while(!inventory.endpos()) {
            LibraryItem item = (LibraryItem) inventory.elem();
            if(item.getDescription().contains(text)){
                matches.add(item);
            }
            inventory.advance();
        }
        // if there was no match we will just return null
        return matches.empty()? null : matches;
    }
}
