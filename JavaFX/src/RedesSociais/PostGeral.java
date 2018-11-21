package RedesSociais;

import java.util.Date;

public abstract class PostGeral {
	
	protected Date data;
	protected String conteudo;
	protected String titulo;
	
	public PostGeral(Date data, String conteudo, String titulo) {
		this.data = data;
		this.conteudo = conteudo;
		this.titulo = titulo;
	}

	public Date getData() {
		return data;
	}

	public String getConteudo() {
		return conteudo;
	}

	public String getTitulo() {
		return titulo;
	}
	
	
	
}
