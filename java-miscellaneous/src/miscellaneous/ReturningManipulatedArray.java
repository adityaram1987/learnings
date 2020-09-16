package miscellaneous;

public class ReturningManipulatedArray {

	public static void main(String[] args) {
		Long[] in_main= {0L, 0L, 0L};
		in_main = changedLongArray();
		System.out.println(in_main[0] + " " + in_main[1] + " " + in_main[2] + " " + in_main[3]);
	}

	private static Long[] changedLongArray() {
		Long[] in_main= {0L, 0L, 0L, 0L};
		in_main[0]++;
		in_main[1]++;
		in_main[2]++;
		return in_main;
	}

}
