package baseDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import RedesSociais.EmailPost;
import RedesSociais.Facebook;
import RedesSociais.FacebookPost;
import RedesSociais.Gmail;
import RedesSociais.PostGeral;
import RedesSociais.TwitterObject;
import RedesSociais.TwitterPost;

public class BaseDados {
	
	public void connectToDB() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dadosBDA.db");
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	//Só dá para os posts do Facebook e do Twitter
	public void createTable(String redeSocial) {
		 Connection c = null;
	     Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:dadosBDA.db");
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "CREATE TABLE " + redeSocial +
	                        "(ID INT PRIMARY KEY     NOT NULL," +
	                        " REDESOCIAL           TEXT    NOT NULL, " + 
	                        " TITULO               TEXT    NOT NULL, " + 
	                        " CONTEUDO             TEXT    NOT NULL, " + 
	                        " DATA                 TEXT    NOT NULL)"; 
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//	         System.exit(0);
	      }
	      System.out.println("Table created " + redeSocial + " successfully");
	}
	
	public void createTableGmail(String redeSocial) {
		 Connection c = null;
	     Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:dadosBDA.db");
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "CREATE TABLE " + redeSocial +
	                        "(ID INT PRIMARY KEY     NOT NULL," +
	                        " REDESOCIAL            TEXT    NOT NULL, " + 
	                        " TITULO                TEXT    NOT NULL, " + 
	                        " CONTEUDO              TEXT    NOT NULL, " +
	                        " DATA                  TEXT    NOT NULL, " +
	                        " DE                 	TEXT    NOT NULL, " +
	                        " PARA                  TEXT    NOT NULL)"; 
	         stmt.executeUpdate(sql);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//	         System.exit(0);
	      }
	      System.out.println("Table created GMAIL successfully");
	}
	
	public String createSqlValues(int Id, String redeSocial, String titulo, String conteudo, String data) {
		String sqlValues = "VALUES (" + Id + ", '" + redeSocial + "', '" +
							titulo + "', '" + conteudo + "', '" +
							data + "' );";
		return sqlValues;
	}
	
	//So da para o facebook e para o twitter
	public void insertOperation(String redeSocial, ArrayList<PostGeral> posts) {
		Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:dadosBDA.db");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO " + redeSocial + " (ID,REDESOCIAL,TITULO,CONTEUDO,DATA) ";
	         String sqlValues = "";
	         for(PostGeral post: posts) {
	        	 sqlValues = sql + createSqlValues(post.id, redeSocial, post.getTitulo(), post.getConteudo().replaceAll("'", ""), post.getData().toString());
	        	 System.out.println("SQL Value ID: " + post.id + " \n");
	        	 System.out.println(sqlValues);
	        	 stmt.executeUpdate(sqlValues);
	         }
	         stmt.close();
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.out.println("Existem ID's iguais");
	         System.out.println("Elimina primeiro a tabela da respetiva rede social: " + redeSocial + "\n");
//	         System.exit(0);
	      }
	      System.out.println("Records created in " + redeSocial + " successfully");
	}
	
	public String createSqlValuesGmail(int Id, String redeSocial, String titulo, String conteudo, String data, String from, String to) {
		String sqlValues = "VALUES (" + Id + ", '" + redeSocial + "', '" +
							titulo + "', '" + conteudo + "', '" +
							data + "', '" + from + "', '" + to + "' );";
		return sqlValues;
	}
	
	public void insertOperationGmail(String redeSocial, ArrayList<PostGeral> posts) {
		Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:dadosBDA.db");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO " + redeSocial + " (ID,REDESOCIAL,TITULO,CONTEUDO,DATA,DE,PARA) ";
	         String sqlValues = "";
	         for(PostGeral post: posts) {
	        	 sqlValues = sql + createSqlValuesGmail(post.id, redeSocial, post.getTitulo(), post.getConteudo().replaceAll("'", ""), post.getData().toString(), ((EmailPost)post).getFrom(), ((EmailPost)post).getTo());
	        	 System.out.println("SQL Value ID: " + post.id + " \n");
	        	 System.out.println(sqlValues);
	        	 stmt.executeUpdate(sqlValues);
	         }
	         stmt.close();
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	    	  System.out.println("ENTREI NO CATACH DO INSERT_OPERATIONS_GMAIL");
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.out.println("Existem ID's iguais");
	         System.out.println("Elimina primeiro a tabela da respetiva rede social: " + redeSocial + "\n");
//	         System.exit(0);
	      }
	      System.out.println("Records created in " + redeSocial + " successfully");
	}
	
	public ArrayList<PostGeral> getFacebookPosts() {
		   Connection c = null;
		   Statement stmt = null;
		   ArrayList<PostGeral> lista = new ArrayList<PostGeral>();
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:dadosBDA.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");
		      
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT * FROM FACEBOOK;");
		      
		      while ( rs.next() ) {
		         int id = rs.getInt("ID");
		         String titulo = rs.getString("TITULO");
		         String conteudo = rs.getString("CONTEUDO");
		         String data = rs.getString("DATA");
		         SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
		         Date data2 = formatter.parse(data);
		         lista.add(new FacebookPost(id, data2, conteudo, titulo));
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		   System.out.println("Operation get FACEBOOK posts done successfully");
		   return lista;
	}
	
	public ArrayList<PostGeral> getTwitterPosts() {
		   Connection c = null;
		   Statement stmt = null;
		   ArrayList<PostGeral> lista = new ArrayList<PostGeral>();
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:dadosBDA.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");
		      
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT * FROM TWITTER;");
		      
		      while ( rs.next() ) {
		         int id = rs.getInt("ID");
		         String titulo = rs.getString("TITULO");
		         String conteudo = rs.getString("CONTEUDO");
		         String data = rs.getString("DATA");
		         SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
		         
		         
		         
		         Date data2 = formatter.parse(data);
		         lista.add(new TwitterPost(id, data2, conteudo, titulo));
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		   System.out.println("Operation get TWITTER posts done successfully");
		   return lista;
	}
	
	public ArrayList<PostGeral> getGmailPosts() {
		   Connection c = null;
		   Statement stmt = null;
		   ArrayList<PostGeral> lista = new ArrayList<PostGeral>();
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:dadosBDA.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");
		      
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT * FROM GMAIL;");
		      
		      while ( rs.next() ) {
		         int id = rs.getInt("ID");
		         String titulo = rs.getString("TITULO");
		         String conteudo = rs.getString("CONTEUDO");
		         String data = rs.getString("DATA");
		         String from = rs.getString("DE");
		         String to = rs.getString("PARA");
		         SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
		         Date data2 = formatter.parse(data);
		         lista.add(new EmailPost(id, titulo, data2, conteudo, from, to));
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		   System.out.println("Operation get GMAIL posts done successfully");
		   return lista;
	}
	
	
	public void deleteOperation(String redeSocial) {
		System.out.println("===>DELETE OPERATION<===");
		 Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         
	         c = DriverManager.getConnection("jdbc:sqlite:dadosBDA.db");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         stmt = c.createStatement();
	         String sql = "DELETE FROM " + redeSocial + ";";
	         stmt.executeUpdate(sql);
	         c.commit();
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//	         System.exit(0);
	      }
	      System.out.println("Operation delete TABLE " + redeSocial + " done successfully");
	}
	
	public static void main(String[] args) {
//		TwitterMain tw = new TwitterMain();
//		Gmail gm = new Gmail();
//		BaseDados db = new BaseDados();
//		db.connectToDB();
//		db.createTable("Twitter");
//		db.insertOperationGmail("Gmail", gm.getEmails());
		Facebook fb = new Facebook();
		BaseDados db = new BaseDados();
		db.connectToDB();
		ArrayList<PostGeral> lista = db.getFacebookPosts();
		for(PostGeral post: lista) {
			System.out.println("ID: " + post.id);
			System.out.println("Conteudo: " + post.getConteudo() + "\n");
		}
		
	}
	
}
