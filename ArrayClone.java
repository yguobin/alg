public class ArrayClone {
	private static class Mutable {
		public String s;
	}
	public static void main(String[] args) {
		Mutable[] m = new Mutable[1];
		m[0] = new Mutable();
		m[0].s = "abc";
		
		Mutable[] n = m.clone();	// shallow copy
		m[0].s = "xyz";
		System.out.println(m[0].s);
		System.out.println(n[0].s);
	}
}