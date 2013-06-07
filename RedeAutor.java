import java.util.ArrayList;
import java.util.HashMap;

public class RedeAutor {
	private HashMap<Autor,ArrayList<Autor>> rautor;
	
	public RedeAutor() {
		this.rautor = new HashMap<>();
	}
	
	public RedeAutor(RedeAutor redeAutor) {
		// TODO Auto-generated constructor stub
	}

	public RedeAutor clone() {
		return new RedeAutor(this);
	}
}
