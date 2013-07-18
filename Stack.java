public class Stack {
	private class Node {
		private Object value;
		private Node next;
	}
	
	private Node top;

	public Object pop() {
		if( top == null ) return null;
		Object v = top.value;
		top = top.next;
		return v;
	}

	public void push(Object v) {
		Node n = new Node();
		n.value = v;
		n.next = top;
		top = n;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public static void main(String[] args) {
		Stack s = new Stack();
		for(int i=0; i<10; i++) {
			s.push(i);
		}
		while( !s.isEmpty() ) {
			System.out.println("" + s.pop() + " ");
		}
	}
}

	