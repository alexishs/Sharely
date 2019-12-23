package fr.dawan.sharely.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Participation")
public class Participation extends DbObject{

	@ManyToOne(fetch=FetchType.EAGER)
	@JsonIgnore private Facture facture;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Utilisateur utilisateur;
	
	@Column(scale=2, precision=2)
	private double montantDu; // part de la participation
	
	@Column(scale=2, precision=2)
	private double montantPaye; // montant payé le jour de la facture
	private Date dateValidationSaisie;
	
	@ManyToMany(fetch=FetchType.EAGER, mappedBy = "participations")
	// temp @ManyToMany(fetch=FetchType.EAGER, mappedBy = "LigneFacture")
	private Set<LigneFacture> lignesFactures = new HashSet<LigneFacture>(); // non obligatoire selon méthode de répartition
	
	public Participation() {}
	
	public Participation(Facture facture, Utilisateur utilisateur) {
		this.facture = facture;
		this.utilisateur = utilisateur;
	}

	public double getMontantDu() {
		return montantDu;
	}

	public void setMontantDu(double montantDu) {
		this.montantDu = montantDu;
	}

	public double getMontantPaye() {
		return montantPaye;
	}

	public void setMontantPaye(double montantPaye) {
		this.montantPaye = montantPaye;
	}

	public Date getDateValidationSaisie() {
		return dateValidationSaisie;
	}

	public void setDateValidationSaisie(Date dateValidationSaisie) {
		this.dateValidationSaisie = dateValidationSaisie;
	}

	public Facture getFacture() {
		return facture;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public Set<LigneFacture> getLignesFactures() {
		return lignesFactures;
	}  
	
	
}
