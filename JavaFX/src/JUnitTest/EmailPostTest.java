package JUnitTest;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import RedesSociais.EmailPost;
import RedesSociais.FacebookPost;

public class EmailPostTest {
	
	private EmailPost post;
	
	@Before
	public void setUp() {
		Date data = new Date("Wed Nov 21 09:39:07 GMT 2018");
		this.post = new EmailPost(1,"Teste", data, "A testar o emailPost", "ISCTE", "MARTE");
	}
	
	@Test
	public void test_getId() {
		assertTrue(post.getId() == 1);
	}
	
	@Test
	public void test_getRedeSocial() {
		assertTrue(post.getRedeSocial().equals("Email"));
	}
	
	@Test
	public void test_createTitulo() {
		String s = "[" + post.getRedeSocial() + "] " + post.getData() + " - " + post.getTitulo();
		assertTrue(post.createTitulo().equals(s));
	}
	
	@Test
	public void test_getFrom() {
		assertTrue(post.getFrom().equals("ISCTE"));
	}
	
	@Test
	public void test_getTo() {
		assertTrue(post.getTo().equals("MARTE"));
	}
	
	@Test
	public void test_emailPostPreview() {
		String s = post.getData() + " - " + post.getTitulo();
		assertTrue(post.emailPostPreview().equals(s));
	}
	
	@Test
	public void test_compareTo() {
		Date data = new Date("Wed Nov 21 09:39:07 GMT 2018");
		EmailPost post2 = new EmailPost(1,"Teste", data, "A testar o emailPost", "ISCTE", "MARTE");
		int i = post.compareTo(post2);
		assertTrue(i != -2);
	}

}
