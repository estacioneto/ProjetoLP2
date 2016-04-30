package projeto.hospital.gerencia;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.AcessoBloqueadoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.funcionarios.Funcionario;
import projeto.hospital.funcionarios.FuncionarioFactory;
import projeto.hospital.funcionarios.Permissao;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.Util;

public class GerenciadorDeFuncionarios implements Serializable {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 5781785073141058466L;
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

	public void excluiFuncionario(String matriculaDiretor, String senhaDiretor, String matriculaFuncionario) {
		Util.validaString(Constantes.MATRICULA, matriculaFuncionario);
		Util.validaPadraoMatricula(matriculaDiretor, MensagensDeErro.ERRO_EXCLUSAO_FUNCIONARIO + MensagensDeErro.PADRAO_MATRICULA);
		Util.validaPadraoMatricula(matriculaFuncionario, MensagensDeErro.ERRO_EXCLUSAO_FUNCIONARIO + MensagensDeErro.PADRAO_MATRICULA);
		if (this.contemFuncionario(matriculaDiretor) && this.contemFuncionario(matriculaFuncionario)) {
			Funcionario funcionario = this.funcionarios.get(matriculaDiretor);
			this.validador.validaExclusao(funcionario);
			if (funcionario.getSenha().equals(senhaDiretor)) {
				this.funcionarios.remove(matriculaFuncionario);
			}else
				throw new DadoInvalidoException(MensagensDeErro.ERRO_EXCLUSAO_FUNCIONARIO + MensagensDeErro.SENHA_INVALIDA);
		}else
			throw new DadoInvalidoException(MensagensDeErro.ERRO_EXCLUSAO_FUNCIONARIO + MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
	}
	
	public void atualizaInfoFuncionario(Funcionario funcionarioLogado, String matricula, String atributo, String novoValor){
		Util.validaString(MensagensDeErro.ERRO_ATUALIZA_INFO + MensagensDeErro.MATRICULA_FUNCIONARIO, matricula);
		Util.validaPadraoMatricula(matricula, MensagensDeErro.ERRO_ATUALIZA_INFO + MensagensDeErro.PADRAO_MATRICULA);
		Util.validaAtributo(MensagensDeErro.ERRO_ATUALIZA_INFO, atributo, novoValor);
		if(!contemFuncionario(matricula)){
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_ATUALIZA_INFO + MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
		}
		if(!(funcionarioLogado.getMatricula().equals(matricula) || funcionarioLogado.temPermissao(Permissao.ATUALIZAR_INFORMACOES_FUNCIONARIOS)))
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_ATUALIZA_INFO + MensagensDeErro.PERMISSAO_NEGADA_ATUALIZACAO);
		switch (Util.capitalizaString(atributo)) {
		case Constantes.NOME:
			Util.validaNome(MensagensDeErro.ERRO_ATUALIZA_INFO, novoValor);
			this.funcionarios.get(matricula).setNome(novoValor);
			break;
		case Constantes.DATA:
			Util.validaData(MensagensDeErro.ERRO_ATUALIZA_INFO, novoValor);
			this.funcionarios.get(matricula).setDataNascimento(novoValor);
			break;
		default:
			break;
		}
	}
	
	public void atualizaSenha(Funcionario funcionarioLogado, String senhaAntiga, String novaSenha) {
		if(!funcionarioLogado.getSenha().equals(senhaAntiga))
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_ATUALIZA_INFO + MensagensDeErro.SENHA_INVALIDA);
		Util.validaSenha(MensagensDeErro.ERRO_ATUALIZA_INFO, novaSenha);
		this.funcionarios.get(funcionarioLogado.getMatricula()).setSenha(novaSenha);
	}

	public boolean contemFuncionario(String matricula) {
		Util.validaString(Constantes.MATRICULA, matricula);
		return this.funcionarios.containsKey(matricula);
	}

	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) {
		Util.validaString(MensagensDeErro.ERRO_CADASTRO_FUNCIONARIO + MensagensDeErro.NOME_FUNCIONARIO, nome);
		Util.validaCargo(MensagensDeErro.ERRO_CADASTRO_FUNCIONARIO, cargo);
		Util.validaData(MensagensDeErro.ERRO_CADASTRO_FUNCIONARIO + MensagensDeErro.DATA_FUNCIONARIO, dataNascimento);

		if (Constantes.DIRETOR_GERAL.equals(cargo) && !this.isEmpty())
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CADASTRO_DIRETOR_FUNCIONARIO);

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
			throw new AcessoBloqueadoException("Nao foi possivel realizar o login. " + MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
	}

	/**
	 * @return Quantidade de usuarios cadastrados no sistema
	 */
	public boolean isEmpty() {
		return this.funcionarios.isEmpty();
	}

	public String getInfoFuncionario(String matricula, String atributo) {
		Util.validaString(Constantes.MATRICULA, matricula);
		Util.validaPadraoMatricula(matricula,
				MensagensDeErro.ERRO_CONSULTA_FUNCIONARIO + MensagensDeErro.PADRAO_MATRICULA);
		Util.validaString(Constantes.ATRIBUTO, atributo);

		if (this.contemFuncionario(matricula)) {
			switch (Util.capitalizaString(atributo)) {
			case Constantes.NOME:
				return this.funcionarios.get(matricula).getNome();
			case Constantes.CARGO:
				return this.funcionarios.get(matricula).getCargo();
			case Constantes.DATA:
				return this.funcionarios.get(matricula).getDataNascimento();
			case Constantes.SENHA:
				throw new OperacaoInvalidaException(
						MensagensDeErro.ERRO_CONSULTA_FUNCIONARIO + "A senha do funcionario eh protegida.");
			default:
				throw new DadoInvalidoException("Atributo nao valido.");
			}
		}
		throw new OperacaoInvalidaException(
				MensagensDeErro.ERRO_CONSULTA_FUNCIONARIO + MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
	}

}
