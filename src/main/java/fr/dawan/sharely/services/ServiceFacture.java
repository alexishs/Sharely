package fr.dawan.sharely.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.sharely.beans.Facture;
import fr.dawan.sharely.beans.LigneFacture;
import fr.dawan.sharely.beans.Participation;
import fr.dawan.sharely.beans.Utilisateur;
import fr.dawan.sharely.beans.UtilisateurReel;
import fr.dawan.sharely.dao.DataSet;
import fr.dawan.sharely.dao.FactureDAO;
import fr.dawan.sharely.dao.GenericDAO;
import fr.dawan.sharely.enums.EnumResultatTraitement;


@Service
public class ServiceFacture {
	
	private final String VOUS_NE_PARTICIPEZ_PAS_A_LA_FACTURE = "Vous ne participez pas à la facture.";
	private final String FACTURE_DEMANDEE_NEXISTE_PAS = "La facture demandée n'existe pas.";
	private final String FACTURE_VALIDEE = "La facture est validée.";
	private final String PARTICIPATION_VALIDEE = "Votre participation est validée.";
	
	/**
	 * Création d'une nouvelle facture, en précisant le libellé et le montant total.
	 * L'utilisateur créateur est automatiquement ajouté comme premier participant.
	 * En cas de succès, retourne l'instance de la facture créée si succes.
	 * En cas d'echec, retourne null.
	 * @param libelle
	 * @param montant
	 * @param premierParticipant
	 * @param RetourTraitement
	 * @return Facture créée
	 */
	public Facture creerNouvelleFacture(String libelle, double montant, UtilisateurReel premierParticipant, RetourTraitement retourTraitement) {
		try {
			Facture nouvelleFacture = new Facture(LocalDate.now(), libelle, montant);
			nouvelleFacture.getParticipations().add(new Participation(nouvelleFacture, premierParticipant));
			FactureDAO.create(nouvelleFacture);
			if(nouvelleFacture.getId() == 0) {
				retourTraitement.definirResultat(EnumResultatTraitement.UNHANDLED_ERROR,"La création de facture a échoué.", null);
				return null;
			}
			retourTraitement.definirResultat(EnumResultatTraitement.OK, "Nouvelle facture créée.", null);
			return nouvelleFacture;
		}catch (Exception e) {
			retourTraitement.definirResultat(EnumResultatTraitement.UNHANDLED_ERROR, null, null);
			/* TODO: enregistrer l'exception ici*/
			return null;
		}
	}
	
	/**
	 * Récupération d'une facture complête (soit Facture et ses listes de LigneFacture, Participation et detteSurFacture).
	 * L'utilisateur utilisateurLecteur doit être participant de la facture.
	 * En cas d'echec, retourne null.
	 * @param idFacture
	 * @param utilisateurLecteur
	 * @param RetourTraitement
	 * @return Facture
	 */
	public Facture lireFacture(long idFacture, UtilisateurReel utilisateurLecteur, RetourTraitement retourTraitement) {
		Facture factureDemandee = FactureDAO.findById(Facture.class, idFacture);
		
		if(factureDemandee == null) {
			retourTraitement.definirResultat(EnumResultatTraitement.UNKNOWN_RESSOURCE, FACTURE_DEMANDEE_NEXISTE_PAS, null);
			return null;
		} else {
			if(!utilisateurEstParticipant(utilisateurLecteur,factureDemandee)) {
				retourTraitement.definirResultat(EnumResultatTraitement.ACCESS_FORBIDDEN, VOUS_NE_PARTICIPEZ_PAS_A_LA_FACTURE, null);
				return null;
			}
		}
		return factureDemandee;
	}
	
	/**
	 * Modification d'une facture (entête uniquement). Les lignes, participants et dettes ne sont pas traités.
	 * L'utilisateur utilisateurModificateur doit être participant de la facture.
	 * En cas d'echec, retourne false.
	 * @param factureEntete
	 * @param utilisateurModificateur
	 * @param RetourTraitement
	 * @return Facture complête à laquelle se rapporte l'entête. Celle-ci peut contenir des modifications faites par les autres participants.
	 */
	public Facture modifierFactureEntete(Facture factureEntete, UtilisateurReel utilisateurModificateur, RetourTraitement retourTraitement) {
		return null;
	}
	
	/**
	 * Ajout d'une nouvelle ligne vide à la facture demandée.
	 * L'utilisateur utilisateurAjouteur doit être participant de la facture.
	 * En cas d'échec, renvoit null.
	 * @param idFacture
	 * @param utilisateurAjouteur
	 * @param retourTraitement
	 * @return LigneFacture
	 */
	public LigneFacture nouvelleLigneFacture(long idFacture, UtilisateurReel utilisateurAjouteur, RetourTraitement retourTraitement) {
		LigneFacture nouvelleLigne = null;
		Facture facture = GenericDAO.findById(Facture.class, idFacture);
		if(facture == null) {
			retourTraitement.definirResultat(EnumResultatTraitement.UNKNOWN_RESSOURCE, FACTURE_DEMANDEE_NEXISTE_PAS, null);
		}
		
		if(retourTraitement.ok()) {
			if(!utilisateurEstParticipant(utilisateurAjouteur, facture)) {
				retourTraitement.definirResultat(EnumResultatTraitement.ACCESS_FORBIDDEN, VOUS_NE_PARTICIPEZ_PAS_A_LA_FACTURE, null);
			}
		}
		if(retourTraitement.ok()) {
			nouvelleLigne = new LigneFacture();
			nouvelleLigne.setFacture(facture);
			if (!GenericDAO.create(nouvelleLigne)) {
				retourTraitement.definirResultat(EnumResultatTraitement.UNHANDLED_ERROR,null,null);
			}
		}
		return nouvelleLigne;
	}
	
	/**
	 * Ajout/modification d'une liste de lignes de facture.
	 * Les lignes déjà existantes doivent appartenir à la facture idFacture.
	 * Les nouvelles lignes, avec id = 0, seront affectées à la facture idFacture. 
	 * L'utilisateur utilisateurModificateur doit être participant de la facture.
	 * En cas d'echec, retourne false. Le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param idFacture
	 * @param listeLignes
	 * @param utilisateurModificateur
	 * @param RetourTraitement
	 * @return Facture à laquelle se rapporte les lignes. Celle-ci peut contenir des modifications faites par les autres participants.
	 */
	public Facture modifierListeLigneFacture(long idFacture, Set<LigneFacture> listeLignes, UtilisateurReel utilisateurModificateur, RetourTraitement retourTraitement) {
		return null;
	}
	
	/**
	 * Suppression d'une liste de lignes de facture.
	 * L'utilisateur utilisateurModificateur doit être participant de la facture idFacture.
	 * En cas d'echec, retourne false.
	 * @param idFacture
	 * @param listeLignes
	 * @param utilisateurModificateur
	 * @param RetourTraitement
	 * @return Facture à laquelle se rapportent les lignes. Celle-ci peut contenir des modifications faites par les autres participants.
	 */
	public Facture supprimerListeLigneFacture(long idFacture, Set<LigneFacture> listeLignes, UtilisateurReel utilisateurModificateur, RetourTraitement retourTraitement) {
		return null;
	}
	
	/**
	 * Ajout/modification d'une liste de participations sur facture.
	 * Les participations déjà existantes doivent appartenir à la facture idFacture.
	 * Les nouvelles participations, avec id = 0, seront affectées à la facture idFacture.
	 * L'utilisateur utilisateurModificateur doit déjà être participant de la facture.
	 * En cas d'echec, retourne false. Le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param idFacture
	 * @param listeParticipations
	 * @param utilisateurModificateur
	 * @param RetourTraitement
	 * @return boolean
	 */
	public boolean modifierListeParticipation(long idFacture, Set<Participation> listeParticipations, UtilisateurReel utilisateurModificateur, RetourTraitement retourTraitement) {
		return false;
	}
	
	/**
	 * Suppression d'une liste de participations sur facture.
	 * Les participations doivent être existantes et appartenir à la facture dont l'identifiant est le paramètre idFacture.
	 * L'utilisateur utilisateurModificateur doit déjà être participant de la facture.
	 * En cas d'echec, retourne false.
	 * @param idFacture
	 * @param listeParticipations
	 * @param utilisateurModificateur
	 * @param RetourTraitement
	 * @return Facture
	 */
	public Facture supprimerListeParticipation(long idFacture, Set<Participation> listeParticipations, UtilisateurReel utilisateurModificateur, RetourTraitement retourTraitement) {
		return null;
	}
	
	/**
	 * Tester si la participation est validable.
	 * L'utilisateur utilisateurVerificateur doit appartenir à la participation.
	 * Si la participation est validable, retourne true.
	 * Si la participation n'est pas validable, retourne false, et le paramètre messageErreur contient le motif du refus.
	 * 
	 * @param participation
	 * @param utilisateurModificateur
	 * @param RetourTraitement
	 * @return boolean
	 */
	public boolean participationValidable(Participation participation, UtilisateurReel utilisateurVerificateur, RetourTraitement retourTraitement) {
		return false;
	}
	
	/**
	 * Valider une participation d'une facture.
	 * L'utilisateur utilisateurModificateur doit appartenir à la participation.
	 * Si la participation est validee avec succès, retourne la date de validation.
	 * Si la participation n'est pas validée, retourne null, et le paramètre messageErreur contient le motif du refus.
	 * En cas de validation de la participation, une tentative de validation de la facture correspondante est automatiquement effectuée.
	 * Le résultat de la tentative de validation de la facture n'a pas d'incidence sur le résultat de retour de cette méthode.
	 * Si la validation de la facture est effectuée avec succès, alors le paramètre FactureEstValidee renvoie true, sinon false.
	 * @param participation
	 * @param utilisateurModificateur
	 * @param FactureEstValidee (paramètre de retour)
	 * @param RetourTraitement
	 * @return LocalDate
	 */
	public LocalDate validerParticipation(Participation participation, UtilisateurReel utilisateurModificateur, Boolean FactureEstValidee, RetourTraitement retourTraitement) {
		FactureEstValidee = false;
		return null;
	}
	
	/**
	 * Tester si la facture est validable.
	 * L'utilisateur utilisateurVerificateur doit déjà être participant de la facture.
	 * Si la facture est validable, retourne true.
	 * Si la facture n'est pas validable, retourne false.
	 * @param idfacture
	 * @param utilisateurVerificateur
	 * @param RetourTraitement
	 * @return boolean
	 */
	public boolean factureValidable(long idfacture, UtilisateurReel utilisateurVerificateur, RetourTraitement retourTraitement) {
		return false;
	}
	
	/**
	 * Valider une facture.
	 * L'utilisateur utilisateurModificateur doit appartenir à la facture.
	 * Si la validée est validee avec succès, retourne la date de validation.
	 * Si la facture n'est pas validée, retourne null.
	 * @param idfacture
	 * @param utilisateurModificateur
	 * @param RetourTraitement
	 * @return LocalDate
	 */
	public LocalDate validerFacture(long idfacture, UtilisateurReel utilisateurModificateur, RetourTraitement retourTraitement) {
		return null;
	}
	
	public DataSet listeFactures(UtilisateurReel utilisateurDemandeur, RetourTraitement retourTraitement){
		StringBuilder Jpql = new StringBuilder();
		Jpql.append("SELECT distinct\n")
			.append(	"facture.id, facture.libelle, facture.dateFacture, facture.montant\n")
			.append("FROM\n")
			.append(	"UtilisateurReel utilisateurReel\n")
			.append(	"LEFT JOIN utilisateurReel.participations participation\n")
			.append(	"LEFT JOIN participation.facture facture\n")
			.append("WHERE\n")	
			.append(	"utilisateurReel.id = "+Long.toString(utilisateurDemandeur.getId())+"\n");
		DataSet dataSet = GenericDAO.executerSelectDataSetJPQL(Jpql.toString(),"ID;Caption;Date;Amount");
		return dataSet;
	}
	
	private boolean utilisateurEstParticipant(Utilisateur utilisateur, Facture facture) {
		return (participationUtilisateur(facture, utilisateur) != null);
	}
	
	private Participation participationUtilisateur(Facture facture, Utilisateur utilisateur) {
		if(facture != null) {
			Set<Participation> listeParticipations = facture.getParticipations();
			for(Participation participation : listeParticipations) {
				if(participation.getUtilisateur().getId() == utilisateur.getId()) {
					return participation;
				}
			}	
		}
		return null;
	}
	
}
