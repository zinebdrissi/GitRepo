package warehouses.project.entreprise;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import warehouses.project.entrepot.Entrepot;
import warehouses.project.user.Users;

@Entity
public class Entreprise {
	   @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID_entreprise;
	private String Nom;
	private String Description;
	private String Type;
	
	@OneToMany(mappedBy = "entreprise")
	private List<Users> user;
	
	@OneToMany(mappedBy = "entreprise")
	private List<Entrepot> entrepot;
	
	
	protected Entreprise() {
		
	}
	
	
	public List<Users> getUser() {
		return user;
	}


	public void setUser(List<Users> user) {
		this.user = user;
	}

	
	

	public List<Entrepot> getEntrepot() {
		return entrepot;
	}


	public void setEntrepot(List<Entrepot> entrepot) {
		this.entrepot = entrepot;
	}


	public int getID_entreprise() {
		return ID_entreprise;
	}
	public void setID(int iD) {
		ID_entreprise = iD;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	@Override
	public String toString() {
		return "Entreprise [ID=" + ID_entreprise + ", Nom=" + Nom + ", Description=" + Description + ", Type=" + Type + "]";
	}
	public Entreprise(int iD, String nom, String description, String type) {
		super();
		ID_entreprise = iD;
		Nom = nom;
		Description = description;
		Type = type;
	}
	
	
}
