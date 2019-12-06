package fr.dawan.sharely.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LigneFacture")
public class LigneFacture extends DbObject{
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Facture facture;
	
	private String libelle;
	
	@Column(scale=2, precision=2)
	private double montant;
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	// temp @OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER, mappedBy = "LigneFacture")
	private Set<Participation> participations = new HashSet<Participation>(); // contient la ou les participations associée(s) à la ligne de facture.
	
	public LigneFacture() {}
	
	public LigneFacture(String libelle, double montant) {
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

	public Facture getFacture() {
		return facture;
	}
	
	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public void addParticipant(Participation p) {
		participations.add(p);
	}
	
	public Set<Participation> getParticipants() {
		return participations;
	}
	
	
}
