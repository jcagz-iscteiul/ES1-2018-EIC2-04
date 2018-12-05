package jUnitTests;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import bda_redesSociais.FacebookPost;

public class FacebookPostTest {
	
	private FacebookPost post;
	
	@Before
	public void setUp() {
		Date data = new Date("Wed Nov 21 09:39:07 GMT 2018");
		this.post = new FacebookPost(1,data,"Estou a testar o FacebookPost", "Teste");
	}
	
	@Test
	public void test_getData() {
		Date data = new Date("Wed Nov 21 09:39:07 GMT 2018");
		assertTrue(post.getData().equals(data));
	}
	
	@Test
	public void test_getTitulo() {
		assertTrue(post.getTitulo().equals("Teste"));
	}
	
	@Test
	public void test_getId() {
		assertTrue(post.getId() == 1);
	}
	
	@Test
	public void test_createTitulo() {
		String s = "[" + post.getRedeSocial() + "] " + post.getData() + " - " + post.getTitulo();
		assertTrue(post.createTitulo().equals(s));
	}
	
	@Test
	public void test_compareTo() {
		Date data = new Date("Wed Nov 21 09:39:07 GMT 2018");
		FacebookPost post2 = new FacebookPost(1,data,"Estou a testar o FacebookPost", "Teste");
		int i = post.compareTo(post2);
		assertTrue(i != -2);
	}

}
