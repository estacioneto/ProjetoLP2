package projeto.hospital.facade;

import projeto.exceptions.logica.AcessoBloqueadoException;
import projeto.hospital.controller.Controller;
import projeto.util.Constantes;

/**
 * Classe de fachada. A fachada do sistema cuida da interacao do usuario com o
 * sistema.
 * 
 * @author Estacio Pereira
 */
public class Facade {

	private final String CHAVE_DESBLOQUEIO = "c041ebf8";
	private Controller controller;
	private boolean sistemaJaLiberado;

	/**
	 * Construtor padrao.
	 */
	public Facade() {
		this.controller = new Controller();
		this.sistemaJaLiberado = false;
	}

	/**
	 * Inicia o sistema.
	 */
	public void iniciaSistema() {
		this.controller.iniciaSistema();
	}

	/**
	 * Fecha o sistema.
	 */
	public void fechaSistema() {
		this.controller.fechaSistema();
	}

	/**
	 * Usuario sai da sua conta.
	 */
	public void logout() {
		this.controller.logout();
	}

	/**
	 * Libera o sistema pela primeira vez.
	 * 
	 * @param chave
	 *            Chave de liberacao do sistema.
	 * @param nome
	 *            Nome do usuario.
	 * @param dataNascimento
	 *            Data de nascimento do usuario.
	 * @return Matricula gerada para o usuario.
	 */
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

	/**
	 * Usuario entra no sistema.
	 * 
	 * @param matricula
	 *            Matricula do usuario.
	 * @param senha
	 *            Senha do usuario.
	 */
	public void login(String matricula, String senha) {
		this.controller.acessaSistema(matricula, senha);
	}

	/**
	 * Pega determinada informacao do funcionario desejado.
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 * @param atributo
	 *            Atributo do funcionario.
	 * @return Informacao do funcionario.
	 */
	public String getInfoFuncionario(String matricula, String atributo) {
		return this.controller.getInfoFuncionario(matricula, atributo);
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
		return this.controller.cadastraFuncionario(nome, cargo, dataNascimento);
	}

	/**
	 * Exclui um funcionario.
	 * 
	 * @param matriculaFuncionario
	 *            Matricula do funcionario a ser excluido.
	 * @param senha
	 *            Senha do usuario.
	 */
	public void excluiFuncionario(String matriculaFuncionario, String senha) {
		this.controller.excluiFuncionario(senha, matriculaFuncionario);
	}

	/**
	 * Atualiza determinado atributo do funcionario.
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 * @param atributo
	 *            Atributo do funcionario.
	 * @param novoValor
	 *            Valor do novo atributo.
	 */
	public void atualizaInfoFuncionario(String matricula, String atributo, String novoValor) {
		this.controller.atualizaInfoFuncionario(matricula, atributo, novoValor);
	}

	public long cadastraPaciente(String nome, String data, double peso, String sexo, String genero,
			String tipoSanguineo) {
		return this.controller.cadastraPaciente(nome, data, peso, sexo, genero, tipoSanguineo);
	}

	/**
	 * Atualiza a senha do usuario.
	 * 
	 * @param senhaAntiga
	 *            Senha antiga.
	 * @param novaSenha
	 *            Nova senha.
	 */
	public void atualizaSenha(String senhaAntiga, String novaSenha) {
		this.controller.atualizaSenha(senhaAntiga, novaSenha);
	}

	/**
	 * Atualiza determinado atributo do usuario.
	 * 
	 * @param atributo
	 *            Atributo do usuario.
	 * @param novoValor
	 *            Novo valor do atributo.
	 */
	public void atualizaInfoFuncionario(String atributo, String novoValor) {
		this.controller.atualizaInfoFuncionario(atributo, novoValor);
	}
}
