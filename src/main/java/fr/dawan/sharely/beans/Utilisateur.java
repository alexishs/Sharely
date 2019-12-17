package fr.dawan.sharely.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


/*
 * pour empêcher certaines propriétés d'être exportées en JSON, voir https://github.com/FasterXML/jackson-annotations
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Utilisateur")
public abstract class Utilisateur extends DbObject{
	
	private String nom;
	private String prenom;
	
	@ManyToMany
	private Set<Participation> participations = new HashSet<Participation>(); // part de l'utilisateur sur chaque facture. Une seule participation si utilisateur fictif.

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
		this.setEmail(email);
	}


	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public abstract String getEmail();
	public abstract void setEmail(String email);

	public Set<Participation> getParticipations(){
		return participations;
	}
	
	public void addParticipation(Participation participation) {
		if (!participations.contains(participation) && participation != null) {
			this.participations.add(participation);
		}
	}

	public void removeParticipation(Participation participation) {
		this.participations.remove(participation);
	}
	
	public Set<Dette> getDettes(){
		return dettes;
	}
	
	public Set<Remboursement> getRemboursements(){
		return remboursements;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
