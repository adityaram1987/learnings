package miscellaneous;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PalindromePossibility {

	public static String palindromeChecker(String s, List<Integer> startIndex, List<Integer> endIndex, List<Integer> subs) {
		int [][] result = new int[s.length()][s.length()];
		for(int i = 0; i<s.length(); i++) {
			Set<Character> set = new HashSet<Character>();			
			for(int j =i; j<s.length();j++ ) {
				if(!set.add(s.charAt(j))) {
					set.remove(s.charAt(j));
				}				
				result[i][j] = set.size()/2;
			}
		}
		
		String answer = "";	
		for(int i=0; i< startIndex.size(); i++) {
			int start = startIndex.get(i);
			int end = endIndex.get(i);
			int substitutes = subs.get(i);
			
			if(substitutes >= result[start][end]) {
				answer+="1";
			} else {
				answer+= "0";
			}
			
		}
		
		return answer;
		
	}
	
	public static void main(String[] args) {
		String s = "cdecd";
		
		List<Integer> startIndex = new ArrayList<Integer>();
		startIndex.add(0);
		startIndex.add(0);
		startIndex.add(0);
		startIndex.add(0);
		
		List<Integer> endIndex = new ArrayList<Integer>();
		endIndex.add(0);
		endIndex.add(1);
		endIndex.add(2);
		endIndex.add(3);
		
		List<Integer> subs = new ArrayList<Integer>();
		subs.add(0);
		subs.add(1);
		subs.add(1);
		subs.add(0);
		
		PalindromePossibility checker = new PalindromePossibility();
		String answer = checker.palindromeChecker(s, startIndex, endIndex, subs);
		
		System.out.println(answer);
		
	}

}
