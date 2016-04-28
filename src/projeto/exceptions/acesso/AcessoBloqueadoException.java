package projeto.exceptions.acesso;

/**
 * Classe de Excecao nao checada para o caso de acesso por pessoas nao
 * credenciadas.
 * 
 * @author Estacio Pereira
 */
public class AcessoBloqueadoException extends RuntimeException {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 7391495912482885485L;

	/**
	 * Construtor com mensagem padrao.
	 */
	public AcessoBloqueadoException() {
		super("Acesso bloqueado!");
	}

	/**
	 * Construtor com mensagem definida.
	 * 
	 * @param menssagem
	 *            Mensagem da Excecao.
	 */
	public AcessoBloqueadoException(String mensagem) {
		super(mensagem);
	}
}
