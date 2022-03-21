package warehouses.project.localisation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import warehouses.project.entrepot.Entrepot;

@Entity
public class Localisation {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_localisation;
  private int Longitude;
  private int latitude;
  
  @OneToOne
  private Entrepot entrepot;
  
  protected Localisation() {
	  
  }
  
  
  
public Entrepot getEntrepot() {
	return entrepot;
}



public void setEntrepot(Entrepot entrepot) {
	this.entrepot = entrepot;
}



public int getId_localisation() {
	return id_localisation;
}
public void setId_localisation(int id_localisation) {
	this.id_localisation = id_localisation;
}
public int getLongitude() {
	return Longitude;
}
public void setLongitude(int longitude) {
	Longitude = longitude;
}

public int getLatitude() {
	return latitude;
}
public void setLatitude(int latitude) {
	this.latitude = latitude;
}



@Override
public String toString() {
	return "Localisation [id_localisation=" + id_localisation + ", Longitude=" + Longitude+ ", latitude=" + latitude + "]";
}
public Localisation(int id_localisation, int longitude, int altitude, int latitude) {
	super();
	this.id_localisation = id_localisation;
	Longitude = longitude;
	this.latitude = latitude;
}
  
  
  
}
