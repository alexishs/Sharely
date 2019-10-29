package fr.dawan.sharely;

import java.util.ArrayList;

import fr.dawan.sharely.enums.EnumErreurMetier;

public class ErreursMetier {
	
	public class Erreur{
		
		private EnumErreurMetier code;
		private String libelle;
		
		
		@Override
		public String toString() {
			return "Erreur [code=" + code + ", libelle=" + libelle + "]";
		}

		protected Erreur(EnumErreurMetier code, String libelle) {
			this.code = code;
			this.libelle = libelle;
			// code tempo debut
			System.out.println(this);
			// code tempo fin
			
		}
		
	}
	
	private ArrayList<Erreur> erreurs = new ArrayList<Erreur>();
	
	public ArrayList<Erreur> getErreurs() {
		return erreurs;
	}

	public ErreursMetier ajouterErreur(EnumErreurMetier code, String libelle) {
		erreurs.add(new Erreur(code,libelle));
		return this;
	}
	
	public boolean erreursPresentes() {
		return (erreurs.size()>0);
	}

}

