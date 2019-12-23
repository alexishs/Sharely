package fr.dawan.sharely.controlers.facture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.sharely.controlers.ReponseRest;
import fr.dawan.sharely.services.ServiceFacture;

@RestController
@RequestMapping("/invoices")
public class ControleurFacture {
	
	@Autowired
	ServiceFacture serviceFacture;
	
	@GetMapping(value = "/list", produces = "application/json")
	public ReponseRest listeFacture(HttpServletRequest requeteHttp, HttpServletResponse reponseHttp) {
		return ReponseRest.creerInvalide(reponseHttp, "Non implémenté");
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.OPTIONS, produces = "application/json")
	public ReponseRest formatNouvelleFacture() {
		return ReponseRest.creerFormat(new InfosNouvelleFacture(),RequestMethod.POST);
	}
	@PostMapping(value = "/new", produces = "application/json")
	public ReponseRest nouvelleFacture(HttpServletRequest requeteHttp, HttpServletResponse reponseHttp, @RequestBody InfosNouvelleFacture body) {
		return ReponseRest.creerInvalide(reponseHttp, "Non implémenté");
	}

}
