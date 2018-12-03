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
	
	/**
	 * Conecta-se a base de dados
	 */
	public void connectToDB() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dadosBDA.db");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Nao foi possivel conectar-se a base de dados");
		}
		System.out.println("Opened database successfully");
	}

	/**
	 * Cria uma nova tabela na base de dados. Passa uma string para 
	 * designar o nome da nova tabela
	 * @param redeSocial
	 */
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
	         System.out.println("A tabela que tentou criar já existe");
	      }
	      System.out.println("Table created " + redeSocial + " successfully");
	}
	
	/**
	 * Cria uma nova tabela para o Gmail na base de dados
	 */
	public void createTableGmail() {
		 Connection c = null;
	     Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:dadosBDA.db");
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "CREATE TABLE " + "Gmail" +
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
	    	 System.out.println("A tabela que tentou criar já existe");
	      }
	      System.out.println("Table created GMAIL successfully");
	}
	
	/**
	 * Retorna uma String com os valores passados como parâmetros
	 * @param Id
	 * @param redeSocial
	 * @param titulo
	 * @param conteudo
	 * @param data
	 * @return String
	 */
	public String createSqlValues(int Id, String redeSocial, String titulo, String conteudo, String data) {
		String sqlValues = "VALUES (" + Id + ", '" + redeSocial + "', '" +
							titulo + "', '" + conteudo + "', '" +
							data + "' );";
		return sqlValues;
	}
	
	/**
	 * Insere os posts na tabela designada pelo parâmetro redeSocial
	 * @param redeSocial
	 * @param posts
	 */
	public void insertOperation(String redeSocial, ArrayList<PostGeral> posts) {
		System.out.println("====>INSERT OPERATION<====");
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
	        	 sqlValues = sql + createSqlValues(post.getId(), redeSocial, post.getTitulo(), post.getConteudo().replaceAll("'", ""), post.getData().toString());
	        	 stmt.executeUpdate(sqlValues);
	         }
	         stmt.close();
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	         System.out.println("Existem ID's iguais");
	         System.out.println("Elimina primeiro a tabela da respetiva rede social: " + redeSocial + "\n");
	      }
	      System.out.println("Records created in " + redeSocial + " successfully");
	}
	
	/**
	 * Retorna uma String com os valores passados como parâmetros
	 * @param Id
	 * @param redeSocial
	 * @param titulo
	 * @param conteudo
	 * @param data
	 * @param from
	 * @param to
	 * @return String
	 */
	public String createSqlValuesGmail(int Id, String titulo, String conteudo, String data, String from, String to) {
		String sqlValues = "VALUES (" + Id + ", '" + "Gmail" + "', '" +
							titulo + "', '" + conteudo + "', '" +
							data + "', '" + from + "', '" + to + "' );";
		return sqlValues;
	}
	
	/**
	 * Insere os posts na tabela Gmail
	 * @param redeSocial
	 * @param posts
	 */
	public void insertOperationGmail(ArrayList<PostGeral> posts) {
		Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:dadosBDA.db");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO " + "Gmail" + " (ID,REDESOCIAL,TITULO,CONTEUDO,DATA,DE,PARA) ";
	         String sqlValues = "";
	         for(PostGeral post: posts) {
	        	 sqlValues = sql + createSqlValuesGmail(post.getId(), post.getTitulo(), post.getConteudo().replaceAll("'", ""), post.getData().toString(), ((EmailPost)post).getFrom(), ((EmailPost)post).getTo());
	        	 stmt.executeUpdate(sqlValues);
	         }
	         stmt.close();
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	         System.out.println("Existem ID's iguais");
	         System.out.println("Elimina primeiro a tabela da respetiva rede social: " + "Gmail" + "\n");
	      }
	      System.out.println("Records created in " + "Gmail" + " successfully");
	}
	
	/**
	 * Retorna numa ArrayList<PostGeral> os posts da tabela Facebook
	 * @return ArrayList<PostGeral>
	 */
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
		      System.out.println("Nao foi possivel ir buscar os posts do Facebook a base de dados");
		   }
		   System.out.println("Operation get FACEBOOK posts done successfully");
		   return lista;
	}
	
	/**
	 * Retorna numa ArrayList<PostGeral> os posts da tabela Twitter
	 * @return ArrayList<PostGeral>
	 */
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
		         long postID = rs.getLong("POSTID");
		         SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
		         
		         
		         
		         Date data2 = formatter.parse(data);
		         lista.add(new TwitterPost(id, data2, conteudo, titulo,postID));
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
			   System.out.println("Nao foi possivel ir buscar os posts do Twitter a base de dados");
		   }
		   System.out.println("Operation get TWITTER posts done successfully");
		   return lista;
	}
	
	/**
	 * Retorna numa ArrayList<PostGeral> os posts da tabela Gmail
	 * @return ArrayList<PostGeral>
	 */
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
		      System.out.println("Nao foi possivel buscar os posts do Gmail a base de dados");
		   }
		   System.out.println("Operation get GMAIL posts done successfully");
		   return lista;
	}
	
	/**
	 * Elimina os posts da tabela que é designado pelo parâmetro redeSocial
	 * @param redeSocial
	 */
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
	         System.out.println("Nao foi possivel apagar os posts da tabela: " + redeSocial);
	      }
	      System.out.println("Operation delete TABLE " + redeSocial + " done successfully");
	}
	
	
}
