package fr.dawan.sharely.services;

import org.springframework.stereotype.Service;

import fr.dawan.sharely.beans.UtilisateurReel;

@Service
public class ServiceUtilisateur {
	
	/*
	 * Enregistrement d'un nouvel utilisateur.
	 * En cas de succès, un email contenant une URL de confirmation d'inscription est envoyé.
	 * En cas d'echec,le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 */
	public boolean demandeInscriptionUtilisateur(UtilisateurReel nouvelUtilisateur,String messageErreur) {
		return false;
	}
	
	/*
	 * Validation de l'inscription d'un utilisateur suite à l'activation de l'URL de confirmation reçue par mail.
	 * En cas d'echec,le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 */
	public boolean validationInscriptionUtilisateur(UtilisateurReel utilisateur,String messageErreur) {
		return false;
	}

	/*
	 * Connexion d'un utilisateur.
	 * En cas de succès, retourne l'instance de l'utilisateur.
	 * En cas d'echec,retourne null, et le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 */
	public UtilisateurReel connexion(String email,String motDePasse,String messageErreur) {
		return null;
	}
	
	/*
	 * Demande d'envoi d'un email pour récupération d'un mot de passe oublié.
	 * En cas de succès, retourne true.
	 * En cas d'echec,retourne false, et le paramètre messageErreur peut contenir un message pour l'utilisateur.
	 */
	public boolean envoiEmailRecuperationMotDePasse(String emailRecuperation,String messageErreur) {
		return false;
	}
	
}
