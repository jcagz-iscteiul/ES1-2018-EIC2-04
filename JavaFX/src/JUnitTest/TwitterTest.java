package JUnitTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import RedesSociais.PostGeral;
import RedesSociais.TwitterObject;

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
	public void test_getXML() {
		boolean flag = false;
		if(!(tw.getXml().equals(null))) {
			flag = true;
		} else {
			flag = false;
		}
		assertTrue(flag == true);
	}
}
