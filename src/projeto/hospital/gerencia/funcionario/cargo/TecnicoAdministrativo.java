package projeto.hospital.gerencia.funcionario.cargo;

public class TecnicoAdministrativo extends Cargo {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 4048972919015627951L;

	/**
	 * Construtor de tecnico administrativo.
	 */
	public TecnicoAdministrativo(String nome) {
		super(nome);
		this.permissoes.add(Permissao.CADASTRAR_PACIENTES);
		this.permissoes.add(Permissao.CADASTRAR_MEDICAMENTO);
	}
}
