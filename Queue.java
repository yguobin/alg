public class Queue {
	private class Node {
		private Object value;
		private Node next;
	}
	
	private Node first, last;

	public void add(Object v) {
		Node n = new Node();
		n.value = v;
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
		Object v = first.value;
		first = first.next;
		if( first == null )
			last = null;
		return v;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public static void main(String[] args) {
		Queue s = new Queue();
		for(int i=0; i<10; i++) {
			s.add(i);
		}
		while( !s.isEmpty() ) {
			System.out.println("" + s.remove() + " ");
		}
	}
}
	