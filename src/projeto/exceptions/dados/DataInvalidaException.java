package projeto.exceptions.dados;

/**
 * Classe de Excecao nao checada para o caso de uma dada data ser invalida.
 * 
 * @author Estacio Pereira
 */
public class DataInvalidaException extends DadoInvalidoException {

	/**
	 * Serial padrao.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor com mensagem padrao.
	 */
	public DataInvalidaException() {
		super("A data nao eh valida!");
	}

	/**
	 * Construtor com mensagem definida.
	 * 
	 * @param mensagem
	 *            Mensagem da excecao.
	 */
	public DataInvalidaException(String mensagem) {
		super(mensagem);
	}
}