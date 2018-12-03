package JUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import RedesSociais.Facebook;
import RedesSociais.FacebookPost;
import RedesSociais.PostGeral;

public class FacebookTest {

	private Facebook fb;
	
	@Before
	public void setUp() throws ParserConfigurationException, SAXException, IOException {
		this.fb = new Facebook();
	}

	@Test
	public void test_addPostsToArray() throws ParserConfigurationException, SAXException, IOException {

		for(PostGeral post: fb.getLista_posts()) {
			assertFalse("Existe um post null", post.equals(null));
		}
		
	}
	
	@Test
	public void test_createPostPreview() {
		for(PostGeral post: fb.getLista_posts()) {
			assertFalse("Existe um post com titulo null!", post.getTitulo().equals(null));
		}
	}
	
	@Test
	public void test_getPosts() {
		boolean flag = false;
		if(!(fb.getLista_posts().equals(null))) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag == true);
	}
	
	@Test
	public void test_setPosts() {
		ArrayList<PostGeral> lista = new ArrayList<PostGeral>();
		fb.setLista_posts(lista);
		assertTrue(fb.getLista_posts().size() == 0);
	}
	
	@Test
	public void test_palavraChave() {
		FacebookPost fb_post1 = new FacebookPost(1, null, "este post do facebook é um teste, um", "Teste1");
		FacebookPost fb_post2 = new FacebookPost(2, null, "este post do facebook é um teste, dois", "Teste2");
		ArrayList<PostGeral> listaTotal = new ArrayList<PostGeral>();
		listaTotal.add(fb_post1);
		listaTotal.add(fb_post2);
		
		ArrayList<PostGeral> listaComResultados = fb.palavraChave("dois", listaTotal);

		assertTrue(1 == listaComResultados.size());	
	}
	
	//
	
	@Test
	public void test_vinteQuatroHoras() {
		Calendar calendar = Calendar.getInstance(); 
		Date today = calendar.getTime();
		
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		Date lastweek = calendar.getTime();
		
		FacebookPost fb_post1 = new FacebookPost(1, today, "este post do facebook é um teste, um", "Teste1");
		FacebookPost fb_post2 = new FacebookPost(1, lastweek, "este post do facebook é um teste, dois", "Teste2");
		
		ArrayList<PostGeral> listaTotal = new ArrayList<PostGeral>();
		listaTotal.add(fb_post1);
		listaTotal.add(fb_post2);
		
		ArrayList<PostGeral> listaComResultados = fb.vinteQuatroHoras(listaTotal);
		
		assertTrue(1 == listaComResultados.size());	
		
	}
	
	@Test
	public void test_getPostEspecifico() {
		FacebookPost fb_post1 = new FacebookPost(1, null, "este post do facebook é um teste, um", "Teste1");
		FacebookPost fb_post2 = new FacebookPost(2, null, "este post do facebook é um teste, dois", "Teste2");
		ArrayList<PostGeral> listaTotal = new ArrayList<PostGeral>();
		listaTotal.add(fb_post1);
		listaTotal.add(fb_post2);
		
		fb.setLista_posts(listaTotal);

		assertTrue(fb_post2.equals(fb.getPostEspecifico("Teste2")));	
	}
	
	
	
	
	@Test
	public void test_viraLista() {
		FacebookPost fb_post1 = new FacebookPost(1, null, "este post do facebook é um teste, um", "Teste1");
		FacebookPost fb_post2 = new FacebookPost(1, null, "este post do facebook é um teste, dois", "Teste2");
		ArrayList<PostGeral> listaTotal = new ArrayList<PostGeral>();
		listaTotal.add(fb_post1);
		listaTotal.add(fb_post2);
		
		ArrayList<PostGeral> listaComResultados = fb.viraLista(listaTotal);

		assertTrue(fb_post2.equals(listaComResultados.get(0)));	
	}
	
	
	@Test
	public void test_refrescarConteudo() {
		fb.refrescarConteudo();
		for(PostGeral post: fb.getLista_posts()) {
			assertNotNull(post);
		}
	}
	
	
	
	@Test
	public void test_getXML() {
		boolean flag = false;
		if(!(fb.getXml().equals(null))) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag == true);
	}
}
