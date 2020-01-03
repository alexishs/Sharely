package fr.dawan.sharely.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import fr.dawan.sharely.beans.UtilisateurReel;
import fr.dawan.sharely.dao.GenericDAO;
import fr.dawan.sharely.enums.EnumResultatTraitement;

@Service
public class ServiceUtilisateur {
	

	/**
	 * Enregistrement d'un nouvel utilisateur.
	 * En cas de succès, un email contenant une URL de confirmation d'inscription est envoyé.
	 * En cas d'echec,retourne false
	 * @param nouvelUtilisateur
	 * @param retourTraitement
	 * @return boolean
	 */
	public boolean demandeInscriptionUtilisateur(UtilisateurReel nouvelUtilisateur,RetourTraitement retourTraitement) {
		return false;
	}
	
	/**
	 * Validation de l'inscription d'un utilisateur suite à l'activation de l'URL de confirmation reçue par mail.
	 * En cas d'echec,retourne false.
	 * @param utilisateur
	 * @param retourTraitement
	 * @return boolean
	 */
	public boolean validationInscriptionUtilisateur(UtilisateurReel utilisateur,RetourTraitement retourTraitement) {
		return false;
	}

	/**
	 * Connexion d'un utilisateur.
	 * En cas de succès, retourne l'instance de l'utilisateur.
	 * En cas d'echec,retourne null.
	 * @param emailRecuperation
	 * @param retourTraitement
	 * @return boolean
	 */
	public UtilisateurReel connexion(String email,String motDePasse,RetourTraitement retourTraitement) {
		//System.out.println("Mdp encrypté : "+BCrypt.hashpw(motDePasse, BCrypt.gensalt()));
		UtilisateurReel utilisateur = GenericDAO.findByField(UtilisateurReel.class, "email", email);
		if(utilisateur != null) {
			if(!BCrypt.checkpw(motDePasse,utilisateur.getPassword())) {
				utilisateur = null;
			}
		}
		if(utilisateur == null) {
			retourTraitement.definirResultat(EnumResultatTraitement.ECHEC_METIER, "Connexion impossible");
			retourTraitement.getCommentairesUtilisateur().add("Utilisateur ou mot de passe invalide");
		}else {
			retourTraitement.definirResultat(EnumResultatTraitement.OK, null);
		}
		return utilisateur;
	}
	
	/**
	 * Demande d'envoi d'un email pour récupération d'un mot de passe oublié.
	 * En cas de succès, retourne true.
	 * En cas d'echec.
	 * @param emailRecuperation
	 * @param retourTraitement
	 * @return boolean
	 */
	public boolean envoiEmailRecuperationMotDePasse(String emailRecuperation,RetourTraitement retourTraitement) {
		return false;
	}
	
}
