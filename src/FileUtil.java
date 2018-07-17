import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUtil {


	public static char[] readInput(String input){

		char[] board=null;
		try {
			Scanner in = new Scanner(new File(input));
			while(in.hasNextLine()){
				String str= in.next();
				board = str.toCharArray();
				System.out.println("Input Board position:"+str);
			}
			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("exception in reading input"+e.getMessage());
		}
		return board;
	}


	public static void writeToFile(String fileName, char[] board){
		File file = new File(fileName);
		FileWriter fr = null;
		try {
			fr = new FileWriter(file);
			fr.write(new String(board));
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//close resources
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
