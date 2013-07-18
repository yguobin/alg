import java.util.*;

public class SecondLargest {
	public static int count = 0;

	public static int second(int[] data) {
		int n = data.length;
		count = 0;
		
		int gap = 1;
		while( gap < n ) {
			for( int i=gap - 1; i<n; i+=2*gap ) {
				count++;
				if( data[i] > data[i+gap] ) {
					int t = data[i];
					data[i] = data[i+gap];
					data[i+gap] = t;
				}
			}
			gap = gap * 2;
		}

		gap = gap / 2;
		int i = gap - 1;
		while( gap > 0 ) {
			gap = gap / 2;
			count++;
			if( data[i] > data[i + gap] )
				return data[i];
			i = i + gap;
		}

		return data[n-2];		
	}

	public static void main(String[] args) {
		int n = 16;
		int[] data = new int[n];
		Random r = new Random();
		for( int i=0; i<n; i++ ) {
			data[i] = n-i; //r.nextInt(100);
			System.out.println(data[i] + " ");
		}
		
		System.out.println("");
		System.out.println(second(data));
		Arrays.sort(data);
		System.out.println(data[n-2]);
		System.out.println(count);
	}
}