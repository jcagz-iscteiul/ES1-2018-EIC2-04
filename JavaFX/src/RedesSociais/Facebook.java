package RedesSociais;

import java.util.ArrayList;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.User;
import com.restfb.Version;

//import java.util.List;

public class Facebook implements Filtragem{  //implements interfaceFiltragem
	
	private final Version version = Version.VERSION_2_11;
	private final String accessToken = "EAAGZCQ9NArewBAKqCLOMl8JiAPX4lU6WF9XDvgYo9oNMop4dPHECQtop2AUMcJu6zqS390WpmDj2lbhyzsD37G0M5NfGZC86TMQkw5ab4md0ZAaqSSBEtTgb6IvFntopAxLmQLnRcLBqNJ2pYc9j6IwCxshhZBkMWoNGt8x7rHhMDgIuIsXZA";
	private FacebookClient fbClient = new DefaultFacebookClient(accessToken, version);
	private final User me = fbClient.fetchObject("me", User.class);
	private ArrayList<FacebookPost> fb_posts = new ArrayList<FacebookPost>();
	//pageID : 245783216099056
	
	
	public Facebook() {
		addPostsToArray();
	}
	
	
	
	public void addPostsToArray() {
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		
		for(List<Post> page: result) {
			for(Post aPost : page) {
				if(aPost.getMessage() != null)
					fb_posts.add(new FacebookPost(aPost));
			}
		}
		
		
		
		
	}
	
	
	
	public ArrayList<FacebookPost> getPosts(){
		return fb_posts;
	}

	//Funções da Interface Filtragem

	@Override
	public ArrayList<FacebookPost> origemMensagem(ArrayList<FacebookPost> fb_posts) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ArrayList<FacebookPost> palavraChave(String palavra, ArrayList<FacebookPost> fb_posts) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ArrayList<FacebookPost> vinteQuatroHoras(ArrayList<FacebookPost> fb_posts) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
