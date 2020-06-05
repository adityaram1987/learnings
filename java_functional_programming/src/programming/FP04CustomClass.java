package programming;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.sun.tools.javac.util.List;

class Course{
	private String name;
	private String category;
	private int reviewScore;
	private int noOfStudents;
		
	public Course(String name, String category, int reviewScore, int noOfStudents) {
		super();
		this.name = name;
		this.category = category;
		this.reviewScore = reviewScore;
		this.noOfStudents = noOfStudents;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getReviewScore() {
		return reviewScore;
	}
	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}
	public int getNoOfStudents() {
		return noOfStudents;
	}
	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}
	
	public String toString() {
		return name + ":" + noOfStudents + ":" + reviewScore;
	}
}

public class FP04CustomClass {

	public static void main(String[] args) {
		List <Course> courses = List.of(
				new Course("Spring", "Framework", 98, 20000),
				new Course("Spring Boot", "Framework", 95, 18000), 
				new Course("API", "Microservices", 97, 22000),
				new Course("Microservices", "Microservices", 96, 25000),
				new Course("FullStack", "FullStack", 91, 14000), 
				new Course("AWS", "Cloud", 92, 21000),
				new Course("Azure", "Cloud", 99, 21000), 
				new Course("Docker", "Cloud", 92, 20000),
				new Course("Kubernetes", "Cloud", 91, 20000));
		
		System.out.println(courses.stream().allMatch(course -> course.getReviewScore() > 90));
		System.out.println(courses.stream().noneMatch(course -> course.getReviewScore()>95));
		System.out.println(courses.stream().anyMatch(course -> course.getReviewScore()>95));
		
		Comparator <Course> comparingByNoOfStudentsIncreasing 
								= Comparator.comparing(Course::getNoOfStudents);
		System.out.println(
				courses.stream()
				.sorted(comparingByNoOfStudentsIncreasing)
				.collect(Collectors.toList()));
		
		Comparator <Course> comparingByNoOfStudentsDecreasing 
								= Comparator.comparing(Course::getNoOfStudents).reversed();
		System.out.println(
				courses.stream()
				.sorted(comparingByNoOfStudentsDecreasing)
				.collect(Collectors.toList()));
		
		Comparator <Course> comparingByNoOfStudentsAndReviewScore 
								= Comparator.comparing(Course::getNoOfStudents)
											.thenComparing(Course::getReviewScore)
											.reversed();
		System.out.println(
				courses.stream()
				.sorted(comparingByNoOfStudentsAndReviewScore)
				.skip(3)
				.limit(5)
				.collect(Collectors.toList()));
		
		System.out.println(courses);
		
		System.out.println(
				courses.stream()
				.takeWhile(course -> course.getReviewScore() >= 95)
				.collect(Collectors.toList()));
		
		System.out.println(
				courses.stream()
				.dropWhile(course -> course.getReviewScore() > 95)
				.collect(Collectors.toList()));
		
		System.out.println(
				courses.stream()
				.max(comparingByNoOfStudentsAndReviewScore));
		
		System.out.println(
				courses.stream()
				.min(comparingByNoOfStudentsAndReviewScore));
		
		Predicate<Course> reviewScoreLessThan90Predicate = createPredicateWithCutoffReviewScore(90);
		System.out.println(
				courses.stream()
				.filter(reviewScoreLessThan90Predicate )
				.min(comparingByNoOfStudentsAndReviewScore)); //prints Optional.empty
		
		System.out.println(
				courses.stream()
				.filter(reviewScoreLessThan90Predicate )
				.min(comparingByNoOfStudentsAndReviewScore)
				.orElse(new Course("Kubernetes", "Cloud", 91, 20000))); //prints Kubernetes:20000:91 if min is not present
		
		Predicate<Course> reviewScoreLessThan95Predicate = createPredicateWithCutoffReviewScore(95);
		System.out.println(
				courses.stream()
				.filter(reviewScoreLessThan95Predicate )
				.findFirst()
				.orElse(new Course("Kubernetes", "Cloud", 91, 20000))); //prints Optional.empty

		System.out.println(
				courses.stream()
				.filter(reviewScoreLessThan95Predicate )
				.mapToInt(Course::getNoOfStudents)
				.sum());
		
		System.out.println(
				courses.stream()
				.filter(reviewScoreLessThan95Predicate )
				.mapToInt(Course::getNoOfStudents)
				.average());
		
		System.out.println(
				courses.stream()
				.filter(reviewScoreLessThan95Predicate )
				.mapToInt(Course::getNoOfStudents)
				.count());        //no of courses with the predicate condition
		
		System.out.println(
				courses.stream()
				.collect(Collectors.groupingBy(Course::getCategory)));
		
		System.out.println(
				courses.stream()
				.collect(Collectors.groupingBy(Course::getCategory, Collectors.counting())));
		
		System.out.println(
				courses.stream()
				.collect(Collectors.groupingBy(Course::getCategory, 
						Collectors.maxBy(Comparator.comparing(Course::getReviewScore)))));
		
		System.out.println(
				courses.stream()
				.collect(Collectors.groupingBy(Course::getCategory, Collectors.mapping(Course::getName, Collectors.toList()))));
		
	}
	
	
	private static Predicate<Course> createPredicateWithCutoffReviewScore(int cutoffReviewScore) {
		return course -> course.getReviewScore() < cutoffReviewScore;
	}
}
