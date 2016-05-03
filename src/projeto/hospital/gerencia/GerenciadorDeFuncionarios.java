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
	private GeradorDeDadosDeSeguranca geradorDadosSeguranca;

	/**
	 * Construtor padrao.
	 */
	public GerenciadorDeFuncionarios() {
		this.funcionarios = new HashMap<String, Funcionario>();
		this.factoryFuncionarios = new FuncionarioFactory();
		this.validador = new ValidadorDeLogica();
		this.geradorDadosSeguranca = new GeradorDeDadosDeSeguranca();
	}

	/**
	 * Adiciona um funcionario ao sistema.
	 * 
	 * @param funcionario
	 *            Funcionario a ser adicionado.
	 * @return true se o funcionario foi adicionado com sucesso.
	 */
	private boolean adicionaFuncionario(Funcionario funcionario) {
		ValidadorDeDados.validaNaoNulo(Constantes.FUNCIONARIO, funcionario);
		if (this.contemFuncionario(funcionario.getMatricula()))
			return false;
		this.funcionarios.put(funcionario.getMatricula(), funcionario);
		return true;
	}

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
		try {
			ValidadorDeDados.validaPadraoMatricula(matriculaUsuario);
			ValidadorDeDados.validaPadraoMatricula(matriculaFuncionario);

			if (this.contemFuncionario(matriculaUsuario) && this.contemFuncionario(matriculaFuncionario)) {
				Funcionario funcionario = this.funcionarios.get(matriculaUsuario);

				this.validador.validaExclusao(funcionario);
				if (funcionario.getSenha().equals(senhaUsuario)) {
					this.funcionarios.remove(matriculaFuncionario);
				} else
					throw new DadoInvalidoException(MensagensDeErro.SENHA_INVALIDA);
			} else
				throw new DadoInvalidoException(MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
		} catch (DadoInvalidoException e) {
			throw new DadoInvalidoException(MensagensDeErro.ERRO_EXCLUSAO_FUNCIONARIO + e.getMessage());
		}
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
		try {
			ValidadorDeDados.validaPadraoMatricula(matricula);
			ValidadorDeDados.validaAtributoFuncionario(atributo, novoValor);
			if (!contemFuncionario(matricula)) {
				throw new OperacaoInvalidaException(MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
			}
			// Se o usuario nao for ele mesmo ou nao tiver a permissao para
			// atualizar outro (o diretor)
			if (!(funcionarioLogado.getMatricula().equals(matricula)
					|| funcionarioLogado.temPermissao(Permissao.ATUALIZAR_INFORMACOES_FUNCIONARIOS)))
				throw new OperacaoInvalidaException(MensagensDeErro.PERMISSAO_NEGADA_ATUALIZACAO);
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
		} catch (DadoInvalidoException e) {
			throw new DadoInvalidoException(MensagensDeErro.ERRO_ATUALIZA_FUNCIONARIO + e.getMessage());
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
		try {
			if (!funcionarioLogado.getSenha().equals(senhaAntiga))
				throw new OperacaoInvalidaException(MensagensDeErro.SENHA_INVALIDA);
			ValidadorDeDados.validaSenha(novaSenha);
			this.funcionarios.get(funcionarioLogado.getMatricula()).setSenha(novaSenha);
		} catch (OperacaoInvalidaException | DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_ATUALIZA_FUNCIONARIO + e.getMessage());
		}
	}

	/**
	 * Verifica se determinado funcionario esta cadastrado.
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 * @return true se o funcionario foi cadastrado.
	 */
	private boolean contemFuncionario(String matricula) {
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
		try {
			// Validacao necessaria antes de realizar o cadastro
			// A ordem importa e a geracao de uma matricula depende de um cargo
			// valido e uma data valida.
			ValidadorDeDados.validaNome(Constantes.DO_FUNCIONARIO, nome);
			ValidadorDeDados.validaCargo(cargo);
			ValidadorDeDados.validaData(dataNascimento);

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
		} catch (DadoInvalidoException | OperacaoInvalidaException e) {
			throw new DadoInvalidoException(MensagensDeErro.ERRO_CADASTRO_FUNCIONARIO + e.getMessage());
		}
	}

	/**
	 * Retorna o ano atual.
	 * 
	 * @return Ano atual.
	 */
	private String getAnoAtual() {
		LocalDate dataAtual = LocalDate.now();
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
		ValidadorDeDados.validaPadraoMatricula(matricula);
		ValidadorDeDados.validaSenha(senha);
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
		try {
			ValidadorDeDados.validaPadraoMatricula(matricula);
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
					throw new OperacaoInvalidaException("A senha do funcionario eh protegida.");
				default:
					throw new DadoInvalidoException("Atributo nao valido.");
				}
			}
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
		} catch (DadoInvalidoException | OperacaoInvalidaException e) {
			throw new DadoInvalidoException(MensagensDeErro.ERRO_CONSULTA_FUNCIONARIO + e.getMessage());
		}
	}
}
