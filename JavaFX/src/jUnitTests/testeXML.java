package jUnitTests;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import org.xml.sax.SAXException;

import bdaXML.XML;
public class testeXML {

	
	/**
	 * Testamos se a função get e set do AcessToken tem o mesmo resultado
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	@Test
	public void testSetGetFacebookAcessToken() throws ParserConfigurationException, SAXException, IOException {
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
	public void testSetGetFacebookAppID() throws ParserConfigurationException, SAXException, IOException {
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
	public void testSetGetFacebookAppSecret() throws ParserConfigurationException, SAXException, IOException {
		XML teste = new XML();
		String GetAppSecret = teste.getFacebookAppSecret();
		String nova = "teste";
		teste.setFacebookAppSecret(nova);
		String atualizada = teste.getFacebookAppSecret();
		assertEquals(nova, atualizada);
		teste.setFacebookAppSecret(GetAppSecret);
		System.out.println("AppSecret antes do teste reposto!");
		
	
		
	}
	
	
	@Test
	public void testSetGetTwitterAcessToken() throws ParserConfigurationException, SAXException, IOException {
		XML teste = new XML();
		String AcessTokenAntesTest = teste.getTwitterAccessToken();
		String nova = "teste";
		teste.setTwitterAccessToken(nova);
		String atualizada = teste.getTwitterAccessToken();
		assertEquals(nova, atualizada);	
		teste.setTwitterAccessToken(AcessTokenAntesTest);
		System.out.println("Twitter: AcessToken antes do teste reposto!");
	}
	
	
	@Test
	public void testSetGetTwitterAcessTokenSecret() throws ParserConfigurationException, SAXException, IOException {
		XML teste = new XML();
		String AcessTokenAntesTestSecret = teste.getTwitterAccessTokenSecret();
		String nova = "teste";
		teste.setTwitterAcessTokenSecret(nova);
		String atualizada = teste.getTwitterAccessTokenSecret();
		assertEquals(nova, atualizada);	
		teste.setTwitterAcessTokenSecret(AcessTokenAntesTestSecret);
		System.out.println("Twitter: AcessTokenSecret antes do teste reposto!");
	}
	
	@Test
	public void testSetGetTwitterConsumerSecret() throws ParserConfigurationException, SAXException, IOException {
		XML teste = new XML();
		String ConsumerSecretAntesTestSecret = teste.getTwitterConsumerSecret();
		String nova = "teste";
		teste.setTwitterConsumerSecret(nova);
		String atualizada = teste.getTwitterConsumerSecret();
		assertEquals(nova, atualizada);	
		teste.setTwitterConsumerSecret(ConsumerSecretAntesTestSecret);
		System.out.println("Twitter: ConsumerSecret antes do teste reposto!");
	}
	
	@Test
	public void testSetGetTwitterDebug() throws ParserConfigurationException, SAXException, IOException {
		XML teste = new XML();
		String DebugAntesTestSecret = teste.getTwitterDebugEnable();
		String nova = "teste";
		teste.setTwitterDebugEnable(nova);
		String atualizada = teste.getTwitterDebugEnable();
		assertEquals(nova, atualizada);	
		teste.setTwitterDebugEnable(DebugAntesTestSecret);
		System.out.println("Twitter: DebugEnable antes do teste reposto!");
	}
	
	
	@Test
	public void testgetGmailEmail() throws ParserConfigurationException, SAXException, IOException {
		XML teste = new XML();
		String GmailEmailAntesTestSecret = teste.getGmailEmail();
		String nova = "teste";
		teste.setGmailEmail(nova);
		String atualizada = teste.getGmailEmail();
		assertEquals(nova, atualizada);	
		teste.setGmailEmail(GmailEmailAntesTestSecret);
		System.out.println("Gmail: Email antes do teste reposto!");
	}
	
	
	@Test
	public void testgetGmailPassword() throws ParserConfigurationException, SAXException, IOException {
		XML teste = new XML();
		String GmailPasswordAntesTestSecret = teste.getGmailPassword();
		String nova = "teste";
		teste.setGmailPassword(nova);
		String atualizada = teste.getGmailPassword();
		assertEquals(nova, atualizada);	
		teste.setGmailPassword(GmailPasswordAntesTestSecret);
		System.out.println("Gmail: Password antes do teste reposto!");
	}
	
	
	

}
