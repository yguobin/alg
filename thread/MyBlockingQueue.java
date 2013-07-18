import java.util.*;

public class MyBlockingQueue<T> {
	private int maxSize;
	private Queue<T> q;
	private int currentItemIndex;

	public MyBlockingQueue(int maxSize) {
		this.maxSize = maxSize;
		q = new LinkedList<T>();
		this.currentItemIndex = -1;
	}

	public synchronized boolean offer(T t) {
		if( currentItemIndex+1 < maxSize ) {
			q.add(t);
			return true;
		} else {
			return false;
		}
	}

	public synchronized T poll() {
		if( !q.isEmpty() ) {
			return q.remove();
		} else {
			return null;
		}
	}

	public synchronized int size() {
		return q.size();
	}
	
}