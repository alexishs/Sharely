package fr.dawan.sharely.beans;

import java.util.Date;
import java.util.HashSet;

public class Participation {

	private Facture facture;
	private Utilisateur utilisateur;
	private double montantDu; // part de la participation
	private double montantPaye; // montant payé le jour de la facture
	private Date dateValidationSaisie;
	private HashSet<LigneFacture> lignesFactures = new HashSet<LigneFacture>(); // non obligatoire selon méthode de répartition
	
	public Participation(Facture facture, Utilisateur utilisateur) {
		this.facture = facture;
		this.utilisateur = utilisateur;
	}

	public double getMontantDu() {
		return montantDu;
	}

	public void setMontantDu(double montantDu) {
		this.montantDu = montantDu;
	}

	public double getMontantPaye() {
		return montantPaye;
	}

	public void setMontantPaye(double montantPaye) {
		this.montantPaye = montantPaye;
	}

	public Date getDateValidationSaisie() {
		return dateValidationSaisie;
	}

	public void setDateValidationSaisie(Date dateValidationSaisie) {
		this.dateValidationSaisie = dateValidationSaisie;
	}

	public Facture getFacture() {
		return facture;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public HashSet<LigneFacture> getLignesFactures() {
		return lignesFactures;
	}  
	
	
}
