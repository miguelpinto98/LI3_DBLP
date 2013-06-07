import java.util.TreeMap;


public class RedeAno {
	private TreeMap<Integer, RedeAutor> rano;
	
	public RedeAno() {
		this.rano = new TreeMap<>();
	}
	
	public RedeAno(RedeAno ra) {
		this.rano = ra.getRedeAno();
	}
	
	private TreeMap<Integer, RedeAutor> getRedeAno() {
		return null;
	}

	public RedeAno clone() {
		return new RedeAno(this);
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		for(Integer n : this.rano.keySet())
			str.append("Ano: ")
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
