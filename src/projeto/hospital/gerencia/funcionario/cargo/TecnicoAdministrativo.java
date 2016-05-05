package projeto.hospital.gerencia.funcionario.cargo;

import projeto.util.Constantes;

public class TecnicoAdministrativo extends Cargo {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 4048972919015627951L;

	/**
	 * Construtor de tecnico administrativo.
	 */
	public TecnicoAdministrativo() {
		super(Constantes.TECNICO_ADMINISTATIVO);
		this.permissoes.add(Permissao.CADASTRAR_PACIENTES);
		this.permissoes.add(Permissao.CADASTRAR_MEDICAMENTO);
	}
}
