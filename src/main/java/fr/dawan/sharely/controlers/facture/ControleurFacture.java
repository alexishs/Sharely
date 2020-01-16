package fr.dawan.sharely.controlers.facture;

import java.util.List;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.sharely.beans.Facture;
import fr.dawan.sharely.controlers.ReponseRest;
import fr.dawan.sharely.controlers.SessionUtilisateur;
import fr.dawan.sharely.dao.DataSet;
import fr.dawan.sharely.dao.GenericDAO;
import fr.dawan.sharely.enums.EnumResultatTraitement;
import fr.dawan.sharely.services.RetourTraitement;
import fr.dawan.sharely.services.ServiceFacture;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials = "true")
@RequestMapping("/invoices")
public class ControleurFacture {
	
	@Autowired
	ServiceFacture serviceFacture;
	
	@GetMapping(value = "/", produces = "application/json")
	public ReponseRest listeFacture(HttpServletRequest requeteHttp, HttpServletResponse reponseHttp) {
		RetourTraitement retourTraitement = new RetourTraitement();
		SessionUtilisateur sessionUtilisateur = SessionUtilisateur.getSession(requeteHttp);
		DataSet dataSet = serviceFacture.listeFactures(sessionUtilisateur.getUtilisateur(), retourTraitement);
		return ReponseRest.creerAvecRetourTraitement(reponseHttp, retourTraitement, dataSet);
	}
	
	@GetMapping(value = "/{idfacture}", produces = "application/json")
	public ReponseRest lireFacture(HttpServletRequest requeteHttp, HttpServletResponse reponseHttp, @PathVariable(value = "idfacture") long idFacture) {
		RetourTraitement retourTraitement = new RetourTraitement();
		SessionUtilisateur sessionUtilisateur = SessionUtilisateur.getSession(requeteHttp);
		Facture factureLue = serviceFacture.lireFacture(idFacture, sessionUtilisateur.getUtilisateur(), retourTraitement);
		return ReponseRest.creerAvecRetourTraitement(reponseHttp, retourTraitement, factureLue);
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.OPTIONS, produces = "application/json")
	public ReponseRest formatNouvelleFacture(HttpServletResponse reponseHttp) {
		InfosNouvelleFacture exemple = new InfosNouvelleFacture();
		exemple.setLibelle("Libellé de la facture");
		exemple.setMontant(123.45);
		return ReponseRest.creerFormat(reponseHttp, exemple,RequestMethod.POST);
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
