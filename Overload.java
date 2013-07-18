public class Overload {
	public void m(Number n) {
		System.out.println("In number");
	}

	public void m(Double d) {
		System.out.println("In double");
	}

	public static void main(String[] args) {
		Double d = 32.0d;
		new Overload().m(d);
	}
}