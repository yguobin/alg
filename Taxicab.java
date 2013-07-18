public class Taxicab {
	public static void main(String[] args) {
		int n = 100;
		int[] cubes = new int[n];
		for( int i=1; i<n; i++ ) {
			cubes[i] = i * i * i;
		}
		
		HashTable set = new HashTable(100);
		for( int i=1; i<n; i++ ) {
			for( int j=i+1; j<n; j++ ) {
				int sum = cubes[i] + cubes[j];
				if( set.get(sum) != null ) {
					System.out.println(sum);
				} else {
					set.put(sum, sum);
				}
			}
		}
				
	}
}