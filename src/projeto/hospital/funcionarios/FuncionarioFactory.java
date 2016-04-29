package projeto.hospital.funcionarios;

public class FuncionarioFactory {

	public Funcionario criaFuncionario(String nome, String cargo,
			String dataNascimento, String matricula, String senha) {
		return new Funcionario(nome, new Cargo(cargo), matricula, senha, dataNascimento);
	}

}
