public class Euclid {
	public static int gcd(int m, int n) {
		System.out.println(m);
		System.out.println(n);
		System.out.println();
		if( n == 0 ) return m;
		return gcd(n, m % n);
	}

	
	public static int[] gcdExt(int m, int n) {
		if( n == 0 ) return new int[] {m, 1, 0};
		int mod = m % n;
		int k = (m - mod) / n;
		int[] e = gcdExt(n, mod);
		return new int[] {e[0], e[2], e[1] - k * e[2]};
	}


	public static void main(String[] args) {
		System.out.println(gcd(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
		int[] e = gcdExt(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println(e[0] + " " + e[1] + " " + e[2]);
	}
}