package RedesSociais;

import java.util.Date;

import com.restfb.types.Post;

public class TwitterPost extends PostGeral{

	public TwitterPost(int id, Date data, String conteudo, String autor) {
		super(id, "Twitter",data, conteudo, autor);
	}
	

}
