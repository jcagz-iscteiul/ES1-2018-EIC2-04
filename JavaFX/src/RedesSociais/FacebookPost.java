package RedesSociais;

import java.util.Date;

import com.restfb.types.Post;

/**
 * Simula um post da rede social facebook com a data e o texto do post.
 * @author 
 *
 */

public class FacebookPost extends PostGeral{

	
	private String postPreview;
	private Date date;
	private String fullPost;
	
	/**
	 * É o construtor da classe que recebe como argumento um objeto Post que vem da API Facebook
	 * @param post
	 */
	public FacebookPost(Date data, String conteudo, String titulo) {
		super("Facebook", data, conteudo, titulo);
	}



	public String getTitulo() {
		return super.titulo;
	}

	public Date getDate() {
		return super.data;
	}

	public String getConteudo() {
		return super.conteudo;
	}
	

	
	
	


}
