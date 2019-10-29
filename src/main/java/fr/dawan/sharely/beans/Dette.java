package fr.dawan.sharely.beans;

public class Dette {

	private Utilisateur utilisateurPayeur;  // celui qui doit payer la dette
	private Utilisateur utilisateurAPayer;  // celui qui doit être remboursé
	private double montant;
	
	public Dette(Utilisateur utilisateurPayeur, Utilisateur utilisateurAPayer) {
		super();
		this.utilisateurPayeur = utilisateurPayeur;
		this.utilisateurAPayer = utilisateurAPayer;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	
}
