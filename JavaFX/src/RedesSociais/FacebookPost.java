package RedesSociais;

import java.util.Date;

import com.restfb.types.Post;

public class FacebookPost {

	
	private String postPreview;
	private Date date;
	private String fullPost;
	
	public FacebookPost(Post post) {
		this.postPreview = post.getCreatedTime().toString() + " - " + post.getMessage().substring(0, 22) + "... ";
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


}
