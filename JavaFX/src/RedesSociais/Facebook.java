package RedesSociais;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.User;

import xml.XML;

import com.restfb.Version;

//import java.util.List;
/**
 * Simula a rede social facebook
 * @author
 * @version
 *
 */
public class Facebook implements Filtragem{  //implements interfaceFiltragem
	
	private final Version version = Version.VERSION_2_11;
	private final String accessToken;
	private FacebookClient fbClient;
	private XML xml = new XML();
	



	private final User me;
	private ArrayList<FacebookPost> fb_posts = new ArrayList<FacebookPost>();
	//pageID : 245783216099056
	
	
	public Facebook() throws ParserConfigurationException, SAXException, IOException {
		
		accessToken = xml.getAcessToken();
		fbClient = new DefaultFacebookClient(accessToken, version);
		me = fbClient.fetchObject("me", User.class);
		addPostsToArray();
	}
	
	
	/**
	 * Adiciona os posts do utilizador ao atributo fb_posts
	 */
	public void addPostsToArray() {
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		
		for(List<Post> page: result) {
			for(Post aPost : page) {
				if(aPost.getMessage() != null)
					fb_posts.add(new FacebookPost(aPost));
			}
		}
		
		
		
		
	}
	
	
	/**
	 * 
	 * @return 
	 */
	public ArrayList<FacebookPost> getPosts(){
		return fb_posts;
	}
	
	/**
	 * Altera o atributo fb_posts com uma lista nova
	 * @param fb_posts
	 */
	public void setPosts(ArrayList<FacebookPost> fb_posts) {
		this.fb_posts = fb_posts;
	}

	//Funções da Interface Filtragem

	
	
	@Override
	public ArrayList<FacebookPost> origemMensagem(ArrayList<FacebookPost> fb_posts) {
		return null;
	}



	@Override
	public ArrayList<FacebookPost> palavraChave(String palavra, ArrayList<FacebookPost> fb_posts) {
		// TODO Auto-generated method stub
		ArrayList<FacebookPost> novaListaPosts = new ArrayList<FacebookPost>();
		for(FacebookPost post: fb_posts) {
			if(post.getFullPost().toLowerCase().contains(palavra.toLowerCase())) {
				novaListaPosts.add(post);
			}
		}
		return novaListaPosts;
	}



	@Override
	public ArrayList<FacebookPost> vinteQuatroHoras(ArrayList<FacebookPost> fb_posts) {
		ArrayList<FacebookPost> last24hours = new ArrayList<FacebookPost>();
		
		Calendar calendar = Calendar.getInstance(); 
		Date today = calendar.getTime();
		System.out.println("Data de hoje: " + today.toString());
		
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = calendar.getTime();
		System.out.println("data hà 24h atrás: " + yesterday.toString());
		
		for(FacebookPost post : fb_posts) {
			if(post.getDate().compareTo(yesterday) * post.getDate().compareTo(today)<=0){
				last24hours.add(post);
			}
		}
		
		return last24hours;
	}



	@Override
	public FacebookPost getPostEspecifico(String titulo) {
		for(FacebookPost post: fb_posts) {
			if(post.getPostPreview().equals(titulo)) {
				return post;
			}
		}
		return null;
	}
	
	public XML getXml() {
		return xml;
	}
	

}
