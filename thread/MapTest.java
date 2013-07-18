import java.util.*;
import java.util.concurrent.*;

public class MapTest implements Runnable {
	int mode;
	Map<String, String> map = null;	

	public MapTest(int mode, Map<String, String> map) {
		this.mode = mode;
		this.map = map;
	}

	public void run() {
		if( mode == 0 ) { // READ
//			synchronized(map) {  // this is required for Collections.synchronizedMap()
				Set<Map.Entry<String, String>> entries = map.entrySet();
				for( Map.Entry<String, String> entry : entries ) {
					System.out.println(entry.getKey() + ": " + entry.getValue());
					try {
						Thread.sleep(2000);
					} catch( InterruptedException e ) {
					}
				}
//			}
		} else {
			for( int i=10; i>0; i-- ) {
				map.remove(String.valueOf(i));
				System.out.println(String.valueOf(i) + " removed");
				try {
					Thread.sleep(1000);
				} catch( InterruptedException e ) {
				}
			}
		}		
			
	}

	public static void main(String... args) {
//		Map<String, String> map = new HashMap<String, String>(); // This causes java.util.ConcurrentModificationException
		// If the iterator is not synchronized, This still causes java.util.ConcurrentModificationException
//		Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
		ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
 
		for( int i=0; i<10; i++ ) {
			map.put(String.valueOf(i), String.valueOf(i));
		}
		
		Thread readThread = new Thread(new MapTest(0, map));
		Thread writeThread = new Thread(new MapTest(1, map));

		readThread.start();
		writeThread.start();
	}
}
	