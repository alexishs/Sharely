package fr.dawan.sharely.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.dawan.sharely.enums.EnumRepartition;

@Entity
@Table(name="Facture")
public class Facture extends DbObject{

	private Date dateFacture;
	private Date dateValidation; // à NULL par défaut. Contient la date à laquelle la facture est validée, lorsque chaque participant a validé sa participation
	private String libelle;
	
	@Column(scale=2, precision=2)
	private double montant;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "repartition")
	private EnumRepartition repartition = EnumRepartition.MANUELLE;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "facture")
	private Set<LigneFacture> lignesFactures = new LinkedHashSet<LigneFacture>(); // obligatoire selon le mode de répartition choisi 
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "facture")
	private Set<Participation> participations = new HashSet<Participation>(); // participation par utilisateur
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "facture")
	private Set<DetteSurFacture> dettesSurFacture = new HashSet<DetteSurFacture>();
	
	public Facture() {}
	
	public Facture(Date dateFacture, String libelle) {
		super();
		this.setDateFacture(dateFacture);
		this.libelle = libelle;
	}
	
	/*
	 * crée une facture, l'ajoute à la liste des factures.
	 * les params doivent être valides
	 * Si traitement correct, retourne la facture, sinon
	 */
	
	public Date getDateValidation() {
		return dateValidation;
	}

	public void setDateValidation(Date dateValidation) {
		this.dateValidation = dateValidation;
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

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}

	public Set<LigneFacture> getLignesFactures() {
		return lignesFactures;
	}

	public void addLigneFacture(LigneFacture lf) {
		lignesFactures.add(lf);
	}
	
	public void removeLigneFacture(LigneFacture lf) {
		lignesFactures.remove(lf);
	}

	public Set<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(HashSet<Participation> participations) {
		this.participations = participations;
	}

	public Set<DetteSurFacture> getDettesSurFacture() {
		return dettesSurFacture;
	}

	public void setDettesSurFacture(HashSet<DetteSurFacture> dettesSurFacture) {
		this.dettesSurFacture = dettesSurFacture;
	}


	
	
}
