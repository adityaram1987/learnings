package miscellaneous;

public class MaxSum {

	public static void main(String[] args) {
		//int [] a = {5,5,10,100,10,5};
		int [] a = {3,2,7,10};
		int max_sum_without_adjacent = a[0];
		int max_sum_with_adjacent = a[1];
		
		for(int i = 2; i< a.length; i++) {
			int temp = max_sum_without_adjacent;
			if(max_sum_without_adjacent < max_sum_with_adjacent) {
				max_sum_without_adjacent = max_sum_with_adjacent;
			}
			if(max_sum_with_adjacent < temp+a[i]) {
				max_sum_with_adjacent = temp + a[i];
			}
		}
		System.out.println(Integer.max(max_sum_with_adjacent, max_sum_without_adjacent));
	}

}
