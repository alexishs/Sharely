package fr.dawan.sharely.beans;

import java.util.Date;

public class UtilisateurFictif extends Utilisateur {

	private UtilisateurReel createur; // utilisateur ayant crée l'utilisateur fictif
	private Date dateFin; // date de fin de vie si création de l'utilisateur réel associé - autodestruction
	private UtilisateurReel repreneur; // utilisateur ayant repris l'utilisateur fictif

	public UtilisateurReel getCreateur() {
		return createur;
	}
	
	public void setCreateur(UtilisateurReel createur) {
		this.createur = createur;
	}
	
	public Date getDateFin() {
		return dateFin;
	}
	
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	public UtilisateurReel getRepreneur() {
		return repreneur;
	}
	
	public void setRepreneur(UtilisateurReel repreneur) {
		this.repreneur = repreneur;
	}

}
