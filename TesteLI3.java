import static java.lang.System.out;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class TesteLI3 {
	
	public static String filename = "publicx.txt";
	public static RedeAno ra = new RedeAno();	
	public static int[] res = new int[2];
	
	public static void main(String[] args) {
		ArrayList<String> linhas = new ArrayList<String>();
	      
	    long inicio = System.nanoTime();
	    linhas = Utils.leLinhasScanner(filename);
	       
	    	    
	    Utils.trataLinhas(ra,linhas);
	    
	    out.println("1.1 Ficheiro: "+filename);
	    out.println("1.1 Total Artigos: " + linhas.size());
	    out.println("1.1 Total Nomes Lidos: " + ra.getNumeroAutores());
	    out.println("1.1 Total Autores Distintos: " + Utils.xxx);
	    out.println("1.1 ["+ra.anoInferior()+","+ra.anoSuperior()+"]");
	    out.println();
	    out.println("1.2 Total Autores: " + Utils.xxx);
	    out.println("1.2 Total Artigos Unico Autor: " + ra.getNumeroArtigosUnicoAutor());
	    out.println("1.2 Total Autores que publicaram a solo: " + ra.unicoAutor());
	    out.println("1.2 Total Autores que nuca publicaram a solo: "+ ra.coisa());
	    
	    
	    out.println("\n1.3 ");
	    TreeMap<Integer, Integer> lpa = ra.listaPublicacoesPorAno();
	    for(Integer i : lpa.keySet())
	    	out.println("Ano: "+i+" Publicacoes: "+lpa.get(i));
	    
	    //System.out.println(ra.toString());
	    out.println("\n2.1 ");
		HashSet<String> hs = ra.AutoresPorTodosAnos(1985,1999);
	    for(String s : hs)
	    	System.out.println(s);
	    
	    out.println();
	    System.out.println("2.2 Linhas Duplicadas: "+Utils.verificaDuplicados(filename));
	    
	    //TreeMap<String, Integer> ta = ra.topAutoresPorNome();
	    
	    long fim = System.nanoTime();
	    out.println("\nTempo: " + (fim - inicio)/1.0E09 + " segs.\n");
	   }
	}
