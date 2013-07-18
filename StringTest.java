public class StringTest {

	public static void main(String[] args) {
		char[] a = new char[] {'a', 'b', 'c'};
		String s1 = new String(a);
		System.out.println(s1.equals(a));
		String s2 = "abc";
		System.out.println(s1 == s2);
		System.out.println(s1.equals(null));
		System.out.println(s1.contentEquals(null));
	}

}