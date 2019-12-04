package fr.dawan.sharely.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Dette")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Dette extends DbObject{

	@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Utilisateur utilisateurPayeur;  // celui qui doit payer la dette
	
	@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Utilisateur utilisateurAPayer;  // celui qui doit être remboursé
	
	@Column(scale=2, precision=2)
	private double montant;
	
	public Dette() {}

	
	public Dette(Utilisateur utilisateurPayeur, Utilisateur utilisateurAPayer) {
		this.setUtilisateurPayeur(utilisateurPayeur);
		this.setUtilisateurAPayer(utilisateurAPayer);
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Utilisateur getUtilisateurPayeur() {
		return utilisateurPayeur;
	}

	public void setUtilisateurPayeur(Utilisateur utilisateurPayeur) {
		this.utilisateurPayeur = utilisateurPayeur;
	}

	public Utilisateur getUtilisateurAPayer() {
		return utilisateurAPayer;
	}

	public void setUtilisateurAPayer(Utilisateur utilisateurAPayer) {
		this.utilisateurAPayer = utilisateurAPayer;
	}
	
	
}
