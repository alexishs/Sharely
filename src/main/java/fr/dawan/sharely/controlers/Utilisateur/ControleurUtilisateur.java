package fr.dawan.sharely.controlers.Utilisateur;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.sharely.beans.UtilisateurReel;
import fr.dawan.sharely.controlers.ReponseRest;
import fr.dawan.sharely.services.ServiceUtilisateur;

@RestController
@RequestMapping("/user")
public class ControleurUtilisateur {
	
	@Autowired
	ServiceUtilisateur serviceUtilisateur;
	
	@PostMapping(value ="/login", produces = "application/json")
	public ReponseRest login(HttpServletResponse reponseHttp, @RequestBody InfosConnexion body) {
		StringBuilder messageErreur = new StringBuilder();
		UtilisateurReel utilisateurConnecte = serviceUtilisateur.connexion(body.email, body.motDePasse, messageErreur);
		if (utilisateurConnecte == null) {
			return ReponseRest.creerInvalide(reponseHttp, messageErreur.toString());
		}else {
			return ReponseRest.creerValide(utilisateurConnecte);
		}
	}

}
