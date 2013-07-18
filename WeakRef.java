import java.util.*;

public class WeakRef {
	public static void main(String[] args) {
		Map<String, String> m = new WeakHashMap<String, String>();
		m.put(new String("key"), new String("value"));
		System.out.println(m.size());
		do {
			System.gc();
			System.out.println(m.size()); // normally, this prints 1
			System.out.println(m.size()); // this prints 0
		} while( m.size() > 0 );
		System.out.println(m.size()); // this prints 0
	}
}		