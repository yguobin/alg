import java.util.*;

public class Generics {
	// if this method is named print, it will be compile error
	public static void print1(Collection<? extends List> c) {
		for( List o : c ) {
			System.out.println(o);
		}
	}

	// if this method is named print or print1, it will be compile error
	// this method is not callable, as no instance of extactly Collection<List> can be created
	public static void print2(Collection<List> c) {
		for( List o : c ) {
			System.out.println(o);
		}
	}

	public static <T> void print3(Collection<T> c) {
		for( T o : c ) {
			System.out.println(o);
		}
	}

	// this is invalid
	// public static <T extends List> void print4(Collection<T extends List> c) {
	// this is valid, but print1 is more preferrable. See JLS p53.
	public static <T extends List> void print4(Collection<T> c) {
		for( T o : c ) {
			System.out.println(o);
		}
	}

	// this method is not callable, as no instance of extactly Collection<? super List> can be created
	public static void print5(Collection<? super List> c) {
		// for( List o : c ) { // invalid
		for( Object o : c ) {
			System.out.println(o);
		}
	}

	public static void print(Collection<?> c) {
		for( Object o : c ) {
			System.out.println(o);
		}
	}

	public static void main(String[] args) {
		List<Object> l = new ArrayList<Object>();  // valid
		// List<List<Object>> c = new ArrayList<ArrayList<Object>>(); // invalid
		List<ArrayList<Object>> c = new ArrayList<ArrayList<Object>>(); // valid
		print(c);
		print1(c);
		// print2(c);  // invalid
		print3(c);
		print4(c);
		// print5(c);  // invalid

		// Outer.Inner<Double> x = null; // invalid
		// Outer<Integer>.Inner x = null; // invalid
		Outer<Integer>.Inner<Double> x = null;
		Outer.Inner y = null;
		// Outer.Inner z = new Outer.Inner(); // Invalid
		// Outer.Inner z = new Outer<Integer>.Inner<Double>(); // unchecked compiled error
		Outer<Integer>.Inner<Double> z = new Outer<Integer>.Inner<Double>();
		z.s = 0.0;	
	}
}

class Outer<T> {
	class Inner<S> { S s; }
}

