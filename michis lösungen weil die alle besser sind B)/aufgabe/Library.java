import util.List;

public class Library {

    private List inventory = new List();


    public Library(){
    }

    public void addItem(LibraryItem item){
        inventory.add(item);
    }

    public void deleteItem(LibraryItem item){
        inventory = search(item.getDescription(),true);
    }

    public List search(String text, boolean negativeSearch){
//       test every LibraryItem in the inventory whether or not the description has the
//       the given text or not if it does add it to the list and return it
        List searchResult = new List();
        while (!inventory.empty()){
            LibraryItem item = (LibraryItem) inventory.elem();
            String current  = (String) item.getDescription();
            if (negativeSearch){
                if(!current.equals(text)){
                    searchResult.add(current);
                }
            }
            if(current.contains(text)){
                searchResult.add(current);
            }
            inventory.delete();
        }
        return searchResult;
    }

    public List search(String text){
        return search(text,false);
    }
}
