import java.util.*;

public class MyBlockingQueue<T> {
	private int maxSize;
	private Queue<T> q;

	public MyBlockingQueue(int maxSize) {
		this.maxSize = maxSize;
		q = new LinkedList<T>();
	}

	public boolean offer(T t) {
		synchronized(q) {
			if( q.size() < maxSize ) {
				q.add(t);
				return true;
			} else {
				return false;
			}
		}
	}

	public T poll() {
		synchronized(q) {
			if( !q.isEmpty() ) {
				return q.remove();
			} else {
				return null;
			}
		}
	}

	public void put(T t) throws InterruptedException {
		synchronized(q) {
			while( q.size() >= maxSize ) {
				q.wait();
			}
			q.add(t);
			q.notify();
		}
	}

	public T take(long waitTime) throws InterruptedException {
		synchronized(q) {
			if( waitTime <= 0 ) {
				while( q.isEmpty() ) {
					q.wait();
				}
				T t = q.remove();
				q.notify();
				return t;
			} else {
				if( q.isEmpty() ) {
					q.wait(waitTime);
				}
				if( q.isEmpty() ) {
					return null;
				} else {
					return q.remove();
				}
			}
		}
	}

	public int size() {
		synchronized(q) {
			return q.size();
		}
	}
	
}