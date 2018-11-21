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

public class Twitter2 extends RedeSocial {

	private static Twitter me;
	private XML xml = new XML();
	private static ArrayList<TwitterTweets> tw_tweet = new ArrayList<TwitterTweets>();

	private static Twitter2 instance = new Twitter2();

	private Twitter2() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("02uUxDelvrtGI6AsX84xvOIUt")
				.setOAuthConsumerSecret("zpNE9qyTArI1DoPQ9TnIshokOJiXFgSwVjZy1ZGyDSFAJ8LbxT")
				.setOAuthAccessToken("1047106477910085633-GWY5PA98YwxX66JAHnQJb6wQV6hde4")
				.setOAuthAccessTokenSecret("sXmpcwzaII4lB8FdQ5xOhU6lwYI1kP2GGIeMyO15or5tP");
		TwitterFactory tf = new TwitterFactory(cb.build());
		me = tf.getInstance();
	}

	public static Twitter2 getInstance() {
		return instance;
	}

	public static void fazertweet(String tweet) throws TwitterException {

		me.updateStatus(tweet);

	}

	public static void getTimeLine() throws TwitterException {

		List<Status> statuses = me.getHomeTimeline();
		System.out.println("A mostrar timeline ");
		for (Status status : statuses) {
			if (status.getText() != null) {
				Date data = status.getCreatedAt();
				String conteudo = status.getText();
				String titulo = status.getUser().getName();

				tw_tweet.add(new TwitterTweets(data, conteudo, titulo));

			}
		}
	}

	public static void pesquisar(String palavra) throws TwitterException {

		List<Status> statuses = me.getHomeTimeline();
		System.out.println("A mostrar timeline ");
		for (Status status : statuses) {
			if (status.getText() != null) {
				if (status.getText().toLowerCase().contains(palavra.toLowerCase())) {
					Date data = status.getCreatedAt();
					String conteudo = status.getText();
					String titulo = status.getUser().getName();

					tw_tweet.add(new TwitterTweets(data, conteudo, titulo));
					System.out.println(status.getUser().getName() + ":" + status.getText());
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static int another_profile_tweets(int pagina, String user) {
		
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
		
		
	

	// @SuppressWarnings("static-access")
	// public static void pesquisartweet(String pesquisa) throws TwitterException {
	// twitter4j.Query query = new twitter4j.Query("@"+ pesquisa);
	// //query.setResultType(query.POPULAR);
	// //query.setCount(100);
	// QueryResult result;
	// result = me.search(query);
	// List<Status> tweets = result.getTweets();
	// for (Status tweet : tweets) {
	// System.out.println("@" + tweet.getUser().getScreenName() + " - " +
	// tweet.getText());
	// }
	// }

	public XML getXml() {
		return xml;
	}

	@Override
	public void autenticarCliente() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws TwitterException {
		//getTimeLine();
		//fazertweet("EU SOU O MAIOR CR!");
		//pesquisar("CR");
		 System.out.println("Total: " + another_profile_tweets(1, "EicQuatr"));
	}

}
