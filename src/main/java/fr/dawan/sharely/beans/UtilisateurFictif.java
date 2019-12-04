package fr.dawan.sharely.beans;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "UtilisateurFictif")
public class UtilisateurFictif extends Utilisateur {
		
	public UtilisateurFictif() {}

	public UtilisateurFictif(String nom, String prenom, String email) {
		super(nom, prenom, email);
	}
}
