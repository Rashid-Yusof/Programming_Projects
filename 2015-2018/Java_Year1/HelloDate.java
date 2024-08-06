// HelloDate.java
// Displays the current date
import java.util.Date;

public class HelloDate {
   public static void main( String[] args ) {
      Date d = new Date();
      System.out.println( "The current date: " );
      System.out.println( d );

      d = new Date( -100000 );
      System.out.println( "Another date: " + d );

	  // No need to assign a name to an object
      System.out.println( "The current date: " + new Date() );
   }
}