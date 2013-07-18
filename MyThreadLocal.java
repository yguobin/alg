import java.text.*;
import java.util.*;

public class MyThreadLocal {
	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
             	@Override protected DateFormat initialValue() {
			return new SimpleDateFormat("MM/dd/yyyy");
		}
	};

	public static DateFormat getDateFormatter() {
		return df.get();
	}

	public static void main(String[] args) {
		new Thread(new Task()).start();
		new Thread(new Task()).start();
	}

	
}

class Task implements Runnable {
	public void run() {
		System.out.println(MyThreadLocal.getDateFormatter().format(new Date()));
	}
}