package fr.dawan.sharely.beans;

import java.util.Date;

public class Remboursement extends Dette {
	
	private Date dateRemboursement; // date effective du remboursement initié par le payeur
	private Date dateAccuseReception; // null par défaut, date à laquelle le payé valide le paiement
	
	public Date getDateRemboursement() {
		return dateRemboursement;
	}
	
	public void setDateRemboursement(Date dateRemboursement) {
		this.dateRemboursement = dateRemboursement;
	}

	public Date getDateAccuseReception() {
		return dateAccuseReception;
	}

	public void setDateAccuseReception(Date dateAccuseReception) {
		this.dateAccuseReception = dateAccuseReception;
	}
	
}