package fr.dawan.sharely.beans;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class DetteSurFacture extends Dette {
	
	@ManyToOne
	private Facture facture;

	public DetteSurFacture() {}
	
	public DetteSurFacture(Utilisateur utilisateurPayeur, Utilisateur utilisateurAPayer, Facture facture) {
		super(utilisateurPayeur, utilisateurAPayer);
		this.setFacture(facture);
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

}
