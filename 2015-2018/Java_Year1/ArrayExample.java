// ArrayExample.java
// An example of simple arrays

public class ArrayExample {
   public static void main( String[] args ) {
      int[] a = { 1, 2, 3, 4, 5 };

      // Output elements.
      for (int i = 0; i < a.length; ++i) {
         System.out.print( a[i] + " " );
      }
      System.out.println();

      // Set elements.
      float[] b = new float[5];
      for (int i = 0; i < b.length; ++i) {
         b[i] = (float) i / 5;
      }

      // Output elements.
      for (int i = 0; i < b.length; ++i) {
         System.out.print( b[i] + " " );
      }
      System.out.println();
   }
}
