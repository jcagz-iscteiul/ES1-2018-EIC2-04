package RedesSociais;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import baseDados.BaseDados;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import xml.XML;


/**
 * Simula a rede social Twitter implementando Singleton
 *
 */
public class TwitterObject extends RedeSocial implements Filtragem{

	private Twitter me;
	
	private static TwitterObject instance = new TwitterObject();
	
	/**
	 * Construtor da classe 
	 * 
	 */
	public TwitterObject() {
		
		try {
			autenticarCliente();
			getTimeLine();
		} catch (TwitterException e) {
			System.out.println("O Twitter ESTA DESLIGADO [Excepção]");
			this.db = new BaseDados();
			this.lista_posts = db.getTwitterPosts();
		}
	}

	public static TwitterObject getInstance() {
		return instance;
	}

	/**
	 * 
	 * @param tweet
	 * @throws TwitterException
	 */
	public void fazertweet(String tweet) throws TwitterException {

		me.updateStatus(tweet);

	}
	
	/**
	 * Obtem os tweets da timeline 
	 * @throws TwitterException
	 */
	public void getTimeLine() throws TwitterException {

		List<Status> statuses = me.getHomeTimeline();
		int i = 1;
		for (Status status : statuses) {
			if (status.getText() != null) {
				Date data = status.getCreatedAt();
				String conteudo = status.getText();
				String titulo = status.getUser().getName();

				lista_posts.add(new TwitterPost(i,data, conteudo, titulo));
				i++;
			}
			
		}
	}

	
	@SuppressWarnings("unchecked")
	public int another_profile_tweets(int pagina, String user) {
		
	    @SuppressWarnings("rawtypes")
		List statuses = new ArrayList();

	    while (true) {

	      try {

	        int size = statuses.size(); 
	        Paging page = new Paging(pagina++, 3);
	        statuses.addAll(me.getUserTimeline(user, page));
	        if (statuses.size() == size)
	          break;
	      }
	      catch(TwitterException e) {

	        e.printStackTrace();
	      }
	    }
	    return (statuses.size());
	}
		
	
	/**
	 * Retorna uma String com a data,título e conteúdo do post passado como parâmetro
	 * @param post
	 * @return String
	 */
	public String createPostPreview(TwitterPost post) {
		String str;

		if (post.getConteudo().length() < 22) {
			str = post.getData().toString() + " - " + "[" +  post.getTitulo() + "]" + ": " + post.getConteudo() + "... ";
		} else {
			str = post.getData().toString()  + " - " + "[" +  post.getTitulo() + "]" + ": " + post.getConteudo().substring(0, 22) + "... ";
		}
		return str;
	}



	

	@Override
	public ArrayList<PostGeral> origemMensagem(ArrayList<PostGeral> fb_posts) {
		return null;
	}

	
	@Override
	public ArrayList<PostGeral> palavraChave(String palavra, ArrayList<PostGeral> tweets) {
		
		ArrayList<PostGeral> novaListaPosts = new ArrayList<PostGeral>();
		for(PostGeral post: tweets) {
			if(createPostPreview((TwitterPost)post).toLowerCase().contains(palavra.toLowerCase())){
				novaListaPosts.add(post);
				System.out.println(post.conteudo);
			}
		}
		return novaListaPosts;
	}

	
	@Override
	public ArrayList<PostGeral> vinteQuatroHoras(ArrayList<PostGeral> tweets) {
		ArrayList<PostGeral> last24hours = new ArrayList<PostGeral>();
		
		Calendar calendar = Calendar.getInstance(); 
		Date today = calendar.getTime();
		System.out.println("Data de hoje: " + today.toString());
		
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = calendar.getTime();
		System.out.println("data hà 24h atrás: " + yesterday.toString());
		
		for(PostGeral post : tweets) {
			if(((TwitterPost)post).getData().compareTo(yesterday) * ((TwitterPost)post).getData().compareTo(today)<=0){
				last24hours.add(post);
			}
		}
		
		return last24hours;
	}

	
	@Override
	public TwitterPost getPostEspecifico(String titulo) {
		for(PostGeral post: lista_posts) {
			if(createPostPreview((TwitterPost) post).equals(titulo)) { //getPostPreview retornava o autor e não o preview do conteúdo? ¯\_(O_p)_/¯
				return (TwitterPost) post;
			}
		}
		return null;
	}
	
	
	@Override
	public void viraLista() {
		ArrayList<PostGeral> emails_Aux = new ArrayList<PostGeral>();
		
		for(int i = lista_posts.size()-1 ; i >= 0 ; i--) {
			emails_Aux.add((PostGeral) lista_posts.toArray()[i]);
		}
		
		lista_posts = emails_Aux;
	}
	
	
	@Override
	public void autenticarCliente() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		try {
			cb.setDebugEnabled(true).setOAuthConsumerKey(xml.getDebugEnable())
			.setOAuthConsumerSecret(xml.getTwitterConsumerSecret())
			.setOAuthAccessToken(xml.getTwitterAccessToken())
			.setOAuthAccessTokenSecret(xml.getTwitterAccessTokenSecret());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		TwitterFactory tf = new TwitterFactory(cb.build());
		me = tf.getInstance();
	}
	
	

	@Override
	public void refrescarConteudo() {
		List<Status> statuses;
		try {
			statuses = me.getHomeTimeline();
			int i = 1;
			lista_posts.clear();
			for (Status status : statuses) {
				if (status.getText() != null) {
					Date data = status.getCreatedAt();
					String conteudo = status.getText();
					String titulo = status.getUser().getName();

					lista_posts.add(new TwitterPost(i,data, conteudo, titulo));
					i++;
				}
			}
		} catch (TwitterException e) {
			System.out.println("Ocorreu um erro ao tentar dar refresh no conteudo do Twitter");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		TwitterObject tw = new TwitterObject();
		System.out.println("ANTES DO REFRESH");
		for(PostGeral post: tw.getLista_posts()) {
			System.out.println("ID: " + post.getId());
			System.out.println("Conteudo: " + post.getConteudo());
		}
		System.out.println("\n");
		tw.refrescarConteudo();
		System.out.println("DEPOIS DO REFRESH");
		for(PostGeral post: tw.getLista_posts()) {
			System.out.println("ID: " + post.getId());
			System.out.println("Conteudo: " + post.getConteudo());
		}
	}

	
}