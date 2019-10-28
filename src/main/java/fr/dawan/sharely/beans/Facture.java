package fr.dawan.sharely.beans;

import java.util.Date;
import java.util.List;

import fr.dawan.sharely.enums.EnumRepartition;

public class Facture {

	private Date date;
	private String libelle;
	private double montant;
	private EnumRepartition repartition;
	private List<LigneFacture> ligneFacture;
	private List<Utilisateur> participants;
	
	public Facture() {}

	public Facture(Date date, String libelle, double montant) {
		super();
		this.date = date;
		this.libelle = libelle;
		this.montant = montant;
		this.repartition = EnumRepartition.AUTOMATIQUE;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public EnumRepartition getRepartition() {
		return repartition;
	}

	public void setRepartition(EnumRepartition repartition) {
		this.repartition = repartition;
	}
	
	public List<LigneFacture> getLigneFacture() {
		return ligneFacture;
	}

	public void setLigneFacture(List<LigneFacture> ligneFacture) {
		this.ligneFacture = ligneFacture;
	}

	public List<Utilisateur> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Utilisateur> participants) {
		this.participants = participants;
	}
	
}
