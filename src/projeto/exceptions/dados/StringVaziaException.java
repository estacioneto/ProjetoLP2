package projeto.exceptions.dados;

/**
 * Classe de Excecao nao checada para o caso de uma dada String ser vazia.
 * 
 * @author Estacio Pereira
 */
public class StringVaziaException extends DadoInvalidoException {

	/**
	 * Serial padrao.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor com mensagem padrao.
	 */
	public StringVaziaException() {
		super("A String nao pode ser vazia!");
	}

	/**
	 * Construtor com mensagem definida.
	 * 
	 * @param mensagem
	 *            Mensagem da excecao.
	 */
	public StringVaziaException(String mensagem) {
		super(mensagem);
	}
}
