package warehouses.project.ville;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import warehouses.project.entrepot.Entrepot;

@Entity
public class Ville {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_Ville;
	private String Nom_Ville;
	private String Pays;
	
	
	@ManyToMany
	private List<Entrepot> entrepot;
	
	
	
	
	
	public List<Entrepot> getEntrepot() {
		return entrepot;
	}
	public void setEntrepot(List<Entrepot> entrepot) {
		this.entrepot = entrepot;
	}
	
	
	
	public int getID_Ville() {
		return ID_Ville;
	}
	public void setID_Ville(int iD_Ville) {
		ID_Ville = iD_Ville;
	}
	public String getNom_Ville() {
		return Nom_Ville;
	}
	public void setNom_Ville(String nom_Ville) {
		Nom_Ville = nom_Ville;
	}
	public String getPays() {
		return Pays;
	}
	public void setPays(String pays) {
		Pays = pays;
	}
	
	@Override
	public String toString() {
		return "Ville [ID_Ville=" + ID_Ville + ", Nom_Ville=" + Nom_Ville + ", Pays=" + Pays + "]";
	}
	
	public Ville(int iD_Ville, String nom_Ville, String pays) {
		super();
		ID_Ville = iD_Ville;
		Nom_Ville = nom_Ville;
		Pays = pays;
	}
	
protected Ville() {
		
	}

}
