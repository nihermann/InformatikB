package Library;

import Library.util.List;

public class Library {
    private List inventory = new List();

    public void addItem(LibraryItem item){
        inventory.add(item);
    }

    public void deleteItem(LibraryItem item){
        if(inventory.empty()){
            return;
        }
        while(!inventory.endpos()){
            LibraryItem i = (LibraryItem) inventory.elem();
            if(i.getDescription().equals(item.getDescription())){
                inventory.delete();
                return;
            }
            inventory.advance();
        }
    }

    public List search(String text){
        if(inventory.empty()){
            return null;
        }

        List matches = new List();
        inventory.reset();
        do{
            LibraryItem i = (LibraryItem) inventory.elem();
            if(i.getDescription().contains(text)){
                matches.add(i);
            }
            inventory.advance();
        } while(!inventory.endpos());
        return matches.empty()? null : matches;
    }
}
