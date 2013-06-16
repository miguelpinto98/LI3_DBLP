import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class RedeAutor implements Serializable{
	private HashMap<Autor,ArrayList<Autor>> rautor;
	private int npub;
	
	public RedeAutor() {
		this.rautor = new HashMap<>();
		this.npub = 0;
	}
	
	public RedeAutor(HashMap<Autor,ArrayList<Autor>> ra, int npub) {
		ArrayList<Autor> aux = new ArrayList<>();
		this.rautor = new HashMap<>();
		
		for(Autor a : ra.keySet()) {
			for(Autor ca : ra.get(a))
				aux.add(ca);
			this.rautor.put(a, aux);
			aux = new ArrayList<>();
		}
		this.npub = npub;
	}
	
	public RedeAutor(RedeAutor ra) {
		this.rautor = ra.getRedeAutores();
		this.npub = ra.getNumeroPublicacoes();
	}

	public int getNumeroPublicacoes() {
		return this.npub;
	}

	public HashMap<Autor, ArrayList<Autor>> getRedeAutores() {
		ArrayList<Autor> aux = new ArrayList<>();
		HashMap<Autor, ArrayList<Autor>> hmaux= new HashMap<>();
		
		for(Autor a : this.rautor.keySet()) {
			for(Autor ca : this.rautor.get(a))
				aux.add(ca.clone());
			hmaux.put(a, aux);
			aux = new ArrayList<>();
		}
		return hmaux;
	}
	
	public void setNumeroPublicacoes(int n) {
		this.npub = n;
	}

	public RedeAutor clone() {
		return new RedeAutor(this);
	}
	
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null)
			return false;
		RedeAutor ra = (RedeAutor) o;
		return this.rautor.equals(ra.getRedeAutores());
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		ArrayList<Autor> aux = null;
		
		for(Autor a : this.rautor.keySet()) {
			s.append("Autor: "+a.getNumeroArtigos()+" "+ a);
			aux = this.rautor.get(a);
			for(Autor at : aux)
				s.append("\t\t"+at.toString());
		}
		return s.toString();
	}

	public void insereAutores(Autor a, ArrayList<Autor> ca) {
		ArrayList<Autor> al = null;
		
		if(this.rautor.containsKey(a)) {
			this.AdicionaArtigoAutor(this.rautor.keySet(),a);
			for(Autor co : ca)
				this.rautor.get(a).add(co.clone());
		} else {
			al = new ArrayList<>();
			for(Autor co : ca)
				al.add(co.clone());
			this.rautor.put(a, al);
		}
		
	}

	public void AdicionaArtigoAutor(Set<Autor> set, Autor a) {
		Iterator<Autor> it = set.iterator();
		boolean encontrou = false;
		Autor at = null;
		
		while(it.hasNext() && !encontrou)
			if((at=it.next()).equals(a)) {
				at.addArtigo();
				encontrou = true;
			}
	}

	public HashSet<String> autoresAno() {
		HashSet<String> laut = new HashSet<>();
		
		for(Autor a : this.rautor.keySet())
			if(!(laut.contains(a.getNomeAutor())))
				laut.add(a.getNomeAutor());
		
		return laut;
	}
	
	public void addNumeroPublicacoes() {
		this.setNumeroPublicacoes(this.getNumeroPublicacoes()+1);
	}
	
	public void verificaAutoresPublicaramSozinhos(HashSet<String> hsa) {
		
		for(Autor a : this.rautor.keySet()) {
			if(a.getEscreveuSolo()==false) {
				if(hsa.contains(a.getNomeAutor()))
					;
				else
					hsa.add(a.getNomeAutor());
			} else 
				if(hsa.contains(a.getNomeAutor()))
					hsa.remove(a.getNomeAutor());
		}
		
	}
	
	public void autoresporcoautores(HashMap<Autor, ArrayList<Autor>> ppp) {
		
		for(Autor a : this.rautor.keySet()) {
			if(ppp.containsKey(a))
				for(Autor co : this.rautor.get(a))
				ppp.get(a).add(co.clone());
			else {
				ArrayList<Autor> aux = new ArrayList<>();
				aux = this.rautor.get(a);
				ppp.put(a, aux);
			}
		}
		
	}
	
	public void topRedeAutor(TreeMap<String, Integer> tra) {
		int res=0;
		
		for(Autor a : this.rautor.keySet()) {
			if(tra.containsKey(a.getNomeAutor()))
				res=tra.get(a.getNomeAutor())+a.getNumeroArtigos();
			else 
				res=a.getNumeroArtigos();
			tra.put(a.getNomeAutor(), res);
		}
	}

	public void JuntaCoAutores(HashMap<String, ArrayList<String>> aux) {
		
		for(Autor a : this.rautor.keySet()) {
			if(aux.containsKey(a.getNomeAutor())) {
				for(Autor co : this.rautor.get(a))
					aux.get(a.getNomeAutor()).add(co.getNomeAutor());
			}
			else {
				ArrayList<String> als= new ArrayList<>();
				for(Autor co : this.rautor.get(a))
					als.add(co.getNomeAutor());
				aux.put(a.getNomeAutor(), als);
			}				
		}	
	}

	public void coautoresNesteAno(HashMap<String, ArrayList<String>> aux) {
		
		for(Autor a : this.rautor.keySet())
			if(aux.containsKey(a.getNomeAutor())) {
				for(Autor ca : this.rautor.get(a))
					aux.get(a.getNomeAutor()).add(ca.getNomeAutor());
				
			} else {
				ArrayList<String> al = new ArrayList<>();
				for(Autor ca : this.rautor.get(a))
					al.add(ca.getNomeAutor());
				
				aux.put(a.getNomeAutor(), al);
			}
	}

	public void juntaCoautoresInferioresX(HashMap<String, HashSet<String>> autporcoaut) {		
		for(Autor a : this.rautor.keySet()) {
			if(autporcoaut.containsKey(a.getNomeAutor())) {
				for(Autor co : this.rautor.get(a))
					autporcoaut.get(a.getNomeAutor()).add(co.getNomeAutor());
			}
			else {
				HashSet<String> coaut = new HashSet<>();
				for(Autor co : this.rautor.get(a))
					coaut.add(co.getNomeAutor());
				autporcoaut.put(a.getNomeAutor(), coaut);
			}
		}
	}
}
