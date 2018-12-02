package RedesSociais;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javax.mail.Authenticator;
import javax.mail.Flags.Flag;

import com.sun.mail.imap.IMAPFolder;

import baseDados.BaseDados;
import xml.XML;

/**
 * Simula o Gmail
 * 
 */
public class Gmail extends RedeSocial implements Filtragem {

	private IMAPFolder folder;
	private Store store;
	private String subject;
	private Flag flag;
	private Properties props;
	private Session session;


	/**
	 * Construtor da classe Gmail 
	 */
	public Gmail() {
		try {
			autenticarCliente();
			addEmailsToArray();
			viraLista();
		} catch (Exception e) {
			System.out.println("Nao foi possivel ligar-se ao email");
			db = new BaseDados();
			lista_posts = db.getGmailPosts();
		}
	}

	

	/**
	 * Adiciona os emails da respetiva conta ao atributo emails
	 * 
	 * @throws MessagingException
	 */
	public void addEmailsToArray() throws MessagingException {
		Message msg;
		String assunto;
		Date data;
		String conteudo;
		String from;
		String to;

		if (!folder.isOpen())
			folder.open(Folder.READ_WRITE);
		Message[] messages = folder.getMessages();

		for (int i = 0; i < messages.length; i++) {

			msg = messages[i];

			assunto = msg.getSubject();
			data = msg.getReceivedDate();
			conteudo = getMessageContent(msg);

			// from = msg.getFrom()[0].toString().replaceAll(">","<").split("<")[1];

			from = "garcez";
			to = msg.getAllRecipients()[0].toString();
			EmailPost post = new EmailPost(i,assunto, data, conteudo, from, to);
			lista_posts.add(post);

		}
		if (folder != null && folder.isOpen()) {
			folder.close(true);
		}
		if (store != null) {
			store.close();
		}
	}

	/**
	 * Usa um ciclo for para percorrer a ArrayList do atributo emails e mostra na
	 * consola as informações de cada email
	 */
	public void mostraMailsDaLista() {
		for (PostGeral e : lista_posts) {
			System.out.println("FROM: " + ((EmailPost) e).getFrom());
			System.out.println("TO: " + ((EmailPost) e).getTo());
			System.out.println("Assunto: " + ((EmailPost) e).getTitulo());
			System.out.println("Conteudo: " + e.getConteudo());
			System.out.println("Data: " + e.getData().toString());
		}
	}

	/**
	 * Através do parâmetro message vai buscar o conteúdo da mensagem e vai
	 * retorná-la em String.
	 * 
	 * @param message
	 * @return String
	 * @throws MessagingException
	 */
	private String getMessageContent(Message message) throws MessagingException {
		try {
			Object content = message.getContent();
			if (content instanceof Multipart) {
				StringBuffer messageContent = new StringBuffer();
				Multipart multipart = (Multipart) content;
				for (int i = 0; i < multipart.getCount(); i++) {
					Part part = multipart.getBodyPart(i);
					if (part.isMimeType("text/plain")) {
						messageContent.append(part.getContent().toString());
					}
				}
				return messageContent.toString();
			}
			return content.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	

	/**
	 * Vai enviar para o emailTo com o respetivo assunto e conteudo.
	 * 
	 * @param emailTo
	 * @param assunto
	 * @param conteudo
	 */
	public void sendEmail(String emailTo, String assunto, String conteudo) {
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
	
		try {
			Authenticator auth = new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					try {
						return new PasswordAuthentication(xml.getGmailEmail(), xml.getGmailPassword());
					} catch (ParserConfigurationException | SAXException | IOException e) {
						e.printStackTrace();
					}
					return null;
				}
			};
	
			Session session = Session.getInstance(props, auth);
	
			MimeMessage msg = new MimeMessage(session);
			msg.setText(conteudo);
			msg.setSubject(assunto);
			try {
				msg.setFrom(new InternetAddress(xml.getGmailEmail()));
			} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
			Transport.send(msg);
	
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	
	}


	//Funções interface filtragem
	@Override
	public ArrayList<PostGeral> origemMensagem(ArrayList<PostGeral> fb_posts) {
		return null;
	}

	@Override
	public ArrayList<PostGeral> palavraChave(String palavra, ArrayList<PostGeral> fb_posts) {
		String str;
		ArrayList<PostGeral> novaListaPosts = new ArrayList<PostGeral>();
		for (PostGeral post : lista_posts) {
			str = ((EmailPost) post).emailPostPreview() + ((EmailPost) post).getConteudo();
			if (str.toLowerCase().contains(palavra.toLowerCase()))
				novaListaPosts.add(post);
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

		for (PostGeral post : fb_posts) {
			if (((EmailPost) post).getData().compareTo(yesterday)
					* ((EmailPost) post).getData().compareTo(today) <= 0) {
				last24hours.add(post);
			}
		}

		return last24hours;
	}

	@Override
	public EmailPost getPostEspecifico(String titulo) {
		for (PostGeral post : lista_posts) {
			if (((EmailPost) post).emailPostPreview().equals(titulo)) {
				return (EmailPost) post;
			}
		}
		return null;
	}
	
	@Override
	public void viraLista() {
		ArrayList<PostGeral> emails_Aux = new ArrayList<PostGeral>();

		for (int i = lista_posts.size() - 1; i >= 0; i--) {
			emails_Aux.add((PostGeral) lista_posts.toArray()[i]);
		}

		lista_posts = emails_Aux;
	}
	
	@Override
	public void autenticarCliente() {
		props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		session = Session.getDefaultInstance(props, null);

		try {
			store = session.getStore("imaps");
			try {
				store.connect("imap.googlemail.com", xml.getGmailEmail(), xml.getGmailPassword());
			} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}
			folder = (IMAPFolder) store.getFolder("inbox");
		} catch (MessagingException e) {
			
			System.out.println("O GMAIL ESTA DESLIGADO [Excepção]");
		}

	}


	@Override
	public void refrescarConteudo() {
		try {
			lista_posts.clear();
			Message msg;
			String assunto;
			Date data;
			String conteudo;
			String from;
			String to;
	
			if (!folder.isOpen())
				folder.open(Folder.READ_WRITE);
			Message[] messages = folder.getMessages();
	
			for (int i = 0; i < messages.length; i++) {
	
				msg = messages[i];
	
				assunto = msg.getSubject();
				data = msg.getReceivedDate();
				conteudo = getMessageContent(msg);
	
				// from = msg.getFrom()[0].toString().replaceAll(">","<").split("<")[1];
	
				from = "garcez";
				to = msg.getAllRecipients()[0].toString();
				EmailPost post = new EmailPost(i,assunto, data, conteudo, from, to);
				lista_posts.add(post);
	
			}
			
			if (folder != null && folder.isOpen()) {
				folder.close(true);
			}
			
			if (store != null) {
				store.close();
			}
			
		} catch (MessagingException e) {
			System.out.println("Nao foi possivel dar refresh no conteudo do Gmail");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		

		
	}


}
