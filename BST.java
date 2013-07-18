import java.util.*;

public interface BST {
	Object get(Comparable key);
	List<Object> list();
	void insert(Comparable key, Object value);
	void delete(Comparable key);

	void deleteMin();
	Object min();
	void deleteMax();
	Object max();

	public Comparable floor(Comparable key);
	public Comparable ceil(Comparable key);

	public int size();
	public int rank(Comparable key);

	List<Object> inOrderTraverse();

}