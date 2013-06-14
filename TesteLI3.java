import static java.lang.System.out;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

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
	    out.println("1.1 Total Nomes Lidos: " + ra.getNumeroAutores());
	    out.println("1.1 Total Autores Distintos: " + Utils.nome.size());
	    out.println("1.1 ["+ra.anoInferior()+","+ra.anoSuperior()+"]");
	    out.println();
	    out.println("1.2 Total Autores: " + Utils.nome.size());
	    out.println("1.2 Total Artigos Unico Autor: " + ra.getNumeroArtigosUnicoAutor());
	    System.out.println("1.2 Total Autores que publicaram a solo: " + ra.coisa());
	    
	    
	    out.println("\n1.3 ");
	    TreeMap<Integer, Integer> lpa = ra.listaPublicacoesPorAno();
	    for(Integer i : lpa.keySet())
	    	out.println("Ano: "+i+" Publicacoes: "+lpa.get(i));
	    

	    //System.out.println(ra.toString());
	    out.println("\n2.1 ");
		HashSet<String> hs = ra.AutoresPorTodosAnos(1985,2013);
	    for(String s : hs)
	    	System.out.println(s);
	    
	    out.println();
	    System.out.println("2.2 Linhas Duplicadas: "+Utils.verificaDuplicados(filename));
	    
	    long fim = System.nanoTime();
	    out.println("\nTempo: " + (fim - inicio)/1.0E09 + " segs.\n");
	   }
	}
