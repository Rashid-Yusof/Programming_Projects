import java.awt.Point;

public class ReferenceVariableExample {
	public static void main( String[] args ) {

		Point a = new Point( 10, 20 );
		Point b = a;   // The reference b refers to the same Point object as a.

		System.out.println( "a:" + a + " b:" + b );

		// Modify object via a:
		System.out.println( "Excecuting a.translate( 10, 10 );" );
		a.translate( 10, 10 );
		System.out.println( "a:" + a + " b:" + b );

		// Modify object via b:
		System.out.println( "Excecuting b.translate( 10, 10 );" );
		b.translate( 10, 10 );
		System.out.println( "a:" + a + " b:" + b );
	}
}