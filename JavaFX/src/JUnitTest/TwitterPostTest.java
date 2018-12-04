package JUnitTest;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import RedesSociais.FacebookPost;
import RedesSociais.TwitterPost;

public class TwitterPostTest {
	
	private TwitterPost post;
	
	@Before
	public void setUp() {
		Date data = new Date("Wed Nov 21 09:39:07 GMT 2018");
		long id = 26513218;
		this.post = new TwitterPost(1,data,"A testar o TwitterPost", "ISCTE", id);
	}

	@Test
	public void test_getData() {
		Date data = new Date("Wed Nov 21 09:39:07 GMT 2018");
		assertTrue(post.getData().equals(data));
	}
	
	@Test
	public void test_getTitulo() {
		assertTrue(post.getTitulo().equals("ISCTE"));
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
	public void test_getPostID() {
		assertTrue(post.getPostID() == 26513218);
	}
	
	@Test
	public void test_setPostID() {
		long postID = 11111;
		post.setPostID(postID);
		assertTrue(post.getPostID() == postID);
	}
	
	@Test
	public void test_compareTo() {
		Date data = new Date("Wed Nov 21 09:39:07 GMT 2018");
		long id = 26513218;
		TwitterPost post2 = new TwitterPost(1,data,"A testar o TwitterPost", "ISCTE", id);
		int i = post.compareTo(post2);
		assertTrue(i != -2);
	}
}
