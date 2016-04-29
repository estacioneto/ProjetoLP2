package projeto.hospital.gerencia;

import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.funcionarios.Funcionario;
import projeto.hospital.funcionarios.Permissao;

/**
 * Valida varios aspectos da logica do sistema.
 * 
 * @author Estacio Pereira
 */
public class ValidadorDeLogica {

	/**
	 * Valida a permissao de exclusao de algum funcionario.
	 * 
	 * @param funcionario
	 *            Funcionario que deseja excluir outro.
	 */
	public void validaExclusao(Funcionario funcionario) {
		if (!funcionario.temPermissao(Permissao.EXCLUIR_FUNCIONARIOS))
			throw new OperacaoInvalidaException(
					"Exclusao nao pode ser efetuada! Usuario nao eh diretor!");
	}
}
