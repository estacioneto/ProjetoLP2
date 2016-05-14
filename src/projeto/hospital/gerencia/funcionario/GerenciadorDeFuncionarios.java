package projeto.hospital.gerencia.funcionario;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.dados.ObjetoNuloException;
import projeto.exceptions.logica.AcessoBloqueadoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.gerencia.ValidadorDeLogica;
import projeto.hospital.gerencia.funcionario.cargo.Permissao;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.Util;
import projeto.util.ValidadorDeDados;
import projeto.util.reflexao.Reflection;

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

	/**
	 * Construtor padrao.
	 */
	public GerenciadorDeFuncionarios() {
		this.funcionarios = new HashMap<String, Funcionario>();
	}

	// OPERACAO DE VALIDACAO DO SISTEMA
	/**
	 * Valida acesso do sistema com um determinado funcionario.
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 * @param senha
	 *            Senha do funcionario.
	 * @return Funcionario que entrou no sistema.
	 */
	public Funcionario acessaSistema(String matricula, String senha) {
		try {
			ValidadorDeDados.validaPadraoMatricula(Constantes.MATRICULA + Constantes.DO_FUNCIONARIO, matricula);
			ValidadorDeDados.validaString(Constantes.SENHA, senha);
			if (this.contemFuncionario(matricula))
				if (this.funcionarios.get(matricula).getSenha().equals(senha))
					return this.funcionarios.get(matricula);
				else
					throw new AcessoBloqueadoException(MensagensDeErro.ERRO_LOGIN + "Senha incorreta.");
			else
				throw new AcessoBloqueadoException(
						MensagensDeErro.ERRO_LOGIN + MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_LOGIN + e.getMessage());
		}
	}

	// OPERACAO DE VALIDACAO DO SISTEMA
	// OPERACOES DE FUNCIONARIO
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
	public String cadastraFuncionario(String nome, String cargo, String dataNascimento, Funcionario funcionarioLogado) {
		try {
			// Validacao NAO necessaria antes de realizar o cadastro
			// A ordem importa e a geracao de uma matricula depende de um cargo
			// valido e uma data valida.

			if (Constantes.DIRETOR_GERAL.equals(cargo) && !this.isEmpty())
				throw new OperacaoInvalidaException(
						MensagensDeErro.ERRO_CADASTRO_FUNCIONARIO + MensagensDeErro.ERRO_CADASTRO_DIRETOR_FUNCIONARIO);

			if (!this.isEmpty())
				ValidadorDeLogica.validaOperacao(MensagensDeErro.ERRO_PERMISSAO_CADASTRO_FUNCIONARIO,
						Permissao.CADASTRAR_FUNCIONARIOS, funcionarioLogado);

			Funcionario funcionario = (Funcionario)Reflection.godFactory(Funcionario.class, nome, cargo, dataNascimento);
			if (this.adicionaFuncionario(funcionario))
				return funcionario.getMatricula();
			else
				throw new OperacaoInvalidaException("Funcionario ja cadastrado!");
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CADASTRO_FUNCIONARIO + e.getMessage());
		}
	}

	/**
	 * Adiciona um funcionario ao sistema.
	 * 
	 * @param funcionario
	 *            Funcionario a ser adicionado.
	 * @return true se o funcionario foi adicionado com sucesso.
	 * @throws DadoInvalidoException
	 *             Caso o funcionario seja nulo.
	 */
	private boolean adicionaFuncionario(Funcionario funcionario) throws ObjetoNuloException {
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
			ValidadorDeDados.validaPadraoMatricula(Constantes.MATRICULA + Constantes.DO_FUNCIONARIO, matriculaUsuario);
			ValidadorDeDados.validaPadraoMatricula(Constantes.MATRICULA + Constantes.DO_FUNCIONARIO, matriculaFuncionario);

			if (this.contemFuncionario(matriculaUsuario) && this.contemFuncionario(matriculaFuncionario)) {
				Funcionario funcionario = this.funcionarios.get(matriculaUsuario);

				ValidadorDeLogica.validaExclusao(funcionario);
				if (funcionario.getSenha().equals(senhaUsuario)) {
					this.funcionarios.remove(matriculaFuncionario);
				} else
					throw new DadoInvalidoException(MensagensDeErro.SENHA_INVALIDA);
			} else
				throw new DadoInvalidoException(MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_EXCLUSAO_FUNCIONARIO + e.getMessage());
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
			ValidadorDeDados.validaPadraoMatricula(Constantes.MATRICULA + Constantes.DO_FUNCIONARIO, matricula);
			ValidadorDeDados.validaString(Util.capitalizaString(atributo) + Constantes.DO_FUNCIONARIO, novoValor);
			//ValidadorDeDados.validaAtualizarAtributoFuncionario(atributo, novoValor);
			if (!contemFuncionario(matricula)) {
				throw new OperacaoInvalidaException(
						MensagensDeErro.ERRO_ATUALIZA_FUNCIONARIO + MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
			}
			// Se o usuario nao for ele mesmo ou nao tiver a permissao para
			// atualizar outro (o diretor)
			if (!(funcionarioLogado.getMatricula().equals(matricula)
					|| funcionarioLogado.temPermissao(Permissao.ATUALIZAR_INFORMACOES_FUNCIONARIOS)))
				throw new OperacaoInvalidaException(
						MensagensDeErro.ERRO_ATUALIZA_FUNCIONARIO + MensagensDeErro.PERMISSAO_NEGADA_ATUALIZACAO);
			Reflection.atualizaInfo(this.funcionarios.get(matricula), atributo, novoValor,
					MensagensDeErro.ATRIBUTO_NAO_ATUALIZAVEL);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_ATUALIZA_FUNCIONARIO + e.getMessage());
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
				throw new OperacaoInvalidaException(
						MensagensDeErro.ERRO_ATUALIZA_FUNCIONARIO + MensagensDeErro.SENHA_INVALIDA);
			Reflection.atualizaInfo(this.funcionarios.get(funcionarioLogado.getMatricula()), Constantes.SENHA, novaSenha, MensagensDeErro.ERRO_ATUALIZA_FUNCIONARIO + Constantes.SENHA);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_ATUALIZA_FUNCIONARIO + e.getMessage());
		}
	}

	// OPERACOES DE FUNCIONARIO
	// CONSULTA DE FUNCIONARIO
	/**
	 * Retorna uma informacao do funcionario.
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 * @param atributo
	 *            Atributo do funcionario.
	 * @return Informacao do funcionario.
	 */
	public Object getInfoFuncionario(String matricula, String atributo) {
		try {
			ValidadorDeDados.validaPadraoMatricula(Constantes.MATRICULA + Constantes.DO_FUNCIONARIO, matricula);
			ValidadorDeDados.validaString(Constantes.ATRIBUTO, atributo);
			if (this.contemFuncionario(matricula)) {
				return Reflection.getInfo(this.funcionarios.get(matricula), atributo);
			}
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_CONSULTA_FUNCIONARIO + MensagensDeErro.ERRO_FUNCIONARIO_NAO_CADASTRADO);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTA_FUNCIONARIO + e.getMessage());
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
		return this.funcionarios.containsKey(matricula);
	}

	/**
	 * Verifica se nao ha funcionarios cadastrados.
	 * 
	 * @return true se nao ha funcionarios cadastrados.
	 */
	public boolean isEmpty() {
		return this.funcionarios.isEmpty();
	}
	// CONSULTA DE FUNCIONARIO
}
