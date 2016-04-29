package projeto.hospital.gerencia;

import java.util.HashMap;
import java.util.Map;

import projeto.hospital.funcionarios.Funcionario;
import projeto.hospital.funcionarios.FuncionarioFactory;
import projeto.util.Constantes;
import projeto.util.Util;

public class GerenciadorDeFuncionarios {
	
	private Map<String, Funcionario> funcionarios;
	private FuncionarioFactory factoryFuncionarios;
	private ValidadorDeLogica validador;
	
	public GerenciadorDeFuncionarios(){
		this.funcionarios = new HashMap<String, Funcionario>();
		this.factoryFuncionarios = new FuncionarioFactory();
		this.validador = new ValidadorDeLogica();
	}
	
	public boolean adicionaFuncionario(Funcionario funcionario){
		Util.validaNaoNulo(Constantes.FUNCIONARIO, funcionario);
		if(this.contemFuncionario(funcionario.getMatricula()))
			return false;
		this.funcionarios.put(funcionario.getMatricula(), funcionario);
		return true;
	}
	
	public boolean removeFuncionario(String matriculaDiretor, String senhaDiretor, String matriculaFuncionario){
		Util.validaString(Constantes.MATRICULA, matriculaFuncionario);
		this.validador.validaExclusao(matriculaDiretor, senhaDiretor);
		this.validador.removeLogin(matriculaFuncionario);
		if(!this.funcionarios.containsKey(matriculaFuncionario))
			return false;
		this.funcionarios.remove(matriculaFuncionario);
		return true;
	}
	
	public boolean contemFuncionario(String matricula){
		Util.validaString(Constantes.MATRICULA, matricula);
		return this.funcionarios.containsKey(matricula);
	}

	public boolean cadastraFuncionario(String nome, String cargo,
			String dataNascimento, String matricula, String senha) {
		Funcionario funcionario = this.factoryFuncionarios.criaFuncionario(nome, cargo, dataNascimento, matricula, senha);
		return this.adicionaFuncionario(funcionario);
	}

	public void cadastraLogin(String matricula, String senha) {
		this.validador.cadastraLogin(matricula, senha);
	}
	
	public void acessaSistema(String matricula, String senha) {
		this.validador.validaAcesso(matricula, senha);
	}

	public void validaExclusao(String matriculaDiretor, String senhaDiretor) {
		this.validador.validaExclusao(matriculaDiretor, senhaDiretor);
	}
}
