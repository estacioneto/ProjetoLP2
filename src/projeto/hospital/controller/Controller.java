package projeto.hospital.controller;

import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.funcionarios.Funcionario;
import projeto.hospital.funcionarios.Permissao;
import projeto.hospital.gerencia.GerenciadorDeFuncionarios;
import projeto.hospital.gerencia.ValidadorDeLogica;

public class Controller {

	private Funcionario funcionarioLogado;
	private GerenciadorDeFuncionarios gerenciadorFuncionarios;

	public Controller() {
		this.funcionarioLogado = null;
	}

	/**
	 * Verifica se o usuario tem a permissao para cadastrar e faz o cadastro
	 * 
	 * @param nome
	 *            Nome do usuario a ser cadastrado
	 * @param cargo
	 *            Cargo do novo usuario
	 * @param dataNascimento
	 *            Data de nascimento do novo usuario
	 * @return Matricula do novo cadastro
	 */
	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) {
		// Se o usuario esta logado vai ser verificado se ele pode realizar a operacao
		// Porque o diretor eh cadastrado quando nao existe ninguem logado, por isso precisa disso
		if (!estaLogado()) {
			if (!gerenciadorFuncionarios.isEmpty())
				throw new OperacaoInvalidaException("Voce precisa estar logado no sistema para realizar cadastros.");
		} else
			ValidadorDeLogica.validaOperacao(Permissao.CADASTRAR_FUNCIONARIOS, funcionarioLogado);
		return this.gerenciadorFuncionarios.cadastraFuncionario(nome, cargo, dataNascimento);
	}

	/**
	 * Inicia o sistema se ele ainda nao foi iniciado
	 */
	public void iniciaSistema() {
		if (gerenciadorFuncionarios != null)
			throw new OperacaoInvalidaException("O sistema ja foi iniciado.");
		this.gerenciadorFuncionarios = new GerenciadorDeFuncionarios();
	}

	/**
	 * Fecha o sistema se nenhum usuario estiver mais logado
	 */
	public void fechaSistema() {
		if (estaLogado())
			throw new OperacaoInvalidaException("Nao foi possivel fechar o sistema. Um funcionario ainda esta logado: "
					+ funcionarioLogado.getNome() + ".");
		this.gerenciadorFuncionarios = null;
	}

	public void logout() {
		if (!estaLogado())
			throw new OperacaoInvalidaException("Nao foi possivel realizar o logout. Nao ha um funcionario logado.");
		funcionarioLogado = null;
	}

	public boolean demiteFuncionario(String senhaDiretor, String matriculaFuncionario) {
		if (!estaLogado())
			throw new OperacaoInvalidaException("Voce deve estar logado para acessar o sistema.");
		return this.gerenciadorFuncionarios.demiteFuncionario(this.funcionarioLogado.getMatricula(), senhaDiretor,
				matriculaFuncionario);
	}

	public String getInfoFuncionario(String matricula, String atributo) {
		return this.gerenciadorFuncionarios.getInfoFuncionario(matricula, atributo);
	}

	public void acessaSistema(String matricula, String senha) {
		if (estaLogado())
			throw new OperacaoInvalidaException("Nao foi possivel realizar o login. Um funcionario ainda esta logado: "
					+ funcionarioLogado.getNome() + ".");
		this.funcionarioLogado = this.gerenciadorFuncionarios.acessaSistema(matricula, senha);
	}

	private boolean estaLogado() {
		return this.funcionarioLogado != null;
	}
}
