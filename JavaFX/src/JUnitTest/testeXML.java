package JUnitTest;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import org.xml.sax.SAXException;
import xml.XML;
public class testeXML {

	
	/**
	 * Testamos se a função get e set do AcessToken tem o mesmo resultado
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	public void testSetGetAcessToken() throws ParserConfigurationException, SAXException, IOException {
		XML teste = new XML();
		String AcessTokenAntesTest = teste.getFacebookAccessToken();
		String nova = "teste";
		teste.setFacebookAccessToken(nova);
		String atualizada = teste.getFacebookAccessToken();
		assertEquals(nova, atualizada);	
		teste.setFacebookAccessToken(AcessTokenAntesTest);
		System.out.println("AcessToken antes do teste reposto!");
	}
	
	/**
	 * Testamos se a função get e set do AppID tem o mesmo resultado
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	public void testSetGetAppID() throws ParserConfigurationException, SAXException, IOException {
		XML teste = new XML();
		String GetAppIDAntesTest = teste.getAppID();
		String nova = "teste";
		teste.setFacebookAppID(nova);
		String atualizada = teste.getAppID();
		assertEquals(nova, atualizada);
		teste.setFacebookAppID(GetAppIDAntesTest);
		System.out.println("AppID antes do teste reposto!");
	}
	
	/**
	 * Testamos se a função get e set do AppSecret tem o mesmo resultado
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	public void testSetGetAppSecret() throws ParserConfigurationException, SAXException, IOException {
		XML teste = new XML();
		String GetAppSecret = teste.getFacebookAppSecret();
		String nova = "teste";
		teste.setFacebookAppSecret(nova);
		String atualizada = teste.getFacebookAppSecret();
		assertEquals(nova, atualizada);
		teste.setFacebookAppSecret(GetAppSecret);
		System.out.println("AppSecret antes do teste reposto!");
		
	
		
	}
	

}
