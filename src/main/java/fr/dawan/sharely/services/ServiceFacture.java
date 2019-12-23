package fr.dawan.sharely.services;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.sharely.beans.Facture;
import fr.dawan.sharely.beans.LigneFacture;
import fr.dawan.sharely.beans.Participation;
import fr.dawan.sharely.beans.UtilisateurReel;
import fr.dawan.sharely.dao.FactureDAO;


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
	 * @param messageErreur (paramètre de retour)
	 * @return Facture créée
	 */
	public Facture creerNouvelleFacture(String libelle, double montant, UtilisateurReel premierParticipant, StringBuilder messageErreur) {
		Facture nouvelleFacture = new Facture(LocalDate.now(), libelle, montant);
		nouvelleFacture.getParticipations().add(new Participation(nouvelleFacture, premierParticipant));
		FactureDAO.create(nouvelleFacture);
		if(nouvelleFacture.getId() == 0) {
			messageErreur.append("La création de facture a échoué.");
			return null;
		}
		return nouvelleFacture;
	}
	
	/**
	 * Récupération d'une facture complête (soit Facture et ses listes de LigneFacture, Participation et detteSurFacture).
	 * L'utilisateur utilisateurLecteur doit être participant de la facture.
	 * En cas d'echec, retourne null. Le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param idFacture
	 * @param utilisateurLecteur
	 * @param messageErreur (paramètre de retour)
	 * @return Facture
	 */
	public Facture lireFacture(long idFacture, UtilisateurReel utilisateurLecteur, StringBuilder messageErreur) {
		return null;
	}
	
	/**
	 * Modification d'une facture (entête uniquement). Les lignes, participants et dettes ne sont pas traités.
	 * L'utilisateur utilisateurModificateur doit être participant de la facture.
	 * En cas d'echec, retourne false. Le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param factureEntete
	 * @param utilisateurModificateur
	 * @param messageErreur (paramètre de retour)
	 * @return Facture complête à laquelle se rapporte l'entête. Celle-ci peut contenir des modifications faites par les autres participants.
	 */
	public Facture modifierFactureEntete(Facture factureEntete, UtilisateurReel utilisateurModificateur, StringBuilder messageErreur) {
		return null;
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
	 * @param messageErreur (paramètre de retour)
	 * @return Facture à laquelle se rapporte les lignes. Celle-ci peut contenir des modifications faites par les autres participants.
	 */
	public Facture modifierListeLigneFacture(long idFacture, Set<LigneFacture> listeLignes, UtilisateurReel utilisateurModificateur, StringBuilder messageErreur) {
		return null;
	}
	
	/**
	 * Suppression d'une liste de lignes de facture.
	 * L'utilisateur utilisateurModificateur doit être participant de la facture idFacture.
	 * En cas d'echec, retourne false. Le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param idFacture
	 * @param listeLignes
	 * @param utilisateurModificateur
	 * @param messageErreur (paramètre de retour)
	 * @return Facture à laquelle se rapportent les lignes. Celle-ci peut contenir des modifications faites par les autres participants.
	 */
	public Facture supprimerListeLigneFacture(long idFacture, Set<LigneFacture> listeLignes, UtilisateurReel utilisateurModificateur, StringBuilder messageerreur) {
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
	 * @param messageErreur (paramètre de retour)
	 * @return boolean
	 */
	public boolean modifierListeParticipation(long idFacture, Set<Participation> listeParticipations, UtilisateurReel utilisateurModificateur, StringBuilder messageErreur) {
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
	 * @param messageErreur (paramètre de retour)
	 * @return Facture
	 */
	public Facture supprimerListeParticipation(long idFacture, Set<Participation> listeParticipations, UtilisateurReel utilisateurModificateur, StringBuilder messageErreur) {
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
	 * @param messageErreur (paramètre de retour)
	 * @return boolean
	 */
	public boolean participationValidable(Participation participation, UtilisateurReel utilisateurVerificateur, StringBuilder messageErreur) {
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
	 * @param messageErreur (paramètre de retour)
	 * @return LocalDate
	 */
	public LocalDate validerParticipation(Participation participation, UtilisateurReel utilisateurModificateur, Boolean FactureEstValidee, StringBuilder messageErreur) {
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
	 * @param messageErreur (paramètre de retour)
	 * @return boolean
	 */
	public boolean factureValidable(long idfacture, UtilisateurReel utilisateurVerificateur, StringBuilder messageErreur) {
		return false;
	}
	
	/**
	 * Valider une facture.
	 * L'utilisateur utilisateurModificateur doit appartenir à la facture.
	 * Si la validée est validee avec succès, retourne la date de validation.
	 * Si la facture n'est pas validée, retourne null, et le paramètre messageErreur contient le motif du refus.
	 * @param idfacture
	 * @param utilisateurModificateur
	 * @param messageErreur (paramètre de retour)
	 * @return LocalDate
	 */
	public LocalDate validerFacture(long idfacture, UtilisateurReel utilisateurModificateur, StringBuilder messageErreur) {
		return null;
	}
	

}
