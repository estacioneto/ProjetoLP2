package projeto.exceptions.logica;

/**
 * Classe de Excecao nao checada para o caso de erros de logica.
 * 
 * @author Estacio Pereira
 */
public class LogicaInvalidaException extends RuntimeException {

	/**
	 * Serial padrao.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor com mensagem padrao.
	 */
	public LogicaInvalidaException() {
		super("Logica invalida!");
	}

	/**
	 * Construtor com mensagem definida.
	 * 
	 * @param mensagem
	 *            Mensagem da Excecao.
	 */
	public LogicaInvalidaException(String mensagem) {
		super(mensagem);
	}
}
