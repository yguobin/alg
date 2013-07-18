public class CommonSet {
	public static void main(String[] args) {
		int[] a = new int[] {2, 3, 6, 8, 10, 24};
		int[] b = new int[] {34, 24, 9, 8, 35, 28, 90, 2};
		HashTable h = new HashTable(10);
		Queue q = new Queue();
		for( int i=0; i<a.length; i++ ) {
			h.put(a[i], a[i]);
		}
		for( int i=0; i<b.length; i++ ) {
			if( h.get(b[i] ) != null )
				q.add(b[i]);
		}
		while( !q.isEmpty() ) {
			System.out.println(q.remove());
		}
	}
}
		