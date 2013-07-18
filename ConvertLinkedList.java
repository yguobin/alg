public class ConvertLinkedList {
	private static class SinglyLinkedNode {
		private Object value;
		private SinglyLinkedNode next;
		private SinglyLinkedNode random;
	}

	private static class DoublyLinkedNode {
		private Object value;
		private DoublyLinkedNode next;
		private DoublyLinkedNode prev;
		private DoublyLinkedNode random;
	}

	public static DoublyLinkedNode convert(SinglyLinkedNode root) {
		SinglyLinkedNode n = root;
		DoublyLinkedNode newRoot = null, prev = null, newNode = null;
		
		while( n != null ) {
			newNode = new DoublyLinkedNode();
			newNode.value = n.value;
			if( newRoot == null ) newRoot = newNode;
			newNode.prev = prev;
			prev = newNode;
			n = n.next;
		}

		while( newNode.prev != null ) {
			newNode.prev.next = newNode;
			newNode = newNode.prev;
		}

		return newRoot;
	}

	public static void main(String[] args) {
		SinglyLinkedNode sRoot = null, sPrev = null;
		for( int i=0; i<6; i++ ) {
			SinglyLinkedNode sNode = new SinglyLinkedNode();
			sNode.value = i;
			if( sRoot == null ) sRoot = sNode;
			if( sPrev != null ) sPrev.next = sNode;
			sPrev = sNode;
		}

		DoublyLinkedNode dRoot = convert(sRoot);

		SinglyLinkedNode s = sRoot;
		while( s != null ) {
			System.out.println(s.value);
			s = s.next;
		}
		DoublyLinkedNode d = dRoot, dLast = dRoot;
		while( d != null ) {
			System.out.println(d.value);
			dLast = d;
			d = d.next;
		}
		d = dLast;
		while( d != null ) {
			System.out.println(d.value);
			d = d.prev;
		}
			
	}
}