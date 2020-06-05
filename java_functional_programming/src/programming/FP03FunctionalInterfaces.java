package programming;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FP03FunctionalInterfaces {

	/*
	 	private static boolean isEven(int x){
	 		return x%2 == 0;
	 	}
	 	
	 	private static int square(int x){
	 		return x*x;
	 	}
	 */
	public static void main(String[] args) {
		List<Integer> numbers = List.of(1,4,12,-1,12,16,15);
		
		Predicate<Integer> isEvenPredicate = x -> x%2 == 0;
		
		Predicate<Integer> isEvenPredicate2 = new Predicate<Integer>() {
			@Override
			public boolean test(Integer x) {
				return x%2==0;
			}
		};
		
		Function<Integer, Integer> squareFunction = x -> x*x;
		
		Function<Integer, Integer> squareFunction2 = new Function<Integer, Integer>() {
			@Override
			public Integer apply(Integer x) {
				return x*x;
			}
		};
		
		Consumer<Integer> sysoutConsumer = System.out::println;

		Consumer<Integer> sysoutConsumer2 = new Consumer<Integer>() {
			public void accept(Integer x) {
				System.out.println(x);
			}
		};
		
		numbers.stream()
			.filter(isEvenPredicate)
			.map(squareFunction)
			.forEach(sysoutConsumer);
		
		BinaryOperator<Integer> sumBinaryOperator = Integer::sum;
		
		BinaryOperator<Integer> sumBinaryOperator2 = new BinaryOperator<Integer>() {
			@Override
			public Integer apply(Integer t, Integer u) {
				return t+u;
			}
		};
		
		int sum = numbers.stream()
			.reduce(0, sumBinaryOperator);
		System.out.println(sum);
	}
	
}
