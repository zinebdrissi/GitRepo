package warehouses.project.entrepot;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import warehouses.project.entreprise.Entreprise;
import warehouses.project.localisation.Localisation;
import warehouses.project.ville.Ville;

@Entity
public class Entrepot {
@Id @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;
	private String titre;
	private int id_localisation;
	private int id_ville;
	private int ID_entreprise;
	
	@ManyToOne
	private Entreprise entreprise;
	@ManyToMany
	private List<Ville> ville;
	
	@OneToOne(fetch= FetchType.LAZY, 
			cascade = CascadeType.ALL,
			mappedBy = "entrepot")
	private Localisation localisation;
	
	protected Entrepot() {
		
	}
	
	
	public Localisation getLocalisation() {
		return localisation;
	}


	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
	}


	public Entreprise getEntreprise() {
		return entreprise;
	}


	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	


	public List<Ville> getVille() {
		return ville;
	}


	public void setVille(List<Ville> ville) {
		this.ville = ville;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getId_localisation() {
		return id_localisation;
	}
	public void setId_localisation(int id_localisation) {
		this.id_localisation = id_localisation;
	}
	public int getId_ville() {
		return id_ville;
	}
	public void setId_ville(int id_ville) {
		this.id_ville = id_ville;
	}
	public int getID_entreprise() {
		return ID_entreprise;
	}
	public void setID_entreprise(int iD_entreprise) {
		ID_entreprise = iD_entreprise;
	}
	@Override
	public String toString() {
		return "Entrepot [id=" + id + ", titre=" + titre + ", id_localisation=" + id_localisation + ", id_ville="
				+ id_ville + ", ID_entreprise=" + ID_entreprise + "]";
	}
	public Entrepot(int id, String titre, int id_localisation, int id_ville, int iD_entreprise) {
		super();
		this.id = id;
		this.titre = titre;
		this.id_localisation = id_localisation;
		this.id_ville = id_ville;
		ID_entreprise = iD_entreprise;
	}
	
	
	
	

}
