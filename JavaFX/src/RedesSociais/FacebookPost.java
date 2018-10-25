package RedesSociais;

import java.util.Date;

import com.restfb.types.Post;

/**
 * Simula um post da rede social facebook com a data e o texto do post.
 * @author 
 *
 */

public class FacebookPost {

	
	private String postPreview;
	private Date date;
	private String fullPost;
	
	/**
	 * É o construtor da classe que recebe como argumento um objeto Post que vem da API Facebook
	 * @param post
	 */
	public FacebookPost(Post post) {
		this.postPreview = createPostPreview(post);
		this.date = post.getCreatedTime();
		this.fullPost = post.getMessage();
	}
		
	public String getPostPreview() {
		return postPreview;
	}

	public Date getDate() {
		return date;
	}

	public String getFullPost() {
		return fullPost;
	}
	
	/**
	 * Cria um título para o post de acordo com o tamanho do texto do post
	 * @param post
	 * @return String 
	 */
	public String createPostPreview(Post post) {
		String str;
		
		if(post.getMessage().length() < 22) {
			str = post.getCreatedTime().toString() + " - " + post.getMessage() + "... ";
		}
		else {
			str = post.getCreatedTime().toString() + " - " + post.getMessage().substring(0, 22) + "... ";
		}
		return str;
	}

}
