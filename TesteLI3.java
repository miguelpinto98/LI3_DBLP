import static java.lang.System.out;
import java.util.ArrayList;

public class TesteLI3 {
	
	public static String filename = "publicx.txt";
	public static RedeAno ra = new RedeAno();
	
	public static void main(String[] args) {
		ArrayList<String> linhas = new ArrayList<String>();
	      
	    long inicio = System.nanoTime();
	    linhas = Utils.leLinhasScanner(filename);
	       
	    	    
	    Utils.trataLinhas(ra,linhas);
	    
	    out.println("1.1 Ficheiro: "+filename);
	    out.println("1.1 Total Artigos: " + linhas.size());
	    out.println("1.1 Total Autores: " + ra.getNumeroAutores());
	    out.println("1.1 Total Autores Distintos: " + Utils.nome.size());
	    out.println("1.1 ["+ra.anoInferior()+","+ra.anoSuperior()+"]");
	    out.println();
	    out.println("1.2 Total Autores: " + ra.getNumeroAutores());
	    out.println("1.2 Total Artigos Unico Autor: " + ra.getNumeroArtigosUnicoAutor());

	    
	    

	    //System.out.println(ra.toString());
	    //ra.listaAutoresEntreAnos(2010,2013);
	    
	    long fim = System.nanoTime();
	    out.println("Tempo: " + (fim - inicio)/1.0E09 + " segs.\n");
	   }
	}
