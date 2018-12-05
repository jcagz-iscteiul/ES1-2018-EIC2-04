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
	
	private long postID;         //para permitir retweet
	
	public TwitterPost(int id, Date data, String conteudo, String autor, long postID) {
		super(id, "Twitter",data, conteudo, autor);
		this.postID=postID;
		
	}
	
	public long getPostID() {
		return postID;
	}
	
	public void setPostID(long postID) {
		this.postID = postID;
	}
	
	
	

}
