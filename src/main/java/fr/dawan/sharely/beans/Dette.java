package fr.dawan.sharely.beans;

public class Dette {

	private Utilisateur utilisateurPayeur;  // celui qui doit payer la dette
	private Utilisateur utilisateurAPayer;  // celui qui doit être remboursé
	private double montant;
	
	public Utilisateur getUtilisateurPayeur() {
		return utilisateurPayeur;
	}
	
	public void setUtilisateurPayeur(Utilisateur utilisateurPayeur) {
		this.utilisateurPayeur = utilisateurPayeur;
	}

	public Utilisateur getUtilisateurAPayer() {
		return utilisateurAPayer;
	}

	public void setUtilisateurAPayer(Utilisateur utilisateurAPayer) {
		this.utilisateurAPayer = utilisateurAPayer;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
	
}
