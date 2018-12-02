package RedesSociais;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import baseDados.BaseDados;
import xml.XML;

public abstract class RedeSocial {
	
	private String accountToken;
	protected XML xml = new XML();
	protected ArrayList<PostGeral> lista_posts = new ArrayList<PostGeral>();
	protected BaseDados db;
	
	public abstract void autenticarCliente() throws ParserConfigurationException, SAXException, IOException;

	/**
	 * Retorna o atributo accountToken
	 * @return String
	 */
	public String getAccountToken() {
		return accountToken;
	}

	/**
	 * Altera o atributo accountToken por um novo parametro
	 * @param accountToken
	 */
	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}

	/**
	 * Retorna o atributo xml
	 * @return XML
	 */
	public XML getXml() {
		return xml;
	}
	
	/**
	 * Retorna o atributo lista_posts
	 * @return ArrayList<PostGeral>
	 */
	public ArrayList<PostGeral> getLista_posts() {
		return lista_posts;
	}
	
	/**
	 * Altera o atributo lista_posts por uma nova lista
	 * @param lista_posts
	 */
	public void setLista_posts(ArrayList<PostGeral> lista_posts) {
		this.lista_posts = lista_posts;
	}
	
	/**
	 * Retorna o atributo db
	 * @return BaseDados
	 */
	public BaseDados getDb() {
		return db;
	}
	
	
	
	
	
	
	
	
	//Possivel metodo futuro
	//public abstract void refrescarConteudo();
}
