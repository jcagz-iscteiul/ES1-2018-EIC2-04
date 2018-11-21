package RedesSociais;

import java.util.ArrayList;

public interface Filtragem {
	
	
	/**
	 * Retorna uma ArrayList<FacebookPost> organizado por utilizadores
	 * @param fb_posts
	 * @return ArrayList<FacebookPost>
	 */
	public ArrayList<PostGeral> origemMensagem(ArrayList<FacebookPost> fb_posts);
	
	/**
	 * Retorna uma nova lista de FacebookPost com as respetivas palavras escolhidas pelo utilizador
	 * @param palavra
	 * @param fb_posts
	 * @return ArrayList<FacebookPost>
	 */
	public ArrayList<FacebookPost> palavraChave(String palavra, ArrayList<FacebookPost> fb_posts);
	
	/**
	 * Retorna uma nova lista com os posts das últimas 24 horas
	 * @param fb_posts
	 * @return ArrayList<FacebookPost>
	 */
	public ArrayList<PostGeral> vinteQuatroHoras(ArrayList<FacebookPost> fb_posts);
	
	/**
	 * Retorna um FacebookPost passando o título desse post como argumento
	 * @param titulo
	 * @return FacebookPost
	 */
	public PostGeral getPostEspecifico(String titulo);

}
