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
import com.restfb.types.send.SendResponse;

import baseDados.BaseDados;
import xml.XML;

import com.restfb.Version;


/**
 * Simula a rede social facebook
 *
 */
public class Facebook extends RedeSocial implements Filtragem{  //implements interfaceFiltragem
	
	private final Version version = Version.VERSION_2_11;
	private String accessToken;
	private FacebookClient fbClient;
	
	/**
	 * Construtor da classe
	 */
	public Facebook() {
		try {
			this.online = true;
			this.db = new BaseDados();
			autenticarCliente();
			addPostsToArray();
		} catch (Exception e) {
			System.out.println("Nao foi possivel ligar-se ao Facebook");
			this.online = false;
			db = new BaseDados();
			this.lista_posts = db.getFacebookPosts();
		}

	
	}
	
	/**
	 * Adiciona os posts do utilizador ao atributo fb_posts
	 */
	public void addPostsToArray() {
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		int i=1;
		for(List<Post> page: result) {
			for(Post aPost : page) {
				if(aPost.getMessage() != null) {
					Date data = aPost.getCreatedTime();
					String conteudo = aPost.getMessage();
					String titulo = createPostPreview(aPost);
					
					lista_posts.add(new FacebookPost(i,data, conteudo, titulo));
					i++;
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
	
	
	
	//Funções da Interface Filtragem
	@Override
	public ArrayList<PostGeral> origemMensagem(ArrayList<PostGeral> fb_posts) {
		return null;
	}

	@Override
	public ArrayList<PostGeral> palavraChave(String palavra, ArrayList<PostGeral> fb_posts) {
		ArrayList<PostGeral> novaListaPosts = new ArrayList<PostGeral>();
		for(PostGeral post: fb_posts) {
			if(((FacebookPost)post).getConteudo().toLowerCase().contains(palavra.toLowerCase())) {
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
			if(((FacebookPost)post).getData().compareTo(yesterday) * ((FacebookPost)post).getData().compareTo(today)<=0){
				last24hours.add(post);
			}
		}
		return last24hours;
	}

	@Override
	public FacebookPost getPostEspecifico(String titulo) {
		for(PostGeral post: lista_posts) {
			if(((FacebookPost)post).getTitulo().equals(titulo)) {
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
			System.out.println("O FACEBOOK ESTA DESLIGADO [Excepção]");
		}
		

	}
	
	@Override
	public ArrayList<PostGeral> viraLista(ArrayList<PostGeral> lista) {
		ArrayList<PostGeral> posts_Aux = new ArrayList<PostGeral>();
		
		for(int i = lista.size()-1 ; i >= 0 ; i--) {
			posts_Aux.add((PostGeral) lista.toArray()[i]);
		}
		
		return posts_Aux;
	}

	@Override
	public void refrescarConteudo() {
		lista_posts.clear();
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		int i=1;
		for(List<Post> page: result) {
			for(Post aPost : page) {
				if(aPost.getMessage() != null) {
					Date data = aPost.getCreatedTime();
					String conteudo = aPost.getMessage();
					String titulo = createPostPreview(aPost);
					
					lista_posts.add(new FacebookPost(i,data, conteudo, titulo));
					i++;
				}
					
			}
		}
	}
	
	
}
