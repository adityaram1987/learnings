package miscellaneous;

public class NestedTryAndThrow {

	public static void main(String[] args) {
		int i = 45;
		int j =0;
		
		try {
			try {
				int k = i/j;
				System.out.println(k);
			} catch(ArithmeticException e) {
				System.out.println("Exception occured");
				throw e;
			}
		}
		catch(ArithmeticException e) {
			System.out.println("Outer exception");
		}
		
	}

}
