public class HeapImpl implements Heap {
	private boolean isMax;
	private HeapNode[] nodes;
	private int N; // items

	public HeapImpl(boolean isMax, int capacity) {
		this.isMax = isMax;
		nodes = new HeapNode[capacity + 1];
		N = 0;
	}

	public void insert(Comparable key, Object value) {
		HeapNode n = new HeapNode();
		n.key = key;
		n.value = value;
		nodes[++N] = n;
		bubbleUp(N);
	}

	public HeapNode delete() {
		HeapNode max = nodes[1];
		if( N < 0 ) return null;
		HeapNode n = nodes[1];
		swap(1, N--);
		bubbleDown(1);
		nodes[N+1] = null;
		return max;
	}

	public HeapNode peek() {
		return nodes[1];
	}

	public int size() {
		return N;
	}

	private void bubbleUp(int k) {
		while( k > 1 && less(k/2, k) ) {
			swap(k, k/2);
			k = k/2;
			
		}
	}

	private void bubbleDown(int k) {
		while( 2*k <= N ) {
			int j = 2*k;
			boolean less = false;
			if( j < N && less(j, j+1) ) {
				j++;
			}
			if( !less(k, j) )
				break;
			swap(k, j);
			k = j;
		}
	}

	private void swap(int k, int j) {
		HeapNode t = nodes[k];
		nodes[k] = nodes[j];
		nodes[j] = t;
	}

	private boolean less(int k, int j) {
		return isMax ? nodes[k].key.compareTo(nodes[j].key) < 0 : nodes[k].key.compareTo(nodes[j].key) > 0;
	}

	public static void main(String[] args) {
		Heap h = new HeapImpl(true, 10);
		for( int i=1; i<=10; i++ ) {
			h.insert(i, "v" + i);
		}
		HeapNode n = null;
		while( (n = h.delete()) != null ) {
			System.out.println(n.value);
		}
	}
}	
