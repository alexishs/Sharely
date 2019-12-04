package fr.dawan.sharely.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Remboursement")
public class Remboursement extends Dette {
	
	private Date dateRemboursement; // date effective du remboursement initié par le payeur
	private Date dateAccuseReception; // null par défaut, date à laquelle le payé valide le paiement
	
	public Remboursement(Utilisateur utilisateurPayeur, Utilisateur utilisateurAPayer, Date dateRemboursement) {
		super(utilisateurPayeur, utilisateurAPayer);
		this.dateRemboursement = dateRemboursement;
	}

	public Date getDateRemboursement() {
		return dateRemboursement;
	}

	public Date getDateAccuseReception() {
		return dateAccuseReception;
	}

	public void setDateAccuseReception(Date dateAccuseReception) {
		this.dateAccuseReception = dateAccuseReception;
	}
	
}