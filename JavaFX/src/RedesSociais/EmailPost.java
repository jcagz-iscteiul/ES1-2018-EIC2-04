package RedesSociais;

import java.util.Date;

import com.restfb.types.Post;

public class EmailPost extends PostGeral{
	
	private String from;
	private String to;
	
	
	public EmailPost(String assunto, Date data, String conteudo, String from, String to) {
		super(data, conteudo, assunto);
		this.from = from;
		this.to = to;
	}

	/**
	 * Retorna o atributo da superclasse titulo
	 * @return String
	 */
	public String getAssunto() {
		return titulo;
	}

	/**
	 * Retorna o atributo da superclasse data
	 * @return Date
	 */
	public Date getData() {
		return super.getData();
	}

	/**
	 * Retorna o atributo da superclasse conteudo
	 * @return String
	 */
	public String getConteudo() {
		return conteudo;
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
