package projeto.hospital.controller;

import java.time.LocalDate;

import projeto.util.Constantes;
import projeto.util.Util;

public class Controller {

	private LocalDate dataAtual;
	private GeradorDeDadosDeSeguranca geradorDadosSeguranca;
	private ValidadorDeLogica validador;

	public Controller() {
		this.dataAtual = LocalDate.now();
		this.geradorDadosSeguranca = new GeradorDeDadosDeSeguranca();

		this.validador = new ValidadorDeLogica();
	}

	public void cadastrafuncionario(String nome, String cargo, String dataNascimento) {
		Util.validaString(Constantes.NOME, nome);
		Util.validaString(Constantes.CARGO, cargo);
		Util.validaData(Constantes.DATA_NASCIMENTO, dataNascimento);

		String matricula = geradorDadosSeguranca.geraMatricula(Util.getCodigoPorCargo(cargo), Util.getAnoPorData(dataNascimento));
		String senha = geradorDadosSeguranca.geraSenha(matricula, Util.getAnoPorData(dataNascimento));
		//TODO: Retorna funcionario?
		cadastraLogin(matricula, senha);
	}

	public String novaMatricula(String cargo) {
		return this.geradorDadosSeguranca.geraMatricula(cargo, this.getAnoAtual());
	}

	private String getAnoAtual() {
		return Integer.toString(dataAtual.getYear());
	}

	public void cadastraLogin(String matricula, String senha) {
		this.validador.cadastraLogin(matricula, senha);
	}

	public void realizaLogin(String matricula, String senha) {
		this.validador.validaAcesso(matricula, senha);
	}

}
