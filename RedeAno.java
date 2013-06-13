import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
	
	@SuppressWarnings("unchecked")
	public void carregaRM(String file) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream o = new ObjectInputStream(f);
        
        this.rano = (TreeMap<Integer, RedeAutor>) o.readObject();

        o.close();
        f.close();
	}
	
	public void gravaRM(String file) throws FileNotFoundException, IOException {
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutputStream o = new ObjectOutputStream(f);
        
        o.writeObject(this.rano);

        o.close();
        f.close();
	}
	
	public int keyAnoInferior() {
		return this.rano.firstKey();
	}
	
	public int keyAnoSuperior() {
		return this.rano.lastKey();
	}
	
	public void insereRedeAno(int ano, Autor a, ArrayList<Autor> ca) {
		RedeAutor ra = null;
		
		if(this.rano.containsKey(ano)) {
			ra = this.rano.get(ano);
			ra.insereAutores(a,ca);
		} else {
			ra = new RedeAutor();
			ra.insereAutores(a, ca);
			this.rano.put(ano, ra);		
		}
	}
	
}
