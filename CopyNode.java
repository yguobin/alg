import java.util.*;

public class CopyNode {
	static class Node {
		public String data;
		public List<Node> chain;
	}

	public static Node copyNode(Node node) {
		HashMap<Node, Node> myNodes = new HashMap<Node, Node>();
		return copyNode(node, myNodes);
	}

	public static Node copyNode(Node node, HashMap<Node, Node> myNodes) {
		Node n = myNodes.get(node);
		if( n != null )
			return n;

		n = new Node();
		n.data = new String(node.data);
		n.chain = new ArrayList<Node>(node.chain.size());
		myNodes.put(node, n);

		int i=0;
		for( Node n1 : node.chain ) {
			n.chain.add(copyNode(n1, myNodes));
		}
		return n;
	}

	public static void main(String[] args) {
		Node n = new Node();
		n.data = "hello";
		n.chain = new ArrayList<Node>();
		n.chain.add(n);
		Node n1 = copyNode(n);
		System.out.println(n1.data);		
		System.out.println(n1.chain.size());		
		System.out.println(n1.chain.get(0).chain.size());		
	}
}