package programming;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FP03BehaviorParameteriation {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(1,4,12,-1,12,16,15);
				
		filterAndPrint(numbers, x -> x%2 == 0);		
		filterAndPrint(numbers, x -> x%2 != 0);
		
		List<Integer> sqaures = powersAndPrint(numbers, x -> x*x);
		System.out.println(sqaures);
		
		List<Integer> cubes = powersAndPrint(numbers, x -> x*x*x);
		System.out.println(cubes);
	}

	private static List<Integer> powersAndPrint(List<Integer> numbers, Function<Integer,Integer> square) {
		return numbers.stream()
			.map(square)
			.collect(Collectors.toList());
	}

	private static void filterAndPrint(List<Integer> numbers, Predicate<? super Integer> predicate) {
		numbers.stream()
			.filter(predicate)
			.forEach(System.out::println);
	}
	
}
