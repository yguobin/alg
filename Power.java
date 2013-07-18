public class Power {
	public static long power(int a, int b) {
		long r = 0, p = a;
		while ( b != 0 ) {
			if( (b & 1) == 1 ) {
				r += p;
			}
			b = b >>> 1;
			p = p * p;
		}
		return r;
	}

	public static void main(String[] args) {
		System.out.println(power(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
	}
}