package bda_redesSociais;

import java.util.Date;

import com.restfb.types.Post;

/**
 * Representa os posts do email, extende da classe PostGeral
 *
 */

public class FacebookPost extends PostGeral{
	
	/**
	 * Construtor da classe
	 * @param id
	 * @param data
	 * @param conteudo
	 * @param titulo
	 */
	public FacebookPost(int id, Date data, String conteudo, String titulo) {
		super(id,"Facebook", data, conteudo, titulo);
	}
	


}
