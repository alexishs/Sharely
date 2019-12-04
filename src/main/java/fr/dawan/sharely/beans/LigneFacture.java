package fr.dawan.sharely.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
//	private Set<Utilisateur> participants = new HashSet<Utilisateur>(); // contient le ou les participants associé(s) à la ligne de facture.
	private Set<Participation> participants = new HashSet<Participation>(); // contient le ou les participants associé(s) à la ligne de facture.
	
	
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
		participants.add(p);
	}
	
	public Set<Participation> getParticipants() {
		return participants;
	}
	
	
}
