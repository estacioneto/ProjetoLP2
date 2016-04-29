package projeto.hospital.gerencia;

import java.time.LocalDate;

import projeto.util.Constantes;
import projeto.util.Util;

public class GerenciadorHospital {
	
	private LocalDate dataAtual;
	private GeradorDeDadosDeSeguranca geradorDadosSeguranca;
	private GerenciadorDeFuncionarios gerenciadorFuncionarios;

	public GerenciadorHospital(){
		this.dataAtual = LocalDate.now();
		this.geradorDadosSeguranca = new GeradorDeDadosDeSeguranca();
		this.gerenciadorFuncionarios = new GerenciadorDeFuncionarios();
	}

	public boolean cadastraFuncionario(String nome, String cargo,
			String dataNascimento) {
		Util.validaString(Constantes.NOME, nome);
		Util.validaString(Constantes.CARGO, cargo);
		Util.validaData(Constantes.DATA_NASCIMENTO, dataNascimento);

		String matricula = geradorDadosSeguranca.geraMatricula(cargo, getAnoAtual());
		String senha = geradorDadosSeguranca.geraSenha(matricula, Util.getAnoPorData(dataNascimento));
		cadastraLogin(matricula, senha);		
		return this.gerenciadorFuncionarios.cadastraFuncionario(nome, cargo, dataNascimento, matricula, senha);
	}
	
	public boolean demiteFuncionario(String matriculaDiretor, String senhaDiretor, String matriculaFuncionario) {
		return this.gerenciadorFuncionarios.removeFuncionario(matriculaDiretor, senhaDiretor, matriculaFuncionario);
	}
	
	public String novaMatricula(String cargo) {
		return this.geradorDadosSeguranca.geraMatricula(cargo, this.getAnoAtual());
	}

	public void cadastraLogin(String matricula, String senha) {
		this.gerenciadorFuncionarios.cadastraLogin(matricula, senha);
	}
	
	public void acessaSistema(String matricula, String senha) {
		this.gerenciadorFuncionarios.acessaSistema(matricula, senha);
	}

	public void validaExclusao(String matriculaDiretor, String senhaDiretor) {
		this.gerenciadorFuncionarios.validaExclusao(matriculaDiretor, senhaDiretor);
	}
	private String getAnoAtual() {
		return Integer.toString(dataAtual.getYear());
	}

}
