package fr.dawan.sharely.controlers.facture;

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

import fr.dawan.sharely.beans.Facture;
import fr.dawan.sharely.controlers.ReponseRest;
import fr.dawan.sharely.controlers.SessionUtilisateur;
import fr.dawan.sharely.enums.EnumResultatTraitement;
import fr.dawan.sharely.services.RetourTraitement;
import fr.dawan.sharely.services.ServiceFacture;

@RestController
@CrossOrigin
@RequestMapping("/invoices")
public class ControleurFacture {
	
	@Autowired
	ServiceFacture serviceFacture;
	
	@GetMapping(value = "/list", produces = "application/json")
	public ReponseRest listeFacture(HttpServletRequest requeteHttp, HttpServletResponse reponseHttp) {
		return new ReponseRest(reponseHttp, EnumResultatTraitement.ECHEC_METIER, "Non implémenté", null, null);
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.OPTIONS, produces = "application/json")
	public ReponseRest formatNouvelleFacture(HttpServletResponse reponseHttp) {
		return ReponseRest.creerFormat(reponseHttp, new InfosNouvelleFacture(),RequestMethod.POST);
	}
	@PostMapping(value = "/new", produces = "application/json")
	public ReponseRest nouvelleFacture(HttpServletRequest requeteHttp, HttpServletResponse reponseHttp, @RequestBody InfosNouvelleFacture body) {
		RetourTraitement retourTraitement = new RetourTraitement();
		Facture nouvelleFacture =
			this.serviceFacture.creerNouvelleFacture(
				body.getLibelle(),
				body.getMontant(),
				SessionUtilisateur.getSession(requeteHttp).getUtilisateur(),
				retourTraitement);
		return ReponseRest.creerAvecRetourTraitement(reponseHttp, retourTraitement, nouvelleFacture);
	}
}
