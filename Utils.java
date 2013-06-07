import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;

public class Utils {
	
	public static ArrayList<String> leLinhasScanner(String fichName) {
		ArrayList<String> linhas = new ArrayList<String>();
		Scanner fichScan = null; 
		try {
			fichScan = new Scanner(new FileReader(fichName));
            fichScan.useDelimiter(System.getProperty("line.separator"));
            while (fichScan.hasNext()) linhas.add(fichScan.next());
            fichScan.close();
		} 
		catch(IOException e) {out.println(e.getMessage()); return null; }
		catch(Exception e) {out.println(e.getMessage()); return null; }
       
		return linhas;
	}
	
	public static void trataLinhas(ArrayList<String> linhas) {
		
		for(String s : linhas)
			trataLinha(s);
	}

	public static void trataLinha(String linha) {
		String[] str = linha.split(", ");
		ArrayList<String> ca = new ArrayList<>();
		String a = null;
		int ano = Integer.parseInt(str[str.length-1]);
		
		for(int i=0; i<str.length-1; i++) {
			a = str[i];
			ca = new ArrayList<>(); 
			for(int j=0; j<str.length-1; j++) {
				if(i!=j)
					ca.add(str[j]);
			}
			System.out.println("Ano: "+ano+"\nAutor: "+a+"\nCoAutores: "+ca.toString());
		}		
	}
}



