package fr.dawan.sharely.beans;

import java.util.Date;

public class Remboursement extends Dette {
	
	private Date dateRemboursement; // date effective du remboursement initié par le payeur
	private Date dateAccuseReception; // null par défaut, date à laquelle le payé valide le paiement
	
}