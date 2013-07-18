/* Must use Semaphore here, because Lock must be unlock by the same thread that locks it. */

import java.util.*;
import java.util.concurrent.*;

public class ReaderWriter {
	final Semaphore writeLock = new Semaphore(1);
	final Semaphore readLock = new Semaphore(1);
	final StringBuilder s = new StringBuilder();
	final Random r = new Random();
	final MutableInteger readerCount = new MutableInteger();

	public ReaderWriter() {
		s.append(System.currentTimeMillis());

		int R = 5;
		int W = 2;
		
		for( int i=0; i<R; i++ ) {
			new Thread(new Reader()).start();
		}

		for( int i=0; i<W; i++ ) {
			new Thread(new Writer()).start();
		}

	}
	
	class MutableInteger {
		private volatile int i = 0;

		public void increase() { i++; }
		public void decrease() { i--; }
		public int get() { return i; }

	}
			

	class Reader implements Runnable {
	
		public void run() {
			while( true ) {
				try {
					System.out.println("Begin read");
					readLock.acquire();
					try {
						readerCount.increase();
						if( readerCount.get() == 1 ) {
							writeLock.acquire();
						}
					} finally {
						readLock.release();
					}
	
					read();
	
					readLock.acquire();
					try {
						readerCount.decrease();
						if( readerCount.get() == 0 ) {
							writeLock.release();
						}
					} finally {
						readLock.release();
					}
			
					System.out.println("End read");

					Thread.sleep(r.nextInt(2000));
				} catch( InterruptedException e ) {
				}
			}
		}

		private void read() {
			System.out.println(s);
			if( s.length() == 0 )
				System.out.println("s is empty. WRONG!!!");
		}
	}

	class Writer implements Runnable {

		public void run() {
			while( true ) {
				try {
					System.out.println("Begin write");
					writeLock.acquire();
					try {
						write();
					} finally {
						writeLock.release();
					}	
					System.out.println("End write");
					Thread.sleep(r.nextInt(2000));
				} catch( InterruptedException e ) {
				}
			}
		}

		private void write() {
			s.delete(0, s.length());
			try {
				Thread.sleep(r.nextInt(2000));
			} catch( InterruptedException e ) {
			}
			s.append(System.currentTimeMillis());
		}
	}

	public static void main(String... args) {
		ReaderWriter rw = new ReaderWriter();
	}
	
}
