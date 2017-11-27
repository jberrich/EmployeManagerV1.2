package ma.jberrich.model;

import java.util.ArrayList;
import java.util.List;

public class ListeEmployes {
	
	private List<Employe> employes;

	public ListeEmployes() {
		employes = new ArrayList<>();
	}
	
	public List<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}
	
}
