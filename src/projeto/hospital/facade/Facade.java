package projeto.hospital.facade;

import projeto.exceptions.logica.AcessoBloqueadoException;
import projeto.hospital.controller.Controller;
import projeto.util.Constantes;

public class Facade {

	private final String CHAVE_DESBLOQUEIO = "c041ebf8";
	private Controller controller;
	private boolean sistemaJaLiberado;

	public Facade() {
		this.controller = new Controller();
		this.sistemaJaLiberado = false;
	}

	public void iniciaSistema() {
		this.controller.iniciaSistema();
	}

	public void fechaSistema() {
		this.controller.fechaSistema();
	}

	public void logout() {
		this.controller.logout();
	}

	public String liberaSistema(String chave, String nome, String dataNascimento) {
		if (sistemaJaLiberado) {
			throw new AcessoBloqueadoException("Erro ao liberar o sistema. Sistema liberado anteriormente.");
		} else if (CHAVE_DESBLOQUEIO.equals(chave)) {
			String matricula = this.cadastraFuncionario(nome, Constantes.DIRETOR_GERAL, dataNascimento);
			sistemaJaLiberado = true;
			return matricula;
		} else {
			throw new AcessoBloqueadoException("Erro ao liberar o sistema. Chave invalida.");
		}
	}

	public void login(String matricula, String senha) {
		this.controller.acessaSistema(matricula, senha);
	}

	public String getInfoFuncionario(String matricula, String atributo) {
		return this.controller.getInfoFuncionario(matricula, atributo);
	}

	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) {
		return this.controller.cadastraFuncionario(nome, cargo, dataNascimento);
	}

	public boolean demiteFuncionario(String senhaDiretor, String matriculaFuncionario) {
		return this.controller.demiteFuncionario(senhaDiretor, matriculaFuncionario);
	}

	public long cadastraPaciente(String nome, String data, double peso, String sexo, String genero,
			String tipoSanguineo) {
		return this.controller.cadastraPaciente(nome, data, peso, sexo, genero, tipoSanguineo);
	}
}
