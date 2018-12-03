package JUnitTest;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import RedesSociais.Destaques;
import RedesSociais.Facebook;
import RedesSociais.Gmail;
import RedesSociais.PostGeral;
import RedesSociais.TwitterObject;

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
	
	
	
	
	
	
}
