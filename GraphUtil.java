import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;

public class GraphUtil {

	interface Observer {
		void observe(Vertex v);
	}

	static class ConsoleObserver implements Observer {
		public void observe(Vertex v) {
			System.out.println(v.getId());
		}
	}

	public static void dfs(Graph g, Observer observer) {
		Set<Object> visited = new HashSet<Object>();
		Stack s = new Stack();
		
		for( Vertex v : g.getVertices() ) {
			s.push(v);
		}

		while( !s.isEmpty() ) {
			Vertex v = (Vertex) s.pop();
			if( !visited.contains(v) ) {
				visited.add(v);
				observer.observe(v);
				for( Vertex c : v.getAdjacentVertices() ) {
					s.push(c);
				}
			}
		}
	}

	public static void bfs(Graph g, Observer observer) {
		Set<Object> visited = new HashSet<Object>();
		Queue q = new Queue();
		
		for( Vertex v : g.getVertices() ) {
			q.add(v);
		}

		while( !q.isEmpty() ) {
			Vertex v = (Vertex) q.remove();
			if( !visited.contains(v) ) {
				visited.add(v);
				observer.observe(v);
				for( Vertex c : v.getAdjacentVertices() ) {
					q.add(c);
				}
			}
		}
	}

	public static boolean hasPath(Graph g, Object src, Object dest) {
		Set<Object> visited = new HashSet<Object>();
		Queue q = new Queue();
		boolean hasPath = false;
		
		q.add(g.getVertex(src));
		while( !q.isEmpty() ) {
			Vertex v = (Vertex) q.remove();
			if( !visited.contains(v) ) {
				visited.add(v);
				if( v.getId().equals(dest) ) {
					hasPath = true;
					break;
				}
				for( Vertex c : v.getAdjacentVertices() ) {
					q.add(c);
				}
			}
		}
		return hasPath;
	}

	public static List<Object> getPath(Graph g, Object src, Object dest) {
		Set<Object> visited = new HashSet<Object>();
		HashTable h = new HashTable(10);
		Queue q = new Queue();
		boolean hasPath = false;
		
		q.add(g.getVertex(src));
		Vertex prev = null;
		while( !q.isEmpty() ) {
			Vertex v = (Vertex) q.remove();
			if( !visited.contains(v) ) {
				visited.add(v);
				if( v.getId().equals(dest) ) {
					hasPath = true;
					break;
				}
				for( Vertex c : v.getAdjacentVertices() ) {
					q.add(c);
					h.put(c, v);
				}
			}
		}
		
		if( hasPath ) {
			LinkedList<Object> path = new LinkedList<Object>();
			Vertex v = g.getVertex(dest);
			while( v != null ) {
				path.addFirst(v.getId());
				v = (Vertex) h.get(v);
			}
			return path;
		}

		return null;

	}

	public static void main(String[] args) {
		Observer observer = new ConsoleObserver();

		Graph g = new Graph();
		for( int i=7; i>=1; i-- ) {
			g.addVertex(i);
		}

		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 4);
		g.addEdge(2, 5);
		g.addEdge(3, 6);
		g.addEdge(3, 7);

		dfs(g, observer);

		System.out.println();

		g = new Graph();
		for( int i=1; i<=7; i++ ) {
			g.addVertex(i);
		}

		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 4);
		g.addEdge(2, 5);
		g.addEdge(3, 6);
		g.addEdge(3, 7);

		bfs(g, observer);

		System.out.println(hasPath(g, 1, 6));
		System.out.println(hasPath(g, 4, 7));

		System.out.println(getPath(g, 1, 6));
		System.out.println(getPath(g, 4, 7));
	}
}