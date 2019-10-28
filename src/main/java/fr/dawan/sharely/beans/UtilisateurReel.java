package fr.dawan.sharely.beans;

import java.util.List;

public class UtilisateurReel extends Utilisateur {
	
	private String password;
	private List<UtilisateurFictif> utilisateursFictifs;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<UtilisateurFictif> getUtilisateursFictifs() {
		return utilisateursFictifs;
	}
	
	public void setUtilisateursFictifs(List<UtilisateurFictif> utilisateursFictifs) {
		this.utilisateursFictifs = utilisateursFictifs;
	}
	
}
