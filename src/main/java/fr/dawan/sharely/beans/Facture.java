package fr.dawan.sharely.beans;

import java.util.Date;
import java.util.List;

import fr.dawan.sharely.enums.EnumRepartition;

public class Facture {

	private Date date;
	private String libelle;
	private double montant;
	private EnumRepartition repartition;
	private List<LigneFacture> lignesFactures; // obligatoire selon le mode de répartition choisi 
	private List<Participation> participations;
	
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
	
	public List<LigneFacture> getLignesFactures() {
		return lignesFactures;
	}

	public void setLignesFactures(List<LigneFacture> lignesFactures) {
		this.lignesFactures = lignesFactures;
	}

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}
	
}
