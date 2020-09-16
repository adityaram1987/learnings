package miscellaneous;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PawnVsKingPower {

	public static void main(String[] args) {
		int n = 3;
		long s = 20;
		int d = 5;
		int p[] = {5,7,3};
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.reverseOrder());
		
		for(int i=0; i< n; i++) {
			pq.add(p[i]);
		}
		int steps=0;
		while(steps<d) {
			if(pq.isEmpty()) {
				System.out.println("Impossible");
				return;
			}
			int max = pq.poll();
			s-= max;
			if(max/2 > 0) {
				pq.add(max/2);
			}
			steps++;
			if(s<=0) {
				break;
			}
		}
		System.out.println("steps: " + steps);
		
	}

}
