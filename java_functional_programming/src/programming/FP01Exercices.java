package programming;

import java.util.List;

public class FP01Exercices {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(2,5,13,4,-9,0,-13,17,22,13);
//		printAllOddNumbersInListFunctional(numbers);
//		printCubesOfAllOddNumbersInListFunctional(numbers);
		
		List<String> courses = List.of("Spring", "Spring Boot", "API" , "Microservices","AWS", "PCF","Azure", "Docker", "Kubernetes");
		
//		courses.stream()
//			.forEach(System.out::println);
		
//		courses.stream()
//			.filter(course -> course.contains("Spring"))
//			.forEach(System.out::println);
		
//		courses.stream()
//		.filter(course -> course.length()>=4)
//		.forEach(System.out::println);
		
		courses.stream()
			.map(course-> course + " " + course.length())
			.forEach(System.out::println);
	}

	private static void printAllOddNumbersInListFunctional(List<Integer> numbers) {
		numbers.stream()
			.filter(number -> number%2 != 0)
			.forEach(System.out::println);
	}
	
	private static void printCubesOfAllOddNumbersInListFunctional(List<Integer> numbers) {
		numbers.stream()
			.filter(number -> number%2 != 0)
			.map(number -> number*number*number)
			.forEach(System.out::println);
	}

}
