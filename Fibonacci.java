public class Fibonacci {
	
	public static long fib(int n) {
		long f1 = 0, f2 = 1, t = 0;
		if( n == 0 ) return f1;
		if( n == 1 ) return f2;
		for( int i=2; i<=n; i++ ) {
			t = f1;
			f1 = f2;
			f2 = f1 + t;
		}
		return f2;
	}

	public static void main(String[] args) {
		System.out.println(fib(Integer.parseInt(args[0])));
	}
}