/*
 * Est� classe � reponsav�l pela leitura e escrita do ficheiro xml
 * @author eic2.04
 */

package bdaXML;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class XML {
	
	// ------------------------------------- SETERS ------------------------------------------

	
	// ----------------------------- FACEBOOK ----------------------------
	/**
	 * Altera o campo Facebook AppSecret no ficheiro xml com o novo valor pretendido
	 * @param AppSecret
	 */
	public void setFacebookAppSecret(String AppSecret) {
		try {

			String filepath = "src/bdaXML/config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

			Node Facebook = doc.getElementsByTagName("Facebook").item(0);

			// Não mexer até aqui

			NamedNodeMap attr_appsecret = Facebook.getAttributes();
			Node nodeAttr_appsecret = attr_appsecret.getNamedItem("AppSecret");
			nodeAttr_appsecret.setTextContent(AppSecret);
			
			
			// Print do novo AppSecret
			System.out.print("AppSecret : ");
			System.out.println(nodeAttr_appsecret.getNodeValue());

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Ficheiro xml salvo com as alterações pretendidas");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
	
	
	/**
	 * Altera o campo AppID do Facebook no ficheiro xml com o novo valor pretendido
	 * @param AppID
	 */
	public void setFacebookAppID(String AppID) {

		try {

			String filepath = "src/bdaXML/config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

			Node Facebook = doc.getElementsByTagName("Facebook").item(0);

			// Não mexer até aqui

			NamedNodeMap attr_appid = Facebook.getAttributes();
			Node nodeAttr_appid = attr_appid.getNamedItem("AppID");
			nodeAttr_appid.setTextContent(AppID);

			// Print do novo AppID
			System.out.print("AppID : ");
			System.out.println(nodeAttr_appid.getNodeValue());

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Ficheiro xml salvo com as alterações pretendidas");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
	
	/**
	 * Altera o campo AcessToken do Facebook no ficheiro xml com o novo valor pretendido
	 * @param AcessToken
	 */
	public void setFacebookAccessToken(String AcessToken) {

		try {

			String filepath = "src/bdaXML/config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

			Node Facebook = doc.getElementsByTagName("Facebook").item(0);

			// Não mexer até aqui

			NamedNodeMap attr_acesstoken = Facebook.getAttributes();
			Node nodeAttr_acesstoken = attr_acesstoken.getNamedItem("AcessToken");
			nodeAttr_acesstoken.setTextContent(AcessToken);

			// Print do novo AcessToken
			System.out.print("AcessToken : ");
			System.out.println(nodeAttr_acesstoken.getNodeValue());

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Ficheiro xml salvo com as alterações pretendidas");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
	
	// -------------------------- TWITTER ----------------------------
	
	/**
	 * Altera o campo AccessToken do Twitter no ficheiro xml com o novo valor prentendido
	 * @param AccessToken
	 */
	public void setTwitterAccessToken(String AccessToken) {
		try {

			String filepath = "src/bdaXML/config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

			Node Twitter = doc.getElementsByTagName("Twitter").item(0);

			// Não mexer até aqui

			NamedNodeMap attr_accesstoken = Twitter.getAttributes();
			Node nodeAttr_accesstoken = attr_accesstoken.getNamedItem("AccessToken");
			nodeAttr_accesstoken.setTextContent(AccessToken);
			
			
			// Print do novo AppSecret
			System.out.print("AppSecret : ");
			System.out.println(nodeAttr_accesstoken.getNodeValue());

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Ficheiro xml salvo com as altera��es pretendidas");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
	
	/**
	 * Altera o campo AccessTokenSecret do Twitter no ficheiro xml com o novo valor prentendido
	 * @param AcessTokenSecret
	 */
	public void setTwitterAcessTokenSecret(String AcessTokenSecret) {
		try {

			String filepath = "src/bdaXML/config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

			Node Twitter = doc.getElementsByTagName("Twitter").item(0);

			// Não mexer até aqui

			NamedNodeMap attr_acesstokensecret = Twitter.getAttributes();
			Node nodeAttr_acesstokensecret = attr_acesstokensecret.getNamedItem("AcessTokenSecret");
			nodeAttr_acesstokensecret.setTextContent(AcessTokenSecret);
			
			
			// Print do novo AppSecret
			System.out.print("AppTokenSecret : ");
			System.out.println(nodeAttr_acesstokensecret.getNodeValue());

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Ficheiro xml salvo com as alterações pretendidas");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
	
	/**
	 * Altera o campo ConsumerSecret do Twitter no ficheiro xml com o novo valor prentendido
	 * @param ConsumerSecret
	 */
	public void setTwitterConsumerSecret(String ConsumerSecret) {
		try {

			String filepath = "src/bdaXML/config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

			Node Twitter = doc.getElementsByTagName("Twitter").item(0);

			// Não mexer até aqui

			NamedNodeMap attr_consumersecret = Twitter.getAttributes();
			Node nodeAttr_consumersecret = attr_consumersecret.getNamedItem("ConsumerSecret");
			nodeAttr_consumersecret.setTextContent(ConsumerSecret);
			
			
			// Print do novo AppSecret
			System.out.print("AppSecret : ");
			System.out.println(nodeAttr_consumersecret.getNodeValue());

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Ficheiro xml salvo com as alterações pretendidas");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
	
	/**
	 * Altera o campo DebugEnable do Twitter no ficheiro xml com o novo valor prentendido
	 * @param DebugEnable
	 */
	public void setTwitterDebugEnable(String DebugEnabled) {
		try {

			String filepath = "src/bdaXML/config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

			Node Twitter = doc.getElementsByTagName("Twitter").item(0);

			// Não mexer até aqui

			NamedNodeMap attr_debugenable = Twitter.getAttributes();
			Node nodeAttr_debugenable = attr_debugenable.getNamedItem("DebugEnabled");
			nodeAttr_debugenable.setTextContent(DebugEnabled);
			
			
			// Print do novo AppSecret
			System.out.print("DebugEnable : ");
			System.out.println(nodeAttr_debugenable.getNodeValue());

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Ficheiro xml salvo com as alterações pretendidas");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
	
	
	// ----------------------------- GMAIL --------------------------------------
	
	/**
	 * Altera o campo Email do Gmail no ficheiro xml com o novo valor prentendido
	 * @param Email
	 */
	public void setGmailEmail(String Email) {
		try {

			String filepath = "src/bdaXML/config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

			Node Gmail = doc.getElementsByTagName("Gmail").item(0);

			// Não mexer até aqui

			NamedNodeMap attr_email = Gmail.getAttributes();
			Node nodeAttr_email = attr_email.getNamedItem("Email");
			nodeAttr_email.setTextContent(Email);
			
			
			// Print do novo AppSecret
			System.out.print("Email: ");
			System.out.println(nodeAttr_email.getNodeValue());

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Ficheiro xml salvo com as alterações pretendidas");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
	
	/**
	 * Altera o campo password do Gmail no ficheiro xml com o novo valor prentendido
	 * @param Password
	 */
	public void setGmailPassword(String Password) {
		try {

			String filepath = "src/bdaXML/config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

			Node Gmail = doc.getElementsByTagName("Gmail").item(0);

			// Não mexer até aqui

			NamedNodeMap attr_password = Gmail.getAttributes();
			Node nodeAttr_password = attr_password.getNamedItem("Password");
			nodeAttr_password.setTextContent(Password);
			
			
			// Print do novo AppSecret
			System.out.print("AppSecret : ");
			System.out.println(nodeAttr_password.getNodeValue());

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Ficheiro xml salvo com as alterações pretendidas");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
	
	

// ----------------------------------- GETERS ----------------------------------------------------
	
	
	// -------------------- FACEBOOK ----------------------
	
	/**
	 * Returna o valor AppSecret do Facebook do ficheiro xml
	 * @return O valor AppSecret
	 */
	public String getFacebookAppSecret() throws ParserConfigurationException, SAXException, IOException {

		
		String filepath = "src/bdaXML/config.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		//System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

		Node Facebook = doc.getElementsByTagName("Facebook").item(0);

		// Não mexer até aqui

		NamedNodeMap attr_appsecret = Facebook.getAttributes();
		String nodeAttr_appsecret = attr_appsecret.getNamedItem("AppSecret").toString();
		String result = nodeAttr_appsecret.substring(nodeAttr_appsecret.indexOf("=") + 1);
		String appsecret = result.substring(1, result.length()-1);
		//System.out.println(appsecret);
		
		return appsecret;
			
		
	}
	
	/**
	 * Retorna o valor AcessToken do Facebook do ficheiro xml
	 * @return O valor AcessToken
	 */
	public String getFacebookAccessToken() throws ParserConfigurationException, SAXException, IOException {

		String filepath = "src/bdaXML/config.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		//System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

		Node Facebook = doc.getElementsByTagName("Facebook").item(0);

		// Não mexer até aqui

		NamedNodeMap attr_acesstoken = Facebook.getAttributes();
		String nodeAttr_acesstoken = attr_acesstoken.getNamedItem("AcessToken").toString();
		String result = nodeAttr_acesstoken.substring(nodeAttr_acesstoken.indexOf("=") + 1);
		String acesstoken = result.substring(1, result.length()-1);
		//System.out.println(acesstoken);
		return acesstoken;
		
			
		
	}
	
	/**
	 * Returna o valor AppID do ficheiro xml
	 * @return O valor AppID
	 */
	public String getAppID() throws ParserConfigurationException, SAXException, IOException {
	
	
			String filepath = "src/bdaXML/config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory .newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			//System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

			Node Facebook = doc.getElementsByTagName("Facebook").item(0);

			// Não mexer até aqui

			NamedNodeMap attr_appid = Facebook.getAttributes();
			String nodeAttr_appid = attr_appid.getNamedItem("AppID").toString();
			String result = nodeAttr_appid.substring(nodeAttr_appid.indexOf("=") + 1);
			String appid = result.substring(1, result.length()-1);
			//System.out.println(appid);
			
			return appid;

	}
	
	// --------------------- TWITTER ------------------------
	/**
	 * Retorna o AccessToken do Twitter 
	 * @return String
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public String getTwitterAccessToken() throws ParserConfigurationException, SAXException, IOException {

		String filepath = "src/bdaXML/config.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		//System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

		Node Twitter = doc.getElementsByTagName("Twitter").item(0);

		// Não mexer até aqui

		NamedNodeMap attr_acesstoken = Twitter.getAttributes();
		String nodeAttr_acesstoken = attr_acesstoken.getNamedItem("AccessToken").toString();
		String result = nodeAttr_acesstoken.substring(nodeAttr_acesstoken.indexOf("=") + 1);
		String acesstoken = result.substring(1, result.length()-1);
		//System.out.println(acesstoken);
		return acesstoken;
	}
		
	/**
	 * 	Retorna o AccessTokenSecret do Twitter
	 * @return String
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public String getTwitterAccessTokenSecret() throws ParserConfigurationException, SAXException, IOException {

		String filepath = "src/bdaXML/config.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		//System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

		Node Twitter = doc.getElementsByTagName("Twitter").item(0);
		
		// Não mexer até aqui

		NamedNodeMap attr_acesstokensecret = Twitter.getAttributes();
		String nodeAttr_acesstokensecret = attr_acesstokensecret.getNamedItem("AcessTokenSecret").toString();
		String result = nodeAttr_acesstokensecret.substring(nodeAttr_acesstokensecret.indexOf("=") + 1);
		String acesstokensecret = result.substring(1, result.length()-1);
		//System.out.println(acesstoken);
		return acesstokensecret;
		
			
		
	}
	
	/**
	 * Retorna o ConsumerSecret do Twitter
	 * @return String
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public String getTwitterConsumerSecret() throws ParserConfigurationException, SAXException, IOException {

		String filepath = "src/bdaXML/config.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		//System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

		Node Twitter = doc.getElementsByTagName("Twitter").item(0);
		
		// Não mexer até aqui

		NamedNodeMap attr_consumersecret = Twitter.getAttributes();
		String nodeAttr_consumersecret = attr_consumersecret.getNamedItem("ConsumerSecret").toString();
		String result = nodeAttr_consumersecret.substring(nodeAttr_consumersecret.indexOf("=") + 1);
		String consumersecret = result.substring(1, result.length()-1);
		//System.out.println(acesstoken);
		return consumersecret;
	}
	
	/**
	 * Retorna o DebugEnable do Twitter
	 * @return String
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public String getTwitterDebugEnable() throws ParserConfigurationException, SAXException, IOException {

		String filepath = "src/bdaXML/config.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		//System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

		Node Twitter = doc.getElementsByTagName("Twitter").item(0);
		
		// Não mexer até aqui

		NamedNodeMap attr_debugenabled = Twitter.getAttributes();
		String nodeAttr_debugenabled = attr_debugenabled.getNamedItem("DebugEnabled").toString();
		String result = nodeAttr_debugenabled.substring(nodeAttr_debugenabled.indexOf("=") + 1);
		String debugenabled = result.substring(1, result.length()-1);
		//System.out.println(acesstoken);
		return debugenabled;
	
	}
		
		
	// --------------------- GMAIL -------------------
	
	/**
	 * Retorna o Email do Gmail
	 * @return String
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public String getGmailEmail() throws ParserConfigurationException, SAXException, IOException {

		String filepath = "src/bdaXML/config.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		//System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

		Node Gmail = doc.getElementsByTagName("Gmail").item(0);
			
		// Não mexer até aqui

		NamedNodeMap attr_email = Gmail.getAttributes();
		String nodeAttr_email = attr_email.getNamedItem("Email").toString();
		String result = nodeAttr_email.substring(nodeAttr_email.indexOf("=") + 1);
		String email = result.substring(1, result.length()-1);
		//System.out.println(acesstoken);
		return email;
	}
	
	/**
	 * Retorna a password do Gmail
	 * @return String
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public String getGmailPassword() throws ParserConfigurationException, SAXException, IOException {

		String filepath = "src/bdaXML/config.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		//System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

		Node Gmail = doc.getElementsByTagName("Gmail").item(0);
				
		// Não mexer até aqui

		NamedNodeMap attr_password = Gmail.getAttributes();
		String nodeAttr_password = attr_password.getNamedItem("Password").toString();
		String result = nodeAttr_password.substring(nodeAttr_password.indexOf("=") + 1);
		String password = result.substring(1, result.length()-1);
		//System.out.println(acesstoken);
		return password;
	
	}
}
	

