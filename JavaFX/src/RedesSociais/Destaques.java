package RedesSociais;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * Objeto que contém todos os posts das redes sociais
 */
public class Destaques implements Filtragem{

	ArrayList<PostGeral> destaques = new ArrayList<PostGeral>();
	
	/**
	 * Construtor da classe
	 * @param emails
	 * @param facebookPosts
	 * @param listaDeTweets
	 */
	public Destaques(ArrayList<PostGeral> emails, ArrayList<PostGeral> facebookPosts, ArrayList<PostGeral> listaDeTweets) {
		destaques.addAll(emails);
		destaques.addAll(facebookPosts);
		destaques.addAll(listaDeTweets);
		Collections.sort(destaques);
		this.destaques = viraLista(destaques);
	}
	
	
	
	
	/**
	 * Retorna o atributo ArrayList<PostGeral> destaques
	 * @return ArrayList<PostGeral>
	 */
	public ArrayList<PostGeral> getDestaques() {
		return destaques;
	}




	/**
	 * Altera o atributo ArrayList<PostGeral> destaques por uma nova lista de PostGeral
	 * @param destaques
	 */
	public void setDestaques(ArrayList<PostGeral> destaques) {
		this.destaques = destaques;
		
	}


	//Funções da interface filtragem
	@Override
	public ArrayList<PostGeral> origemMensagem(ArrayList<PostGeral> fb_posts) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public ArrayList<PostGeral> palavraChave(String palavra, ArrayList<PostGeral> destaques) {
		// TODO Auto-generated method stub
		ArrayList<PostGeral> novaListaPosts = new ArrayList<PostGeral>();
		for(PostGeral post: destaques) {
			if(post.getConteudo().toLowerCase().contains(palavra.toLowerCase())) {
				novaListaPosts.add(post);
			} else if (post.getTitulo().toLowerCase().contains(palavra.toLowerCase())) {
				novaListaPosts.add(post);
			}
		}
		return novaListaPosts;
	}


	@Override
	public ArrayList<PostGeral> vinteQuatroHoras(ArrayList<PostGeral> destaques) {
		ArrayList<PostGeral> last24hours = new ArrayList<PostGeral>();
		
		Calendar calendar = Calendar.getInstance(); 
		Date today = calendar.getTime();
		System.out.println("Data de hoje: " + today.toString());
		
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = calendar.getTime();
		System.out.println("data hà 24h atrás: " + yesterday.toString());
		
		for(PostGeral post : destaques) {
			if(post.getData().compareTo(yesterday) * post.getData().compareTo(today)<=0){
				last24hours.add(post);
			}
		}
		return last24hours;
	}


	@Override
	public PostGeral getPostEspecifico(String titulo) {
		for(PostGeral post: destaques) {
			if((post.createTitulo().equals(titulo))) {
				return post;
			}
		}
		return null;
	}


	@Override
	public ArrayList<PostGeral> viraLista(ArrayList<PostGeral> lista) {
		ArrayList<PostGeral> destaques_Aux = new ArrayList<PostGeral>();
		
		for(int i = lista.size()-1 ; i >= 0 ; i--) {
			destaques_Aux.add((PostGeral) lista.toArray()[i]);
		}
		
		return destaques_Aux;
	}


}
