import java.util.concurrent.locks.*;
import java.lang.management.*;

public class DeadLock implements Runnable {

	private static Lock lock1 = new ReentrantLock();
	private static Lock lock2 = new ReentrantLock();

	private int id;

	public DeadLock(int id) {
		this.id = id;
	}

	public void run() {
		final Lock k1 = id == 1 ? lock1 : lock2;
		final Lock k2 = id == 1 ? lock2 : lock1;
		try {
			k1.lockInterruptibly();
			System.out.println("Thread " + id + " obtained first lock");
			Thread.sleep(1000);
			System.out.println("Thread " + id + " trying to obtain 2nd lock");
			k2.lockInterruptibly(); 
			System.out.println("Thread " + id + " obtained 2nd lock");
		} catch( InterruptedException e ) {
			System.out.println("Thread " + id + " interrupted");
		} finally {
			k2.unlock();
			k1.unlock();
		}
	}

		
	public static void main(String[] args) {
		Thread t1 = new Thread(new DeadLock(1));
		Thread t2 = new Thread(new DeadLock(2));
		t1.start();
		t2.start();

		while( true ) {
			ThreadMXBean bean = ManagementFactory.getThreadMXBean();
			long[] threadIds = bean.findDeadlockedThreads(); // Returns null if no threads are deadlocked.

			if (threadIds != null) {
				System.out.println("Dead lock found.");
				ThreadInfo[] infos = bean.getThreadInfo(threadIds);

				for (ThreadInfo info : infos) {
					System.out.println(info.getThreadId() + " " + info.getThreadName());
				}
				t1.interrupt();
			} else {
				System.out.println("No dead lock");
			}

			try {
				Thread.sleep(3000);
			} catch( InterruptedException e ) {
			}
		}
						
	}

}

	