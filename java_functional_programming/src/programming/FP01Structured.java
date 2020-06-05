package programming;

import java.util.List;

public class FP01Structured {

	public static void main(String[] args) {
		printAllNumbersInListStructured(List.of(1,4,12,-1,12,16,15));
	}

	private static void printAllNumbersInListStructured(List<Integer> numbers) {
		for(int number: numbers) {
			System.out.println(number);
		}
	}

}
