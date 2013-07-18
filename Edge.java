public class Edge {
	private Vertex src;
	private Vertex dest;
	private int cost;
	
	public Edge(Vertex src, Vertex dest) {
		this(src, dest, 1);
	}

	public Edge(Vertex src, Vertex dest, int cost) {
		this.src = src; this.dest = dest; this.cost = cost;
	}

	public Vertex getSource() {
		return src;
	}

	public Vertex getDest() {
		return dest;
	}
	
	public int getCost() {
		return cost;
	}

}