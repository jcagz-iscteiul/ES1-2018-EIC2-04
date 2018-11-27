package RedesSociais;

import java.util.Date;

public abstract class PostGeral implements Comparable<PostGeral>{
	
	
	protected Date data;
	protected String conteudo;
	protected String titulo;
	protected String redeSocial;
	
	public PostGeral(String redeSocial, Date data, String conteudo, String titulo) {
		this.redeSocial = redeSocial;
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
	
	
	public String createTitulo() {
		return "[" + redeSocial + "] " + data + " - " + titulo;
	}
	
	
	@Override
	public int compareTo(PostGeral o) {
		return getData().compareTo(o.getData());
	}
	
	
	
}
