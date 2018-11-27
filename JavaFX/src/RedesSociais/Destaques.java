package RedesSociais;

import java.util.ArrayList;
import java.util.Collections;

public class Destaques implements Filtragem{

	
	
	ArrayList<PostGeral> destaques = new ArrayList<PostGeral>();
	
	
	public Destaques(ArrayList<PostGeral> emails, ArrayList<PostGeral> facebookPosts, ArrayList<PostGeral> listaDeTweets) {
		destaques.addAll(emails);
		destaques.addAll(facebookPosts);
		destaques.addAll(listaDeTweets);
		Collections.sort(destaques);
		this.viraLista();
	}
	
	
	
	
	
	public ArrayList<PostGeral> getDestaques() {
		return destaques;
	}





	public void setDestaques(ArrayList<PostGeral> destaques) {
		this.destaques = destaques;
		
	}










	@Override
	public ArrayList<PostGeral> origemMensagem(ArrayList<PostGeral> fb_posts) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public ArrayList<PostGeral> palavraChave(String palavra, ArrayList<PostGeral> fb_posts) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public ArrayList<PostGeral> vinteQuatroHoras(ArrayList<PostGeral> fb_posts) {
		// TODO Auto-generated method stub
		return null;
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
	public void viraLista() {
		
		
		ArrayList<PostGeral> destaques_Aux = new ArrayList<PostGeral>();
		
		for(int i = destaques.size()-1 ; i >= 0 ; i--) {
			destaques_Aux.add((PostGeral) destaques.toArray()[i]);
		}
		
		destaques = destaques_Aux;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}




}
