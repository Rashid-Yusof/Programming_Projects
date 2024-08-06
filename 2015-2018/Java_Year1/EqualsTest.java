public class EqualsTest {

    public static void main( String[] args ) {

        Integer a = new Integer( 57 );
        Integer b = new Integer( 57 );
        Integer c = a;

        System.out.println( "(a == b) = " + (a == b) );
        System.out.println( "(a.equals(b)) = " + (a.equals(b)) );
        System.out.println( );
        System.out.println( "(a == c) = " + (a == c) );
        System.out.println( "(a.equals(c)) = " + (a.equals(c)) );
        System.out.println( );
        System.out.println( "(b == c) = " + (b == c) );
        System.out.println( "(b.equals(c)) = " + (b.equals(c)) );
    }

}