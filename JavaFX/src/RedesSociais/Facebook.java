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


/**
 * Simula a rede social facebook
 * @author
 * @version
 *
 */
public class Facebook extends RedeSocial implements Filtragem{  //implements interfaceFiltragem
	
	private final Version version = Version.VERSION_2_11;
	private String accessToken;
	private FacebookClient fbClient;
	private XML xml = new XML();
	private final User me;
	private ArrayList<PostGeral> fb_posts = new ArrayList<PostGeral>();
	
	
	public Facebook() throws ParserConfigurationException, SAXException, IOException {
		
		autenticarCliente();
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
				if(aPost.getMessage() != null) {
					Date data = aPost.getCreatedTime();
					String conteudo = aPost.getMessage();
					String titulo = createPostPreview(aPost);
					
					fb_posts.add(new FacebookPost(data, conteudo, titulo));
					
				}
					
			}
		}
	}
	
	/**
	 * Cria um titulo para o post de acordo com o tamanho do texto do post
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
	
	
	/**
	 * Retorna o respetivo atributo ArrayList<PostGeral> fb_posts 
	 * @return ArrayList<PostGeral>
	 */
	public ArrayList<PostGeral> getPosts(){
		return fb_posts;
	}
	
	/**
	 * Altera o atributo fb_posts com uma lista nova
	 * @param fb_posts
	 */
	public void setPosts(ArrayList<PostGeral> fb_posts) {
		this.fb_posts = fb_posts;
	}
	
	/**
	 * Retorna o atributo xml da classe Facebook
	 * @return XML
	 */
	public XML getXml() {
		return xml;
	}

	
	//Funções da Interface Filtragem
	@Override
	public ArrayList<PostGeral> origemMensagem(ArrayList<PostGeral> fb_posts) {
		return null;
	}

	@Override
	public ArrayList<PostGeral> palavraChave(String palavra, ArrayList<PostGeral> fb_posts) {
		// TODO Auto-generated method stub
		ArrayList<PostGeral> novaListaPosts = new ArrayList<PostGeral>();
		for(PostGeral post: fb_posts) {
			if(((FacebookPost)post).getFullPost().toLowerCase().contains(palavra.toLowerCase())) {
				novaListaPosts.add(post);
			}
		}
		return novaListaPosts;
	}

	@Override
	public ArrayList<PostGeral> vinteQuatroHoras(ArrayList<PostGeral> fb_posts) {
		ArrayList<PostGeral> last24hours = new ArrayList<PostGeral>();
		
		Calendar calendar = Calendar.getInstance(); 
		Date today = calendar.getTime();
		System.out.println("Data de hoje: " + today.toString());
		
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = calendar.getTime();
		System.out.println("data hà 24h atrás: " + yesterday.toString());
		
		for(PostGeral post : fb_posts) {
			if(((FacebookPost)post).getDate().compareTo(yesterday) * ((FacebookPost)post).getDate().compareTo(today)<=0){
				last24hours.add(post);
			}
		}
		
		return last24hours;
	}

	@Override
	public FacebookPost getPostEspecifico(String titulo) {
		for(PostGeral post: fb_posts) {
			if(((FacebookPost)post).getPostPreview().equals(titulo)) {
				return (FacebookPost) post;
			}
		}
		return null;
	}

	@Override
	public void autenticarCliente() {
		try {
			accessToken = xml.getFacebookAccessToken();
			fbClient = new DefaultFacebookClient(accessToken, version);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void viraLista() {
		ArrayList<PostGeral> emails_Aux = new ArrayList<PostGeral>();
		
		for(int i = fb_posts.size()-1 ; i >= 0 ; i--) {
			emails_Aux.add((PostGeral) fb_posts.toArray()[i]);
		}
		
		fb_posts = emails_Aux;
	}



	
	
}
