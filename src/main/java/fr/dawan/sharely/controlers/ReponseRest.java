package fr.dawan.sharely.controlers;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.dawan.sharely.enums.EnumResultatTraitement;
import fr.dawan.sharely.services.RetourTraitement;

public class ReponseRest {
	
	private EnumResultatTraitement resultCode;
	private String message;
	private Set<String> comments;
	private String dataType;
	private Object data;
	@JsonIgnore
	private HttpServletResponse reponseHttp;
	
	public ReponseRest(HttpServletResponse reponseHttp, EnumResultatTraitement codeResultatService, String messageUtilisateur, Set<String> commentaires, Object donnees) {
		this.reponseHttp = reponseHttp;
		this.resultCode = codeResultatService;
		this.message = messageUtilisateur;
		this.comments = commentaires;
		this.data = donnees;
		this.reponseHttp.addHeader("Access-Control-Allow-Origin", "*");
		this.reponseHttp.addHeader("Access-Control-Allow-Credentials", "true");
		switch (this.resultCode) {
		case OK:
			this.reponseHttp.setStatus(HttpStatus.OK.value());
			if(this.message == null) {
				this.message = "OK.";
			}
			break;
		case REQUEST_REFUSED:
			this.reponseHttp.setStatus(HttpStatus.BAD_REQUEST.value());
			if(this.message == null) {
				this.message = "Le traitement demandé a été refusé.";
			}
			break;
		case UNKNOWN_RESSOURCE:
			this.reponseHttp.setStatus(HttpStatus.NOT_FOUND.value());
			if(this.message == null) {
				this.message = "La ressource demandée est introuvable.";
			}
			break;
		case ACCESS_FORBIDDEN:
			this.reponseHttp.setStatus(HttpStatus.FORBIDDEN.value());
			if(this.message == null) {
				this.message = "L'accès à cette ressource est interdit.";
			}
			break;
		default:
			this.reponseHttp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			if(this.message == null) {
				this.message = "Une erreur est survenue lors du traitement demandé.";
			}
		}
		if(this.data != null) {
			this.dataType = this.data.getClass().getSimpleName();
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

	public EnumResultatTraitement getResultCode() {
		return resultCode;
	}

	public String getMessage() {
		return message;
	}
	
	public Set<String> getComments() {
		return comments;
	}

	public Object getData() {
		return data;
	}

	public HttpServletResponse getReponseHttp() {
		return reponseHttp;
	}

	public String getDataType() {
		return dataType;
	}
	
}
