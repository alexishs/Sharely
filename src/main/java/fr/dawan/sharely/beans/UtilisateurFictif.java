package fr.dawan.sharely.beans;

import java.util.Date;

public class UtilisateurFictif extends Utilisateur {

	private Facture facture;
	private Date dateFin; // null par défaut, date de fin de vie si création de l'utilisateur réel associé - autodestruction
	
	public UtilisateurFictif(int id, String nom, String prenom, String email, Facture facture) {
		super(id, nom, prenom, email);
		this.facture = facture;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

}
