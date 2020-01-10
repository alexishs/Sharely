package fr.dawan.sharely.controlers;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.dawan.sharely.enums.EnumResultatTraitement;
import fr.dawan.sharely.services.RetourTraitement;

public class ReponseRest {
	
	private EnumResultatTraitement codeResultatService;
	private String messageUtilisateur;
	private Set<String> commentaires;
	private String typeDonnee;
	private Object donnees;
	@JsonIgnore
	private HttpServletResponse reponseHttp;
	
	public ReponseRest(HttpServletResponse reponseHttp, EnumResultatTraitement codeResultatService, String messageUtilisateur, Set<String> commentaires, Object donnees) {
		this.reponseHttp = reponseHttp;
		this.codeResultatService = codeResultatService;
		this.messageUtilisateur = messageUtilisateur;
		this.commentaires = commentaires;
		this.donnees = donnees;
		switch (this.codeResultatService) {
		case OK:
			this.reponseHttp.setStatus(HttpStatus.OK.value());
			if(this.messageUtilisateur == null) {
				this.messageUtilisateur = "OK.";
			}
			break;
		case ECHEC_METIER:
			this.reponseHttp.setStatus(HttpStatus.BAD_REQUEST.value());
			if(this.messageUtilisateur == null) {
				this.messageUtilisateur = "Traitement impossible.";
			}
			break;
		case RESSOURCE_INCONNUE:
			this.reponseHttp.setStatus(HttpStatus.NOT_FOUND.value());
			if(this.messageUtilisateur == null) {
				this.messageUtilisateur = "La ressource demandée est introuvable.";
			}
			break;
		case ACCES_INTERDIT:
			this.reponseHttp.setStatus(HttpStatus.FORBIDDEN.value());
			if(this.messageUtilisateur == null) {
				this.messageUtilisateur = "L'accès à cette ressource est interdit.";
			}
			break;
		default:
			this.reponseHttp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			if(this.messageUtilisateur == null) {
				this.messageUtilisateur = "Une erreur est survenue lors du traitement demandé.";
			}
		}
		if(this.donnees != null) {
			this.typeDonnee = this.donnees.getClass().getSimpleName();
		}
	}
	
	public static ReponseRest creerAvecRetourTraitement(HttpServletResponse reponseHttp, RetourTraitement retourTraitement, Object donnees) {
		return new ReponseRest(reponseHttp,
				retourTraitement.getCodeRetour(),
				retourTraitement.getMessageUtilisateur(),
				retourTraitement.getCommentairesUtilisateur(),
				donnees);
	}
	
	public static ReponseRest creerFormat(HttpServletResponse reponseHttp, Object exempleDonnees, RequestMethod[] methodesAcceptees) {
		StringBuilder libellesMethodes = new StringBuilder();
		String messageMethodes = new String();
		for(int iMethode = 0; iMethode<methodesAcceptees.length; iMethode++) {
			if(iMethode != 0) {
				libellesMethodes.append(", ");
			}
			libellesMethodes.append(methodesAcceptees[iMethode].name());
		}
		reponseHttp.setHeader("Allow", libellesMethodes.toString());
		if(methodesAcceptees.length == 1) {
			messageMethodes = "Méthode acceptée : "+libellesMethodes.toString();
		}else {
			messageMethodes = "Méthodes acceptées : "+libellesMethodes.toString();
		}
		return new ReponseRest(reponseHttp,EnumResultatTraitement.OK, messageMethodes, null, exempleDonnees);
	}
	
	public static ReponseRest creerFormat(HttpServletResponse reponseHttp, Object exempleDonnees, RequestMethod methodeAcceptee) {
		RequestMethod[] tableauMethodes = {methodeAcceptee};
		return ReponseRest.creerFormat(reponseHttp, exempleDonnees, tableauMethodes);
	}

	public EnumResultatTraitement getCodeResultatService() {
		return codeResultatService;
	}

	public String getMessageUtilisateur() {
		return messageUtilisateur;
	}
	
	public Set<String> getCommentaires() {
		return commentaires;
	}

	public Object getDonnees() {
		return donnees;
	}

	public HttpServletResponse getReponseHttp() {
		return reponseHttp;
	}

	public String getTypeDonnee() {
		return typeDonnee;
	}
	
}
