import static java.lang.System.out;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class TesteLI3 {
	public static Scanner s = new Scanner(System.in);
	public static String str = null;
	public static String filename = "publicx.txt";
	public static RedeAno ra = new RedeAno();	
	public static int[] res = new int[2];
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		ArrayList<String> linhas = new ArrayList<String>();
	      
	    long inicio = System.nanoTime();
	    /*linhas = Utils.leLinhasScanner(filename);
	       	    
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
	    
	    int anoi = 1968;
	    int anof = 2013;
	    
	    out.println("\n1.3 ");
	    TreeMap<Integer, Integer> lpa = ra.listaPublicacoesPorAno();
	    for(Integer i : lpa.keySet())
	    	out.println("Ano: "+i+" Publicacoes: "+lpa.get(i));
	    
	    //System.out.println(ra.toString());
	    out.println("\n2.1 Top autores ");
	    ArrayList<String> tp = ra.topAutoresPorNome(anoi, anof,10);
		for(String s : tp)
			System.out.println(s);
		/*
	    out.println("\n2.1 Top Co-Autores ");
	    HashMap<String, Integer> tca = ra.topCoAutores(anoi, anof, 10);
	    for(String s : tca.keySet())
	    	System.out.println(tca.get(s)+s);
	    
		out.println("\n2.1 CoAutores Comuns aos Autores ");
		ArrayList<String> teste = new ArrayList<>();
		teste.add("Kazunori Komatani"); teste.add("Tetsuya Ogata"); teste.add("Hiroshi G. Okuno");
		TreeSet<String> aux10 = ra.coautoresComunsDestesAutores(teste, anoi, anof);
		for(String s : aux10)
			System.out.println(s);
		
	    out.println("\n2.1 Autores que publicaram artigos");
		HashSet<String> hs = ra.AutoresPorTodosAnos(anoi,anof);
	    for(String s : hs)
	    	System.out.println(s);
	    
	    out.println();
	    System.out.println("2.2 Linhas Duplicadas: "+Utils.verificaDuplicados(filename));
	    
	    //TreeMap<String, Integer> ta = ra.topAutoresPorNome();
	    
	     */
	    long fim = System.nanoTime();
	    out.println("\nTempo: " + (fim - inicio)/1.0E09 + " segs.\n");
	    
	    int x=0;
	    do {
	    	x = Welcome();
	    	if(x==1) {
	    		boolean flag = true;
				ra = new RedeAno();
    			do {
    				try {
    					str = MenuLeFicheiro();
    					ra.setNomeFicheiro(str);
    				    linhas = Utils.leLinhasScanner(str);
    				    ra.setTotalArtigos(linhas.size());
    				    Utils.trataLinhas(ra,linhas);
    				    MenuPrincipal(ra);
    					flag=true;
 				   	} catch(FileNotFoundException e) {
 				   		System.out.println("Ficheiro nao encontrado, insira novamente!");
 				   	}
 			    } while(flag);
	    	} else {
	    		if(x==2) {
				    boolean flag = true;
				    ra = new RedeAno();
	    			do {
	    				try {
	    					str = MenuCarregaObjecto();
	    					ra.setNomeFicheiro(str);
	    					ra.carregaObj(str);
	    				    MenuPrincipal(ra);
	    					flag=true;
	 				   	} catch(FileNotFoundException e) {
	 				   		System.out.println("Ficheiro nao encontrado, insira novamente!");
	 				   	}
	 			    } while(flag);
	    		} else {
	    			if(x==3)
						System.exit(0);
	    			else
						System.out.println("Opcao Invalida!\n");

	    		}
	    	}
	    } while(x>3);
	    
	   }
	
	public static int Welcome() {
		System.out.println("################## PROJECTO JAVA LI3 ####################");
		System.out.println("#                                                       #");
		System.out.println("#        1 - LER FICHEIRO .TXT                          #");
		System.out.println("#        2 - CARREGAR FICHEIRO .OBJ                     #");
		System.out.println("#        3 - SAIR                                       #");
		System.out.println("#                                                       #");
		System.out.println("#        Escolha uma opcao:                             #");
		System.out.println("#########################################################");
	 
		return s.nextInt();
	}
	
	public static String MenuCarregaObjecto() {
		System.out.println("#################### CARREGAR OBJECTO ###################");
		System.out.println("#                                                       #");
		System.out.println("#  * Insira \"1\" caso prentenda carregar o ficheiro      #");
		System.out.println("#  * default (\"publicx.obj\"), caso contrário            #");
		System.out.println("#  * insira o nome do ficheiro a ser carregado.         #");
		str=null;
		if((str=s.next()).equals("1"))
			return "publicx.obj";
		else
			return str;
	}
	
	public static String MenuLeFicheiro() {
		System.out.println("################### LER FICHEIRO TEXTO ##################");
		System.out.println("#                                                       #");
		System.out.println("#  * Insira \"1\" caso prentenda carregar o ficheiro      #");
		System.out.println("#  * default (\"publicx.txt\"), caso contrário insira     #");
		System.out.println("#  * nome de outro ficheiro a ser carregado.            #");
		str=null;
		if((str=s.next()).equals("1"))
			return "publicx.txt";
		else
			return str;
	}
	
	public static void MenuPrincipal(RedeAno ra) throws FileNotFoundException, IOException {
		int x = 0;
		do {
		System.out.println("#################### MENU PRINCIPAL #####################");
		System.out.println("#                                                       #");
		System.out.println("#        1 - CONSULTAS ESTATISTICAS                     #");
		System.out.println("#        2 - CONSULTAS POR ANO/ANOS                     #");
		System.out.println("#        3 - CONSULTAS GLOBAIS ESPECIAIS                #");
		System.out.println("#        4 - GRAVAR                                     #");
		System.out.println("#        5 - SAIR                                       #");
		System.out.println("#                                                       #");
		System.out.println("#        Escolha uma opcao:                             #");
		System.out.println("#########################################################");
		x  = s.nextInt();
		
		if(x==1) {
			MenuConsultaEstatisticas(ra);
		}
		if(x==2) {
			MenuConsultaAnos(ra);
		}
		if(x==3) {
			MenuConsultasEspeciais(ra);
		}
		if(x==4) {
			str=MenuGrava();
			ra.gravaObj(str);
			MenuPrincipal(ra);
		}
		if(x==5) {
			System.exit(0);
		} else
			System.out.println("Opcao Invalida!");
		} while(x>5);
	}

	public static String MenuGrava() {
		System.out.println("#################### GRAVAR FICHEIRO ####################");
		System.out.println("#                                                       #");
		System.out.println("#  * Insira o nome do ficheiro a ser gravado,           #");
		System.out.println("#  * \"1\" para ficheiro default (\"publicx.obj\".          #");
		str=null;
		if((str=s.next()).equals("1"))
			return "publicx.obj";
		else
			return str;
	}

	private static void MenuConsultasEspeciais(RedeAno ra2) {
		System.out.println("#########################################################");
		System.out.println("#                                                       #");

		
	}

	private static void MenuConsultaAnos(RedeAno ra2) {
		// TODO Auto-generated method stub
		
	}

	private static void MenuConsultaEstatisticas(RedeAno ra) throws FileNotFoundException, IOException {
		int x  = 0;
	   	do {
		System.out.println("################ CONSULTAS ESTATISTICAS #################");
		System.out.println("#                                                       #");
		System.out.println("#        1 - DADOS ULTIMO FICHEIRO LIDO                 #");
		System.out.println("#        2 - DADOS GERAIS ACTUAIS NA ESTRUTURA          #");
		System.out.println("#        3 - TABELA PUBLICACOES POR ANO                 #");
		System.out.println("#        4 - VOLTAR AO MENU PRINCIPAL                   #");
		System.out.println("#                                                       #");
		System.out.println("#        Escolha uma opcao:                             #");
		System.out.println("#########################################################");
		x = s.nextInt();
		
		if(x==1) {
			System.out.println("#########################################################");
			System.out.println("#                                                       #");
			System.out.println("#        Ficheiro: "+ra.getNomeFicheiro());
			System.out.println("#        Total de Artigos: "+ra.getTotalArtigos());
			System.out.println("#        Total Nomes Lidos: "+ra.getNumeroAutores());
			System.out.println("#        Total Nomes Distintos: "+ra.getNomesDistintos());
			System.out.println("#        Intervalo Anos: ["+ra.anoInferior()+","+ra.anoSuperior()+"]");
			System.out.println("#                                                       #");
			System.out.println("#########################################################");
			MenuConsultaEstatisticas(ra);
		}
		if(x==2) {
			System.out.println("#########################################################");
			System.out.println("#                                                       #");
			System.out.println("#        Total de autores: "+ra.getNomesDistintos());
			System.out.println("#        Artigos de um unico autor: "+ra.getNumeroArtigosUnicoAutor());
			System.out.println("#        Autores que apenas publicaram a solo: "+ra.unicoAutor());
			System.out.println("#        Autores que nunca publicaram a solo: "+ra.coisa());
			System.out.println("#                                                       #");
			System.out.println("#########################################################");
			MenuConsultaEstatisticas(ra);
		}
		if(x==3) {
			System.out.println("#########################################################");
			System.out.println("#                                                       #");
			TreeMap<Integer, Integer> lpa = ra.listaPublicacoesPorAno();
		    for(Integer i : lpa.keySet())
		    	out.println("#        Ano: "+i+"\tPublicacoes: "+lpa.get(i));
			System.out.println("#                                                       #");
			System.out.println("#########################################################");
			MenuConsultaEstatisticas(ra);
		}
		if(x==4)
			MenuPrincipal(ra);
		else
			System.out.println("Opcao Invalida!");
	   	} while(x>4);
	}

}
