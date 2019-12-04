package fr.dawan.sharely.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Utilisateur extends DbObject{
	
	private String nom;
	private String prenom;
	private String email; // obligatoire si Utilisateur Réel
	
	@ManyToMany
	private Set<Facture> factures = new HashSet<Facture>(); // part de l'utilisateur sur chaque facture

	@OneToMany
	private Set<Dette> dettes;
	
	@Transient
	private Set<Dette> dettesAPayer = new HashSet<Dette>(); // total, toutes factures, des dettes à payer par l'utilisateur aux autres utilisateurs
	
	@Transient
	private Set<Dette> dettesARecevoir = new HashSet<Dette>(); // total, toutes factures, des dettes à payer par les autres utilisateurs, à l'utilisateur
	
	@OneToMany
	private Set<Remboursement> remboursements;
	
	@Transient
	private Set<Remboursement> remboursementsPayes = new HashSet<Remboursement>();
	
	@Transient
	private Set<Remboursement> remboursementsRecus = new HashSet<Remboursement>();
	
	public Utilisateur() {}
	
	public Utilisateur(String nom, String prenom, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}


	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getEmail() {
		return email;
	}

	public Set<Facture> getFactures() {
		return factures;
	}

	public Set<Dette> getDettesAPayer() {
		return dettesAPayer;
	}

	public Set<Dette> getDettesARecevoir() {
		return dettesARecevoir;
	}

	public Set<Remboursement> getRemboursementsPayes() {
		return remboursementsPayes;
	}

	public Set<Remboursement> getRemboursementsRecus() {
		return remboursementsRecus;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
