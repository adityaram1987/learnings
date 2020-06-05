package programming;

import java.util.List;

public class FP02Exercises {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(1,4,12,-1,12,16,15);
		int sum = addSumOfSquaresOfListFunctional(numbers);
		System.out.println(sum);
		
		sum = addSumOfCubesOfListFunctional(numbers);
		System.out.println(sum);
		
		sum = addSumOfOddNumbersOfListFunctional(numbers);
		System.out.println(sum);
	}

	private static int addSumOfSquaresOfListFunctional(List<Integer> numbers) {
		return numbers.stream().map(number -> number*number).reduce(0, Integer :: sum);
	}

	private static int addSumOfCubesOfListFunctional(List<Integer> numbers) {
		return numbers.stream().map(number -> number*number*number).reduce(0, Integer :: sum);
	}
	
	private static int addSumOfOddNumbersOfListFunctional(List<Integer> numbers) {
		return numbers.stream().filter(number -> number%2 != 0).reduce(0, Integer :: sum);
	}
}
