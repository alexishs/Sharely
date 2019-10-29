package fr.dawan.sharely.beans;

import java.util.List;

public class LigneFacture {
	
	private Facture facture;
	private byte numerLigne;
	
	private String libelle;
	private int quantite;
	private double montant;
	private List<Utilisateur> participants; // contient le ou les participants associé(s) à la ligne de facture.
	
	public LigneFacture() {}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public int getNumerLigne() {
		return numerLigne;
	}

	public void setNumerLigne(byte numerLigne) {
		this.numerLigne = numerLigne;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}
	
}
