package RedesSociais;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags.Flag;

import com.sun.mail.imap.IMAPFolder;

public class Gmail extends RedeSocial{

	private IMAPFolder folder;
	private Store store;
	private String subject;
	private Flag flag;

	private ArrayList<EmailPost> emails = new ArrayList<EmailPost>();



	public Gmail() {
		autenticarCliente();
	}


	@Override
	public void autenticarCliente() {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		Session session = Session.getDefaultInstance(props, null);

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

			from = msg.getFrom()[0].toString().replaceAll(">","<").split("<")[1];
			to = msg.getAllRecipients()[0].toString();

			emails.add(new EmailPost(assunto, data, conteudo , from, to));



		}
		if (folder != null && folder.isOpen()) { 
			folder.close(true); 
		}
		if (store != null) {
			store.close(); 
		}
	}



	public void mostraMailsDaLista() {
		for(EmailPost e : emails) {
			System.out.println("FROM: " + e.getFrom());
			System.out.println("TO: " + e.getTo());
			System.out.println("Assunto: " + e.getAssunto());
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


	public static void main(String[] args) {
		Gmail g = new Gmail();
		try {
			g.addEmailsToArray();
			g.mostraMailsDaLista();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
