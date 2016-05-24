package projeto.util;

import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.gerencia.funcionario.Funcionario;
import projeto.hospital.gerencia.funcionario.cargo.Permissao;

/**
 * Valida varios aspectos da logica do sistema.
 * 
 * @author Estacio Pereira
 */
public abstract class ValidadorDeLogica {

	/**
	 * Valida a permissao de exclusao de algum funcionario.
	 * 
	 * @param funcionario
	 *            Funcionario que deseja excluir outro.
	 */
	public static void validaExclusao(Funcionario funcionario) {
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
		if (!funcionario.getCargo().temPermissao(operacao))
			throw new OperacaoInvalidaException(String.format(mensagem_erro, funcionario.getNome()));
	}
}
