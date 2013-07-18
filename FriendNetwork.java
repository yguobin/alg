import java.security.SecureRandom;

class Log {
	public int ts;
	public int i;
	public int j;
}	

public class FriendNetwork {
	private int network[];
	private int size[];
	private int largest[];
	private int numberOfClusters;
	public int calls = 0;

	public FriendNetwork(int N) {
		network = new int[N];
		size = new int[N];
		largest = new int[N];
		for( int i=0; i<N; i++ ) {
			network[i] = i;
			size[i] = 1;
			largest[i] = i;
		}
		numberOfClusters = N;
		calls = 0;
	}

	public void union(int i, int j) {
		int p = root(i);
		int q = root(j);
		if( root(i) != root(j) ) {
			if( size[p] < size[q] ) {
				network[i] = q;
				size[p] += size[q];
				
			} else {
				network[j] = p;
				size[q] += size[p];
			}
			numberOfClusters--;
		}
	}

	public int root(int i) {
		calls++;
		while( i != network[i] ) {
			calls++;
			network[i] = network[network[i]];
			i = network[i];
		}
		return i;
	}

	public int getNumberOfClusters() {
		return this.numberOfClusters;
	}

	public static void main(String[] args) {
		int N = 1000 * 1000;
		FriendNetwork f = new FriendNetwork(N);
		Log[] logs = new Log[2 * N];
		SecureRandom r = new SecureRandom();
		for( int i=0; i<2 * N; i++ ) {
			int x = r.nextInt(N);
			int y = r.nextInt(N);
			Log log = new Log();
			log.ts = 100+i;
			log.i = x;
			log.j = y;
			logs[i] = log;
		}
		
		Log last = null;
		for( Log log : logs ) {
			f.union(log.i, log.j);
			if( f.getNumberOfClusters() <= 1 ) {
				last = log;
				break;
			}
		}
		if( last != null ) {
			System.out.println("All friends made on: " + last.ts);
		} else {
			System.out.println("Not reached.");
		}
		System.out.println("Calls to root(): " + f.calls);
	}
}