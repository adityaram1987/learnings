package miscellaneous;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TryWithResources {

	public static void main(String[] args) {
		 try(  FileInputStream input = new FileInputStream("C:\\mywork\\java-miscellaneous\\src\\miscellaneous\\sample.txt");
		          BufferedInputStream bufferedInput = new BufferedInputStream(input)
		    ) {

		        int data = bufferedInput.read();
		        while(data != -1){
		            System.out.print((char) data);
		            data = bufferedInput.read();
		        }
		    } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
