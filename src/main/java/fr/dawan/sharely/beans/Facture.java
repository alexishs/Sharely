package fr.dawan.sharely.beans;

import java.util.List;

public class Facture {

	private String libelle;
	private double montant;
	private List<LigneFacture> ligneFacture;
	
	public Facture() {}

	public Facture(String libelle, double montant) {
		super();
		this.libelle = libelle;
		this.montant = montant;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public List<LigneFacture> getLigneFacture() {
		return ligneFacture;
	}

	public void setLigneFacture(List<LigneFacture> ligneFacture) {
		this.ligneFacture = ligneFacture;
	}
	
}
