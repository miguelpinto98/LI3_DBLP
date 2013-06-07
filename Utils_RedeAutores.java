/**
 * Write a description of class Utils_RedeAutores here.
 * 
 * @author LI3 
 * @version 1/2013
 */  
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;

public class Utils_RedeAutores {
   
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
   
   // programa de teste; deve ser removido da biblioteca
   public static ArrayList<String> main() {  
       ArrayList<String> linhas = new ArrayList<String>();
       
       long inicio = System.nanoTime();
       linhas = leLinhasScanner("publicx.txt");
       long fim = System.nanoTime();
       
       out.println("Linhas Lidas com Scanner: " + linhas.size());
       out.println("Tempo: " + (fim - inicio)/1.0E09 + " segs.\n");
       
       return linhas;
   }
}



