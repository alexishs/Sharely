package fr.dawan.sharely.beans;

import java.util.HashSet;

public class UtilisateurReel extends Utilisateur {

	private String password;
	private HashSet<UtilisateurFictif> utilisateursFictifs = new HashSet<UtilisateurFictif>();
	
	public UtilisateurReel(int id, String nom, String prenom, String email) {
		super(id, nom, prenom, email);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public HashSet<UtilisateurFictif> getUtilisateursFictifs() {
		return utilisateursFictifs;
	}
	
}
