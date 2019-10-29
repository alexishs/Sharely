package fr.dawan.sharely.beans;

import java.util.HashSet;

public class LigneFacture {
	
	private Facture facture;
	private byte numerLigne;
	private String libelle;
	private double montant;
	private HashSet<Utilisateur> participants = new HashSet<Utilisateur>(); // contient le ou les participants associé(s) à la ligne de facture.
	
	public LigneFacture(Facture facture, byte numerLigne) {
		super();
		this.facture = facture;
		this.numerLigne = numerLigne;
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

	public Facture getFacture() {
		return facture;
	}

	public byte getNumerLigne() {
		return numerLigne;
	}

	public HashSet<Utilisateur> getParticipants() {
		return participants;
	}
	
	
	
}
