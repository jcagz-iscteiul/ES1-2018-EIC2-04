package JUnitTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import RedesSociais.FacebookPost;
import RedesSociais.PostGeral;
import RedesSociais.TwitterObject;
import RedesSociais.TwitterPost;
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
	
//	@Test
//	public void test_getTimeline() {
//		int antes = tw.getLista_posts().size();
//		
//		try {
//			tw.getTimeLine();
//		} catch (TwitterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		int depois = tw.getLista_posts().size();
//		assertTrue(depois == 2*antes);		
//	}
	
	
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
	
//	@Test
//	public void test_getPostEspecifico() {
//		TwitterPost tw_post1 = new TwitterPost(1, null, "este post do facebook é um teste, um", "Teste1",11);
//		TwitterPost tw_post2 = new TwitterPost(1, null, "este post do facebook é um teste, dois", "Teste2",22);
//		ArrayList<PostGeral> listaTotal = new ArrayList<PostGeral>();
//		listaTotal.add(tw_post1);
//		listaTotal.add(tw_post2);
//		
//		tw.setLista_posts(listaTotal);
//
//		assertTrue(tw_post2.equals(tw.getPostEspecifico("Teste2")));	
//	}
	

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
		boolean flag = false;
		if(!(tw.getXml().equals(null))) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag == true);
	}
	
	
	@Test
	public void test_refrescarConteudo() {
		tw.refrescarConteudo();
		for(PostGeral post: tw.getLista_posts()) {
			assertNotNull(post);
		}
	}
	
	
}
