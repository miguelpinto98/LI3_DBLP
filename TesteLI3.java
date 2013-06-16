import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class TesteLI3 {
	public static Scanner s = new Scanner(System.in);
	public static String str = null;
	public static RedeAno ra = new RedeAno();
	public static ArrayList<String> linhas = new ArrayList<String>();
	public static int anoi = 0, anof = 0;
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {	    
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
    				    long inicio = System.nanoTime();
    				    linhas = Utils.leLinhasScanner(str);
    				    ra.setTotalArtigos(linhas.size());
    				    Utils.trataLinhas(ra,linhas);
    				    long fim = System.nanoTime();
    				    out.println("\nTempo de Leitura: " + (fim - inicio)/1.0E09 + " segs.\n");
    				    MenuPrincipal(ra);
    					flag=true;
 				   	} catch(FileNotFoundException e) {
 				   		System.out.println("Ficheiro nao encontrado, insira novamente!");
 				   	}
 			    } while(flag);
	    	} else {
	    		if(x==2) {
				    boolean flag = true;
				    ra = null;
	    			do {
	    				try {
	    					str = MenuCarregaObjecto();
	    					ra = RedeAno.carregaObj(str);
	    					//ra.setNomeFicheiro(str);
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
			MenuConsultasIntervalos(ra);
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
		System.out.println("#  * \"1\" para ficheiro default (\"publicx.obj\").         #");
		str=null;
		if((str=s.next()).equals("1"))
			return "publicx.obj";
		else
			return str;
	}

	private static void MenuConsultasIntervalos(RedeAno ra) throws FileNotFoundException, IOException {
		System.out.println("#########################################################");
		System.out.println("#                                                       #");
		System.out.println("#  * Defina o intervalo de anos das consultas           #");
		System.out.print("#  * Ano inicial: ");
		anoi=s.nextInt();
		System.out.print("#  * Ano final: ");
		anof=s.nextInt();
		
		MenuConsultaAnos(ra,anoi,anof);
	}
	
	public static void MenuConsultaAnos(RedeAno ra2, int ai, int af) throws FileNotFoundException, IOException {
		int x=0, xx=0;
	   	do {
		System.out.println("################ CONSULTAS POR ANOS #####################");
		System.out.println("#                                                       #");
		System.out.println("#        ["+ai+","+af+"]                                    #");
		System.out.println("#                                                       #");
		System.out.println("#        1 - TOP X PUBLICACOES                          #");
		System.out.println("#        2 - TOP X CO-AUTORIAS                          #");
		System.out.println("#        3 - CO-AUTORES COMUNS A AUTORES                #");
		System.out.println("#        4 - AUTORES QUE PUBLICARAM ARTIGOS             #");
		System.out.println("#        5 - VOLTAR ATRAS                               #");
		System.out.println("#                                                       #");
		System.out.println("#        Escolha uma opcao:                             #");
		System.out.println("#########################################################");
		x = s.nextInt();
		
		if(x==1) {
			System.out.println("################# TOP X PUBLICACOES #####################");
			System.out.println("#                                                       #");
			System.out.print("#  * Defina X: ");
			xx = s.nextInt();
			System.out.println("#                                                       #");			
			ArrayList<String> tp = ra.topAutoresPorNome(ai, af,xx);
			for(String s : tp)
				System.out.println("#        "+s);
			System.out.println("#                                                       #");
			System.out.println("#########################################################");
			MenuConsultaAnos(ra2, ai, af);
		}
		if(x==2) {
			System.out.println("################# TOP X PUBLICACOES #####################");
			System.out.println("#                                                       #");
			System.out.print("#  * Defina x: ");
			xx = s.nextInt();
			HashMap<String, Integer> tca = ra.topCoAutores(ai, af, xx);
		    for(String s : tca.keySet())
		    	System.out.println(tca.get(s)+s);
			System.out.println("#                                                       #");
			System.out.println("#########################################################");
			MenuConsultaAnos(ra2, ai, af);
		}
		if(x==3) {
			ArrayList<String> autins = new ArrayList<>();
			System.out.println("############# CO-AUTORES COMUNS A AUTORES ###############");
			System.out.println("#                                                       #");
			System.out.println("#  * Introduza os autores:                              #");
			System.out.println("#  * FORMATO: \"Autor1\", \"Autor2\" <ENTER>                #");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  
			String str = in.readLine(); 
                
			String[] straux = str.split(", ");
			for(String ss : straux) {
				autins.add(ss);
			}
			System.out.println("#                                                       #");
			TreeSet<String> comuns = ra.coautoresComunsDestesAutores(autins, ai, af);
			for(String s : comuns)
				System.out.println("#        "+s);
			System.out.println("#                                                       #");
			System.out.println("#########################################################");
			MenuConsultaAnos(ra2, ai, af);
		}
		if(x==4) {
			System.out.println("## AUTORES QUE PUBLICARAM SEMPRE ARTIGOS NO INTERVALO ###");
			System.out.println("#                     ["+ai+","+af+"]                       #");
			System.out.println("#                                                       #");			
			HashSet<String> hs = ra.AutoresPorTodosAnos(ai,af);
		    for(String s : hs)
		    	System.out.println("#        "+s);
			System.out.println("#                                                       #");
			System.out.println("#########################################################");
			MenuConsultaAnos(ra2, ai, af);
		}
		if(x==5)
			MenuPrincipal(ra);
		else
			System.out.println("Opcao Invalida!");	
	   	}while(x>5);
	}
	
	private static void MenuConsultasEspeciais(RedeAno ra) throws FileNotFoundException, IOException {
		int x=0, xx=0;
	   	do {
		System.out.println("################ CONSULTAS ESPECIAIS ####################");
		System.out.println("#                                                       #");
		System.out.println("#        1 - NUMEROS DE LINHAS EM DUPLICADO             #");
		System.out.println("#        2 - AUTORES POR CO-AUTORES INFERIORES A X      #");
		System.out.println("#        3 - VOLTAR ATRAS                               #");
		System.out.println("#                                                       #");
		System.out.println("#        Escolha uma opcao:                             #");
		System.out.println("#########################################################");
		x = s.nextInt();
	   	if(x==1){
			System.out.println("########### NUMEROS DE LINHAS EM DUPLICADO ##############");
			System.out.println("#                                                       #");
			System.out.println("#        Linhas em duplicado: "+Utils.verificaDuplicados(ra.getNomeFicheiro()));
			System.out.println("#                                                       #");
			System.out.println("#########################################################");
			MenuConsultasEspeciais(ra);  		
	   	}
	   	if(x==2) {
			System.out.println("######## AUTORES POR CO-AUTORES INFERIORES A X ##########");
			System.out.println("#                                                       #");
			System.out.print("#  Defina x: ");
			xx = s.nextInt();
			System.out.println("#                                                       #");
			HashMap<String, HashSet<String>> autcoaut = ra.autoresPorCoautoresInferior();
			for(String s : autcoaut.keySet()) {
				System.out.println("#  Autor:"+s);
					if(autcoaut.get(s).size()<xx)			
						for(String ss : autcoaut.get(s))
							System.out.println("#        "+ss);
			}
			System.out.println("#                                                       #");
			System.out.println("#########################################################");
			MenuConsultasEspeciais(ra);  		
	   	}
		if(x==3)
			MenuPrincipal(ra);
		else
			System.out.println("Opcao Invalida!");	
	   	} while(x>3);
	}

	private static void MenuConsultaEstatisticas(RedeAno ra) throws FileNotFoundException, IOException {
		int x  = 0;
	   	do {
		System.out.println("################ CONSULTAS ESTATISTICAS #################");
		System.out.println("#                                                       #");
		System.out.println("#        1 - DADOS ULTIMO FICHEIRO LIDO                 #");
		System.out.println("#        2 - DADOS GERAIS ACTUAIS NA ESTRUTURA          #");
		System.out.println("#        3 - TABELA PUBLICACOES POR ANO                 #");
		System.out.println("#        4 - VOLTAR ATRAS                               #");
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
			System.out.println("#        Autores que nunca publicaram a solo: "+ra.autEscrevemSolo());
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
