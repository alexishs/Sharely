package fr.dawan.sharely;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.dawan.sharely.beans.UtilisateurReel;
import fr.dawan.sharely.dao.GenericDAO;

public class TestUtilisateur {

	private final static String USER_NOM = "Le Jeune";
	private final static String USER_PRENOM = "Hél";
	private final static String USER_EMAIL = "hlejeune@dawan.fr";

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("INITIALISATION BEFORE CLASS");
		UtilisateurReel user = new UtilisateurReel(USER_NOM, USER_PRENOM, USER_EMAIL);
		
		GenericDAO.create(user);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
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
