package projeto.hospital.gerencia.funcionario.cargo;

/**
 * Classe que representa o cargo de Tecnico Administrativo.
 * 
 * @author Estacio Pereira
 *
 */
public class TecnicoAdministrativo extends Cargo {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 4048972919015627951L;

	/**
	 * Construtor de tecnico administrativo.
	 * 
	 * @param nome
	 *            Nome do cargo
	 */
	public TecnicoAdministrativo(String nome) {
		super(nome);
		this.permissoes.add(Permissao.CADASTRAR_PACIENTES);
		this.permissoes.add(Permissao.CADASTRAR_MEDICAMENTO);
	}
}
