public class Sort {
	public static Comparable[] selectionSort(Comparable[] data) {
		for(int i=0; i<data.length; i++ ) {
			int min=i;
			for( int j=i+1; j<data.length; j++ ) {
				if( less(data[j], data[min]) ) {
					min = j;
				}
			}
			swap(data, i, min);
		}
		return data;
	}

	public static Comparable[] insertionSort(Comparable[] data) {
		for(int i=0; i<data.length; i++ ) {
			for(int j=i; j>0; j--) {
				if( less(data[j], data[j-1]) )
					swap(data, j, j-1);
				else
					break;
			}
		}
		return data;
	}
	
	public static Comparable[] mergeSort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		mergeSort(a, aux, 0, a.length-1);
		return a;
	}

	public static void mergeSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if( hi <= lo ) return;
		int mid = lo + (hi - lo) / 2;
		mergeSort(a, aux, lo, mid);
		mergeSort(a, aux, mid+1, hi);
		merge(a, aux, lo, mid, hi);
	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for( int k=lo; k <= hi; k++ )
			aux[k] = a[k];

		int i = lo, j = mid+1;
		for( int k=lo; k<=hi; k++ ) {
			if( i > mid )			a[k] = aux[j++];
			else if( j > hi )		a[k] = aux[i++];
			else if(less(aux[j], aux[i]))	a[k] = aux[j++];
			else				a[k] = aux[i++];
		}
	}
		

	public static Object[] shuffle(Object[] a) {
		java.security.SecureRandom r = new java.security.SecureRandom();
		for( int i=0; i<a.length; i++ ) {
			int x = r.nextInt(i+1);
			swap(a, i, x);
		}
		return a;
	} 


	public static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	public static Comparable[] quickSort(Comparable[] a) {
		quickSort(a, 0, a.length - 1);
		return a;
	}

	private static void quickSort(Comparable[] a, int p, int r) {
		if( p >= r )
			return; 
		int q = partition(a, p, r);
		quickSort(a, p, q - 1);
		quickSort(a, q + 1, r);
	}

	private static int partition(Comparable[] a, int p, int r) {
		int pivot = choosePivot(a, p, r);
		Comparable v = a[pivot];
		swap(a, p, pivot);
		int j=p;
		for( int i=p; i<= r; i++ ) {
			if( less(a[i], v) ) {
				swap(a, i, ++j);
			}
		}
		swap(a, p, j);
		return j;
	}

	private static int choosePivot(Comparable[] data, int p, int r) {
		return r;
	}

	public static void swap(Object[] data, int i, int j) {
		Object t = data[i];
		data[i] = data[j];
		data[j] = t;
	}

	public static Integer[] getData(int n) {
		Integer[] data = new Integer[n];
		java.security.SecureRandom r = new java.security.SecureRandom();
		for( int i=0; i<n; i++ ) {
			data[i] = r.nextInt(10*n);
		}
		return data;
	}

	public static Integer[] getDataByShuffle(int n) {
		Integer[] data = new Integer[n];
		java.security.SecureRandom r = new java.security.SecureRandom();
		for( int i=0; i<n; i++ ) {
			data[i] = 10 * i;
		}
		return (Integer[]) shuffle(data);
	}

	public static void print(Comparable[] data) {
		for( int i=0; i<data.length; i++ ) {
			System.out.print(data[i] + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		print(selectionSort(getData(10)));
		print(insertionSort(getData(10)));
		print(mergeSort(getData(10)));
		print(quickSort(getData(10)));
		print(selectionSort(getDataByShuffle(10)));
		print(insertionSort(getDataByShuffle(10)));
		print(mergeSort(getDataByShuffle(10)));
		print(quickSort(getDataByShuffle(10)));
	}
}