package RedesSociais;

import java.util.Date;

/**
 * Representa um objeto Post
 *
 */
public abstract class PostGeral implements Comparable<PostGeral>{
	
	private int id;
	protected Date data;
	protected String conteudo;
	protected String titulo;
	protected String redeSocial;
	
	/**
	 * Construtor da classe PostGeral
	 * @param id
	 * @param redeSocial
	 * @param data
	 * @param conteudo
	 * @param titulo
	 */
	public PostGeral(int id, String redeSocial, Date data, String conteudo, String titulo) {
		this.id = id;
		this.redeSocial = redeSocial;
		this.data = data;
		this.conteudo = conteudo;
		this.titulo = titulo;
	}
	
	/**
	 * Retorna o atributo data
	 * @return Data
	 */
	public Date getData() {
		return data;
	}
	
	/**
	 * Retorna o atributo conteudo
	 * @return String
	 */
	public String getConteudo() {
		return conteudo;
	}
	
	/**
	 * Retorna o atributo titulo
	 * @return String
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Retorna o atributo id
	 * @return int
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Retorna o atributo redeSocial
	 * @return String
	 */
	public String getRedeSocial() {
		return redeSocial;
	}

	/**
	 * Retorna uma string com os respetivos atributos:
	 * 	-redeSocial
	 * 	-data
	 * 	-titulo
	 * @return String
	 */
	public String createTitulo() {
		return "[" + redeSocial + "] " + data + " - " + titulo;
	}
	
	
	@Override
	public int compareTo(PostGeral o) {
		return getData().compareTo(o.getData());
	}
	
	
	
}
