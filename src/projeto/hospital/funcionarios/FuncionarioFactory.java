package projeto.hospital.funcionarios;

/**
 * Factory de funcionarios. Cria os funcionarios de acordo com seus dados.
 * 
 * @author Estacio Pereira
 *
 */
public class FuncionarioFactory {

	/**
	 * Metodo que cria o funcionario.
	 * 
	 * @param nome
	 *            Nome do funcionario.
	 * @param cargo
	 *            Cargo do funcionario.
	 * @param dataNascimento
	 *            Data de nascimento do funcionario.
	 * @param matricula
	 *            Matricula do funcionario.
	 * @param senha
	 *            Senha do funcionario.
	 * @return Funcionario criado.
	 */
	public Funcionario criaFuncionario(String nome, String cargo,
			String dataNascimento, String matricula, String senha) {
		return new Funcionario(nome, new Cargo(cargo), matricula, senha,
				dataNascimento);
	}

}
