package fr.dawan.sharely.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import fr.dawan.sharely.beans.Facture;
import fr.dawan.sharely.beans.LigneFacture;
import fr.dawan.sharely.beans.Participation;
import fr.dawan.sharely.beans.UtilisateurReel;

/*
 * 
7 – Rechercher un ou plusieurs participant(s)

*/

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
	 * Les lignes déjà existantes doivent appartenir à la facture dont l'identifiant est le paramètre idFacture.
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
	 * Les lignes doivent être existantes et appartenir à la facture dont l'identifiant est le paramètre idFacture.
	 * L'utilisateur utilisateurModificateur doit être participant de la facture.
	 * En cas d'echec, retourne false. Le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param idFacture
	 * @param listeLignes
	 * @param utilisateurModificateur
	 * @param messageerreur
	 * @return boolean
	 */
	public boolean supprimerListeLigneFacture(long idFacture, Set<LigneFacture> listeLignes, UtilisateurReel utilisateurModificateur, String messageerreur) {
		return false;
	}
	
	/**
	 * Ajout/modification d'une liste de participations sur facture.
	 * Les participations déjà existantes doivent appartenir à la facture dont l'identifiant est le paramètre idFacture.
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
	 * Tester si la facture est validable.
	 * L'utilisateur utilisateurVerificateur doit déjà être participant de la facture.
	 * Si la facture est validable, retourne true.
	 * Si la facture n'est pas validable, retourne false, et le paramètre messageErreur contient le motif du refus.
	 * @param factureATester
	 * @param utilisateurVerificateur
	 * @param messageErreur
	 * @return boolean
	 */
	public boolean factureValidable(Facture factureATester, UtilisateurReel utilisateurVerificateur, String messageErreur) {
		return false;
	}
	
	
	public boolean validerFacture(Facture factureAValider, UtilisateurReel utilisateurModificateur, String messageErreur) {
		return false;
	}
	

}
