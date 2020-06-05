package programming;

import java.util.List;

public class FP02Structured {

	public static void main(String[] args) {
		int sum = addListStructured(List.of(1,4,12,-1,12,16,15));
		System.out.println(sum);
	}

	private static int addListStructured(List<Integer> numbers) {
		int sum=0;
		for(int number : numbers) {
			sum += number;
		}
		return sum;
	}

}
