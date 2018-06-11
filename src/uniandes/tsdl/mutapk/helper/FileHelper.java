package uniandes.tsdl.mutapk.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {


	public static List<String> readLines(String filePath){
		List<String> lines = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));

			String line;
			while((line = br.readLine())!=null){
				lines.add(line);
			}

			br.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}


	public static boolean writeLines(String filePath, List<String> lines){
		
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
			
			for (String newLine : lines) {
				bw.write(newLine);
				bw.newLine();
				bw.flush();
			}
			
			bw.close(); 
		}  catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
