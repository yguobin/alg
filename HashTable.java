public class HashTable {
	private class Node {
		private Object key;
		private Object value;
		private Node next;
	}

	private Node[] buckets;

	public HashTable(int numberOfBuckets) {
		this.buckets = new Node[numberOfBuckets];
	}

	public void put(Object key, Object value) {
		int hash = hash(key);
		for( Node n = buckets[hash]; n != null; n = n.next ) {
			if( key.equals(n.key) ) {
				n.value = value;
				return;
			}
		}
		Node n = new Node();
		n.key = key;
		n.value = value;
		n.next = buckets[hash];
		buckets[hash] = n;
	}

	public Object get(Object key) {
		int hash = hash(key);
		for( Node n = buckets[hash]; n != null; n = n.next ) {
			if( key.equals(n.key) )
				return n.value;
		}
		return null;
	}

	private int hash(Object key) {
		return (key.hashCode() & 0x7fffffff) % buckets.length;
	}

	public static void main(String[] args) {
		HashTable h = new HashTable(4);
		for( int i=0; i<10; i++ ) {
			h.put(i, "v" + i);
		}
		System.out.println(h.get(5));
	}
}