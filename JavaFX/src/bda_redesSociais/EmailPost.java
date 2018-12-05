package bda_redesSociais;

import java.util.Date;

import com.restfb.types.Post;

/**
 * Representa os posts do email, extende da classe PostGeral.
 *
 */
public class EmailPost extends PostGeral{
	
	private String from;
	private String to;
	
	/**
	 * Construtor da classe
	 * @param id
	 * @param assunto
	 * @param data
	 * @param conteudo
	 * @param from
	 * @param to
	 */
	public EmailPost(int id,String assunto, Date data, String conteudo, String from, String to) {
		super(id, "Email",data, conteudo, assunto);
		this.from = from;
		this.to = to;
	}

	/**
	 * Retorna o atributo from
	 * @return String
	 */
	public String getFrom() {
		return from;
	}


	/**
	 * Retorna o atributo to
	 * @return String
	 */
	public String getTo() {
		return to;
	}

	
	/**
	 * Retorna uma string concatenado com a data do email mais o titulo
	 * @return String
	 */
	public String emailPostPreview() {
		String str = data.toString() + " - " + titulo;
		
		return str;
	}

}
