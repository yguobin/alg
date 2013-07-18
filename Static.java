public class Static {
	public final int x;
	// public final static int y; // invalid, must init explicitly
	public final static int y = 0;

	public final static int z;

	static {
		z = 0;
	}

	public Static(int x) { 
		this.x = x; // valid
		// y = 0;   // invalid
	}

	public void setX(int x) { 
		// this.x = x;  // this is invalid 
	}
}