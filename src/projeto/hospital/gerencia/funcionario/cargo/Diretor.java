package projeto.hospital.gerencia.funcionario.cargo;

import projeto.util.Constantes;

public class Diretor extends Cargo {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = -973354028002391051L;

	/**
	 * Construtor de diretor.
	 */
	public Diretor() {
		super(Constantes.DIRETOR_GERAL);
		
		this.permissoes.add(Permissao.CADASTRAR_FUNCIONARIOS);
		this.permissoes.add(Permissao.EXCLUIR_FUNCIONARIOS);
		this.permissoes.add(Permissao.ATUALIZAR_INFORMACOES_FUNCIONARIOS);
		this.permissoes.add(Permissao.CADASTRAR_MEDICAMENTO);
		this.permissoes.add(Permissao.CADASTRAR_PACIENTES);
		this.permissoes.add(Permissao.REALIZAR_PROCEDIMENTO);
	}

}
