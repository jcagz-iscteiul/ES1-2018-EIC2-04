package JUnitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import RedesSociais.EmailPost;
import RedesSociais.Gmail;
import RedesSociais.PostGeral;

public class GmailTest {
	
	private Gmail gm;
	
	@Before
	public void setUp() {
		this.gm = new Gmail();
	}

	
	@Test
	public void test_addEmailsToArray() {
		for(PostGeral post: gm.getLista_posts()) {
			assertFalse("Existe um email a null!", post.equals(null));
		}
	}
	
	@Test
	public void test_getMessageContent() {
		for(PostGeral post: gm.getLista_posts()) {
			assertFalse("Conteudo null!", post.getConteudo().equals(null));
		}
	}
	
	@Test
	public void test_getEmails() {
		assertNotNull(gm.getLista_posts());
	}
	
	@Test
	public void test_palavraChave() {
		ArrayList<PostGeral> lista = gm.palavraChave("EIC", gm.getLista_posts());
		for(PostGeral post: lista) {
			assertNotNull(post);
		}
	}
	
	@Test
	public void test_vinteQuatroHoras() {
		ArrayList<PostGeral> lista = gm.vinteQuatroHoras(gm.getLista_posts());
		assertTrue(lista.size() >= 0);
	}
	
	@Test
	public void test_getPostEspecifico() {
		String titulo = "Wed Nov 28 15:36:08 GMT 2018 - Alerta de segurança";
		PostGeral post = gm.getPostEspecifico(titulo);
		assertTrue(((EmailPost)post).emailPostPreview().equals(titulo));
	}
	
	@Test
	public void viraArrayList() {
		ArrayList<PostGeral> lista = gm.viraLista(gm.getLista_posts());
		for(PostGeral post: lista) {
			assertNotNull(post);
		}
	}
	
	@Test
	public void test_refrescarConteudo() {
		gm.refrescarConteudo();
		for(PostGeral post: gm.getLista_posts()) {
			assertNotNull(post);
		}
	}
	
	

}
