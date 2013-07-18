public class Inheritance {
	private static class A {
		protected int a() throws Exception {
			throw new Exception();
		}
		private int b() {
			return 1;
		}
		public int c() {
			return 1;
		}
		int d() {
			return 1;
		}
	}
	private static class B extends A {
		// this is OK: can increase visibility and remove throws clause
		public int a() { 
			return 1;
		}
		// this is OK: can increase visibility
		public int b() {
			return 1;
		}
		// compile error: cannot assign weaker visibility
		// protected int c() {
		//	return 1;
		// }
		// compile error: overridden method does not throw Exception
		// public int c() throws Exception {
		// 	return 1;
		// }
		// this is OK:
		protected int d() {
			return 1;
		}
	}
}