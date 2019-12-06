package fr.dawan.sharely.beans;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class UtilisateurFictif extends Utilisateur {
	private String email; // doublons possibles (car un fictif peut se retrouver recopié de factures en factures...)
	public UtilisateurFictif() {}

	public UtilisateurFictif(String nom, String prenom, String email) {
		super(nom, prenom, email);
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
