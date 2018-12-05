package jUnitTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import bda_redesSociais.FacebookPost;
import bda_redesSociais.PostGeral;
import bda_redesSociais.TwitterObject;
import bda_redesSociais.TwitterPost;
import twitter4j.TwitterException;

public class TwitterTest {

	private TwitterObject tw;
	
	@Before
	public void setUp() throws ParserConfigurationException, SAXException, IOException {
		this.tw = new TwitterObject();
	}
	
	@Test
	public void test_addPostsToArray() throws ParserConfigurationException, SAXException, IOException {
		for(PostGeral post: tw.getLista_posts()) {
			assertFalse("Existe um post null", post.equals(null));
		}
	}
	
	@Test
	public void test_createPostPreview() {
		for(PostGeral post: tw.getLista_posts()) {
			assertFalse("Existe um post com titulo null!", post.getTitulo().equals(null));
		}
	}
	
	@Test
	public void test_getPosts() {
		boolean flag = false;
		if(!(tw.getLista_posts().equals(null))) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag == true);
	}
	
	@Test
	public void test_setPosts() {
		ArrayList<PostGeral> lista = new ArrayList<PostGeral>();
		tw.setLista_posts(lista);
		assertTrue(tw.getLista_posts().size() == 0);
	}
	
	@Test
	public void test_getTimeline() {
		for(PostGeral post: tw.getLista_posts()) {
			assertNotNull(post);
		}
	}
	
	
	@Test
	public void test_vinteQuatroHoras() {
		Calendar calendar = Calendar.getInstance(); 
		Date today = calendar.getTime();
		
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		Date lastweek = calendar.getTime();
		
		TwitterPost tw_post1 = new TwitterPost(1, today, "este post do facebook é um teste, um", "Teste1",11);
		TwitterPost tw_post2 = new TwitterPost(1, lastweek, "este post do facebook é um teste, dois", "Teste2",22);
		
		ArrayList<PostGeral> listaTotal = new ArrayList<PostGeral>();
		listaTotal.add(tw_post1);
		listaTotal.add(tw_post2);
		
		ArrayList<PostGeral> listaComResultados = tw.vinteQuatroHoras(listaTotal);
		
		assertTrue(1 == listaComResultados.size());	
		
	}
	
	@Test
	public void test_getPostEspecifico() {
//		String titulo = "Tue Dec 04 21:20:53 GMT 2018 - [SL Benfica]: #HóqueiBenfica | 1-1 |... ";
//		PostGeral post = tw.getPostEspecifico(titulo);
//		assertTrue(tw.createPostPreview((TwitterPost)post).equals(titulo));
		ArrayList<PostGeral> listaTotal = new ArrayList<PostGeral>();
		Date data1 = new Date(2018-12-10);
		Date data2 = new Date(2018-12-13);
		TwitterPost tw_post1 = new TwitterPost(1,data1,"teste1","autor1",1);
		TwitterPost tw_post2 = new TwitterPost(2,data2,"teste2","autor2",2);
		listaTotal.add(tw_post1);
		listaTotal.add(tw_post2);
		tw.setLista_posts(listaTotal);
		String titulo = tw.createPostPreview(tw_post1);
		TwitterPost tw_post3 = tw.getPostEspecifico(titulo);
		assertTrue(tw_post1.getTitulo().equals(tw_post3.getTitulo()));
	}
	

	@Test
	public void test_viraLista() {
		TwitterPost tw_post1 = new TwitterPost(1, null, "este post do facebook é um teste, um", "Teste1",11);
		TwitterPost tw_post2 = new TwitterPost(1, null, "este post do facebook é um teste, dois", "Teste2",22);
		ArrayList<PostGeral> listaTotal = new ArrayList<PostGeral>();
		listaTotal.add(tw_post1);
		listaTotal.add(tw_post2);
		
		ArrayList<PostGeral> listaComResultados = tw.viraLista(listaTotal);

		assertTrue(tw_post2.equals(listaComResultados.get(0)));	
	}
	
	
	@Test
	public void test_getXML() {
		assertNotNull(tw.getXml());
	}
	
	
	@Test
	public void test_refrescarConteudo() {
		tw.refrescarConteudo();
		for(PostGeral post: tw.getLista_posts()) {
			assertNotNull(post);
		}
	}
	
	@Test
	public void test_palavraChave() {
		ArrayList<PostGeral> lista = tw.palavraChave("Benfica", tw.getLista_posts());
		for(PostGeral post: lista) {
			assertNotNull(post);
		}
	}
	
	@Test
	public void test_getInstance() {
		TwitterObject tw2 = TwitterObject.getInstance();
		assertNotNull(tw2);
	}
	
	
}
