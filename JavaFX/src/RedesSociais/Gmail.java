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
import javax.mail.Authenticator;
import javax.mail.Flags.Flag;

import com.sun.mail.imap.IMAPFolder;

public class Gmail extends RedeSocial implements Filtragem{

	private IMAPFolder folder;
	private Store store;
	private String subject;
	private Flag flag;
	private Properties props;
	private Session session;

	private ArrayList<PostGeral> emails = new ArrayList<PostGeral>();



	public Gmail() {
		try {
			autenticarCliente();
			addEmailsToArray();
			viraArraylist();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void autenticarCliente() {
		props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		session = Session.getDefaultInstance(props, null);

		try {
			store = session.getStore("imaps");
			store.connect("imap.googlemail.com","eic2.04.lei@gmail.com", "ISCTE2018lei");
			folder = (IMAPFolder) store.getFolder("inbox");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void addEmailsToArray() throws MessagingException {
		Message msg;
		String assunto;
		Date data;
		String conteudo;
		String from;
		String to;



		if(!folder.isOpen())
			folder.open(Folder.READ_WRITE);
		Message[] messages = folder.getMessages();

		for (int i=0; i < messages.length;i++) {

			msg =  messages[i];

			assunto = msg.getSubject();
			data = msg.getReceivedDate();
			conteudo = getMessageContent(msg);

			//from = msg.getFrom()[0].toString().replaceAll(">","<").split("<")[1];
			
			
			from = "garcez";
			to = msg.getAllRecipients()[0].toString();
			EmailPost post = new EmailPost(assunto, data, conteudo, from, to);
			emails.add(post);

			

		}
		if (folder != null && folder.isOpen()) { 
			folder.close(true); 
		}
		if (store != null) {
			store.close(); 
		}
	}



	public void mostraMailsDaLista() {
		for(PostGeral e : emails) {
			System.out.println("FROM: " + ((EmailPost) e).getFrom());
			System.out.println("TO: " + ((EmailPost) e).getTo());
			System.out.println("Assunto: " + ((EmailPost) e).getAssunto());
			System.out.println("Conteudo: " + e.getConteudo());
			System.out.println("Data: " + e.getData().toString());
		}
	}




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


	public ArrayList<PostGeral> getEmails() {
		return emails;
	}


	@Override
	public ArrayList<PostGeral> origemMensagem(ArrayList<PostGeral> fb_posts) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<PostGeral> palavraChave(String palavra, ArrayList<PostGeral> fb_posts) {
		// TODO Auto-generated method stub
		ArrayList<PostGeral> novaListaPosts = new ArrayList<PostGeral>();
		for(PostGeral post: emails) {
			if(((EmailPost)post).getConteudo().toLowerCase().contains(palavra.toLowerCase()))
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

		for(PostGeral post : fb_posts) {
			if(((EmailPost)post).getData().compareTo(yesterday) * ((EmailPost)post).getData().compareTo(today)<=0){
				last24hours.add(post);
			}
		}

		return last24hours;
	}


	@Override
	public EmailPost getPostEspecifico(String titulo) {
		// TODO Auto-generated method stub
		for(PostGeral post: emails) {
			if(((EmailPost)post).emailPostPreview().equals(titulo)) {
				return (EmailPost) post;
			}
		}
		return null;
	}



	public void sendEmail(String emailTo, String assunto, String conteudo) {
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.auth", "true"); 
		
		try
        {
        Authenticator auth = new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("eic2.04.lei@gmail.com", "ISCTE2018lei");
            }
          };

        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        msg.setText(conteudo);
        msg.setSubject(assunto);
        msg.setFrom(new InternetAddress("eic2.04.lei@gmail.com"));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
        Transport.send(msg);

        }catch (MessagingException mex) {
           mex.printStackTrace();
        }

	
	}
	
	
	public void viraArraylist() {
		
		ArrayList<PostGeral> emails_Aux = new ArrayList<PostGeral>();
		
		for(int i = emails.size()-1 ; i >= 0 ; i--) {
			emails_Aux.add((PostGeral) emails.toArray()[i]);
		}
		
		emails = emails_Aux;
	}
	
	
//	public static void main(String[] args) {
//		Gmail gm = new Gmail();
//		
//		gm.mostraMailsDaLista();
//	}

}





