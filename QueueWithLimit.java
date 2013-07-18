public class QueueWithLimit {
	private class Node {
		private Object value;
		private Node next;
	}
	
	private Node first, last;
	private int size, maxSize;

	public QueueWithLimit(int maxSize) {
		this.maxSize = maxSize;
		this.size = 0;
	}

	public void add(Object v) {
		Node n = new Node();
		n.value = v;

		if( this.size >= this.maxSize )
			remove();
		this.size++;

		if( first == null )
			first = n;
		if( last == null )
			last = n;
		else {
			last.next = n;
			last = n;
		}
	}

	public Object remove() {
		if( first == null )
			return null;
		this.size--;
		Object v = first.value;
		first = first.next;
		if( first == null )
			last = null;
		return v;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public float update(int i) {
		add(i);
		Node n = first;
		int sum = 0;
		int count = 0;
		while( n != null ) {
			sum += (Integer) n.value;
			count++;
			n = n.next;
		}
		return count > 0 ? ((float)sum) / count : 0f;
	}

	public static void main(String[] args) {
		QueueWithLimit s = new QueueWithLimit(2);
		System.out.println(s.update(1));
		System.out.println(s.update(2));
		System.out.println(s.update(3));
		System.out.println(s.update(4));
	}
}

	