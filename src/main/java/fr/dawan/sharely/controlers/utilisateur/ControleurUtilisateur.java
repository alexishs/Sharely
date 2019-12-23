package fr.dawan.sharely.controlers.utilisateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.sharely.beans.UtilisateurReel;
import fr.dawan.sharely.controlers.ReponseRest;
import fr.dawan.sharely.controlers.SessionUtilisateur;
import fr.dawan.sharely.controlers.facture.InfosNouvelleFacture;
import fr.dawan.sharely.services.ServiceUtilisateur;

@RestController
@RequestMapping("/user")
public class ControleurUtilisateur {
	
	@Autowired
	ServiceUtilisateur serviceUtilisateur;
	
	@RequestMapping(value = "/login", method = RequestMethod.OPTIONS, produces = "application/json")
	public ReponseRest formatlogin() {
		return ReponseRest.creerFormat(new InfosConnexion(),RequestMethod.POST);
	}
	@PostMapping(value ="/login", produces = "application/json")
	public ReponseRest login(HttpServletRequest requeteHttp, HttpServletResponse reponseHttp, @RequestBody InfosConnexion body) {
		SessionUtilisateur sessionUtilisateur = SessionUtilisateur.getSession(requeteHttp);
		StringBuilder messageErreur = new StringBuilder();
		UtilisateurReel utilisateurIdentifie = serviceUtilisateur.connexion(body.email, body.motDePasse, messageErreur);
		if(sessionUtilisateur.connexion(utilisateurIdentifie)) {
			return ReponseRest.creerValide(sessionUtilisateur.getUtilisateur());
		}else {
			return ReponseRest.creerInvalide(reponseHttp, messageErreur.toString());
		}
	}
	
	@GetMapping(value ="/logout", produces = "application/json")
	public ReponseRest logout(HttpServletRequest requeteHttp) {
		SessionUtilisateur.getSession(requeteHttp).deconnexion();
		return ReponseRest.creerValide(null);
	}
	
	@GetMapping(value ="/info", produces = "application/json")
	public ReponseRest info(HttpServletRequest requeteHttp) {
		return ReponseRest.creerValide(SessionUtilisateur.getSession(requeteHttp).getUtilisateur());
	}
}
