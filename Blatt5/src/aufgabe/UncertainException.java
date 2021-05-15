package aufgabe;

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
         throw new RuntimeException();
      } catch (RuntimeException e) {
         i++;
         throw new ClassCastException();
      } finally {
         i++;
         throw new NumberFormatException();
      }
   }

   public int uncertain2() {
      for (; ; ) {
         try {
            break;
         } catch (RuntimeException e) {
            i++;
         } finally {
            i++;
            throw new RuntimeException();
         }
      } // here the return statement was put after the finally block making it unreachable
      // return i++;
   }

   public int uncertain3() {
      do {
         try {
            throw new RuntimeException();
         } catch (RuntimeException e) {
            i++;
            continue;
         } finally {
            i++;
         }
      } while (false);
      return i++;
   }

   public int uncertain4() {
      try {
         return i++;
      } catch (RuntimeException e) {
         i++;
      } finally {
         i++;
      }
      return i++;
   }

   public int uncertain5() {
      try {
         return i;
      } finally {
         throw new RuntimeException();
      }
   }

   public int uncertain6() {
      try {
         throw new RuntimeException();
      } finally {
         return ++i;
      }
   }

   public int uncertain7() {
      try {
         throw new java.io.IOException();
      } catch (RuntimeException e) {
         i++;
      } finally {
         return i++;
      }
   }

   public int uncertain8() {
      try {
         throw new NumberFormatException();
      } catch (RuntimeException e) {
         i++;
         throw new RuntimeException();
      } finally {
         i++;
      }
   }

   public int uncertain9() {
      try {
         throw new ClassCastException();
      } catch (RuntimeException e) {
         return i++;
      } finally {
         return i++;
      }
   }

   public int uncertain10() {
      try {
         throw new java.io.IOException(); //here we throw a new Exception but we also have to catch it (which wasn't done originally)
      } catch (java.io.IOException e) {
      } catch (RuntimeException e) {
      }
      return 1;

   }
   /** Explanations:
    * In uncertain1 we throw a new RuntimeException after catching it we increment i by 1 and thow a new ClassCast Exception.
    * However, this exception is not caught as a new NumberFormat Exception is thrown in the finally part
    * (after incrementing i by 1 resulting in i = 2).
    * For the second uncertain we enter a endless loop, however in the first iteration we break this loop.
    * Even though the RuntimeException would catch (while incrementing i) it isn't as the programm is broken before.
    * The result although is i = 1 which is a result of the finally block being executed even after a explicit break.
    * The uncertain3 method employs a do while loop where do to the continue command and the false boolean in the while
    * condition only one iteration is done.
    * The continue skips the iteration and thus directly returns the 2 times incremented i.
    * However after that the finally catches one additionally incrementing i one time resulting in i being 3.
    * In uncertain4 i is incremented after the return statement and overshadows it.
    * Uncertain5, uncertain6 and uncertain7 are all examples of the finally statement overscribin the return statement
    * (however in 5 the throw undoes the return statement and in 6,7 the opposite is done).
    * Uncertain8 shows that NumberFormatException is a RuntimeException thus i is 2 and the Runtime Exception is thrown.
    * Uncertain9 demonstrates that the finally undoes the return statement in the catch above it.
    * After solving the missing declaration of the IPException only 1 is returned (but i is never incremented so its 0).
    */
}