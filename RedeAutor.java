import java.util.ArrayList;
import java.util.HashMap;

public class RedeAutor {
	private HashMap<Autor,ArrayList<Autor>> rautor;
	
	public RedeAutor() {
		this.rautor = new HashMap<>();
	}
	
	public RedeAutor(HashMap<Autor,ArrayList<Autor>> ra) {
		ArrayList<Autor> aux = new ArrayList<>();
		this.rautor = new HashMap<>();
		
		for(Autor a : ra.keySet()) {
			for(Autor ca : ra.get(a))
				aux.add(ca);
			this.rautor.put(a, aux);
			aux = new ArrayList<>();
		}
	}
	
	public RedeAutor(RedeAutor ra) {
		this.rautor = ra.getRedeAutores();
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
			s.append("Autor: "+a);
			aux = this.rautor.get(a);
			for(Autor at : aux)
				s.append(at.toString());
		}
		return s.toString();
	}
}
