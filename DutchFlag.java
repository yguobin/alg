public class DutchFlag {

	public static void swap(int[] data, int i, int j) {
		int t = data[i];
		data[i] = data[j];
		data[j] = t;
	}

	public static void main(String[] args) {
		int[] data = new int[] {3, 1, 2, 1, 2, 3, 2, 1, 3, 3, 2, 2, 1};
		int k = 0, m = 0, n = data.length - 1;
		for( ; k<n && k<data.length; k++ ) {
			int c = data[k];
			if( c == 1 ) {
				swap(data, k, m++);
			} else if( c == 3 ) {
				swap(data, k, n--);
			}
		}
		for( k=0; k<data.length; k++ ) {
			System.out.println(data[k]);
		}
	}
}