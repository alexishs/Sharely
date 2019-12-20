package fr.dawan.sharely.controlers;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.dawan.sharely.beans.UtilisateurReel;

public class SessionUtilisateur implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UtilisateurReel utilisateur;
	
	public boolean estConnecte() {
		return (utilisateur != null);
	}
	
	public boolean connexion(UtilisateurReel utilisateur) {
		if (this.estConnecte()) {
			this.deconnexion();
		}
		if(utilisateur != null) {
			this.utilisateur = utilisateur;
		}
		return estConnecte();
	}
	
	public void deconnexion() {
		this.utilisateur = null;
	}
	
	public static SessionUtilisateur getSession(HttpServletRequest requeteHttp) {
		HttpSession sessionHttp = requeteHttp.getSession();
		SessionUtilisateur sessionUtilisateur = (SessionUtilisateur) sessionHttp.getAttribute("sessionutilisateur");
		if(sessionUtilisateur == null) {
			sessionUtilisateur = new SessionUtilisateur();
			sessionHttp.setAttribute("sessionutilisateur", sessionUtilisateur);
		}
		return sessionUtilisateur;
	}

	public UtilisateurReel getUtilisateur() {
		return this.utilisateur;
	}
	
}
