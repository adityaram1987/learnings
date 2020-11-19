package miscellaneous;

import java.util.ArrayList;
import java.util.List;

public class Sample {

	public static void main(String[] args) {
		int n = 3;
		List <String> answer = new ArrayList<String>();
		Sample sample = new Sample();
		sample.possibilities(n, n, answer, 0, "");
		System.out.println(answer);
		System.out.println("Number of elements: " + answer.size());
	}

	public void possibilities(int open, int close,List <String> answer, int close_possibilities, String current) {
		if(open==0 && close==0) {
			answer.add(current);
			return;
		}
		if(open > 0) {
			possibilities(open-1, close, answer, close_possibilities+1, current.concat("("));
		}
		if(close_possibilities > 0 && close > 0) {
			possibilities(open, close-1, answer, close_possibilities-1, current + ")");
		}
	}
}
