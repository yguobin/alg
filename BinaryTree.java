import java.util.*;

public class BinaryTree {
	private static class Node {
		int key;
		Node left, right;
	}

	private Node root;

	public void insert(int key) {
		Node n = new Node();
		n.key = key;

		if( root == null ) {
			root = n;
			return;
		}

		Node lastNode = getLastNode(root);
		if( lastNode.left == null )
			lastNode.left = n;
		else
			lastNode.right = n;
	}

	public boolean isBST() {
		return root != null && isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public boolean isBalanced() {
		return maxDepth(root) != -1;
	}

	private int maxDepth(Node n) {
		if( n == null ) return 0;
		int leftMaxDepth = maxDepth(n.left);
		int rightMaxDepth = maxDepth(n.right);
		if( leftMaxDepth == -1 || rightMaxDepth == -1 || Math.abs(leftMaxDepth - rightMaxDepth) > 1 )
			return -1;
		return Math.max(leftMaxDepth, rightMaxDepth) + 1;
	}

	private Node getLastNode(Node n) {
		if( n.left == null || n.right == null )
			return n;
		if( n.left.right == null )
			return n.left;
		if( n.right.right == null )
			return n.right;
		return getLastNode(n.left);
	}		

	private boolean isBST(Node n, int min, int max) {
		if (n == null) return true;
		return min < n.key && isBST(n.left, min, n.key) && n.key < max && isBST(n.right, n.key, max);
	}

	public void printTree() {
		printNode(root);
		System.out.println("");
	}

	public String dfs() {
		StringBuilder b = new StringBuilder();
		dfs(root, b);
		return b.toString().trim();
	}

	public void dfs(Node n, StringBuilder b) {
		if( n != null ) {
			dfs(n.left, b);
			b.append(" " + n.key);
			dfs(n.right, b);
		}
	}
	
	public String bfs() {
		return bfs(root);
	}

	public static String bfs(Node root) {
		StringBuilder b = new StringBuilder();
		LinkedList<Node> nodes = new LinkedList<Node>();
		nodes.add(root);
		Node n = null;
		while( !nodes.isEmpty() ) {
			n = nodes.remove();
			b.append(n.key + " ");
			if( n.left != null )
				nodes.add(n.left);
			if( n.right != null )
				nodes.add(n.right);
		}
		return b.toString().trim();
	}

	public static void buildBST(int[] nodes) {
		Node root = buildBST(nodes, 0, nodes.length - 1);
		System.out.println(bfs(root));
	}

	private static Node buildBST(int[] nodes, int i, int j) {
		System.out.println(i + " " + j);
		int mid = (j + i) / 2;
		Node n = new Node();
		n.key = nodes[mid];
		if( mid > i ) 
			n.left = buildBST(nodes, i, mid-1);
		if( j > mid )
			n.right = buildBST(nodes, mid+1, j);
		return n;
	}			
		

	private void printNode(Node n) {
		if( n != null ) {
			System.out.print(n.key + " ");
			if( n.left != null )
				System.out.print(n.left.key + " ");
			if( n.right != null )
				System.out.print(n.right.key + " ");
		}
	}

	public static void main(String[] args) {
		BinaryTree t;
		t = new BinaryTree();
		t.insert(2);
		t.insert(1);
		t.insert(3);
		t.insert(5);
		t.insert(4);
		t.insert(6);
		System.out.println(t.isBST());

		t = new BinaryTree();
		t.insert(4);
		t.insert(2);
		t.insert(6);
		t.insert(1);
		t.insert(3);
		t.insert(5);
		System.out.println(t.dfs());
		System.out.println(t.bfs());
		System.out.println(t.isBST());
		System.out.println(0.0d == -0.0d);
		System.out.println(new Double(0.0d).equals(new Double(-0.0d)));

		System.out.println();
		System.out.println(t.isBalanced());

		int[] in = new int[10];
		for( int i=0; i<10; i++ )
			in[i] = i;
		buildBST(in);
	
	}
}