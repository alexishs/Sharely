package fr.dawan.sharely.controlers.utilisateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import fr.dawan.sharely.enums.EnumResultatTraitement;
import fr.dawan.sharely.services.RetourTraitement;
import fr.dawan.sharely.services.ServiceUtilisateur;

@RestController
@CrossOrigin
@RequestMapping("/me")
public class ControleurUtilisateur {
	
	@Autowired
	ServiceUtilisateur serviceUtilisateur;
	
	@RequestMapping(value = "/login", method = RequestMethod.OPTIONS, produces = "application/json")
	public ReponseRest formatlogin(HttpServletResponse reponseHttp) {
		return ReponseRest.creerFormat(reponseHttp, new InfosConnexion(),RequestMethod.POST);
	}
	@PostMapping(value ="/login", produces = "application/json")
	public ReponseRest login(HttpServletRequest requeteHttp, HttpServletResponse reponseHttp, @RequestBody InfosConnexion body) {
		SessionUtilisateur sessionUtilisateur = SessionUtilisateur.getSession(requeteHttp);
		RetourTraitement retourTraitement = new RetourTraitement();
		UtilisateurReel utilisateurIdentifie = serviceUtilisateur.connexion(body.email, body.motDePasse, retourTraitement);
		if(!retourTraitement.enErreur()) {
			if(!sessionUtilisateur.connexion(utilisateurIdentifie)) {
				retourTraitement.definirResultat(EnumResultatTraitement.ERREUR_INATTENDUE, "Une erreur est survenue lors de la création de session.");
			}
		}
		return ReponseRest.creerAvecRetourTraitement(reponseHttp, retourTraitement, null);
	}
	
	@GetMapping(value ="/logout", produces = "application/json")
	public ReponseRest logout(HttpServletRequest requeteHttp, HttpServletResponse reponseHttp) {
		SessionUtilisateur.getSession(requeteHttp).deconnexion();
		return new ReponseRest(reponseHttp, EnumResultatTraitement.OK, null, null, null);
	}
	
	@GetMapping(value ="/info", produces = "application/json")
	public ReponseRest info(HttpServletRequest requeteHttp, HttpServletResponse reponseHttp) {
		return new ReponseRest(reponseHttp, EnumResultatTraitement.ECHEC_METIER, "Non implémenté.", null, null);
	}
}
