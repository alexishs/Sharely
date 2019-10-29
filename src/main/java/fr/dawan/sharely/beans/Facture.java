package fr.dawan.sharely.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;

import fr.dawan.sharely.ErreursMetier;
import fr.dawan.sharely.enums.EnumErreurMetier;
import fr.dawan.sharely.enums.EnumRepartition;

public class Facture {

	private int id;
	private Date dateFacture;
	private Date dateValidation; // à NULL par défaut. Contient la date à laquelle la facture est validée, lorsque chaque participant a validé sa participation
	private String libelle;
	private double montant;
	
	private EnumRepartition repartition = EnumRepartition.MANUELLE;
	private LinkedHashSet<LigneFacture> lignesFactures = new LinkedHashSet<LigneFacture>(); // obligatoire selon le mode de répartition choisi 
	private HashSet<Participation> participations = new HashSet<Participation>(); // participation par utilisateur
	private HashSet<DetteSurFacture> dettesSurFacture = new HashSet<DetteSurFacture>();
	
	
	public Facture(int id, Date dateFacture, String libelle) {
		super();
		this.id = id;
		this.dateFacture = dateFacture;
		this.libelle = libelle;
	}
	
	public Facture creerNouvelleFacture(Date dateFacture, String libelle, ErreursMetier erreurs) {
		Facture nouvelleFacture = null;
		erreurs = new ErreursMetier()
				.ajouterErreur(EnumErreurMetier.DATE_INVALIDE, "Date de nouvelle facture invalide")
				.ajouterErreur(EnumErreurMetier.LIBELLE_INVALIDE, "Le libellé de facture doit être renseigné");
		int id = 0;
		return new Facture(id, dateFacture, libelle);
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


	
	
}
