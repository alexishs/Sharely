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
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param motDePasse
	 * @param retourTraitement
	 * @return boolean
	 */
	public boolean demandeInscriptionUtilisateur(String nom, String prenom, String email, String motDePasse, RetourTraitement retourTraitement) {
		
		final String ENREGISTREMENT_IMPOSSIBLE = "Enregistrement de l'inscription impossible";
		final String REGEX_EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		
		retourTraitement.definirResultat(EnumResultatTraitement.OK, null, null);
		if ((nom == null) || (nom.equals(""))){
			retourTraitement.definirResultat(EnumResultatTraitement.REQUEST_REFUSED, ENREGISTREMENT_IMPOSSIBLE, "Le nom n'est pas renseigné.");
		}
		if ((prenom == null) || (prenom.equals(""))){
			retourTraitement.definirResultat(EnumResultatTraitement.REQUEST_REFUSED, ENREGISTREMENT_IMPOSSIBLE, "Le prénom n'est pas renseigné.");
		}	
		if(!email.matches(REGEX_EMAIL)) {
			retourTraitement.definirResultat(EnumResultatTraitement.REQUEST_REFUSED, ENREGISTREMENT_IMPOSSIBLE, "L'adresse email n'est pas valide.");
		}
		if(retourTraitement.ok()) {
			UtilisateurReel utilisateurDejaExistant = GenericDAO.findByField(UtilisateurReel.class, "email", email);
			if(utilisateurDejaExistant != null) {
				retourTraitement.definirResultat(EnumResultatTraitement.REQUEST_REFUSED, ENREGISTREMENT_IMPOSSIBLE, "Un compte est déjà enregistré avec cette adresse email.");
			}
		}
		if(retourTraitement.ok()) {
			if ((motDePasse == null) || (motDePasse.equals(""))){
				retourTraitement.definirResultat(EnumResultatTraitement.REQUEST_REFUSED, ENREGISTREMENT_IMPOSSIBLE, "Le mot de passe n'est pas renseigné.");
			} else {
				if(!(motDePasse.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$"))) {
					retourTraitement.definirResultat(EnumResultatTraitement.REQUEST_REFUSED, ENREGISTREMENT_IMPOSSIBLE,null);
					retourTraitement
						.ajouterCommantaire("Le mot de passe doit contenir :")
						.ajouterCommantaire("- au moins 8 caractères")
						.ajouterCommantaire("- au moins une lettre en minuscule")
						.ajouterCommantaire("- au moins une lettre en majuscule")
						.ajouterCommantaire("- au moins un caractère spécial parmis les caractères suivants : ! @ # $ % ^ &");
				}
			}
		}
		if(retourTraitement.ok()) {
			UtilisateurReel nouvelUtilisateur = new UtilisateurReel(nom,prenom, email);
			nouvelUtilisateur.setPassword(BCrypt.hashpw(motDePasse, BCrypt.gensalt()));
			nouvelUtilisateur.setValide(false);
			if(!GenericDAO.create(nouvelUtilisateur)) {
				retourTraitement.definirResultat(EnumResultatTraitement.UNHANDLED_ERROR,"Une erreur est survenue lors de l'enregistrement des informations. Veuillez réessayer ultérieurement",null);
			}
		}
		return retourTraitement.ok();
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
			retourTraitement.definirResultat(EnumResultatTraitement.REQUEST_REFUSED, "Connexion impossible", "Utilisateur ou mot de passe invalide");
		}else {
			retourTraitement.definirResultat(EnumResultatTraitement.OK, null, null);
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
