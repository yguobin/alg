import java.util.*;

public class RadixSort {
	public static String[] sort(String[] data) {
		int maxLength = 0;
		String[] aux = new String[data.length];
		int i=0;
		for( String s : data ) {
			if( maxLength < s.length() )
				maxLength = s.length();
			aux[i] = data[i];
			i++;
		}
		sort(data, aux, 0, 0, data.length, maxLength);
		return data;
	}

	private static void sort(String[] data, String[] aux, int position, int startIndex, int endIndex, int maxLength) {
		if( startIndex >= endIndex - 1 ) return;

		TreeMap<Character, Integer> h = new TreeMap<Character, Integer>();
		for( int i=startIndex; i<endIndex; i++ ) {
			char c = ' ';
			if( position < data[i].length() )
				c = data[i].charAt(position);
			Integer count = h.get(c);
			if( count == null )
				h.put(c, 1);
			else
				h.put(c, count+1);
		} // h holds <char, count)

		Set<Character> keys = h.keySet();
		int i = startIndex;
		for( Character c : keys ) {
			int size = h.get(c);
			h.put(c, i);
			i += size;
		}	// h holds <char, startIndex_for_words_that_has_char_in_the_position>
		
		for( i=startIndex; i<endIndex; i++ ) {
			char c = ' ';
			if( position < data[i].length() )
				c = data[i].charAt(position);
			int start = h.get(c);
			aux[start++] = data[i];
			h.put(c, start);
		}

		for( i=startIndex; i<endIndex; i++ ) {
			data[i] = aux[i];
		}

		if( position < maxLength ) {
			int start = startIndex;
			for( Character c : keys ) {
				sort(data, aux, position+1, start, h.get(c), maxLength);
				start = h.get(c);
			}
		}				
	}

	public static void main(String[] args) {
		String[] ss = "Below is the ASCII character table and this includes descriptions of the first 32 non-printing characters".split(" ");
		sort(ss);
		for( String s : ss )
			System.out.println(s);
	}
}