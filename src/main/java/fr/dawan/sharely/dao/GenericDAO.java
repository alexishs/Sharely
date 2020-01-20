package fr.dawan.sharely.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.util.CollectionUtils;

import fr.dawan.sharely.beans.DbObject;

public class GenericDAO {

	public static <T extends DbObject> boolean create(T entity) {
		boolean resultat = true;
		if (entity.getId() == 0) {
			EntityManager entityManager = createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();

			try {
				transaction.begin();
				entityManager.persist(entity);
				transaction.commit();
			} catch (Exception ex) {
				transaction.rollback();
				ex.printStackTrace();
				resultat = false;
			} finally {
				entityManager.close();
			}
		}
		return resultat;
	}

	public static <T extends DbObject> T findById(Class<T> clazz, long id) {

		EntityManager entityManager = createEntityManager();
		T entity = null;

		try {
			entity = entityManager.find(clazz, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			entityManager.close();
		}

		return entity;
	}
	
	public static <T extends DbObject> T findByField(Class<T> clazz, String field, String fieldValue)
	{
		EntityManager entityManager = createEntityManager();

	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
	    Root<T> root = criteriaQuery.from(clazz);
	    criteriaQuery.select(root);

	    ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
	    criteriaQuery.where(criteriaBuilder.equal(root.get(field), params));

	    TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
	    query.setParameter(params, fieldValue);

	    List<T> queryResult = query.getResultList();

	    T returnObject = null;

	    if (!CollectionUtils.isEmpty(queryResult)) {
	        returnObject = queryResult.get(0);
	    }

	    return returnObject;
	}

	public static <T extends DbObject> boolean update(T entity) {
		boolean resultat = true;
		if (entity.getId() > 0) {
			EntityManager entityManager = createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			try {
				transaction.begin();
				entityManager.merge(entity);
				transaction.commit();
			} catch (Exception ex) {
				resultat = false;
				transaction.rollback();
				ex.printStackTrace();
			} finally {
				entityManager.close();
			}
		}
		return resultat;
	}

	public static <T extends DbObject> void delete(Class<T> clazz, long id) {
		EntityManager entityManager = createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			T entity = entityManager.find(clazz, id);
			entityManager.remove(entity);
			transaction.commit();
		} catch (Exception ex) {
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
	
	public static <T extends DbObject> List<T> executerSelectEntiteJPQL(String requeteJPQL,Class<T> clazz){
		EntityManager em = createEntityManager();
		List<T> resultat = null;
		TypedQuery<T> query = em.createQuery(requeteJPQL, clazz);
		resultat = query.getResultList();
		em.close();
		return resultat;
	}
	
	public static DataSet executerSelectDataSetJPQL(String requeteJPQL,String libelles){
		EntityManager em = createEntityManager();
		DataSet dataSet = new DataSet(libelles);
		TypedQuery<Tuple> query = em.createQuery(requeteJPQL, Tuple.class);
		dataSet.SetEnregistrements(query.getResultList());
		em.close();
		return dataSet;
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
		
		if(infosUtilisateurMySql==null) {
			factory = Persistence.createEntityManagerFactory("sharely");
		}else {
			factory = Persistence.createEntityManagerFactory("sharely",infosUtilisateurMySql);
		}
		
		EntityManager entityManager = factory.createEntityManager();
		return entityManager;
	}
}
