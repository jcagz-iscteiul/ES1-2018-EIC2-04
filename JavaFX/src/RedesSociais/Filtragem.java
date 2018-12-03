package RedesSociais;

import java.util.ArrayList;

public interface Filtragem{
	
	/**
	 * Retorna uma nova lista de PostGeral com as respetivas palavras escolhidas pelo utilizador
	 * @param palavra
	 * @param lista
	 * @return ArrayList<PostGeral>
	 */
	public ArrayList<PostGeral> palavraChave(String palavra, ArrayList<PostGeral> lista);
	
	/**
	 * Retorna uma nova lista com os posts das últimas 24 horas
	 * @param fb_posts
	 * @return ArrayList<PostGeral>
	 */
	public ArrayList<PostGeral> vinteQuatroHoras(ArrayList<PostGeral> lista);
	
	/**
	 * Retorna um PostGeral passando o título desse post como argumento
	 * @param titulo
	 * @return PostGeral
	 */
	public PostGeral getPostEspecifico(String titulo);
	
	
	/**
	 * Vira a lista de PostGeral (ascendente ou descente) de acordo com a data de cada PostGeral
	 */
	public ArrayList<PostGeral> viraLista(ArrayList<PostGeral> lista);
	
	

}
