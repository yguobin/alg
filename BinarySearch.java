public class BinarySearch {

	public static <T extends Comparable> int binarySearch(T[] data, T key) {
		return binarySearch(data, key, 0, data.length);
	}

	private static <T extends Comparable> int binarySearch(T[] data, T key, int start, int end) {
		if( start >= end ) return -1;
		int middle = (start + end) / 2;
		int c = data[middle].compareTo(key);
		if( c == 0 ) return middle;
		if( c < 0 ) return binarySearch(data, key, middle + 1, end);
		return binarySearch(data, key, start, middle);
	}
	
	public static void main(String[] args) {
		Integer[] data = new Integer[] {1, 5, 10, 15, 20, 25, 30};
		System.out.println(binarySearch(data, 1));	
		System.out.println(binarySearch(data, 10));	
		System.out.println(binarySearch(data, 30));	
		System.out.println(binarySearch(data, 18));	
		System.out.println(binarySearch(data, 38));	
	}
}