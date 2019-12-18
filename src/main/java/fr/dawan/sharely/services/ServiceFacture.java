package fr.dawan.sharely.services;

import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Service;

import fr.dawan.sharely.beans.Facture;
import fr.dawan.sharely.beans.LigneFacture;
import fr.dawan.sharely.beans.Participation;
import fr.dawan.sharely.beans.UtilisateurReel;


@Service
public class ServiceFacture {
	
	/**
	 * Création d'une nouvelle facture, en précisant le libellé et le montant total.
	 * L'utilisateur créateur est automatiquement ajouté comme premier participant.
	 * En cas de succès, retourne l'instance de la facture créée si succes.
	 * En cas d'echec, retourne null. Le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param libelle
	 * @param montant
	 * @param premierParticipant
	 * @param messageErreur
	 * @return Facture
	 */
	public Facture creerNouvelleFacture(String libelle, double montant, UtilisateurReel premierParticipant, String messageErreur) {
		return null;
	}
	
	/**
	 * Récupération d'une facture complête (soit Facture et ses listes de LigneFacture, Participation et detteSurFacture).
	 * L'utilisateur utilisateurLecteur doit être participant de la facture.
	 * En cas d'echec, retourne null. Le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param idFacture
	 * @param utilisateurLecteur
	 * @param messageErreur
	 * @return Facture
	 */
	public Facture lireFactureComplete(long idFacture, UtilisateurReel utilisateurLecteur, String messageErreur) {
		return null;
	}
	
	/**
	 * Modification d'une facture (entête uniquement). Les lignes, participants et dettes ne sont pas traités.
	 * L'utilisateur utilisateurModificateur doit être participant de la facture.
	 * En cas d'echec, retourne false. Le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param factureEntete
	 * @param utilisateurModificateur
	 * @param messageErreur
	 * @return boolean
	 */
	public boolean modifierFactureEntete(Facture factureEntete, UtilisateurReel utilisateurModificateur, String messageErreur) {
		return false;
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
	 * @param messageErreur
	 * @return boolean
	 */
	public boolean modifierListeLigneFacture(long idFacture, Set<LigneFacture> listeLignes, UtilisateurReel utilisateurModificateur, String messageErreur) {
		return false;
	}
	
	/**
	 * Suppression d'une liste de lignes de facture.
	 * L'utilisateur utilisateurModificateur doit être participant des factures des lignes de facture.
	 * En cas d'echec, retourne false. Le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param listeLignes
	 * @param utilisateurModificateur
	 * @param messageerreur
	 * @return boolean
	 */
	public boolean supprimerListeLigneFacture(Set<LigneFacture> listeLignes, UtilisateurReel utilisateurModificateur, String messageerreur) {
		return false;
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
	 * @param messageErreur
	 * @return boolean
	 */
	public boolean modifierListeParticipation(long idFacture, Set<Participation> listeParticipations, UtilisateurReel utilisateurModificateur, String messageErreur) {
		return false;
	}
	
	/**
	 * Suppression d'une liste de participations sur facture.
	 * Les participations doivent être existantes et appartenir à la facture dont l'identifiant est le paramètre idFacture.
	 * L'utilisateur utilisateurModificateur doit déjà être participant de la facture.
	 * En cas d'echec, retourne false. Le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param idFacture
	 * @param listeParticipations
	 * @param utilisateurModificateur
	 * @param messageErreur
	 * @return boolean
	 */
	public boolean supprimerListeParticipation(long idFacture, Set<Participation> listeParticipations, UtilisateurReel utilisateurModificateur, String messageErreur) {
		return false;
	}
	
	/**
	 * Tester si la participation est validable.
	 * L'utilisateur utilisateurVerificateur doit appartenir à la participation.
	 * Si la participation est validable, retourne true.
	 * Si la participation n'est pas validable, retourne false, et le paramètre messageErreur contient le motif du refus.
	 * 
	 * @param participation
	 * @param utilisateurModificateur
	 * @param messageErreur
	 * @return boolean
	 */
	public boolean participationValidable(Participation participation, UtilisateurReel utilisateurVerificateur, String messageErreur) {
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
	 * @param FactureEstValidee
	 * @param messageErreur
	 * @return Date
	 */
	public Date validerParticipation(Participation participation, UtilisateurReel utilisateurModificateur, Boolean FactureEstValidee, String messageErreur) {
		FactureEstValidee = false;
		return null;
	}
	
	/**
	 * Tester si la facture est validable.
	 * L'utilisateur utilisateurVerificateur doit déjà être participant de la facture.
	 * Si la facture est validable, retourne true.
	 * Si la facture n'est pas validable, retourne false, et le paramètre messageErreur contient le motif du refus.
	 * @param idfacture
	 * @param utilisateurVerificateur
	 * @param messageErreur
	 * @return boolean
	 */
	public boolean factureValidable(long idfacture, UtilisateurReel utilisateurVerificateur, String messageErreur) {
		return false;
	}
	
	/**
	 * Valider une facture.
	 * L'utilisateur utilisateurModificateur doit appartenir à la facture.
	 * Si la validée est validee avec succès, retourne la date de validation.
	 * Si la facture n'est pas validée, retourne null, et le paramètre messageErreur contient le motif du refus.
	 * @param idfacture
	 * @param utilisateurModificateur
	 * @param messageErreur
	 * @return Date
	 */
	public Date validerFacture(long idfacture, UtilisateurReel utilisateurModificateur, String messageErreur) {
		return null;
	}
	

}
