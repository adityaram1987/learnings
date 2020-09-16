import java.util.Arrays;
import java.util.Stack;

public class Demo2 {
	public static void main(String [] args) {
		

		String number = "0100";
		char digits[] = number.toCharArray();
		
		int n = digits.length;
		int i;
		for(i = n-1; i> 0; i--) {
			if(digits[i] > digits[i-1]) {
				break;
			}
		}
		
		if(i==0) {
			System.out.println("THis is the highest number");
			return;
		}
		
		System.out.println("Broke at index : " + i);
		int temp = digits[i-1];
		int min = i;
		
		for(int j = i+1; j <n; j++) {
			if(digits[j] > temp && digits[j] < digits[min]) {
				min = j;
			}
		} 
		
		
		char min_no = digits[i-1];
		digits[i-1] = digits[min];
		digits[min] = min_no;
		System.out.println("min no: " + min_no);
		Arrays.sort(digits,i,n);
		
		for( i =0 ;i < n; i++) {
			System.out.print(digits[i]);
		}
	}
	
}




//1243



//1342  



