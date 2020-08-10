package miscellaneous;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorDemo {

	public static void main(String[] args) {
		List<Integer> a = new ArrayList<Integer>();
		a.add(2);
		a.add(5);
		a.add(14);
		a.add(-14);
		a.add(3);
		a.add(4);
		a.add(0);
		a.add(-1);
		
		int sum = 19;
		int start =0;
		int end = a.size()-1;
		
		a.sort(new CustomComparator());
		System.out.println(a);
		while(start< end) {
			if(a.get(start) + a.get(end) == sum) {
				System.out.println(start + "-" + a.get(start) + " : " + end  +"-" + a.get(end));
				return;
			}
			else if(a.get(start) + a.get(end) < sum) {
				start++;
			}
			else
				end--;
		}
		System.out.println("Not found");
	}
	
}


class CustomComparator implements Comparator<Integer>{
	@Override
	public int compare(Integer o1, Integer o2) {
		return o1-o2;
	}	
}
