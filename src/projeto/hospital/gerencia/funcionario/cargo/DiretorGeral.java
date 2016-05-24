package projeto.hospital.gerencia.funcionario.cargo;

/**
 * Classe que representa o cargo de Diretor Geral.
 * @author Estacio Pereira
 */
public class DiretorGeral extends Cargo {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = -973354028002391051L;

	/**
	 * Construtor de diretor.
	 * 
	 * @param nome
	 *            Nome do cargo.
	 */
	public DiretorGeral(String nome) {
		super(nome);

		this.permissoes.add(Permissao.CADASTRAR_FUNCIONARIOS);
		this.permissoes.add(Permissao.EXCLUIR_FUNCIONARIOS);
		this.permissoes.add(Permissao.ATUALIZAR_INFORMACOES_FUNCIONARIOS);
		this.permissoes.add(Permissao.CADASTRAR_MEDICAMENTO);
		this.permissoes.add(Permissao.CADASTRAR_PACIENTES);
		this.permissoes.add(Permissao.REALIZA_PROCEDIMENTO);
	}
}
