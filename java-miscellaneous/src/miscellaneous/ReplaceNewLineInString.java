package miscellaneous;

public class ReplaceNewLineInString {

	public static void main(String[] args) {
		String withNewLine = "I have new line. \n I end \nhere";
		System.out.println(withNewLine);
		System.out.println(withNewLine.replaceAll("\n", ""));
		
		checkStringMutability();
	}

	private static void checkStringMutability() {
		String s = new String("aditya");
		s= "rayapati";
		System.out.println(s);
	}

}
