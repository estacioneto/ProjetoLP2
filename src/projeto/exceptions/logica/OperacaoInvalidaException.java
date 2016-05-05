package projeto.exceptions.logica;

/**
 * Classe de Excecao nao checada para o caso de operacoes nao permitidas.
 * 
 * @author Estacio Pereira
 */
public class OperacaoInvalidaException extends LogicaInvalidaException {

	/**
	 * Serial padrao.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor com mensagem padrao.
	 */
	public OperacaoInvalidaException() {
		super("Operacao invalida!");
	}

	/**
	 * Construtor com mensagem definida.
	 * 
	 * @param mensagem
	 *            Mensagem da Excecao.
	 */
	public OperacaoInvalidaException(String mensagem) {
		super(mensagem);
	}
}
