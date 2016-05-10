package projeto.hospital.gerencia.funcionario;

import java.io.Serializable;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.hospital.gerencia.funcionario.cargo.Cargo;
import projeto.hospital.gerencia.funcionario.cargo.Diretor;
import projeto.hospital.gerencia.funcionario.cargo.Medico;
import projeto.hospital.gerencia.funcionario.cargo.TecnicoAdministrativo;
import projeto.util.Constantes;
import projeto.util.reflexao.Reflection;

/**
 * Factory de funcionarios. Cria os funcionarios de acordo com seus dados.
 * 
 * @author Estacio Pereira
 *
 */
public class FuncionarioFactory implements Serializable{
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 1334465920795166628L;

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
		
		// Validacao de cargo sendo feita uma camada acima.
		Cargo cargoFuncionario = null;
		
		if(Constantes.DIRETOR_GERAL.equals(cargo)){
			cargoFuncionario = new Diretor();
		} else if(Constantes.TECNICO_ADMINISTATIVO.equals(cargo)){
			cargoFuncionario = new TecnicoAdministrativo();
		} else if(Constantes.MEDICO.equals(cargo)){
			cargoFuncionario = new Medico();
		}
		return new Funcionario(nome, cargoFuncionario, matricula, senha,
				dataNascimento);
	}

}
