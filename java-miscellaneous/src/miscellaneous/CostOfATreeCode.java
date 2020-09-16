package miscellaneous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

public class CostOfATreeCode {

	public static void main(String[] args) {
		int n = 5;
		int c = 1;
		int u = 3;
		int [][]a = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				a[i][j]= -1;
			}
		}
		a[0][1]=a[1][0]=2;
		a[1][2]=a[2][1]=3;
		a[2][3]=a[3][2]=4;
		a[3][4]=a[4][3]=5;
		
		int []sol = new int[n];
		Arrays.fill(sol, Integer.MAX_VALUE);
		sol[u-1]=0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(u-1);
		
		HashMap<Integer, ArrayList<CustomObject>> map = new HashMap<Integer, ArrayList<CustomObject>>();
		map.putIfAbsent(u-1, new ArrayList<CustomObject>());
		
		while(!q.isEmpty()) {
			int x= q.poll();
			for(int i =0; i < n;i++) {
				if(a[x][i] > 0 && sol[x]+a[x][i] < sol[i]) {
					sol[i] = sol[x]+a[x][i];
					q.add(i);
					
					map.remove(i);
					ArrayList<CustomObject> object = new ArrayList<CustomObject>(map.get(x));
					object.add(new CustomObject(x,i));
					map.put(i, object);
				}
			}
		}
		int answer=0;
		for(int i=0;i<n;i++) {
			System.out.print(sol[i] + " ");
			answer+= sol[i];
		}
		System.out.println(answer);
		
		
		int [][]freq = new int[n][n];
		for(int i =0; i<n;i++) {
			for(int j=0;j<n;j++) {
				freq[i][j]=0;
			}
		} 
		
		for(Entry<Integer, ArrayList<CustomObject>> entry : map.entrySet()) {
			System.out.print(entry.getKey() + " : ");
			for(CustomObject obj : entry.getValue()) {
				System.out.print(obj.from + "," + obj.to + "   ");
				freq[obj.from][obj.to]++;
				freq[obj.to][obj.from]++;
			}
			System.out.println();
		}
		
		for(int i =0; i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(freq[i][j] + " ");
			}
			System.out.println();
		}
		
		List<Integer> weights = new ArrayList<Integer>();
		
		for(int i =0; i<n;i++) {
			for(int j=0;j<n;j++) {
				if(freq[i][j]>0) {
					weights.add(freq[i][j]*a[i][j]);
					freq[j][i]=0;
				}
			}
		}
		
		Collections.sort(weights, Comparator.reverseOrder());
		System.out.println(weights);
		int reduce=0;
		for(int i=0; i<c && i<weights.size(); i++) {
			reduce+= weights.get(i);
		}
		answer-= reduce;
		System.out.println("Answer: " + answer);
	}

}

class CustomObject{
	public CustomObject(int from, int to) {
		this.from = from;
		this.to = to;
	}
	int from;
	int to;
}
