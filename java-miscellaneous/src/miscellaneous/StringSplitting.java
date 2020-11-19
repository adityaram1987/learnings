package miscellaneous;

public class StringSplitting {

	public static void main(String[] args) {
		String value = "Archive/acs/gb/customerdata/daily/20201006/engagementsources/4637faf0-8369-47e6-8ff9-f65e75b4b4d6";
		
		int lastIndex = value.lastIndexOf('/');
		String id = value.substring(lastIndex + 1);
		String subString = value.substring(0, lastIndex);
		String file = subString.substring(subString.lastIndexOf('/', lastIndex) + 1);
		
		System.out.println(id);
		System.out.println(file);
	}

}
