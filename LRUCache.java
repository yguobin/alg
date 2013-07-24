import java.util.*;

public class LRUCache<K, V> {
	class Node<K, V> {
		public K key;
		public Long ts;
		public V value;
	}

	private int size;
	private Map<K, Node<K, V>> hashMap = new HashMap<K, Node<K, V>>();
	private TreeMap<Long, Node<K, V>> tsMap = new TreeMap<Long, Node<K, V>>();

	private static long ts = 1;

	public LRUCache(int size) {
		this.size = size;
	}

	public V get(K key) {
		V value = null;
		Node<K, V> n = hashMap.get(key);
		if( n != null ) {
			value = n.value;
			tsMap.remove(n.ts);
			n.ts = ts++;
			tsMap.put(n.ts, n);
		} 
		return value;
	}

	public Long getTS(K key) {
		Node<K, V> n = hashMap.get(key);
		if( n != null ) {
			return n.ts;
		} else {
			return null;
		}
	}

	public void put(K key, V value) {
		Node<K, V> n = hashMap.get(key);
		if( n != null ) {
			Long ts = n.ts;
			tsMap.remove(ts);
		} else {
			if( tsMap.size() >= this.size ) {
				Node<K, V> lruNode = tsMap.get(tsMap.firstKey());
				tsMap.remove(lruNode.ts);
				hashMap.remove(lruNode.key);
			}
			n = new Node<K, V>();
			n.key = key;
		}
		n.ts = ts++;
		n.value = value;
		hashMap.put(n.key, n);
		tsMap.put(n.ts, n);
	}

	public static void main(String... args) throws Exception {
		for( int size = 128000; size <= 1024000 * 16; size += size ) {

			LRUCache<Integer, String> c = new LRUCache<Integer, String>(size);
			
			for( int i=1; i<=size; i++ ) {
				c.put(i, "Item " + i);
			}

			long s, t;

			s = System.currentTimeMillis();
			for( int i=1; i<=size; i++ ) {
				c.get(i);
			}
			t = System.currentTimeMillis();
			long getTime = t - s;

			s = System.currentTimeMillis();
			for( int i=size + 1; i<=2 * size; i++ ) {
				c.put(i, "Item " + i);
			}
			t = System.currentTimeMillis();
			long putTime = t - s;

			System.out.println(String.format("%d\t%d\t%d\t%f\t%f", size, getTime, putTime, 
				((float)getTime)/((float)size), ((float)putTime)/((float)size)));		
		}
	}

}
	