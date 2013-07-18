import java.util.*;

public class Merge {
	public static int[] merge(int[] a, int[] b) {
		int[] aux = new int[a.length + b.length];
		
		int i=0, j=0, k=0;
		for( ; k<aux.length; k++ ) {
			if( i >= a.length ) 
				aux[k] = b[j++];
			else if( j >= b.length )
				aux[k] = a[i++];
			else if( a[i] < b[j] )
				aux[k] = a[i++];
			else
				aux[k] = b[j++];
		}

		return aux;		
	}

	public static void main(String[] args) {
		int[] a = new int[10];
		int[] b = new int[20];
		int[] d = new int[a.length + b.length];
		Random r = new Random();
		int k =0;
		for( int i=0; i<a.length; i++ ) {
			a[i] = r.nextInt(100);
			d[k++] = a[i];
		}
		for( int i=0; i<b.length; i++ ) {
			b[i] = r.nextInt(100);
			d[k++] = b[i];
		}

		Arrays.sort(a);
		Arrays.sort(b);
		int[] c = merge(a, b);
		Arrays.sort(d);
		for( int i=0; i<c.length; i++ ) {
			System.out.print(c[i] + " ");
		}		
		System.out.println();

		for( int i=0; i<d.length; i++ ) {
			System.out.print(d[i] + " ");
		}		
	}
}