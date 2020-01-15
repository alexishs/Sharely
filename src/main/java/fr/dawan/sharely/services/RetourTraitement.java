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
	
	public void definirResultat(EnumResultatTraitement codeRetour,String messageUtilisateur, String commentaire) {
		this.CodeRetour = codeRetour;
		this.messageUtilisateur = messageUtilisateur;
		if(commentaire != null) {
			commentairesUtilisateur.add(commentaire);
		}
	}
	
	public boolean enErreur() {
		return (getCodeRetour() != EnumResultatTraitement.OK);
	}
	
	public boolean ok() {
		return (getCodeRetour() == EnumResultatTraitement.OK);
	}

}
