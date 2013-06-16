import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

@SuppressWarnings("serial")
public class RedeAno implements Serializable {
	private TreeMap<Integer, RedeAutor> rano;
	private int nautores;
	private int nartunicoaut;
	private String nomefile;
	private int totalart;
	private int nndist;
	private int autumax;
	
	public RedeAno() {
		this.rano = new TreeMap<>();
		this.nautores = 0;
		this.nartunicoaut = 0;
		this.nomefile = "";
		this.totalart = 0;
		this.nndist = 0;
		this.autumax = 0;
	}
	
	public RedeAno(TreeMap<Integer, RedeAutor> tra, int naut, int nunico, int talart,int nndist) {
		this.rano = new TreeMap<>();
		for(Integer n : tra.keySet()) {
			RedeAutor aux = tra.get(n);
			this.rano.put(n, aux.clone());
		}
		this.nautores = naut;
		this.nartunicoaut = nunico;
		this.nomefile = "";
		this.totalart = talart;
		this.nndist = nndist;
		this.autumax = 0;
	} 
	
	public RedeAno(RedeAno ra) {
		this.rano = ra.getRedeAno();
		this.nautores = ra.getNumeroAutores();
		this.nartunicoaut = ra.getNumeroArtigosUnicoAutor();
		this.nomefile = ra.getNomeFicheiro();
		this.totalart = ra.getTotalArtigos();
		this.nndist = ra.getNomesDistintos();
		this.autumax = ra.getAutoresQueEscreveramUmaVezSolo();
	}
	
	public int getNomesDistintos() {
		return this.nndist;
	}
	
	public int getTotalArtigos() {
		return this.totalart;
	}

	public String getNomeFicheiro() {
		return this.nomefile;
	}
	
	public int getNumeroArtigosUnicoAutor() {
		return this.nartunicoaut;
	}

	public TreeMap<Integer, RedeAutor> getRedeAno() {
		TreeMap<Integer, RedeAutor> aux = new TreeMap<>();
		
		for(Integer n : this.rano.keySet()) {
			RedeAutor ra = this.rano.get(n);
			aux.put(n, ra.clone());
		}
		return aux;
	}
	
	public int getNumeroAutores() {
		return this.nautores;
	}
	
	public void setNomesDistintos(int ndist) {
		this.nndist = ndist;
	}
	
	public void setNomeFicheiro(String s) {
		this.nomefile = s;
	}
	
	public void setNumeroAutores(int n) {
		this.nautores = n;
	}
	
	public void setNumeroArtigosUnicoAutor(int nunico) {
		this.nartunicoaut = nunico;
	}
	
	public void setTotalArtigos(int nart) {
		this.totalart = nart;
	}

	public RedeAno clone() {
		return new RedeAno(this);
	}
	
	public String toString() {
		StringBuilder s =  new StringBuilder("REDE\n");
		
		for(Integer n : this.rano.keySet())
			s.append("Ano: "+n+"\n"+ this.rano.get(n).toString());
		
		return s.toString();
	}
	
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if((o == null) || (this.getClass() != o.getClass()))
			return false;
		RedeAno ra = (RedeAno) o;
		return this.rano.equals(ra.getRedeAno());
	}
	
	public static RedeAno carregaObj(String file) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream o = new ObjectInputStream(f);
        RedeAno ra = null;
        
        Object obj = o.readObject();
        if(obj.getClass().equals(RedeAno.class));
        	ra = (RedeAno) obj;
         
        o.close();
        f.close();
        
        return ra;
	}
	
	public void gravaObj(String file) throws FileNotFoundException, IOException {
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutputStream o = new ObjectOutputStream(f);
        
        o.writeObject(this);

        o.flush();
        o.close();
        f.flush();
        f.close();
	}
	
	public int anoInferior() {
		return this.rano.firstKey();
	}
	
	public int anoSuperior() {
		return this.rano.lastKey();
	}
	
	public void insereRedeAno(int ano, Autor a, ArrayList<Autor> ca) {
		RedeAutor ra = null;
		
		if(this.rano.containsKey(ano)) {
			ra = this.rano.get(ano);
			ra.insereAutores(a, ca);
		} else {
			ra = new RedeAutor();
			ra.insereAutores(a, ca);
			this.rano.put(ano, ra);		
		}
	}

	public void addNumeroAutores(int tam) {
		this.setNumeroAutores(this.getNumeroAutores()+tam);
	}
	
	public void addNumeroArtigosUnicoAutor() {
		this.setNumeroArtigosUnicoAutor(this.getNumeroArtigosUnicoAutor()+1);
	} 
	
	public TreeMap<Integer, HashSet<String>> AutoresEntreAnos(int anoi, int anof) {
		TreeMap<Integer,HashSet<String>> laut = new TreeMap<>();
		
		for(Integer i : this.rano.keySet()) {
			if(i>=anoi && i<=anof) {
				HashSet<String> la = this.rano.get(i).autoresAno();
				laut.put(i, la);
			}
		}
		return laut;
	}
	
	public HashSet<String> AutoresPorTodosAnos(int anoi, int anof) {
		TreeMap<Integer, HashSet<String>> lautores = this.AutoresEntreAnos(anoi, anof);
		int n = lautores.firstKey();
		HashSet<String> hs = lautores.get(n), aux;
		
		for(Integer i : lautores.keySet()) {
			aux = new HashSet<>();
			for(String s : lautores.get(i)) {
				if(hs.contains(s))
					aux.add(s);				}
			hs = aux;
		}
		return hs;
	}

	public TreeMap<Integer, Integer> listaPublicacoesPorAno() {
		TreeMap<Integer, Integer> lpa = new TreeMap<>();
		
		for(Integer i : this.rano.keySet())
			lpa.put(i, this.rano.get(i).getNumeroPublicacoes());
		
		return lpa;
	}

	public void addNumeroPublicacoesAno(int ano) {
		this.rano.get(ano).addNumeroPublicacoes();
	}
	
	
	public int autEscrevemSolo() {	
		return getNomesDistintos()-this.getAutoresQueEscreveramUmaVezSolo();
	}
	
	public HashMap<Autor, ArrayList<Autor>> autorPorCoautores() {
		HashMap<Autor, ArrayList<Autor>> aux = new HashMap<>();
		
		for(RedeAutor ra : this.rano.values())
			ra.autoresporcoautores(aux);
	
		return aux;
	}
	
	public int unicoAutor() {
		HashMap<Autor, ArrayList<Autor>> aux = this.autorPorCoautores();
		int unico=0;
		
		for(ArrayList<Autor> ala : aux.values()) {
			if(ala.size()==0)
				unico++;
		}
		return unico;
	}
	
	public ArrayList<String> topAutoresPorNome(int anoi, int anof, int n) {
		TreeMap<String, Integer> aux = new TreeMap<>(), aux2 = new TreeMap<>();
		int j=0, max=0, num;
		ArrayList<String> aut = new ArrayList<>();
		
		for(Integer i : this.rano.keySet())
			if(i>=anoi && i<=anof)
				this.rano.get(i).topRedeAutor(aux);
		
		max=maximoValor(aux);
		while(j<n) {
			for(String s : aux.keySet()) {
				if((num=aux.get(s))==max && j<n) {
					aut.add(num+" - "+s);
					j++;
				}
				else
					aux2.put(s, num);
			}
			aux = aux2;
			aux2 = new TreeMap<>();
			max = maximoValor(aux);
		}
		return aut;
	}

	private int maximoValor(TreeMap<String, Integer> tmax) {
		int max = 0;
		
		for(Integer i : tmax.values()) {
			if(i>max)
				max = i;
		}
		return max;
	}
	
	public ArrayList<String> topCoAutores(int anoi, int anof, int n) {
		HashMap<String, ArrayList<String>> aux = new HashMap<>();
		ArrayList<String> test = new ArrayList<>();

		for(Integer i : this.rano.keySet())
			if(i>=anoi && i<=anof)
				this.rano.get(i).JuntaCoAutores(aux);
		
		TreeMap<String, Integer> taut = new TreeMap<>(), taut2 = new TreeMap<>();
		String a1 = null, a2 = null;
		int res=0;
		for(String s : aux.keySet()) {
			res=0;
			for(String ss : aux.get(s)) {
				a1 = s+", "+ss;
				a2 = ss+", "+s;
				if(taut.containsKey(a1))
					res = taut.get(a1)+1;
				else {
					if(taut.containsKey(a2)) {	
					res = taut.get(a2);
					a1 = a2;
					} else
						res=1;
				}
				taut.put(a1, res);	
				}
			}
		
		int j=0, max=0, num;
		max=maximoValor(taut);
		while(j<n) {
			for(String s : taut.keySet()) {
				if((num=taut.get(s))==max && j<n) {
					test.add(num+" - "+s);
					j++;
				}
				else
					taut2.put(s, num);
			}
			taut = taut2;
			taut2 = new TreeMap<>();
			max = maximoValor(taut);
		}
		
		return test;
	}
	
	public TreeSet<String> coautoresComunsDestesAutores(ArrayList<String> al, int anoi, int anof) {
		TreeSet<String> tco = new TreeSet<>(), ts = new TreeSet<>();
		HashMap<String, ArrayList<String>> aux = new HashMap<>(), aux2 = new HashMap<>();
		
		for(Integer i : this.rano.keySet())
			if(i>=anoi && i<=anof)
				this.rano.get(i).coautoresNesteAno(aux);
		
		for(String s : al)
			if(aux.containsKey(s))
				aux2.put(s, aux.get(s));
		
		for(ArrayList<String> als : aux2.values()) {
			if(tco.isEmpty())
				for(String s : als)
					tco.add(s);
			else {
				for(String s : als) {
					if(tco.contains(s))
						ts.add(s);
				}
				tco=ts;
				ts=new TreeSet<>();
			}
				
		}
		return tco;
	}

	public void addAutoresQueEscreveramUmaVezSolo(int size) {
		this.setAutoresQueEscreveramUmaVezSolo(size);
	}

	public void setAutoresQueEscreveramUmaVezSolo(int size) {
		this.autumax = size;
	}
	
	public int getAutoresQueEscreveramUmaVezSolo() {
		return this.autumax;
	}
	
	public void addAutoresDistintos(int size) {
		setNomesDistintos(size);
	}
	
	public HashMap<String, HashSet<String>> autoresPorCoautoresInferior() {
		HashMap<String, HashSet<String>> autporcoaut = new HashMap<>();
		
		for(RedeAutor ra : this.rano.values())
			ra.juntaCoautoresInferioresX(autporcoaut);

		return autporcoaut;
	}
}
