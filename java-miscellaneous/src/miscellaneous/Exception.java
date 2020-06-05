package miscellaneous;

import java.util.logging.Logger;

public class Exception {

	public static void main(String[] args) throws ArithmeticException{
		final Logger logger = Logger.getLogger(Exception.class.getName());
		int num;
		try {
			num = 100/0;
			System.out.println(num);
		} catch(ArithmeticException e) {
			
		}
	}

}
