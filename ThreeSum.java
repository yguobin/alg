public class ThreeSum {

	public static void main(String[] args) {
		int[] data = new int[] {1, 2, 3, 4, 5,6, 7, 8, 9, 10};

		HashTable t = new HashTable(data.length);
		for( int i=0; i<data.length; i++ ) {
			t.put(i, i);
		}
		for( int i=0; i<data.length; i++ ) {
			for( int j=i+1; j<data.length; j++ ) {
				int sum = data[i] + data[j];
				Integer k = (Integer)t.get(sum);
				if( k != null ) {
					System.out.println("" + data[i] + " + " + data[j] + " = " + k);
				}
			}
		}
	}
}					