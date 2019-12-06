package fr.dawan.sharely.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class UtilisateurReel extends Utilisateur {
	@Column(unique = true)
	private String email; // obligatoire si Utilisateur Réel
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

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

}
