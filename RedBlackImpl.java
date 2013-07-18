import java.util.*;

public class RedBlackImpl implements BST {
	private static final boolean RED = true;
	private static final boolean BLACK = true;

	private class Node {
		Comparable key;
		Object value;
		Node leftChild;
		Node rightChild;
		boolean color;
		int count;
	}

	Node root;

	public Object get(Comparable key) {
		Node n = root;
		Object v = null;
		while( n != null ) {
			int c = key.compareTo(n.key);
			if( c < 0 ) {
				n = n.leftChild;
			} else if( c == 0 ) {
				v = n.value;
				break;
			} else {
				n = n.rightChild;
			}
		}
		return v;
	}

	public List<Object> list() {
		List<Object> list = new LinkedList<Object>();
		addToList(list, 0, root);		
		return list;
	}

	private void addToList(List<Object> list, int index, Node n) {
		if( n != null ) {
			list.add(index, n.value);
			addToList(list, index, n.leftChild);
			addToList(list, index + 1, n.rightChild);
		}
	}


	public void insert(Comparable key, Object value) {
		root = insert(root, key, value);		
	}

	private Node insert(Node n, Comparable key, Object value) {
		if( n == null ) {
			Node x = new Node();
			x.key = key;
			x.value = value;
			x.count = 1;
			return x;
		}
			
		int c = key.compareTo(n.key);
		if( c < 0 ) {
			n.leftChild = insert(n.leftChild, key, value);
		} else if( c == 0 ) {
			n.value = value;
		} else {
			n.rightChild = insert(n.rightChild, key, value);
		}
		n.count = 1 + size(n.leftChild) + size(n.rightChild);
		return n;	
			
	}

	public void delete(Comparable key) {
		root = delete(root, key);
	}

	private Node delete(Node n, Comparable key) {
		if( n == null )
			return null;
		int c = key.compareTo(n.key);
		if( c < 0 ) {
			n.leftChild = delete(n.leftChild, key);
		} else if( c > 0 ) {
			n.rightChild = delete(n.rightChild, key);
		} else {
			if( n.rightChild == null ) 
				return n.leftChild;
			if( n.leftChild == null )
				return n.rightChild;
			Node t = n;
			n = min(t.rightChild);
			n.rightChild = deleteMin(t.rightChild);
			n.leftChild = t.leftChild;
		}
		n.count--;
		return n;
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node n) {
		if( n.leftChild == null )
			return n.rightChild;
		n.leftChild = deleteMin(n.leftChild);
		n.count--;
		return n;
	}

	public void deleteMax() {
		root = deleteMax(root);
	}

	private Node deleteMax(Node n) {
		if( n.rightChild == null )
			return n.leftChild;
		n.rightChild = deleteMax(n.rightChild);
		n.count--;
		return n;
	}

	public Object min() {
		Node n = min(root);
		if( n == null ) return null;
		else return n.value;
	}

	public Object max() {
		Node n = max(root);
		if( n == null ) return null;
		else return n.value;
	}

	public Comparable floor(Comparable key) {
		Node n = floor(root, key);
		if( n == null ) return null;
		else return n.key;
	}

	public Comparable ceil(Comparable key) {
		Node n = ceil(root, key);
		if( n == null ) return null;
		else return n.key;
	}


	public int size() {
		return size(root);
	}

	public int rank(Comparable key) {
		return rank(key, root);
	}

	private int rank(Comparable key, Node n) {
		if( n == null ) return 0;
		int c = key.compareTo(n.key);
		if( c < 0 ) return rank(key, n.leftChild);
		if( c > 0 ) return 1 + size(n.leftChild) + rank(key, n.rightChild);
		return size(n.leftChild);
	}

	private int size(Node n) {
		if( n == null ) return 0;
		return n.count;
	}


	private Node floor(Node n, Comparable key) {
		if( n == null ) return null;
		int c = key.compareTo(n.key);
		if( c == 0 ) return n;
		if( c < 0 ) return floor(n.leftChild, key);
		Node t = floor(n.rightChild, key);
		if( t != null ) return t;
		else return n;		
	}

	private Node ceil(Node n, Comparable key) {
		if( n == null ) return null;
		int c = key.compareTo(n.key);
		if( c == 0 ) return n;
		if( c > 0 ) return ceil(n.rightChild, key);
		Node t = ceil(n.leftChild, key);
		if( t != null ) return t;
		else return n;		
	}

	private Node min(Node n) {
		if( n == null ) 
			return null;
		while( n.leftChild != null ) {
			n = n.leftChild;
		}
		return n;
	}

	private Node max(Node n) {
		if( n == null ) 
			return null;
		while( n.rightChild != null ) {
			n = n.rightChild;
		}
		return n;
	}

	private boolean isRed(Node n) {
		if( n == null return false;
		return n.color == RED;
	}

	private Node rotateLeft(Node h) {
		

	public static void main(String[] args) {
		BST t = new BSTImpl();
		System.out.println(t.list());
		
		for( int i=10; i>0; i-- ) {
			t.insert(i, "V" + i);
		}

		System.out.println(t.get(5));
		System.out.println(t.list());
		
		t.delete(5);
		System.out.println(t.get(5));
		System.out.println(t.list());

		t.deleteMin();
		System.out.println(t.list());
		t.deleteMax();
		System.out.println(t.list());

		System.out.println(t.size());
		System.out.println(t.floor(5));
		System.out.println(t.ceil(5));

		System.out.println(t.rank(5));
	}

}