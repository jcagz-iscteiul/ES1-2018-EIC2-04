package RedesSociais;

import java.util.ArrayList;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.User;

//import java.util.List;

public class Facebook {  //implements interfaceFiltragem
	
	private final String accessToken = "EAAfIbZAN045UBAGZB4i8TxPzMZAwvjDS7WqrUAk1ZCteeZBmcwBTksKH2gjmI5OaAeqZCx6bfr3UGSWSWAbjIINVN7CtnD7La4ZA4do49Ieye3lZAk0EfQtmI8J2qXzVZCgZA4hazBHgtmV7LRlqHRlUayc95P2j992H3oFFgAZAxlAip8AdSpMa4fF";
	private FacebookClient fbClient = new DefaultFacebookClient(accessToken);
	private final User me = fbClient.fetchObject("me", User.class);
	private List<Post> posts = new ArrayList<Post>();
	
	
	
	
	//private List<Post> posts;
	
	public Facebook() {
		addPostsToArray();
	}
	
	
	public void addPostsToArray() {
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		
		for(List<Post> page: result) {
			for(Post aPost : page) {
				posts.add(aPost);
			}
		}
		
		
	}
	
	
	public List<Post> getPosts(){
		return posts;
	}
	
	

}
