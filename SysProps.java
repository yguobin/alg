import java.util.Properties;

public class SysProps {

	private static final String JAVA_VER = "java.version";

	public static void main(String[] args) {
		System.out.println("Java version: " + System.getProperty(JAVA_VER));
		
		System.setProperties(null);

		System.out.println("Java version: " + System.getProperty(JAVA_VER));

		// notice: we can override system properties, security concerns?
		System.setProperties(new Properties());

		System.out.println("Java version: " + System.getProperty(JAVA_VER));
	}
}