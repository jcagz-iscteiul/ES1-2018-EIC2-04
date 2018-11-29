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

public class TwitterObject extends RedeSocial implements Filtragem{

	private Twitter me;
	private XML xml = new XML();
	private ArrayList<PostGeral> tw_tweet = new ArrayList<PostGeral>();
	private BaseDados db;
	
	private static TwitterObject instance = new TwitterObject();

	public TwitterObject() {
		
		try {
			autenticarCliente();
			getTimeLine();
		} catch (TwitterException e) {
			System.out.println("O Twitter ESTA DESLIGADO [Excepção]");
			this.db = new BaseDados();
			this.tw_tweet = db.getTwitterPosts();
		}
	}

	public static TwitterObject getInstance() {
		return instance;
	}

	public void fazertweet(String tweet) throws TwitterException {

		me.updateStatus(tweet);

	}

	public void getTimeLine() throws TwitterException {

		List<Status> statuses = me.getHomeTimeline();
		int i = 0;
		for (Status status : statuses) {
			if (status.getText() != null) {
				Date data = status.getCreatedAt();
				String conteudo = status.getText();
				String titulo = status.getUser().getName();

				tw_tweet.add(new TwitterPost(i,data, conteudo, titulo));
				i++;
				//System.out.println(data + " " + titulo + " : " + conteudo);
			}
			
		}
	}

//	public void pesquisar(String palavra) throws TwitterException {
//		
//		List<Status> statuses = me.getHomeTimeline();
//		System.out.println("A mostrar timeline ");
//		for (Status status : statuses) {
//			if (status.getText() != null) {
//				if (status.getText().toLowerCase().contains(palavra.toLowerCase())) {
//					Date data = status.getCreatedAt();
//					String conteudo = status.getText();
//					String titulo = status.getUser().getName();
//			
//					tw_tweet.add(new TwitterPost(data, conteudo, titulo));
//					
//					
//					System.out.println(status.getUser().getName() + ":" + status.getText());
//				}
//				
//			}
//		}
//	}
	
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
		

	public XML getXml() {
		return xml;
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
			if(((TwitterPost)post).getDate().compareTo(yesterday) * ((TwitterPost)post).getDate().compareTo(today)<=0){
				last24hours.add(post);
			}
		}
		
		return last24hours;
	}

	@Override
	public TwitterPost getPostEspecifico(String titulo) {
		for(PostGeral post: tw_tweet) {
			if(createPostPreview((TwitterPost) post).equals(titulo)) { //getPostPreview retornava o autor e não o preview do conteúdo? ¯\_(O_p)_/¯
				return (TwitterPost) post;
			}
		}
		return null;
	}
	
	public ArrayList<PostGeral> getTw_tweet() {
		return tw_tweet;
	}

	public void setTw_tweet(ArrayList<PostGeral> tw_tweet) {
		this.tw_tweet = tw_tweet;
	}
	
	public String createPost(TwitterPost post) {
		
		String str;
		str = post.getConteudo();
		return str;
	}
	
	public String createPostPreview(TwitterPost post) {
		String str;

		if (post.getConteudo().length() < 22) {
			str = post.getDate().toString() + " - " + "[" +  post.getTitulo() + "]" + ": " + post.getConteudo() + "... ";
		} else {
			str = post.getDate().toString()  + " - " + "[" +  post.getTitulo() + "]" + ": " + post.getConteudo().substring(0, 22) + "... ";
		}
		return str;
	}
	
	@Override
	public void viraLista() {
		ArrayList<PostGeral> emails_Aux = new ArrayList<PostGeral>();
		
		for(int i = tw_tweet.size()-1 ; i >= 0 ; i--) {
			emails_Aux.add((PostGeral) tw_tweet.toArray()[i]);
		}
		
		tw_tweet = emails_Aux;
	}
	
	
	
	public static void main(String[] args) throws TwitterException {
		
	}

	
}
