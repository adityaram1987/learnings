package programming;

import java.util.List;
import java.util.stream.Collectors;

public class FP02Functional {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(1,4,12,-1,12,16,15);
		int sum = addListFunctional(numbers);
		System.out.println(sum);
		
		List<Integer> doubledNumbers = doubleNumbersFunctional(numbers);
		System.out.println(doubledNumbers);
		
		List<Integer> evenNumbersOnly = numbers.stream()
											.filter(x -> x%2 == 0)
											.collect(Collectors.toList());
		System.out.println(evenNumbersOnly);
	}
	
	private static List<Integer> doubleNumbersFunctional(List<Integer> numbers) {
		// TODO Auto-generated method stub
		return numbers.stream()
				.map(x -> x*2).collect(Collectors.toList());
	}

	private static int sum(int aggregate, int nextNumber) {
		return aggregate+nextNumber;
	}

	private static int addListFunctional(List<Integer> numbers) {
//		return numbers.stream().reduce(0, FP02Functional::sum);
//		return numbers.stream().reduce(0, (x,y) -> x + y);
		return numbers.stream().reduce(0, Integer :: sum);
	}

}
