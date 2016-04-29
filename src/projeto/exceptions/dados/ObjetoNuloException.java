package projeto.exceptions.dados;

/**
 * Classe de Excecao nao checada para o caso de um dado objeto ser nulo.
 * 
 * @author Estacio Pereira
 */
public class ObjetoNuloException extends DadoInvalidoException {

	/**
	 * Serial padrao.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor com mensagem padrao.
	 */
	public ObjetoNuloException() {
		super("O objeto nao pode ser nulo!");
	}

	/**
	 * Construtor com mensagem definida.
	 * 
	 * @param mensagem
	 *            Mensagem da excecao.
	 */
	public ObjetoNuloException(String mensagem) {
		super(mensagem);
	}
}