package projeto.hospital.gerencia;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.AcessoBloqueadoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.funcionarios.Funcionario;
import projeto.hospital.funcionarios.FuncionarioFactory;
import projeto.util.Constantes;
import projeto.util.Util;

public class GerenciadorDeFuncionarios {

	private Map<String, Funcionario> funcionarios;
	private FuncionarioFactory factoryFuncionarios;
	private ValidadorDeLogica validador;
	private LocalDate dataAtual;
	private GeradorDeDadosDeSeguranca geradorDadosSeguranca;

	public GerenciadorDeFuncionarios() {
		this.funcionarios = new HashMap<String, Funcionario>();
		this.factoryFuncionarios = new FuncionarioFactory();
		this.validador = new ValidadorDeLogica();
		this.dataAtual = LocalDate.now();
		this.geradorDadosSeguranca = new GeradorDeDadosDeSeguranca();
	}

	public boolean adicionaFuncionario(Funcionario funcionario) {
		Util.validaNaoNulo(Constantes.FUNCIONARIO, funcionario);
		if (this.contemFuncionario(funcionario.getMatricula()))
			return false;
		this.funcionarios.put(funcionario.getMatricula(), funcionario);
		return true;
	}

	public boolean demiteFuncionario(String matriculaDiretor, String senhaDiretor, String matriculaFuncionario) {
		Util.validaString(Constantes.MATRICULA, matriculaFuncionario);
		if (this.contemFuncionario(matriculaDiretor)) {
			Funcionario funcionario = this.funcionarios.get(matriculaDiretor);
			if (funcionario.getSenha().equals(senhaDiretor)) {
				this.validador.validaExclusao(funcionario);
				if (!this.contemFuncionario(matriculaFuncionario))
					return false;
				this.funcionarios.remove(matriculaFuncionario);
				return true;
			}
		}
		return false;
	}

	public boolean contemFuncionario(String matricula) {
		Util.validaString(Constantes.MATRICULA, matricula);
		return this.funcionarios.containsKey(matricula);
	}

	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) {
		Util.validaString(Constantes.ERRO_NOME_FUNCIONARIO, nome);
		Util.validaCargo(cargo);
		Util.validaData(Constantes.ERRO_DATA_FUNCIONARIO, dataNascimento);

		if (Constantes.DIRETOR_GERAL.equals(cargo) && !this.isEmpty())
			throw new OperacaoInvalidaException(Constantes.ERRO_CADASTRO_DIRETOR_FUNCIONARIO);

		String matricula = geradorDadosSeguranca.geraMatricula(cargo, getAnoAtual());
		String senha = geradorDadosSeguranca.geraSenha(matricula, Util.getAnoPorData(dataNascimento));
		Funcionario funcionario = this.factoryFuncionarios.criaFuncionario(nome, cargo, dataNascimento, matricula,
				senha);
		if (this.adicionaFuncionario(funcionario))
			return matricula;
		else
			throw new OperacaoInvalidaException("Funcionario ja cadastrado!");
	}

	public String novaMatricula(String cargo) {
		return this.geradorDadosSeguranca.geraMatricula(cargo, this.getAnoAtual());
	}

	public String primeiraMatricula(String cargo) {
		return this.geradorDadosSeguranca.getMatriculaCadastro(cargo, this.getAnoAtual(), Constantes.PRIMEIRO_CADASTRO);
	}

	private String getAnoAtual() {
		return Integer.toString(dataAtual.getYear());
	}

	public Funcionario acessaSistema(String matricula, String senha) {
		Util.validaString(Constantes.MATRICULA, matricula);
		Util.validaString(Constantes.SENHA, senha);
		if (this.contemFuncionario(matricula))
			if (this.funcionarios.get(matricula).getSenha().equals(senha))
				return this.funcionarios.get(matricula);
			else
				throw new AcessoBloqueadoException("Nao foi possivel realizar o login. Senha incorreta.");
		else
			throw new AcessoBloqueadoException("Nao foi possivel realizar o login. Funcionario nao cadastrado.");
	}

	/**
	 * @return Quantidade de usuarios cadastrados no sistema
	 */
	public boolean isEmpty() {
		return this.funcionarios.isEmpty();
	}

	public String getInfoFuncionario(String matricula, String atributo) {
		Util.validaString(Constantes.MATRICULA, matricula);
		Util.validaString(Constantes.ATRIBUTO, atributo);

		if (this.contemFuncionario(matricula)) {
			switch (atributo) {
			case Constantes.NOME:
				return this.funcionarios.get(matricula).getNome();
			case Constantes.CARGO:
				return this.funcionarios.get(matricula).getCargo();
			case Constantes.DATA:
				return this.funcionarios.get(matricula).getDataNascimento();
			case Constantes.SENHA:
				throw new OperacaoInvalidaException(
						Constantes.ERRO_CONSULTA_FUNCIONARIO + "A senha do funcionario eh protegida.");
			default:
				throw new DadoInvalidoException("Atributo nao valido.");
			}
		}
		throw new DadoInvalidoException("Funcionario nao cadastrado no sistema.");
	}
}
