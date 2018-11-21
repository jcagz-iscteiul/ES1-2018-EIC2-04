package RedesSociais;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import xml.XML;

public class TwitterMain extends RedeSocial implements Filtragem{

	private Twitter me;
	private XML xml = new XML();
	private ArrayList<PostGeral> tw_tweet = new ArrayList<PostGeral>();

	private static TwitterMain instance = new TwitterMain();

	private TwitterMain() {
		
		try {
			autenticarCliente();
			getTimeLine();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static TwitterMain getInstance() {
		return instance;
	}

	public void fazertweet(String tweet) throws TwitterException {

		me.updateStatus(tweet);

	}

	public void getTimeLine() throws TwitterException {

		List<Status> statuses = me.getHomeTimeline();
		System.out.println("A mostrar timeline ");
		for (Status status : statuses) {
			if (status.getText() != null) {
				Date data = status.getCreatedAt();
				String conteudo = status.getText();
				String titulo = status.getUser().getName();

				tw_tweet.add(new TwitterPost(data, conteudo, titulo));
				//System.out.println(data + " " + titulo + " : " + conteudo);
			}
			
		}
	}

	public void pesquisar(String palavra) throws TwitterException {
		
		List<Status> statuses = me.getHomeTimeline();
		System.out.println("A mostrar timeline ");
		for (Status status : statuses) {
			if (status.getText() != null) {
				if (status.getText().toLowerCase().contains(palavra.toLowerCase())) {
					Date data = status.getCreatedAt();
					String conteudo = status.getText();
					String titulo = status.getUser().getName();
			
					tw_tweet.add(new TwitterPost(data, conteudo, titulo));
					
					
					System.out.println(status.getUser().getName() + ":" + status.getText());
				}
				
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
		

	public XML getXml() {
		return xml;
	}

	@Override
	public void autenticarCliente() {
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("02uUxDelvrtGI6AsX84xvOIUt")
		.setOAuthConsumerSecret("zpNE9qyTArI1DoPQ9TnIshokOJiXFgSwVjZy1ZGyDSFAJ8LbxT")
		.setOAuthAccessToken("1047106477910085633-GWY5PA98YwxX66JAHnQJb6wQV6hde4")
		.setOAuthAccessTokenSecret("sXmpcwzaII4lB8FdQ5xOhU6lwYI1kP2GGIeMyO15or5tP");
		TwitterFactory tf = new TwitterFactory(cb.build());
		me = tf.getInstance();
	}

	

	@Override
	public ArrayList<PostGeral> origemMensagem(ArrayList<PostGeral> fb_posts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PostGeral> palavraChave(String palavra, ArrayList<PostGeral> tweets) {
		
		ArrayList<PostGeral> novaListaPosts = new ArrayList<PostGeral>();
		for(PostGeral post: tweets) {
			if(((TwitterPost)post).getFullPost().toLowerCase().contains(palavra.toLowerCase())) {
				novaListaPosts.add(post);
				System.out.println(post.conteudo);
			}
		}
		return novaListaPosts;
	}

	@Override
	public ArrayList<PostGeral> vinteQuatroHoras(ArrayList<PostGeral> fb_posts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostGeral getPostEspecifico(String titulo) {
		// TODO Auto-generated method stub
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
			str = post.getDate().toString() + " - " + post.getConteudo() + "... ";
		} else {
			str = post.getDate().toString()  + " - " + post.getConteudo().substring(0, 22) + "... ";
		}
		return str;
	}
	
	
	
	
	
	
	public static void main(String[] args) throws TwitterException {
		
	}
}
