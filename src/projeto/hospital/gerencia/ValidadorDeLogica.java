package projeto.hospital.gerencia;

import java.io.Serializable;

import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.funcionarios.Cargo;
import projeto.hospital.funcionarios.Funcionario;
import projeto.hospital.funcionarios.Permissao;

/**
 * Valida varios aspectos da logica do sistema.
 * 
 * @author Estacio Pereira
 */
public class ValidadorDeLogica implements Serializable {

	/**
	 * Serial gerado automaticamente. 
	 */
	private static final long serialVersionUID = 8905446257346882767L;

	/**
	 * Valida a permissao de exclusao de algum funcionario.
	 * 
	 * @param funcionario
	 *            Funcionario que deseja excluir outro.
	 */
	public void validaExclusao(Funcionario funcionario) {
		if (!funcionario.temPermissao(Permissao.EXCLUIR_FUNCIONARIOS))
			throw new OperacaoInvalidaException("Exclusao nao pode ser efetuada! Usuario nao eh diretor!");
	}

	/**
	 * Valida se um funcionario tem a permissao para executar uma operacao
	 * 
	 * @param operacao
	 *            Operacao
	 * @param funcionario
	 *            Funcionario
	 */
	public static void validaOperacao(Permissao operacao, Funcionario funcionario) {
		String codigo = funcionario.getMatricula().substring(0, 1);
		if (!Cargo.getPermissoesPorCodigo(codigo).contains(operacao))
			throw new OperacaoInvalidaException("Erro no cadastro de funcionario. O funcionario "
					+ funcionario.getNome() + " nao tem permissao para cadastrar funcionarios.");
	}
}
