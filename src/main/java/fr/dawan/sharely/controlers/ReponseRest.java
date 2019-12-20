package fr.dawan.sharely.controlers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReponseRest {
	
	private static final int CODE_RETOUR_VALIDE = 0;
	private static final int CODE_RETOUR_INVALIDE = 1;
	
	private int codeRetourMetier;
	private String messageUtilisateur;
	private String typeDonnee;
	private Object donnees;
	@JsonIgnore
	private HttpServletResponse reponseHttp;
	
	private ReponseRest(HttpServletResponse reponseHttp, int codeRetourMetier, String messageUtilisateur, Object donnees) {
		this.reponseHttp = reponseHttp;
		this.codeRetourMetier = codeRetourMetier;
		this.messageUtilisateur = messageUtilisateur;
		this.donnees = donnees;
		if((this.codeRetourMetier>0) && (this.reponseHttp != null)) {
			this.reponseHttp.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		if(this.donnees != null) {
			this.typeDonnee = this.donnees.getClass().getSimpleName();
		}
	}
	
	public static ReponseRest creerValide(Object donnees) {
		return new ReponseRest(null, ReponseRest.CODE_RETOUR_VALIDE, "OK", donnees);
	}
	
	public static ReponseRest creerInvalide(HttpServletResponse reponseHttp, String messageErreur) {
		return new ReponseRest(reponseHttp, ReponseRest.CODE_RETOUR_INVALIDE, messageErreur, null);
	}

	public int getCodeRetourMetier() {
		return codeRetourMetier;
	}

	public String getMessageUtilisateur() {
		return messageUtilisateur;
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
