package JUnitTest;

import static org.junit.Assert.*;

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
		String nova = "teste";
		teste.setAcessToken(nova);
		String atualizada = teste.getAcessToken();
		assertEquals(nova, atualizada);	
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
		String nova = "teste";
		teste.setAppID(nova);
		String atualizada = teste.getAppID();
		assertEquals(nova, atualizada);
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
		String nova = "teste";
		teste.setAppSecret(nova);
		String atualizada = teste.getAppSecret();
		assertEquals(nova, atualizada);
		
		
		
	}
	

}
