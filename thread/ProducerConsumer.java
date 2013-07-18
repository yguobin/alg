import java.util.*;
import java.util.concurrent.*;

public class ProducerConsumer {
	public static void main(String... args) {
		int N = 5;
		int M = 3;

		Random r = new Random();
		BlockingQueue<String> q = new ArrayBlockingQueue<String>(10);

		Producer[] p = new Producer[N];
		Thread[] pt = new Thread[N];
		Consumer[] c = new Consumer[M];
		Thread[] ct = new Thread[M];

		for( int i=0; i<N; i++ ) {
			p[i] = new Producer(q, i, r.nextInt(10));
			pt[i] = new Thread(p[i]);
			pt[i].start();
		}

		for( int i=0; i<M; i++ ) {
			c[i] = new Consumer(q, i);
			ct[i] = new Thread(c[i]);
			ct[i].start();
		}

		for( int i=0; i<N; i++ ) {
			try {
				pt[i].join();
			} catch( InterruptedException e ) {
			}
		}

		for( int i=0; i<M; i++ ) {
			c[i].done();
		}
		
		for( int i=0; i<M; i++ ) {
			try {
				ct[i].join();
			} catch( InterruptedException e ) {
			}
		}

		System.out.println("All threads are done.");
		System.out.println(String.format("Messages in queue: %d", q.size()));
	}
}

class Producer implements Runnable {
	private BlockingQueue<String> q;
	private int id;
	private int numberOfMessages;

	public Producer(BlockingQueue<String> q, int id, int numberOfMessages) {
		this.q = q;
		this.id = id;
		this.numberOfMessages = numberOfMessages;
	}

	public void run() {
		Random r = new Random();
		int i=numberOfMessages;
		while( i > 0  ) {
			String m = String.format("Producer %d produces #%d", id, i);
			if( q.offer(m) ) {
				System.out.println(m);
				i--;
			}
			try {
				Thread.sleep(r.nextInt(2000));
			} catch( InterruptedException e ) {
			}
		}
		System.out.println(String.format("Producer %d is done", id));
	}
}

class Consumer implements Runnable {
	private BlockingQueue<String> q;
	private int id;
	private boolean done;

	public Consumer(BlockingQueue<String> q, int id) {
		this.q = q;
		this.id = id;
		done = false;
	}

	public void done() {
		this.done = true;
	}

	public void run() {
		Random r = new Random();
		while( true ) {
			try {
				String m = q.poll(1, TimeUnit.MILLISECONDS);
				if( m != null ) {
					System.out.println(String.format("Consumer %d consumers: %s", id, m));
				} else {
					if( done )
						break;
				}
				Thread.sleep(r.nextInt(2000));
			} catch( InterruptedException e ) {
			}
		}
		System.out.println(String.format("Consumer %d is done", id));
	}
}