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
		String AcessTokenAntesTest = teste.getAcessToken();
		String nova = "teste";
		teste.setAcessToken(nova);
		String atualizada = teste.getAcessToken();
		assertEquals(nova, atualizada);	
		teste.setAcessToken(AcessTokenAntesTest);
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
		String GetAppIDAntesTest = teste.getAcessToken();
		String nova = "teste";
		teste.setAppID(nova);
		String atualizada = teste.getAppID();
		assertEquals(nova, atualizada);
		teste.setAppID(GetAppIDAntesTest);
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
		String GetAppSecret = teste.getAcessToken();
		String nova = "teste";
		teste.setAppSecret(nova);
		String atualizada = teste.getAppSecret();
		assertEquals(nova, atualizada);
		teste.setAppSecret(GetAppSecret);
		System.out.println("AppSecret antes do teste reposto!");
		
	
		
	}
	

}
