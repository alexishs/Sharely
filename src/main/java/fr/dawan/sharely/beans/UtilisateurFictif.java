package fr.dawan.sharely.beans;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UtilisateurFictif")
public class UtilisateurFictif extends Utilisateur {

	@ManyToOne
	private Facture facture;
		
	public UtilisateurFictif(String nom, String prenom, String email, Facture facture) {
		super(nom, prenom, email);
		this.setFacture(facture);
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

}
