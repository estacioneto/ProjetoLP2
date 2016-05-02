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
import projeto.util.ValidadorDeDados;

/**
 * Gerenciador de funcionarios. Este gerenciador trata de toda a logica do
 * sistema relacionada aos funcionarios.
 * 
 * @author Estacio Pereira
 */
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

	/**
	 * Construtor padrao.
	 */
	public GerenciadorDeFuncionarios() {
		this.funcionarios = new HashMap<String, Funcionario>();
		this.factoryFuncionarios = new FuncionarioFactory();
		this.validador = new ValidadorDeLogica();
		this.dataAtual = LocalDate.now();
		this.geradorDadosSeguranca = new GeradorDeDadosDeSeguranca();
	}

	/**
	 * Adiciona um funcionario ao sistema.
	 * 
	 * @param funcionario
	 *            Funcionario a ser adicionado.
	 * @return true se o funcionario foi adicionado com sucesso.
	 */
	public boolean adicionaFuncionario(Funcionario funcionario) {
		ValidadorDeDados.validaNaoNulo(Constantes.FUNCIONARIO, funcionario);
		if (this.contemFuncionario(funcionario.getMatricula()))
			return false;
		this.funcionarios.put(funcionario.getMatricula(), funcionario);
		return true;
	}

	// public boolean demiteFuncionario(String matriculaDiretor, String
	// senhaDiretor, String matriculaFuncionario) {
	// Util.validaString(Constantes.MATRICULA, matriculaFuncionario);
	// if (this.contemFuncionario(matriculaDiretor)) {
	// Funcionario funcionario = this.funcionarios.get(matriculaDiretor);
	// if (funcionario.getSenha().equals(senhaDiretor)) {
	// this.validador.validaExclusao(funcionario);
	// if (!this.contemFuncionario(matriculaFuncionario))
	// return false;
	/**
	 * Exclui funcionario no sistema.
	 * 
	 * @param matriculaUsuario
	 *            Matricula do usuario.
	 * @param senhaUsuario
	 *            Senha do usuario.
	 * @param matriculaFuncionario
	 *            Matricula do funcionario a ser excluido.
	 */
	public void excluiFuncionario(String matriculaUsuario, String senhaUsuario, String matriculaFuncionario) {
		ValidadorDeDados.validaString(Constantes.MATRICULA, matriculaFuncionario);
		ValidadorDeDados.validaPadraoMatricula(matriculaUsuario,
				MensagensDeErro.ERRO_EXCLUSAO_FUNCIONARIO + MensagensDeErro.PADRAO_MATRICULA);
		ValidadorDeDados.validaPadraoMatricula(matriculaFuncionario,
				MensagensDeErro.ERRO_EXCLUSAO_FUNCIONARIO + MensagensDeErro.PADRAO_MATRICULA);
		if (this.contemFuncionario(matriculaUsuario) && this.contemFuncionario(matriculaFuncionario)) {
			Funcionario funcionario = this.funcionarios.get(matriculaUsuario);
			this.validador.validaExclusao(funcionario);
			if (funcionario.getSenha().equals(senhaUsuario)) {
				this.funcionarios.remove(matriculaFuncionario);
			} else
				throw new DadoInvalidoException(
						MensagensDeErro.ERRO_EXCLUSAO_FUNCIONARIO + MensagensDeErro.SENHA_INVALIDA);
		} else
			throw new DadoInvalidoException(
					MensagensDeErro.ERRO_EXCLUSAO_FUNCIONARIO + MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
	}

	/**
	 * Atualiza determinada informacao do funcionario.
	 * 
	 * @param funcionarioLogado
	 *            Funcionario logado no sistema.
	 * @param matricula
	 *            Matricula do funcionario a ter informacao atualizada.
	 * @param atributo
	 *            Atributo a ser atualizado.
	 * @param novoValor
	 *            Novo valor do atributo.
	 */
	public void atualizaInfoFuncionario(Funcionario funcionarioLogado, String matricula, String atributo,
			String novoValor) {
		ValidadorDeDados.validaString(MensagensDeErro.ERRO_ATUALIZA_INFO + MensagensDeErro.MATRICULA_FUNCIONARIO,
				matricula);
		ValidadorDeDados.validaPadraoMatricula(matricula,
				MensagensDeErro.ERRO_ATUALIZA_INFO + MensagensDeErro.PADRAO_MATRICULA);
		ValidadorDeDados.validaAtributoFuncionario(MensagensDeErro.ERRO_ATUALIZA_INFO, atributo, novoValor);
		if (!contemFuncionario(matricula)) {
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_ATUALIZA_INFO + MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
		}
		if (!(funcionarioLogado.getMatricula().equals(matricula)
				|| funcionarioLogado.temPermissao(Permissao.ATUALIZAR_INFORMACOES_FUNCIONARIOS)))
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_ATUALIZA_INFO + MensagensDeErro.PERMISSAO_NEGADA_ATUALIZACAO);
		switch (Util.capitalizaString(atributo)) {
		case Constantes.NOME:
			this.funcionarios.get(matricula).setNome(novoValor);
			break;
		case Constantes.DATA:
			this.funcionarios.get(matricula).setDataNascimento(novoValor);
			break;
		default:
			break;
		}
	}

	/**
	 * Atualiza a senha do usuario.
	 * 
	 * @param funcionarioLogado
	 *            Usuario.
	 * @param senhaAntiga
	 *            Senha antiga do usuario.
	 * @param novaSenha
	 *            Nova senha do usuario.
	 */
	public void atualizaSenha(Funcionario funcionarioLogado, String senhaAntiga, String novaSenha) {
		if (!funcionarioLogado.getSenha().equals(senhaAntiga))
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_ATUALIZA_INFO + MensagensDeErro.SENHA_INVALIDA);
		this.funcionarios.get(funcionarioLogado.getMatricula()).setSenha(novaSenha);
	}

	/**
	 * Verifica se determinado funcionario esta cadastrado.
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 * @return true se o funcionario foi cadastrado.
	 */
	public boolean contemFuncionario(String matricula) {
		ValidadorDeDados.validaString(Constantes.MATRICULA, matricula);
		return this.funcionarios.containsKey(matricula);
	}

	/**
	 * Cadastra um funcionario.
	 * 
	 * @param nome
	 *            Nome do funcionario.
	 * @param cargo
	 *            Cargo do funcionario.
	 * @param dataNascimento
	 *            Data de nascimento do funcionario.
	 * @return Matricula do funcionario cadastrado.
	 */
	public String cadastraFuncionario(String nome, String cargo, String dataNascimento) {
		// Validacao necessaria antes de realizar o cadastro
		// A ordem importa e a geracao de uma matricula depende de um cargo
		// valido e uma data valida.
		ValidadorDeDados.validaString(MensagensDeErro.ERRO_CADASTRO_FUNCIONARIO + MensagensDeErro.NOME_FUNCIONARIO,
				nome);
		ValidadorDeDados.validaCargo(MensagensDeErro.ERRO_CADASTRO_FUNCIONARIO, cargo);
		ValidadorDeDados.validaData(MensagensDeErro.ERRO_CADASTRO_FUNCIONARIO + MensagensDeErro.DATA_FUNCIONARIO,
				dataNascimento);

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

	/**
	 * Retorna uma nova matricula.
	 * 
	 * @param cargo
	 *            Cargo do funcionario.
	 * @return Matricula gerada.
	 */
	public String novaMatricula(String cargo) {
		return this.geradorDadosSeguranca.geraMatricula(cargo, this.getAnoAtual());
	}

	/**
	 * Retorna a primeira matricula cadastrada.
	 * 
	 * @param cargo
	 *            Cargo do funcionario.
	 * @return Primeira matricula cadastrada.
	 */
	public String primeiraMatricula(String cargo) {
		return this.geradorDadosSeguranca.getMatriculaCadastro(cargo, this.getAnoAtual(), Constantes.PRIMEIRO_CADASTRO);
	}

	/**
	 * Retorna o ano atual.
	 * 
	 * @return Ano atual.
	 */
	private String getAnoAtual() {
		return Integer.toString(dataAtual.getYear());
	}

	/**
	 * Acessa o sistema com um determinado funcionario.
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 * @param senha
	 *            Senha do funcionario.
	 * @return Funcionario que entrou no sistema.
	 */
	public Funcionario acessaSistema(String matricula, String senha) {
		ValidadorDeDados.validaString(Constantes.MATRICULA, matricula);
		ValidadorDeDados.validaString(Constantes.SENHA, senha);
		if (this.contemFuncionario(matricula))
			if (this.funcionarios.get(matricula).getSenha().equals(senha))
				return this.funcionarios.get(matricula);
			else
				throw new AcessoBloqueadoException("Nao foi possivel realizar o login. Senha incorreta.");
		else
			throw new AcessoBloqueadoException(
					"Nao foi possivel realizar o login. " + MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
	}

	/**
	 * Verifica se nao ha funcionarios cadastrados.
	 * 
	 * @return true se nao ha funcionarios cadastrados.
	 */
	public boolean isEmpty() {
		return this.funcionarios.isEmpty();
	}

	/**
	 * Retorna uma informacao do funcionario.
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 * @param atributo
	 *            Atributo do funcionario.
	 * @return Informacao do funcionario.
	 */
	public String getInfoFuncionario(String matricula, String atributo) {
		ValidadorDeDados.validaString(Constantes.MATRICULA, matricula);
		ValidadorDeDados.validaPadraoMatricula(matricula,
				MensagensDeErro.ERRO_CONSULTA_FUNCIONARIO + MensagensDeErro.PADRAO_MATRICULA);
		ValidadorDeDados.validaString(Constantes.ATRIBUTO, atributo);

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
