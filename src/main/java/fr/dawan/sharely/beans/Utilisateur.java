package fr.dawan.sharely.beans;

import java.util.List;

public abstract class Utilisateur {
	
	private int id;
	private String nom;
	private String prenom;
	private String email; // obligatoire si Utilisateur Réel
	
	private List<Participation> participations;
	private List<Dette> dettesAPayer; // total, toutes factures, des dettes à payer par l'utilisateur aux autres utilisateurs
	private List<Dette> dettesARecevoir; // total, toutes factures, des dettes à payer par les autres utilisateurs, à l'utilisateur
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}
	
}
