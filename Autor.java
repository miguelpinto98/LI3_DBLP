
public class Autor {
	private String nome;
	
	public Autor() {
		this.nome = "";
	}
	
	public Autor(String nome) {
		this.nome = nome;
	}
	
	public Autor(Autor a) {
		this.nome = a.getNomeAutor();
	}

	public String getNomeAutor() {
		return this.nome;
	}
	
	public void setNomeAutor(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return "Nome: "+this.nome;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
}
