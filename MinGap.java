public class MinGap {

	public static void main(String[] args) {
		int m = 4, n = 6;
		int[][] data = new int[m][n];
		data[0] = new int[] {1, 4, 7, 10, 22, 90, 198};
		data[1] = new int[] {12, 98, 202, 342, 345, 365};
		data[2] = new int[] {15, 78, 93, 456, 490, 510};
		data[3] = new int[] {18, 27, 240, 250, 300, 400};

		Heap h = new HeapImpl(false, m);
		int[] indice = new int[m];
		int minGap = 999;
		for( int i=0; i<m; i++ ) {
			h.insert(data[i][indice[i]], i);
		}

		HeapNode node1 = h.delete();
		HeapNode node2 = null;
		int v = (Integer) node1.value;
		indice[v]++;
		if( indice[v] < n ) {
			h.insert(data[v][indice[v]], v);
		}
		while( h.size() > 0 ) {
			node2 = h.delete();
//			System.out.println(node2.key);
			int diff = (Integer)node2.key - (Integer)node1.key;
			if( diff < minGap ) {
				minGap = diff;
			}
			node1 = node2;
			v = (Integer) node2.value;
			indice[v]++;
			if( indice[v] < n ) {
				h.insert(data[v][indice[v]], v);
			}
		}
		
		System.out.println(minGap);
		int x = 0x80000000;
		System.out.println(x);
		System.out.println(Math.abs(x));
		System.out.println(x & 0x7fffffff);
	
	}

}