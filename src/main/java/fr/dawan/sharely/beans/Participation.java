package fr.dawan.sharely.beans;

import java.util.Date;
import java.util.List;

public class Participation {

	private int id;
	private Facture facture;
	private double montantDu;
	private double montantPaye;
	private Date dateValidationSaisie;

	private Utilisateur utilisateur;
	private List<LigneFacture> lignesFactures;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Facture getFacture() {
		return facture;
	}
	
	public void setFacture(Facture facture) {
		this.facture = facture;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public List<LigneFacture> getLignesFactures() {
		return lignesFactures;
	}
	
	public void setLignesFactures(List<LigneFacture> lignesFactures) {
		this.lignesFactures = lignesFactures;
	}

	public Date getDateValidationSaisie() {
		return dateValidationSaisie;
	}

	public void setDateValidationSaisie(Date dateValidationSaisie) {
		this.dateValidationSaisie = dateValidationSaisie;
	}
	
}
