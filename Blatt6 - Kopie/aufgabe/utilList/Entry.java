package utilList;

/**
 * An Entry holds an Object <code>o</code> and a reference <code>next</code> to
 * the next Entry such that a linked List of Entry elements is generated.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
class Entry<T> {

   T o;
   Entry next;

   Entry() {
      this(null, null);
   }

   Entry(T o) {
      this(o, null);
   }

   Entry(T o, Entry e) {
      this.o = o;
      this.next = e;
   }
   @Override
   public boolean equals(Object c){
      if (this.equals(c)){
         return true;
      }else {
         return false;
      }
   }

}
