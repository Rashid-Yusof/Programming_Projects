public class AutoboxingExample {
   public static void main(String[] args) {
      int x = 3;
	  Integer y = x + 5;
	  System.out.println("y = " + y);
	  
	  // Can compare Integer objects to int variables/literals
	  System.out.println("y == 8 : " + (y == 8));
	  
	  // But be careful
	  Integer z = new Integer(8);
	  System.out.println("y == z : " + (y == z));
	  
   }
}