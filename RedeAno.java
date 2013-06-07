import java.util.TreeMap;


public class RedeAno {
	private TreeMap<Integer, RedeAutor> rano;
	
	public RedeAno() {
		this.rano = new TreeMap<>();
	}
	
	public RedeAno(TreeMap<Integer, RedeAutor> tra) {
		this.rano = new TreeMap<>();
		
		for(Integer n : tra.keySet()) {
			RedeAutor aux = tra.get(n);
			this.rano.put(n, aux.clone());
		}
	} 
	
	public RedeAno(RedeAno ra) {
		this.rano = ra.getRedeAno();
	}
	
	private TreeMap<Integer, RedeAutor> getRedeAno() {
		TreeMap<Integer, RedeAutor> aux = new TreeMap<>();
		
		for(Integer n : this.rano.keySet()) {
			RedeAutor ra = this.rano.get(n);
			aux.put(n, ra.clone());
		}
		return aux;
	}

	public RedeAno clone() {
		return new RedeAno(this);
	}
	
	public String toString() {
		StringBuilder s =  new StringBuilder();
		
		for(Integer n : this.rano.keySet())
			s.append("Ano: "+n+this.rano.get(n).toString());
		
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
}
