import java.io.IOException;

public class UncertainException {

   private int i = 0;

   public static void uncertain(int number) {
      UncertainException uncertain = new UncertainException();
      int result = 0;

      try {
         System.out.println("uncertain" + number + "()");
         switch (number) {
            case 1:
               result = uncertain.uncertain1();
               break;
            case 2:
               result = uncertain.uncertain2();
               break;
            case 3:
               result = uncertain.uncertain3();
               break;
            case 4:
               result = uncertain.uncertain4();
               break;
            case 5:
               result = uncertain.uncertain5();
               break;
            case 6:
               result = uncertain.uncertain6();
               break;
            case 7:
               result = uncertain.uncertain7();
               break;
            case 8:
               result = uncertain.uncertain8();
               break;
            case 9:
               result = uncertain.uncertain9();
               break;
            case 10:
               result = uncertain.uncertain10();
               break;
         }
         System.out.println("result = " + result + ", i = " + uncertain.i);
      } catch (Exception e) {
         System.out.println("i = " + uncertain.i + " Exception (" + e.getClass()
               .getName() + ")");
      } finally {
      }
   }

   public static void main(String args[]) {
      for (int i = 1; i <= 10; ++i) {
         uncertain(i);
      }
   }

   public int uncertain1() {
      try {
         throw new RuntimeException(); // RuntimeException is thrown and will be caught in the catch part.
      } catch (RuntimeException e) {
         i++;
         // i was increased and now we throw an ClassCastException but will visit the finally block before terminating.
         throw new ClassCastException();
      } finally {
         i++; // i gets increased again (now i = 2)
         // throw in finally overwrites the throw of the ClassCastException and only the NumberFormatException will be passed on.
         throw new NumberFormatException();
      }
   }

   public int uncertain2() {
      for (; ; ) { // endless loop
         try {
            break; // break the endless loop - no exception thrown!
         } catch (RuntimeException e) {
            i++; // not reached
         } finally {
            i++; // i = 1
            throw new RuntimeException(); // terminate with RuntimeException
         }
      }
      // return i++; // unreachable statement error.
   }

   public int uncertain3() {
      do {
         try {
            throw new RuntimeException();
         } catch (RuntimeException e) {
            // exception gets caught.
            i++; // i = 1
            continue; // redundant as continue is the last statement in the loop + condition is false anyway.
         } finally {
            i++; // i = 2
         }
      } while (false);
      return i++; // i will be incremented after its former value was returned. Thus return value = 2, i = 3.
   }

   public int uncertain4() {
      try {
         return i++; // increment i after returning i's former value. Thus return value = 0, i = 2.
      } catch (RuntimeException e) {
         // not reached.
         i++;
      } finally {
         i++; // increment i after return in try block.
      }
      // not reached.
      return i++;
   }

   public int uncertain5() {
      try {
         return i;
      } finally {
         // exception terminates process before conducting the return.
         throw new RuntimeException();
      }
   }

   public int uncertain6() {
      try {
         throw new RuntimeException();
      } finally {
         // RuntimeException wont be caught, but overwritten by the return statement in the finally block.
         return ++i; // increment i before returning its value. i = 1
      }
   }

   public int uncertain7() {
      try {
         throw new java.io.IOException();
      } catch (RuntimeException e) {
         // IOException is not related to RuntimeException, thus not caught.
         i++;
      } finally {
         // return overwrites the IOException throwing.
         return i++; // i will be incremented after returning i's former value. Return value = 0, i = 1.
      }
   }

   public int uncertain8() {
      try {
         throw new NumberFormatException();
      } catch (RuntimeException e) {
         // NumberFormatException is derived from RuntimeException thus it will be caught here.
         i++;
         throw new RuntimeException();
      } finally {
         i++; // increment i to i = 2 and finally throw the RuntimeException.
      }
   }

   public int uncertain9() {
      try {
         throw new ClassCastException();
      } catch (RuntimeException e) {
         // ClassCastException is derived from RuntimeException, thus it will be caught here.
         return i++; // increment i after returning even though this return will not terminate.
      } finally {
         // i = 2, return value = 1.
         return i++; // former increment was applied and another increment will be added after returning here.
      }
   }
   //                       was missing and IOException was not caught in catch
   public int uncertain10() throws IOException {
      try {
         throw new java.io.IOException();
      } catch (RuntimeException e) {
         // never reached as IOException is not derived from RuntimeException.
      }
      // never reached as IOException has already terminated the process.
      return 1;
   }
}