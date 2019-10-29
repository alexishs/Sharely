package fr.dawan.sharely.beans;

import java.util.Date;
import java.util.List;

import fr.dawan.sharely.enums.EnumRepartition;

public class Facture {

	private int id;
	private Date dateFacture;
	private Date dateValidation; // à NULL par défaut. Contient la date à laquelle la facture est validée, lorsque chaque participant a validé sa participation
	private String libelle;
	private double montant;
	private EnumRepartition repartition;
	private List<LigneFacture> lignesFactures; // obligatoire selon le mode de répartition choisi 
	private List<Participation> participations;
	private List<DetteSurFacture> dettesSurFacture;

	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}

	public Date getDateValidation() {
		return dateValidation;
	}

	public void setDateValidation(Date dateValidation) {
		this.dateValidation = dateValidation;
	}

	public List<DetteSurFacture> getDettesSurFacture() {
		return dettesSurFacture;
	}

	public void setDettesSurFacture(List<DetteSurFacture> dettesSurFacture) {
		this.dettesSurFacture = dettesSurFacture;
	}
	
}
