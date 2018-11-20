package RedesSociais;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.Query;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterAPIConfiguration;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.api.TweetsResources;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthSupport;
import twitter4j.conf.ConfigurationBuilder;
import xml.XML;

public class Twitter extends RedeSocial{
	
	private static twitter4j.Twitter me;
	private XML xml = new XML();
	
	  public static void main(String args[]) throws Exception {
		  
		  ConfigurationBuilder cb = new ConfigurationBuilder();
		   cb.setDebugEnabled(true)  
		  .setOAuthConsumerKey("02uUxDelvrtGI6AsX84xvOIUt")
		  .setOAuthConsumerSecret("zpNE9qyTArI1DoPQ9TnIshokOJiXFgSwVjZy1ZGyDSFAJ8LbxT")
		  .setOAuthAccessToken("1047106477910085633-GWY5PA98YwxX66JAHnQJb6wQV6hde4")
		  .setOAuthAccessTokenSecret("sXmpcwzaII4lB8FdQ5xOhU6lwYI1kP2GGIeMyO15or5tP");
		  TwitterFactory tf = new TwitterFactory(cb.build());
		  me = tf.getInstance(); 
		  //fazertweet();
		  //getTimeLine();
		  pesquisartweet("ISCTEIUL");
		  
	 }
	  
	public static void fazertweet() throws TwitterException {
		me.updateStatus("Oi miao");
	}
	
	public static void getTimeLine() throws TwitterException {
		
		    List<Status> statuses = me.getHomeTimeline();
		    System.out.println("A mostrar timeline ");
		    for (Status status : statuses) {
		        System.out.println(status.getUser().getName() + ":" +
		                           status.getText());
		   }
		
	}
	
	

	
	@SuppressWarnings("static-access")
	public static void pesquisartweet(String pesquisa) throws TwitterException {
		twitter4j.Query query = new twitter4j.Query("@"+ pesquisa);
		//query.setResultType(query.POPULAR);
		//query.setCount(100);
		QueryResult result;      
        result = me.search(query);
        List<Status> tweets = result.getTweets();
        for (Status tweet : tweets) {
            System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
        }
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	public XML getXml() {
		return xml;
	}

	@Override
	public void autenticarCliente() {
		// TODO Auto-generated method stub
		
	}

//	public static autenticarCliente() {
//		
//
//	ConfigurationBuilder cb = new ConfigurationBuilder();
//	   cb.setDebugEnabled(true)  
//	  .setOAuthConsumerKey("02uUxDelvrtGI6AsX84xvOIUt")
//	  .setOAuthConsumerSecret("zpNE9qyTArI1DoPQ9TnIshokOJiXFgSwVjZy1ZGyDSFAJ8LbxT")
//	  .setOAuthAccessToken("1047106477910085633-GWY5PA98YwxX66JAHnQJb6wQV6hde4")
//	  .setOAuthAccessTokenSecret("sXmpcwzaII4lB8FdQ5xOhU6lwYI1kP2GGIeMyO15or5tP");
//	
//		
//	}
//		  
		 
}

