public class QueueWithStacks {
	private Stack inbox = new Stack(), outbox = new Stack();

	public void add(Object v) {
		inbox.push(v);
	}

	public Object remove() {
		if( outbox.isEmpty() ) {
			while( !inbox.isEmpty() ) {
				outbox.push(inbox.pop());
			}
		}
		return outbox.pop();
	}

	public boolean isEmpty() {
		return inbox.isEmpty() && outbox.isEmpty();
	}

	public static void main(String[] args) {
		QueueWithStacks q = new QueueWithStacks();
		for( int i=0; i<10; i++ )
			q.add(i);
		while( !q.isEmpty() )
			System.out.println(q.remove());
	}
}
		