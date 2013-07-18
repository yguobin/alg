import java.util.List;
import java.util.LinkedList;

public class Graph {
	private List<Vertex> vertices = new LinkedList<Vertex>();

	public void addVertex(Object id) {
		vertices.add(new Vertex(id));
	}

	public Edge addEdge(Object src, Object dest) {
		Vertex u = getVertex(src);
		Vertex v = getVertex(dest);
		if( u != null && v != null ) {
			u.addAdjacentVertex(v);
			return new Edge(u, v);
		}
		return null;
	}

	public Vertex getVertex(Object v) {
		for( Vertex t : vertices ) {
			if( t.getId().equals(v) )
				return t;
		}
		return null;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}
}
