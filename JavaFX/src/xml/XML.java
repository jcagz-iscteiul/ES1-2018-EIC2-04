package xml;

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

	private static String email;
	private static String password;


	public static void main(String[] args){
		

	   try {
		String filepath = "src/xml/config.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);
		
		System.out.println("Leitura do ficheiro config.xml realizada com sucesso!");

		
		Node Facebook = doc.getElementsByTagName("Facebook").item(0);
		
		if(email == null && password == null) {
			System.out.println("Nenhum dado foi alterado");
		} else {
		    System.out.println("Dados alterados:");
		}
		
		// Set email attribute
		if(email != null) {
		NamedNodeMap attr_email = Facebook.getAttributes();
		Node nodeAttr_email = attr_email.getNamedItem("Email");
		nodeAttr_email.setTextContent(email);
		
		
        // Print do novo email
		System.out.print("Email : ");
        System.out.println(nodeAttr_email.getNodeValue());
		}
		
		// Set password attribute
        if(password != null) {
		NamedNodeMap attr_password = Facebook.getAttributes();
		Node nodeAttr_password = attr_password.getNamedItem("Password");
		nodeAttr_password.setTextContent(password);
        
        
		 // Print da nova password
		System.out.print("Password : ");
        System.out.println(nodeAttr_password.getNodeValue());
        }


		// Escrever no config.xml
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
}