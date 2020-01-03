package fr.dawan.sharely.services;

import java.util.Arrays;
import java.util.LinkedHashSet;

import fr.dawan.sharely.enums.EnumResultatTraitement;

public class RetourTraitement {

	private EnumResultatTraitement CodeRetour = EnumResultatTraitement.OK;
	private String messageUtilisateur;
	private LinkedHashSet<String> commentairesUtilisateur;
	
	public RetourTraitement() {
		this.messageUtilisateur = "";
		this.commentairesUtilisateur = new LinkedHashSet<String>();
	}

	public EnumResultatTraitement getCodeRetour() {
		return CodeRetour;
	}

	public String getMessageUtilisateur() {
		return messageUtilisateur;
	}

	public LinkedHashSet<String> getCommentairesUtilisateur() {
		return commentairesUtilisateur;
	}
	
	public void definirResultat(EnumResultatTraitement codeRetour,String messageUtilisateur) {
		this.CodeRetour = codeRetour;
		this.messageUtilisateur = messageUtilisateur;
	}
	
	public boolean enErreur() {
		EnumResultatTraitement[] tableauErreurs = {EnumResultatTraitement.ECHEC_METIER, EnumResultatTraitement.ERREUR_INATTENDUE};
		return (Arrays.asList(tableauErreurs).indexOf(this.getCodeRetour())>-1);
	}

}
