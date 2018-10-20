package xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XML {
	
<<<<<<< HEAD
=======
	private String AcessToken;
	private String AppID;
	private String AppSecret;
>>>>>>> refs/heads/Garcez
	
	// ------------------------------------- SETERS ------------------------------------------

	public void setAppSecret(String AppSecret) {

		try {

			String filepath = "src/xml/config.xml";
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

	public void setAppID(String AppID) {

		try {

			String filepath = "src/xml/config.xml";
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

	public void setAcessToken(String AcessToken) {

		try {

			String filepath = "src/xml/config.xml";
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
	
	

// ----------------------------------- GETERS ----------------------------------------------------
	
	
	
	
	
	public String getAppSecret() throws ParserConfigurationException, SAXException, IOException {

		
		String filepath = "src/xml/config.xml";
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
	
	public String getAcessToken() throws ParserConfigurationException, SAXException, IOException {
		

		
		String filepath = "src/xml/config.xml";
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

	public String getAppID() throws ParserConfigurationException, SAXException, IOException {
	
	
			String filepath = "src/xml/config.xml";
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
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		XML xml = new XML();
		
		String appSecret = xml.getAppSecret();
		System.out.println(appSecret);
		
		String acessToken = xml.getAcessToken();
		System.out.println(acessToken);
		
		String appid = xml.getAppID();
		System.out.println(appid);
		
		
	}
}