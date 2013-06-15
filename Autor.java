
public class Autor {
	private String nome;
	private int nartigos;
	
	
	public Autor() {
		this.nome = "";
		this.nartigos = 1;
	}
	
	public Autor(String nome) {
		this.nome = nome;
		this.nartigos = 1;
	}
	
	public Autor(String nome, int nart) {
		this.nome = nome;
		this.nartigos = nart;
	}
	
	public Autor(Autor a) {
		this.nome = a.getNomeAutor();
		this.nartigos = a.getNumeroArtigos();
	}

	public String getNomeAutor() {
		return this.nome;
	}
	
	public int getNumeroArtigos() {
		return this.nartigos;
	}
	
	public void setNomeAutor(String nome) {
		this.nome = nome;
	}
	
	public void setNumeroArtigo(int n) {
		this.nartigos = n;
	}
	
	public String toString() {
		return this.nome+"\n";
	}
	
	public Autor clone() {
		return new Autor(this);
	}
	
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if((o == null) || (this.getClass() != o.getClass()))
			return false;
		Autor a = (Autor) o;
		return this.nome.equals(a.getNomeAutor());
	} 

	public int hashCode() {
		final int prime = 41;
		int result = 1;
		result = prime * result + ((this.nome == null) ? 0 : this.nome.hashCode());
		return result;
	}
	
	public void addArtigo() {
		this.setNumeroArtigo(this.getNumeroArtigos()+1);
	}
}
