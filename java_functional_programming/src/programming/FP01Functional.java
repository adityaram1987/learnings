package programming;

import java.util.List;

public class FP01Functional {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(1,4,12,-1,12,16,15);
//		printAllNumbersInListFunctional(numbers);
//		printAllEvenNumbersInListFunctional(numbers);
		
	}
	
	private static void print(int number) {
		System.out.println(number);
	}
	
//	private static boolean isEven(int num) {
//		return num%2 == 0;
//	}
	
	private static void printAllNumbersInListFunctional(List<Integer> numbers) {
		numbers.stream()
			.forEach(FP01Functional::print);
	}
	
	private static void printAllEvenNumbersInListFunctional(List<Integer> numbers) {
//		numbers.stream()
//			.filter(FP01Functional::isEven)
//			.forEach(System.out::println);
		
		numbers.stream()
			.filter(number -> number%2 == 0)
			.forEach(System.out::println);
	}
}
