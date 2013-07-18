public class ObjClone {
	private static class C implements Cloneable {
		private static C instance;
		
		private C() {}

		public static C getInstance() {
			if( instance == null ) 
				instance = new C();
			return instance;
		}

		public C clone() throws CloneNotSupportedException {
			return (C) super.clone();
		}

	}

	public static void main(String[] args) throws CloneNotSupportedException {
		C c1 = C.getInstance();
		C c2 = c1.clone();
		System.out.println(c1 == c2);
	}

}