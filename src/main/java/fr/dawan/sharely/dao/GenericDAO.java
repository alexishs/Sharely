package fr.dawan.sharely.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.dawan.sharely.beans.DbObject;

public class GenericDAO {

	public static <T extends DbObject> void create(T entity) {
		if (entity.getId() == 0) {
			EntityManager entityManager = createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();

			try {
				// début de la transaction
				transaction.begin();

				// On insère la formation dans la BDD
				entityManager.persist(entity);

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

	public static <T extends DbObject> T findById(Class<T> clazz, long id) {

		EntityManager entityManager = createEntityManager();
		T entity = null;

		try {
			// On charge la formation depuis la BDD, selon son ID
			entity = entityManager.find(clazz, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			entityManager.close();
		}

		return entity;
	}

	public static <T extends DbObject> void update(T entity) {
		if (entity.getId() > 0) {
			EntityManager entityManager = createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();

			try {
				// début de la transaction
				transaction.begin();

				// On met à jour la formation
				entityManager.merge(entity);

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

	public static <T extends DbObject> void delete(Class<T> clazz, long id) {
		EntityManager entityManager = createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			// début de la transaction
			transaction.begin();

			T entity = entityManager.find(clazz, id);
			entityManager.remove(entity);

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

	public static <T extends DbObject> List<T> findAll(Class<T> clazz) {
		List<T> resultat = null;

		EntityManager em = createEntityManager();

		// on crée la requête
		TypedQuery<T> query = em.createQuery("SELECT entity FROM " + clazz.getName() + " entity", clazz);

		// on exécute la requête et on récupère le résultat
		resultat = query.getResultList();

		em.close();

		return resultat;
	}

	/**
	 * Permet de récupérer toutes les entrées pour une table données à partir d'une
	 * entrée, pour un nombre de résultat donné
	 * 
	 * @param clazz    : le type que l'on souhaite récupérer
	 * @param begin    : l'index du premier résultat
	 * @param nbResult : le nombre de résultat que l'on souhaite récupérer
	 * @return une liste d'entrées paginée
	 */
	public static <T extends DbObject> List<T> findAll(Class<T> clazz, int begin, int nbResult) {
		List<T> resultat = null;

		EntityManager em = createEntityManager();

		// on crée la requête
		TypedQuery<T> query = em.createQuery("SELECT entity FROM " + clazz.getName() + " entity", clazz);

		// on paramètre et on exécute la requête, et on récupère le résultat
		resultat = query.setFirstResult(begin) // On commence à ce numéro (begin) - au Nième résultat
				.setMaxResults(nbResult) // on charge nbResult résultats
				.getResultList();

		em.close();

		return resultat;
	}

	public static <T extends DbObject> void deleteAll(Class<T> clazz) {

		EntityManager em = createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		Query query = em.createQuery("Delete FROM " + clazz.getName());
		query.executeUpdate();
		
		transaction.commit();
		em.close();
	}
	
	private static Map<String, String> lireInfosConnexionMySql(){
		Map<String, String> mapInfos = null;
        try (InputStream inputStream = new FileInputStream("credentials.data")) {           
        	Properties proprietesFichier = new Properties();
        	proprietesFichier.load(inputStream);
        	mapInfos = new HashMap<String, String>();
        	mapInfos.put("javax.persistence.jdbc.user", proprietesFichier.getProperty("user"));
        	mapInfos.put("javax.persistence.jdbc.password", proprietesFichier.getProperty("password"));
        } catch (IOException ex) {
            // on ne fait rien. Le fichier peut ne pas exister.
        	// Dans ce cas, on renvoie null
        	mapInfos = null;
        }
        return mapInfos;
	}

	public static EntityManager createEntityManager() {
		
		Map<String, String> infosUtilisateurMySql = lireInfosConnexionMySql();
		EntityManagerFactory factory;
		
		// javax.persistence.jdbc.user
		// javax.persistence.jdbc.password
		if(infosUtilisateurMySql==null) {
			factory = Persistence.createEntityManagerFactory("sharely");
		}else {
			factory = Persistence.createEntityManagerFactory("sharely",infosUtilisateurMySql);
		}
		
		EntityManager entityManager = factory.createEntityManager();
		return entityManager;
	}
}
