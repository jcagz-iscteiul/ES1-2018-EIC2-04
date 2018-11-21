package JUnitTest;




import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;
import RedesSociais.Facebook;
import RedesSociais.FacebookPost;
import RedesSociais.PostGeral;

class FacebookTest {

	@Test
	void test_addPostsToArray() throws ParserConfigurationException, SAXException, IOException {

		Facebook fb1 = new Facebook();
		ArrayList<PostGeral> listPosts1 = fb1.getPosts();
		assertEquals(listPosts1.size(), fb1.getPosts().size());	
		}
}
