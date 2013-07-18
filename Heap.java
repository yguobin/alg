public interface Heap {
	void insert(Comparable key, Object value);
	HeapNode delete();
	HeapNode peek();
	int size();
}