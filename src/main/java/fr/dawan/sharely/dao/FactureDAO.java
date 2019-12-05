package fr.dawan.sharely.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

				// pour chaque session de la formation
				for (LigneFacture lignefacture : facture.getLignesFactures()) {
					// insertion de la session de formation
					lignefacture.setFacture(facture);
					entityManager.persist(lignefacture);
					System.out.println("Insertion de " + lignefacture.toString());
					
				}

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
