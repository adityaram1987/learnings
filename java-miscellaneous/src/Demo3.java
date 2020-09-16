
public class Demo3 {

	public static void main(String[] args) {
		
		String main_sentence = "today is tuesday. But I am not going to work.";
		//String main_sentence = "today is tuesday";
		char sentence[] = main_sentence.toCharArray();
		
		Demo3 object = new Demo3();
		System.out.println(object.reverseString(sentence).toString());
		//System.out.println(answer);
		
	}

	public String reverseString(char sentence[]) {
		
		String reverse_words = "";
		
		System.out.println("checking");
		int size= sentence.length;
		int i=0;
		while( i < size) {
			String temp="";
			while(i<size && sentence[i]!=' ') {
				temp = sentence[i]+temp;
				i++;
			}
			reverse_words += temp;
			
			if(i!=size) {
				reverse_words += " ";
			}
			i++;
		}
		System.out.println("checking");
		System.out.println("printing reverse: " + reverse_words);
		
		char answer[] = reverse_words.toCharArray();
		
		for(i =0 ; i< answer.length/2 ; i++) {
			char temp = answer[i];
			answer[i] = answer[size-1-i];
			answer[size-1-i] = temp;
		}
		
		for(i =0 ; i< answer.length ; i++) {
			System.out.print(answer[i]);
		}
		
		return answer.toString();
	}
}
