package RedesSociais;

import java.util.Date;

import com.restfb.types.Post;

public class TwitterPost extends PostGeral{

	public TwitterPost(Date data, String conteudo, String titulo) {
		super(data, conteudo, titulo);
		// TODO Auto-generated constructor stub
	}
	
	
	public String getPostPreview() {
		return super.titulo;
	}

	public Date getDate() {
		return super.data;
	}

	public String getFullPost() {
		return super.conteudo;
	}

}
