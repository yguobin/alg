import java.util.List;
import java.util.LinkedList;

public class Vertex {
	private Object id;
	private List<Vertex> adjacentVertices = new LinkedList<Vertex>();

	public Vertex() {
		this.id = "";
	}

	public Vertex(Object id) {
		this.id = id;
	}

	public Object getId() {
		return this.id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public List<Vertex> getAdjacentVertices() {
		return this.adjacentVertices;
	}

	public void addAdjacentVertex(Vertex v) {
		if( v != null && !v.equals(this) && !adjacentVertices.contains(v) )
			this.adjacentVertices.add(v);
	}

	public void removeAdjacentVertex(Vertex v) {
		this.adjacentVertices.remove(v);
	}

	public boolean equals(Object o) {
		if( o == null || !(o instanceof Vertex) ) return false;
		Vertex v = (Vertex)o;
		return v.getId() != null && v.getId().equals(this.getId());
	}

	public int hashCode() {
		return this.getId().hashCode();
	}
}