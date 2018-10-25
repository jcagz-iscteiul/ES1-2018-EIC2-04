package RedesSociais;

public abstract class RedeSocial {
	private String accountToken;
	
	public abstract void autenticarCliente();
	
	public abstract void refrescarConteudo();
}
