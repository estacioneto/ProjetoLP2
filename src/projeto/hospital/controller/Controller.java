package projeto.hospital.controller;

import projeto.hospital.gerencia.GerenciadorHospital;

public class Controller {

	private GerenciadorHospital gerenciadorHospital;
	
	public Controller() {
		this.gerenciadorHospital = new GerenciadorHospital();
	}

	public String novaMatricula(String cargo){
		return this.gerenciadorHospital.novaMatricula(cargo);
	}
	
	
	public boolean cadastraFuncionario(String nome, String cargo, String dataNascimento) {
		return this.gerenciadorHospital.cadastraFuncionario(nome, cargo, dataNascimento);
	}

	public boolean demiteFuncionario(String matriculaDiretor, String senhaDiretor, String matriculaFuncionario){
		return this.gerenciadorHospital.demiteFuncionario(matriculaDiretor, senhaDiretor, matriculaFuncionario);
	}
	
	public void cadastraLogin(String matricula, String senha) {
		this.gerenciadorHospital.cadastraLogin(matricula, senha);
	}

	public void acessaSistema(String matricula, String senha) {
		this.gerenciadorHospital.acessaSistema(matricula, senha);
	}

}
