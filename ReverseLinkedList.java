public class ReverseLinkedList {
	public static class Node {
		public int data;
		public Node next;
	}
	
	public static Node reverse(Node node) {
		if( node == null )
			return null;
		Node prev = null, next = null;
		while( true ) {
			next = node.next;
			node.next = prev;
			prev = node;
			if( next == null )
				return node;
			node = next;
		}
	}

	public static Node reverse1(Node node) {
		if( node == null )
			return null;
		if( node.next == null )
			return node;
		Node n = node.next;
		node.next = null;
		Node r = reverse1(n);
		n.next = node;
		return r;
	}

	public static void main(String[] args) {
		Node n1 = new Node();
		n1.data = 1;
		
		Node n2 = new Node();
		n2.data = 2;
		Node n3 = new Node();
		n3.data = 3;
		
		n1.next = n2;
		n2.next = n3;

		Node n = reverse(n1);
		while( n != null ) {
			System.out.println(n.data);
			n = n.next;
		}
	}
}