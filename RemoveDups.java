import java.util.*;
import java.lang.reflect.Array;

public class RemoveDups {
	public static <T> T[] removeDups1(T[] data) {
		Set<T> s = new HashSet<T>();
		
		for( T t : data ) {
			if( !s.contains(t) ) {
				s.add(t);
			}
		}

		// You cannot do this: T[] r = new T[s.size()];
		T[] r = (T[]) Array.newInstance(data[0].getClass(), s.size());
		int i=0;
		for( T t : s )
			r[i++] = t;

		return r;

	}

	
	public static <T> T[] removeDups2(T[] data) {
		java.util.Arrays.sort(data, null);

		int distinctItemCount = 1;
		for( int i=1; i<data.length; i++ ) {
			if( !data[i].equals(data[i-1]) )
				distinctItemCount++;
		}

		// You cannot do this: T[] r = new T[s.size()];
		T[] r = (T[]) Array.newInstance(data[0].getClass(), distinctItemCount);
		r[0] = data[0];

		for( int i=1, j=1; i<data.length; i++ ) {
			if( !data[i].equals(data[i-1]) )
				r[j++] = data[i];
		}
		
		return r;
		
	}

	public static <T> T[] reverse1(T[] data) {
		if( data == null || data.length == 0 ) return data;

		T[] r = (T[]) Array.newInstance(data[0].getClass(), data.length);
		for( int i=0; i<data.length; i++ )
			r[data.length - i - 1] = data[i];

		return r;		
	}

	public static <T> void reverse2(T[] data) {
		if( data == null ) return;

		int middle = data.length/2;
		for( int i=0; i<middle; i++ ) {
			swap(data, i, data.length - i - 1);
		}

	}

	public static <T> void shuffle(T[] data) {
		Random r = new Random();
		for( int i=0; i<data.length; i++ ) {
			int t = r.nextInt(i+1);
			swap(data, i, t);
		}
	}

	public static <T> void swap(T[] data, int i, int j) {
		T t = data[i];
		data[i] = data[j];
		data[j] = t;
	}

	public static void main(String[] args) {
		Integer[] data = new Integer[15];
		for( int i=0; i<10; i++ )
			data[i] = i;

		for( int i=10; i<data.length; i++ )
			data[i] = i - 10;

		shuffle(data);

		for( int i=0; i<data.length; i++ ) 
			System.out.print(data[i] + " ");

		System.out.println();

		Integer[] r1 = removeDups1(data);

		for( int i=0; i<r1.length; i++ ) 
			System.out.print(r1[i] + " ");

		System.out.println();

		Integer[] r2 = removeDups2(data);

		for( int i=0; i<r2.length; i++ ) 
			System.out.print(r2[i] + " ");

		System.out.println();

		Integer[] r3 = reverse1(data);

		for( int i=0; i<r3.length; i++ ) 
			System.out.print(r3[i] + " ");

		System.out.println();

		reverse2(r3);

		for( int i=0; i<r3.length; i++ ) 
			System.out.print(r3[i] + " ");

		System.out.println();

	}
}

