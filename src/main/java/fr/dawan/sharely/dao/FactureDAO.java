package fr.dawan.sharely.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import fr.dawan.sharely.beans.Facture;
import fr.dawan.sharely.beans.LigneFacture;

public class FactureDAO extends GenericDAO {

	public static void create(Facture facture) {
		if (facture.getId() == 0) {
			EntityManager entityManager = createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();

			try {
				// début de la transaction
				transaction.begin();

				// On insère la formation dans la BDD
				entityManager.persist(facture);
				System.out.println("Insertion de " + facture.toString());

				/* pas nécessaire à la création de facture
				for (LigneFacture lignefacture : facture.getLignesFactures()) {
					lignefacture.setFacture(facture);
					entityManager.persist(lignefacture);
					System.out.println("Insertion de " + lignefacture.toString());
				}
				*/

				// on commit tout ce qui s'est fait dans la transaction
				transaction.commit();
			} catch (Exception ex) {
				// en cas d'erreur, on effectue un rollback
				transaction.rollback();
				ex.printStackTrace();
			} finally {
				entityManager.close();
			}
		}
	}
}
