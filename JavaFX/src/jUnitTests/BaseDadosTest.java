package jUnitTests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import bda_baseDeDados.BaseDados;
import bda_redesSociais.PostGeral;

public class BaseDadosTest {
	
	private BaseDados db;
	
	@Before
	public void setUp() {
		this.db = new BaseDados();
	}
	
	@Test
	public void test_createSqlValues() {
		String s = db.createSqlValues(1, "Facebook", "teste", "a testar", "Fri Oct 26 14:42:37 BST 2018");
		assertTrue(s.equals("VALUES (1, 'Facebook', 'teste', 'a testar', 'Fri Oct 26 14:42:37 BST 2018' );"));
	}
	
	@Test
	public void test_createSqlValuesGmail() {
		String s = db.createSqlValuesGmail(1, "teste", "a testar", "Fri Oct 26 14:42:37 BST 2018", "ISCTE", "MARTE");
		assertTrue(s.equals("VALUES (1, 'Gmail', 'teste', 'a testar', 'Fri Oct 26 14:42:37 BST 2018', 'ISCTE', 'MARTE' );"));
	}
	
	@Test
	public void test_getFacebookPosts() {
		ArrayList<PostGeral> lista = db.getFacebookPosts();
		for(PostGeral post: lista) {
			assertNotNull(post);
		}
	}
	
	@Test
	public void test_getTwitterPosts() {
		ArrayList<PostGeral> lista = db.getTwitterPosts();
		for(PostGeral post: lista) {
			assertNotNull(post);
		}
	}

	@Test
	public void test_getGmailPosts() {
		ArrayList<PostGeral> lista = db.getGmailPosts();
		for(PostGeral post: lista) {
			assertNotNull(post);
		}
	}
	
}
