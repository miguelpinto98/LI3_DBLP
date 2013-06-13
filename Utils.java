import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static java.lang.System.out;

public class Utils {
	
	public static HashSet<Autor> nome = new HashSet<>();
	
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
	
	public static void trataLinhas(RedeAno ra ,ArrayList<String> linhas) {
		for(String s : linhas) {
			trataLinha(ra,s);
		}
	}

	public static void trataLinha(RedeAno ra, String linha) {
		String[] str = linha.split(", ");
		ArrayList<Autor> ca = new ArrayList<>();
		int tam = str.length-1, ano = Integer.parseInt(str[tam]);
		
		ra.addNumeroAutores(tam);
		
		if(tam==1)
			ra.addNumeroArtigosUnicoAutor();
		
		for(int i=0; i<tam; i++) {
			Autor a = new Autor(str[i]);
			ca = new ArrayList<>();
			nome.add(a);
			for(int j=0; j<tam; j++) {
				if(i!=j)
					ca.add(new Autor(str[j]));
			}
			ra.insereRedeAno(ano, a, ca);
			//System.out.println("Ano: "+ano+"\nAutor: "+a+"\nCoAutores: "+ca.toString());
		}		
	}
}