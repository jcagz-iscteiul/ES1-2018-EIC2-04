package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		for(PostGeral post: gm.getEmails()) {
			assertFalse("Existe um email a null!", post.equals(null));
		}
	}
	
	@Test
	public void test_getMessageContent() {
		for(PostGeral post: gm.getEmails()) {
			assertFalse("Conteudo null!", post.getConteudo().equals(null));
		}
	}
	
	@Test
	public void test_getEmails() {
		boolean flag = false;
		if((gm.getEmails().equals(null))) {
			flag = false;
		} else {
			flag = true;
		}
		assertTrue(flag == true);
	}
	
	@Test
	public void viraArrayList() {
		gm.viraArraylist();
		for(PostGeral post: gm.getEmails()) {
			assertNotNull(post);
		}
	}

}
