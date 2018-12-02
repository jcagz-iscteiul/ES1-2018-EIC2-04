package JUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import RedesSociais.Facebook;
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
