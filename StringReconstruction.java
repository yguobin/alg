import java.util.*;

public class StringReconstruction {
	public static String reconstruct(String s, Set<String> dict) {
		int n = s.length();
		int[][] F = new int[n][n];
		for( int d = 1; d<n; d++ ) {
			for( int i=0; i<(n-d+1); i++ ) {
				int j = i+d-1;
				if( dict.contains(s.substring(i, j+1)) ) {
					F[i][j] = j;
				} else {
					for( int k = i + 1; k<j; k++ ) {
						if( F[i][k] != 0 && F[k+1][j] != 0 ) {
							F[i][j] = k;
						}
					}
				}
			}
		}

		int m = 0;
		StringBuilder b = new StringBuilder();
		while( m < n ) {
			b.append(s.substring(F[m][n-1]));
			b.append(" ");
			m += F[m+1][n-1];
		}
		return b.toString();
	}

	public static void main(String[] args) {
		Set<String> dict = new HashSet<String>();
		dict.add("the");
		dict.add("sea");
		dict.add("are");
		dict.add("son");
		dict.add("these");
		dict.add("reasons");
		System.out.println(reconstruct("thesearethereasons", dict));
	}
}
	