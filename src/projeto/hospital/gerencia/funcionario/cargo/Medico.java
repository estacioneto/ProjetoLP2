package projeto.hospital.gerencia.funcionario.cargo;

/**
 * Classe que representa o cargo de Medico.
 * 
 * @author Estacio Pereira
 */
public class Medico extends Cargo {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 9207148165069827985L;

	/**
	 * Construtor de tecnico administrativo.
	 * 
	 * @param nome
	 *            Nome do cargo.
	 */
	public Medico(String nome) {
		super(nome);
		this.permissoes.add(Permissao.REALIZA_PROCEDIMENTO);
	}
}
