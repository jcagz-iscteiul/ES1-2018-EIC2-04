package RedesSociais;

import java.util.Date;

import com.restfb.types.Post;

/**
 * Representa os posts do twitter, extende da classe PostGeral
 *
 */
public class TwitterPost extends PostGeral{
	
	/**
	 * Construtor da classe 
	 * @param id
	 * @param data
	 * @param conteudo
	 * @param autor
	 */
	public TwitterPost(int id, Date data, String conteudo, String autor) {
		super(id, "Twitter",data, conteudo, autor);
	}
	

}
