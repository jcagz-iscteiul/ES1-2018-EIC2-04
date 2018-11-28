package RedesSociais;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public abstract class RedeSocial {
	private String accountToken;
	
	public abstract void autenticarCliente() throws ParserConfigurationException, SAXException, IOException;
	
	//Possivel metodo futuro
	//public abstract void refrescarConteudo();
}
