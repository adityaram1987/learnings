package programming;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class FP03FunctionalInterfaces2 {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(1,4,12,-1,12,16,15);
		
		Predicate<Integer> isEvenPredicate = x -> x%2 == 0;
		
		Function<Integer, String> stringFunction = x -> x + " ";
		
		Consumer<String> sysoutConsumer = System.out::println;
		
		numbers.stream()
			.filter(isEvenPredicate)
			.map(stringFunction)
			.forEach(sysoutConsumer);
		
		BinaryOperator<Integer> sumBinaryOperator = Integer::sum;
				
		int sum = numbers.stream()
			.reduce(0, sumBinaryOperator);
		System.out.println(sum);
		
		//No input but return something
		Supplier<Integer> randomGenerator = () -> {
			Random random = new Random();
			return random.nextInt(1000);
		};
		System.out.println(randomGenerator.get());

		UnaryOperator<Integer> doubleNumber = x -> 2*x;
		System.out.println(doubleNumber.apply(10));
		
		
		BiPredicate<Integer, String> biPredicate = (number,str) -> {
			return number<10 && str.length()>5;
		};
		
		System.out.println(biPredicate.test(10, "in28minutes"));
		
		BiFunction<Integer, String, String> biFunction = (number,str) -> {
			return number + " " + str;
		};
		
		System.out.println(biFunction.apply(15, "in28minutes"));
		
		BiConsumer<Integer, String> biConsumer = (s1,s2) -> {
			System.out.println(s1);
			System.out.println(s2);
		};
		
		biConsumer.accept(25, "in28Minutes");
		
		BinaryOperator<Integer> sumBinaryOperator2 = (x, y) -> x + y;
		
		IntBinaryOperator intBinaryOperator = (x,y) -> x + y;
		
		//IntBinaryOperator
		//IntConsumer
		//IntFunction
		//IntPredicate
		//IntSupplier
		//IntToDoubleFunction
		//IntToLongFunction
		//IntUnaryOperator
		
		//Long, Double, Int
		

		//numbers.stream().filter(isEvenPredicate).map(squareFunction).forEach(sysoutConsumer);

		//int sum = numbers.stream().reduce(0, sumBinaryOperator);
	}
	
}
