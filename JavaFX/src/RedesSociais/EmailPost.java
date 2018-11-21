package RedesSociais;

import java.util.Date;

import com.restfb.types.Post;

public class EmailPost {
	
	private String assunto;
	private Date data;
	private String conteudo;
	private String from;
	private String to;
	
	
	
	
	
	
	public EmailPost(String assunto, Date data, String conteudo, String from, String to) {
		this.assunto = assunto;
		this.data = data;
		this.conteudo = conteudo;
		this.from = from;
		this.to = to;
	}

	
	





	public String getAssunto() {
		return assunto;
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
		String str = data.toString() + " - " + assunto;
		
		return str;
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
