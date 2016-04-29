package projeto.hospital.gerencia;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import projeto.exceptions.logica.AcessoBloqueadoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.funcionarios.Cargo;
import projeto.hospital.funcionarios.Permissao;
import projeto.util.Constantes;
import projeto.util.Util;

/**
 * Valida varios aspectos da logica do sistema como acesso e permissoes de
 * acordo com os dados fornecidos.
 * 
 * @author Estacio Pereira
 */
public class ValidadorDeLogica {

	private Map<String, String> logins;

	/**
	 * Construtor padrao. Inicializa logins.
	 */
	public ValidadorDeLogica() {
		this.logins = new HashMap<String, String>();
	}

	/**
	 * Valida o acesso dada a matricula e a senha do funcionario.
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 * @param senha
	 *            Senha do funcionario.
	 */
	public void validaAcesso(String matricula, String senha) {
		Util.validaString(Constantes.MATRICULA, matricula);
		Util.validaString(Constantes.SENHA, senha);
		if (!this.logins.containsKey(matricula))
			throw new AcessoBloqueadoException("Matricula nao cadastrada!");
		if (!this.logins.get(matricula).equals(senha))
			throw new AcessoBloqueadoException("Senha incorreta!");
	}

	/**
	 * Cadastra o login do funcionario.
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 * @param senha
	 *            Senha do funcionario.
	 */
	public void cadastraLogin(String matricula, String senha) {
		if (this.logins.containsKey(matricula))
			throw new OperacaoInvalidaException("Matricula ja cadastrada!");
		this.logins.put(matricula, senha);
	}

	/**
	 * Remove o login do funcionario (caso de demissao/exclusao).
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 */
	public void removeLogin(String matricula) {
		if (this.logins.containsKey(matricula))
			this.logins.remove(matricula);
		else
			throw new OperacaoInvalidaException("Matricula nao cadastrada!");
	}

	/**
	 * Valida a permissao de exclusao de algum funcionario.
	 * 
	 * @param matricula
	 *            Matricula do funcionario que deseja excluir outro.
	 * @param senha
	 *            Senha do funcionario que deseja excluir outro.
	 */
	public void validaExclusao(String matricula, String senha) {
		validaAcesso(matricula, senha);
		Set<Permissao> permissoes = Cargo.getPermissoesPorCodigo(Util
				.getCodigoPorMatricula(matricula));
		if (!permissoes.contains(Permissao.EXCLUIR_FUNCIONARIOS))
			throw new OperacaoInvalidaException(
					"Exclusao nao pode ser efetuada! Usuario nao eh diretor!");
	}
}
