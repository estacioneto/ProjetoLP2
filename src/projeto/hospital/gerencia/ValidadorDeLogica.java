package projeto.hospital.gerencia;

import java.io.Serializable;

import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.funcionarios.Cargo;
import projeto.hospital.funcionarios.Funcionario;
import projeto.hospital.funcionarios.Permissao;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;

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
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_EXCLUSAO_FUNCIONARIO + "O funcionario "
					+ funcionario.getNome() + " nao tem permissao para excluir funcionarios.");
	}

	/**
	 * Valida se um funcionario tem a permissao para executar uma operacao
	 * 
	 * @param mensagem_erro
	 *            Mensagem de erro da operacao.
	 * @param operacao
	 *            Operacao a ser validada.
	 * @param funcionario
	 *            Funcionario.
	 */
	public static void validaOperacao(String mensagem_erro, Permissao operacao, Funcionario funcionario) {
		String codigo = funcionario.getMatricula().substring(Constantes.ZERO, Constantes.UM);
		if (!Cargo.getPermissoesPorCodigo(codigo).contains(operacao))
			throw new OperacaoInvalidaException(String.format(mensagem_erro, funcionario.getNome()));
	}
}
