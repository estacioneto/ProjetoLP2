package projeto.exceptions.dados;

/**
 * Classe de Excecao nao checada para o caso de um dado passado nao ser valido.
 * 
 * @author Estacio Pereira
 */
public class DadoInvalidoException extends Exception {

	/**
	 * Serial padrao.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor com mensagem padrao.
	 */
	public DadoInvalidoException() {
		super("Dado invalido!");
	}

	/**
	 * Construtor com mensagem definida.
	 * 
	 * @param mensagem
	 *            Mensagem da excecao.
	 */
	public DadoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
