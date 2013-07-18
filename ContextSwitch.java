//import javax.util.concurrent.lock.*;

public class ContextSwitch {
	private static class ThreadInfo {
		public int threadId;
		public long ts;
		public ThreadInfo(int threadId, long ts) {
			this.threadId = threadId;
			this.ts = ts;
		}
	}

	private static class ThreadA extends Thread {
		private int threadId;
		private static final int maxLoop = 10000;
		private int loopCount = 0;
		private static Stack lastThread = new Stack();
		private static Stack csTime = new Stack();

		public ThreadA(int threadId) {
			this.threadId = threadId;
		}

		public void run() {
			while( loopCount++ < maxLoop ) {
				synchronized(ThreadA.class) {
					long ts = System.currentTimeMillis();
					if( !lastThread.isEmpty() ) {
						ThreadInfo i = (ThreadInfo) lastThread.pop();
						if( i.threadId != this.threadId ) {
							csTime.push(ts - i.ts);
						}		
					}
					lastThread.push(new ThreadInfo(this.threadId, System.currentTimeMillis()));
				}
			}
		}
	}

	public static void main(String[] args) {
		ThreadA t1 = new ThreadA(1);
		ThreadA t2 = new ThreadA(2);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch(InterruptedException e) {
			System.out.println("Interrupted.");
		}
		printCSTime();
	}

	private static void printCSTime() {
		long csTime = 0, count = 0;
		while( !ThreadA.csTime.isEmpty() ) {
			csTime += (Long) ThreadA.csTime.pop();
			count++;
		}
		System.out.println("Totoal CS: " + count);
		System.out.println("Avg time: " + ((float)csTime) / ((float)count));
	}
}
			