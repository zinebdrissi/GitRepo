package warehouses.project.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import warehouses.project.entreprise.Entreprise;

@Entity
public class Users {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int Id_User;
	private String FullName;
	private String PhoneNumber;
	private String Email;
	private String Role;
	private int ID_entreprise;
	
	@ManyToOne
	private Entreprise entreprise; 
	
protected Users() {
		
	}
	
	
	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	
	public Users(int id_User, String fullName, String phoneNumber, String email, String role, int iD_entreprise) {
		super();
		Id_User = id_User;
		FullName = fullName;
		PhoneNumber = phoneNumber;
		Email = email;
		Role = role;
		ID_entreprise = iD_entreprise;
	}

	
	
	@Override
	public String toString() {
		return "Users [Id_User=" + Id_User + ", FullName=" + FullName + ", PhoneNumber=" + PhoneNumber + ", Email="
				+ Email + ", Role=" + Role + ", ID_entreprise=" + ID_entreprise + "]";
	}

	public int getId_User() {
		return Id_User;
	}
	public void setId_User(int id) {
		Id_User = id;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}

	public int getID_entreprise() {
		return ID_entreprise;
	}

	public void setID_entreprise(int iD_entreprise) {
		ID_entreprise = iD_entreprise;
	}
	
	
	

}
