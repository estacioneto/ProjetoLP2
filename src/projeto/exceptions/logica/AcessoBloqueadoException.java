package projeto.exceptions.logica;

/**
 * Classe de Excecao nao checada para o caso de acesso por pessoas nao
 * credenciadas.
 * 
 * @author Estacio Pereira
 */
public class AcessoBloqueadoException extends LogicaInvalidaException {

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
	 * @param mensagem
	 *            Mensagem da Excecao.
	 */
	public AcessoBloqueadoException(String mensagem) {
		super(mensagem);
	}
}
