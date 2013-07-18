//import static java.lang.System; // this is invalid
import static java.lang.System.*;

public class XOR {
	public static void main(String... args) {
		// if( 1 ^ 2 ) { // this is invalid
		if( true & false ) { // this is valid, and evaluated to be false
		// if( true ^ false ) { // this is valid, and evaluated to be true
			System.out.println("Good");
		}

		java.util.List list = new java.util.ArrayList();
		list.add(5);
		list.add(new Integer("17"));
		System.out.println(list);
		String $ = ""; // this is OK
	}

	public void XOR() {} // this is OK, not a constructor
}

class _ { } // this is OK