package projeto.hospital.controller;

import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.funcionarios.Funcionario;
import projeto.hospital.gerencia.GerenciadorDeFuncionarios;

public class Controller {

	private Funcionario funcionarioLogado;
	private GerenciadorDeFuncionarios gerenciadorFuncionarios;
	
	public Controller() {
		this.funcionarioLogado = null;
		this.gerenciadorFuncionarios = new GerenciadorDeFuncionarios();
	}

	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) {
		return this.gerenciadorFuncionarios.cadastraFuncionario(nome, cargo, dataNascimento);
	}

	public boolean demiteFuncionario(String senhaDiretor, String matriculaFuncionario){
		if(!estaLogado())
			throw new OperacaoInvalidaException("Voce deve estar logado para acessar o sistema.");
		return this.gerenciadorFuncionarios.demiteFuncionario(this.funcionarioLogado.getMatricula(), senhaDiretor, matriculaFuncionario);
	}
	

	public void acessaSistema(String matricula, String senha) {
		this.funcionarioLogado = this.gerenciadorFuncionarios.acessaSistema(matricula, senha);
	}

	private boolean estaLogado() {
		return (this.funcionarioLogado == null ? false : true);
	}
}
