package fr.dawan.sharely;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.dawan.sharely.beans.Facture;
import fr.dawan.sharely.beans.LigneFacture;
import fr.dawan.sharely.beans.Utilisateur;
import fr.dawan.sharely.beans.UtilisateurReel;
import fr.dawan.sharely.dao.FactureDAO;
import fr.dawan.sharely.dao.GenericDAO;

public class TestUtilisateur {

	private final static String USER_NOM = "Le Jeune";
	private final static String USER_PRENOM = "Hél";
	private final static String USER_EMAIL = "hlejeune@dawan.fr";

	private static long idFacture;
	private static long idUser;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("INITIALISATION BEFORE CLASS");
		UtilisateurReel user = new UtilisateurReel(USER_NOM, USER_PRENOM, USER_EMAIL);
		
		Facture facture = new Facture(new Date(), "Restaurant la Houblonnière", 35);
		
		LigneFacture lf1 = new LigneFacture("Bouteille de coca 2L", 5.90);
		LigneFacture lf2 = new LigneFacture("Pizza royale", 15.90);
		LigneFacture lf3 = new LigneFacture("Penne Di Bufala", 11.50);
		LigneFacture lf4 = new LigneFacture("Salade Vegan", 7.40);
		
		facture.addLigneFacture(lf1);
		facture.addLigneFacture(lf2);
		facture.addLigneFacture(lf3);
		facture.addLigneFacture(lf4);

		FactureDAO.create(facture);
		idFacture = facture.getId();
		
		user.addFacture(facture);
		GenericDAO.create(user);
		idUser = user.getId();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		GenericDAO.delete(Facture.class, idFacture);
		GenericDAO.delete(Utilisateur.class, idUser);
	}

	@Test
	public void testUtilisateur() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNom() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPrenom() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDettesAPayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNom() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPrenom() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEmail() {
		fail("Not yet implemented");
	}

}
