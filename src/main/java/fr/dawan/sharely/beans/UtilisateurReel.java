package fr.dawan.sharely.beans;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "UtilisateurReel")
public class UtilisateurReel extends Utilisateur {

	private String password;
		
	public UtilisateurReel() {}

	public UtilisateurReel(String nom, String prenom, String email) {
		super(nom, prenom, email);
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
