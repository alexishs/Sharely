package fr.dawan.sharely.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import fr.dawan.sharely.beans.UtilisateurReel;
import fr.dawan.sharely.dao.GenericDAO;

@Service
public class ServiceUtilisateur {
	

	/**
	 * Enregistrement d'un nouvel utilisateur.
	 * En cas de succès, un email contenant une URL de confirmation d'inscription est envoyé.
	 * En cas d'echec,le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param nouvelUtilisateur
	 * @param messageErreur
	 * @return boolean
	 */
	public boolean demandeInscriptionUtilisateur(UtilisateurReel nouvelUtilisateur,StringBuilder messageErreur) {
		return false;
	}
	
	/**
	 * Validation de l'inscription d'un utilisateur suite à l'activation de l'URL de confirmation reçue par mail.
	 * En cas d'echec,le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param utilisateur
	 * @param messageErreur
	 * @return boolean
	 */
	public boolean validationInscriptionUtilisateur(UtilisateurReel utilisateur,StringBuilder messageErreur) {
		return false;
	}

	/**
	 * Connexion d'un utilisateur.
	 * En cas de succès, retourne l'instance de l'utilisateur.
	 * En cas d'echec,retourne null, et le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param emailRecuperation
	 * @param messageErreur
	 * @return boolean
	 */
	public UtilisateurReel connexion(String email,String motDePasse,StringBuilder messageErreur) {
		System.out.println("Mdp encrypté : "+BCrypt.hashpw(motDePasse, BCrypt.gensalt()));
		UtilisateurReel utilisateur = GenericDAO.findByField(UtilisateurReel.class, "email", email);
		if(utilisateur != null) {
			if(!BCrypt.checkpw(motDePasse,utilisateur.getPassword())) {
				utilisateur = null;
			}
		}
		if(utilisateur == null) {
			messageErreur.append("Utilisateur ou mot de passe invalide");
		}
		return utilisateur;
	}
	
	/**
	 * Demande d'envoi d'un email pour récupération d'un mot de passe oublié.
	 * En cas de succès, retourne true.
	 * En cas d'echec,retourne false, et le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 * @param emailRecuperation
	 * @param messageErreur
	 * @return boolean
	 */
	public boolean envoiEmailRecuperationMotDePasse(String emailRecuperation,StringBuilder messageErreur) {
		return false;
	}
	
}
