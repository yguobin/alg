public class HeapMedian {
	Heap heapMin;
	Heap heapMax;

	public HeapMedian(int capacity) {
		heapMin = new HeapImpl(false, capacity);
		heapMax = new HeapImpl(true, capacity);
	}
	
	public void insert(Integer n) {
		HeapNode max = heapMax.peek();
		HeapNode min = heapMin.peek();

		if( max == null ) {
			heapMax.insert(n, n);
		} else {
			if( n.compareTo((Integer)max.key) < 0 ) {
				heapMax.insert(n, n);
				if( heapMax.size() > heapMin.size() + 1 ) {
					HeapNode k = heapMax.delete();
					heapMin.insert(k.key, k.value);
				}
			} else {
				heapMin.insert(n, n);
				if( heapMin.size() > heapMax.size() + 1 ) {
					HeapNode k = heapMin.delete();
					heapMax.insert(k.key, k.value);
				}
			}
		}
	}

	public float getMedian() {
		HeapNode max = heapMax.peek();
		HeapNode min = heapMin.peek();
		if( heapMin.size() != heapMax.size() )
			return (Integer) min.key;
		else {
			float f = (Integer)min.key + (Integer) max.key;
			return f / 2.0f;
		}
	}

	public static void main(String[] args) {
		HeapMedian m;
		m = new HeapMedian(11);
		for( int i=1; i<10; i++ ) {
			m.insert(i);
		}
		System.out.println(m.getMedian());
		m = new HeapMedian(11);
		for( int i=1; i<=10; i++ ) {
			m.insert(i);
		}
		System.out.println(m.getMedian());
		m = new HeapMedian(11);
		for( int i=20; i>10; i-- ) {
			m.insert(i);
		}
		System.out.println(m.getMedian());
		m = new HeapMedian(11);
		for( int i=20; i>=10; i-- ) {
			m.insert(i);
		}
		System.out.println(m.getMedian());
	}

}