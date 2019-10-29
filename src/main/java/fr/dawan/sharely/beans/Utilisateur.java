package fr.dawan.sharely.beans;

import java.util.Date;
import java.util.HashSet;

import fr.dawan.sharely.ErreursMetier;
import fr.dawan.sharely.enums.EnumErreurMetier;

public abstract class Utilisateur {
	
	private int id;
	private String nom;
	private String prenom;
	private String email; // obligatoire si Utilisateur Réel
	
	private HashSet<Facture> factures = new HashSet<Facture>(); // part de l'utilisateur sur chaque facture
	private HashSet<Dette> dettesAPayer = new HashSet<Dette>(); // total, toutes factures, des dettes à payer par l'utilisateur aux autres utilisateurs
	private HashSet<Dette> dettesARecevoir = new HashSet<Dette>(); // total, toutes factures, des dettes à payer par les autres utilisateurs, à l'utilisateur
	private HashSet<Remboursement> remboursementsPayes = new HashSet<Remboursement>();
	private HashSet<Remboursement> remboursementsRecus = new HashSet<Remboursement>();
	
	public Utilisateur(int id, String nom, String prenom, String email) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	public int getId() {
		return id;
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

	public HashSet<Facture> getFactures() {
		return factures;
	}

	public HashSet<Dette> getDettesAPayer() {
		return dettesAPayer;
	}

	public HashSet<Dette> getDettesARecevoir() {
		return dettesARecevoir;
	}

	public HashSet<Remboursement> getRemboursementsPayes() {
		return remboursementsPayes;
	}

	public HashSet<Remboursement> getRemboursementsRecus() {
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
