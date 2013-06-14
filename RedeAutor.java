import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RedeAutor {
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

	public void insereAutores(Autor a, ArrayList<Autor> ca) { // !
		ArrayList<Autor> al = null;
		
		if(this.rautor.containsKey(a)) {
			this.masquelixo(this.rautor.keySet(),a);
			this.rautor.get(a).addAll(ca); //teste
		} else {
			al = new ArrayList<>();
			al.addAll(ca);					//teste
			this.rautor.put(a, al);
		}
		
	}

	public void masquelixo(Set<Autor> set, Autor a) { //teste
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
	
	public void verificaAutoresPublicaramSozinhos(HashSet<String> up, HashSet<String> hist) {
		
		for(Autor a : this.rautor.keySet()) {
			if(this.rautor.get(a).size()==0) {
				if(up.isEmpty())
					up.add(a.getNomeAutor());
				else {
					if(up.contains(a.getNomeAutor()))
						;
					else {
						if(hist.contains(a.getNomeAutor()))
							up.remove(a.getNomeAutor());
						else
							up.add(a.getNomeAutor());
					}
				}
			}
			else {
				hist.add(a.getNomeAutor());
			}	
		}
	}
}
