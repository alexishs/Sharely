package fr.dawan.sharely.beans;

public class DetteSurFacture extends Dette {
	private Facture facture;

	public DetteSurFacture(Utilisateur utilisateurPayeur, Utilisateur utilisateurAPayer, Facture facture) {
		super(utilisateurPayeur, utilisateurAPayer);
		this.facture = facture;
	}

}
