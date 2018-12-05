package jUnitTests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import bda_redesSociais.Destaques;
import bda_redesSociais.Facebook;
import bda_redesSociais.Gmail;
import bda_redesSociais.PostGeral;
import bda_redesSociais.TwitterObject;

public class DestaquesTest {
	
	private Destaques destaques;
	
	@Before
	public void setUp() {
		Gmail gm = new Gmail();
		Facebook fb = new Facebook();
		TwitterObject tw = new TwitterObject();
		this.destaques = new Destaques(gm.getLista_posts(), fb.getLista_posts(), tw.getLista_posts());
	}
	
	@Test
	public void test_getDestaques() {
		for(PostGeral post: destaques.getDestaques()) {
			assertNotNull(post);
		}
	}
	
	@Test
	public void test_setDestaques() { 
		destaques.setDestaques(destaques.getDestaques());
		for(PostGeral post: destaques.getDestaques()) {
			assertNotNull(post);
		}
		
	}
	
	@Test
	public void test_palavraChave() {
		ArrayList<PostGeral> lista = destaques.palavraChave("EIC", destaques.getDestaques());
		for(PostGeral post: lista) {
			assertNotNull(post);
		}
	}
	
	@Test
	public void test_vinteQuatroHoras() {
		ArrayList<PostGeral> lista = destaques.vinteQuatroHoras(destaques.getDestaques());
		assertTrue(lista.size() >= 0);
	}
	
	@Test
	public void test_getPostEspecifico() {
		String titulo = "[Facebook] Fri Oct 26 14:42:37 BST 2018 - Fri Oct 26 14:42:37 BST 2018 - [ES] Vim apresentar o ... ";
		PostGeral post = destaques.getPostEspecifico(titulo);
		assertTrue(post.createTitulo().equals(titulo));
	}
	
	@Test
	public void test_viraLista() {
		ArrayList<PostGeral> lista = destaques.viraLista(destaques.getDestaques());
		assertTrue(lista.size() == destaques.getDestaques().size());
	}
	
	
	
	
	
	
}
