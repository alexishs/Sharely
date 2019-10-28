package fr.dawan.sharely.beans;

public class LigneFacture {
	
	private int id;
	private Facture facture;
	private String libelle;
	private int quantite;
	private double montant;
	private Utilisateur utilisateur;
	
	public LigneFacture() {}

	public LigneFacture(String libelle, int quantite, double montant, Utilisateur utilisateur) {
		super();
		this.libelle = libelle;
		this.montant = montant;
		this.utilisateur = utilisateur;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}


	
}
