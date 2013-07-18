public class PrintMatrix {

	public static StringBuilder print(int[][] A) {
		int m = A.length, n = A[0].length;
		int size = m * n;
		int x=0, y=0, i=0, count=0;
		StringBuilder b = new StringBuilder();

		while( count < size ) {
			for( i=x; i<n; i++ ) {
				b.append(A[y][i]); b.append(" "); count++;
			}
			for( i=y+1; i<m; i++ ) {
				b.append(A[i][n-1]); b.append(" "); count++;
			}
			for( i=n-2; i>=x; i-- ) {
				b.append(A[m-1][i]); b.append(" "); count++;
			}
			for( i=m-2; i>y; i-- ) {
				b.append(A[i][x]); b.append(" "); count++;
			}
			x++; y++; n--; m--;
		}
		return b;				
	}

	public static StringBuilder printInsideOut(int[][] A) {
		return print(A).reverse();
	}

	public static void main(String[] args) {
		int[][] A = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
		System.out.println(print(A));
		System.out.println(printInsideOut(A));
	}
}