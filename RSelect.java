import java.util.*;

public class RSelect {

	public static int select(int[] data, int k) {
		return select(data, 0, data.length - 1, k);
	}

	private static int select(int[] data, int p, int r, int k) {
		if( p == r )
			return data[p];
		int q = partition(data, p, r);
		if( k == q+1 )
			return data[q];
		if( k < q+1 )
			return select(data, p, q - 1, k);
		else
			return select(data, q + 1, r, k);
	}

	private static int partition(int[] data, int p, int r) {
		int pivot = choosePivot(data, p, r);
		int v = data[pivot];
		swap(data, p, pivot);
		int j = p;
		for( int i=p+1; i<=r; i++ ) {
			if( v > data[i] ) {
				swap(data, i, ++j);
			}
		}
		swap(data, p, j);
		return j;
	}

	private static int choosePivot(int[] data, int p, int r) {
		return r;
	}

	private static void swap(int[] data, int i, int j) {
		int t = data[i];
		data[i] = data[j];
		data[j] = t;
	}

	public static void main(String[] args) {
		int n = 16;
		int[] data = new int[n];
		Random r = new Random();
		for( int i=0; i<n; i++ ) {
			data[i] = r.nextInt(100);
			System.out.println(data[i] + " ");
		}
		System.out.println("");
		System.out.println(select(data, 4));
		Arrays.sort(data);
		System.out.println(data[3]);
	}
	

}