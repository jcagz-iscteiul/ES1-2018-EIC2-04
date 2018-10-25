package RedesSociais;

import java.util.Date;

import com.restfb.types.Post;

public class FacebookPost {

	
	private String postPreview;
	private Date date;
	private String fullPost;
	
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
