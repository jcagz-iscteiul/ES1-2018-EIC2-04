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

	
	public String getAssunto() {
		return conteudo;
	}


	public Date getData() {
		return data;
	}


	public String getConteudo() {
		return conteudo;
	}


	public String getFrom() {
		return from;
	}



	public String getTo() {
		return to;
	}



	public String emailPostPreview() {
		String str = data.toString() + " - " + conteudo;
		
		return str;
	}



}
