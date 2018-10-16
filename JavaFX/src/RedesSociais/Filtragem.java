package RedesSociais;

import java.util.ArrayList;

public interface Filtragem {
	
	//Organizar post de acordo com a origem da mensagem
	public ArrayList<FacebookPost> origemMensagem(ArrayList<FacebookPost> fb_posts);
	
	//Recebe uma palavra chave no conteudo
	public ArrayList<FacebookPost> palavraChave(String palavra, ArrayList<FacebookPost> fb_posts);
	
	//últimas 24 horas
	public ArrayList<FacebookPost> vinteQuatroHoras(ArrayList<FacebookPost> fb_posts);
	
	//vai buscar um post especifico atraves do post_preview
	public FacebookPost getPostEspecifico(String titulo);

}
