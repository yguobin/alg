import java.util.concurrent.locks.*;

public class PhilosopherDeadLock implements Runnable {

	private int number;
	private Lock leftChopstick;
	private Lock rightChopstick;

	public Philosopher(int number, Lock leftChopstick, Lock rightChopstick) {
		this.number = number;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;
	}

	public void eat() {
		System.out.println(number + " prepares to eat.");
		leftChopstick.lock();
		System.out.println(number + " picks up left chopstick");
		rightChopstick.lock();
		System.out.println(number + " picks up right chopstick");
/*
		if( rightChopstick.tryLock() ) {
			System.out.println(number + " picks up right chopstick");
			try {
				Thread.sleep(200);
			} catch(InterruptedException e) {
			}
			rightChopstick.unlock();
			System.out.println(number + " put down right chopstick");
		} else {
			System.out.println(number + " can't eat, right chopstick is being used");
		}
*/
		leftChopstick.unlock();
		System.out.println(number + " put down left chopstick");
	}

	public void run() {
		while( true ) {
			eat();
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
			}
		}
	}
	
	public static void main(String[] args) {
		int n = 5;
		Lock[] chopsticks = new Lock[n];
		for( int i=0; i<chopsticks.length; i++ )
			chopsticks[i] = new ReentrantLock();

		Philosopher[] philosophers = new Philosopher[n];
		Thread[] threads = new Thread[philosophers.length];
		for( int i=0; i<philosophers.length; i++ ) {
			philosophers[i] = new Philosopher(i, chopsticks[i%5], chopsticks[(i+1)%5]);
			threads[i] = new Thread(philosophers[i]);
			threads[i].start();
		}
	}
}		