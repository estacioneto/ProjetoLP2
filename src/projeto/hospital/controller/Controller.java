package projeto.hospital.controller;

import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.funcionarios.Funcionario;
import projeto.hospital.funcionarios.Permissao;
import projeto.hospital.gerencia.GerenciadorDeFuncionarios;
import projeto.hospital.gerencia.GerenciadorDeMedicamento;
import projeto.hospital.gerencia.GerenciadorDePacienteProntuario;
import projeto.hospital.gerencia.ValidadorDeLogica;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.Util;

/**
 * Classe controladora. Gerencia a logica de negocio do sistema delegando
 * responsabilidades e conectando a facade com a logica do sistema (Model).
 * 
 * @author Estacio Pereira
 * @author Eric
 * @author Thaynan
 */
public class Controller {

	private Funcionario funcionarioLogado;
	private GerenciadorDeFuncionarios gerenciadorFuncionarios;
	private GerenciadorDePacienteProntuario gerenciadorDePaciente;
	private GerenciadorDeMedicamento gerenciadorDeMedicamento;

	/**
	 * Construtor
	 */
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
		// Se o usuario esta logado vai ser verificado se ele pode realizar a
		// operacao porque o diretor eh cadastrado quando nao existe ninguem
		// logado, por isso precisa disso
		if (!estaLogado()) {
			if (!gerenciadorFuncionarios.isEmpty())
				throw new OperacaoInvalidaException("Voce precisa estar logado no sistema para realizar cadastros.");
		} else
			ValidadorDeLogica.validaOperacao(MensagensDeErro.ERRO_PERMISSAO_CADASTRO_FUNCIONARIO,
					Permissao.CADASTRAR_FUNCIONARIOS, funcionarioLogado);
		return this.gerenciadorFuncionarios.cadastraFuncionario(nome, cargo, dataNascimento);
	}

	/**
	 * Inicia o sistema se ele ainda nao foi iniciado
	 */
	public void iniciaSistema() {
		if (gerenciadorFuncionarios != null)
			throw new OperacaoInvalidaException("O sistema ja foi iniciado.");
		this.gerenciadorDePaciente = new GerenciadorDePacienteProntuario();
		this.gerenciadorDeMedicamento = new GerenciadorDeMedicamento();
		try {
			this.gerenciadorFuncionarios = (GerenciadorDeFuncionarios) Util
					.getObjeto(Constantes.ARQUIVO_GERENCIADOR_FUNCIONARIOS);
		} catch (Exception excecao) {
			this.gerenciadorFuncionarios = new GerenciadorDeFuncionarios();
			Util.criaArquivo(Constantes.ARQUIVO_GERENCIADOR_FUNCIONARIOS);
		}
	}

	/**
	 * Fecha o sistema se nenhum usuario estiver mais logado
	 */
	public void fechaSistema() {
		if (estaLogado())
			throw new OperacaoInvalidaException("Nao foi possivel fechar o sistema. Um funcionario ainda esta logado: "
					+ funcionarioLogado.getNome() + ".");
		Util.setObjeto(Constantes.ARQUIVO_GERENCIADOR_FUNCIONARIOS, gerenciadorFuncionarios);
		this.gerenciadorFuncionarios = null;
		this.gerenciadorDePaciente = null;
	}

	/**
	 * Desloga do sistema
	 */
	public void logout() {
		if (!estaLogado())
			throw new OperacaoInvalidaException("Nao foi possivel realizar o logout. Nao ha um funcionario logado.");
		funcionarioLogado = null;
	}

	/**
	 * Exclui um funcionario do sistema
	 * 
	 * @param senhaDiretor
	 *            Senha do diretor
	 * @param matriculaFuncionario
	 *            Matricula do usuario a ser excluido
	 */
	public void excluiFuncionario(String senhaDiretor, String matriculaFuncionario) {
		if (!estaLogado())
			throw new OperacaoInvalidaException("Voce deve estar logado para acessar o sistema.");
		this.gerenciadorFuncionarios.excluiFuncionario(this.funcionarioLogado.getMatricula(), senhaDiretor,
				matriculaFuncionario);
	}

	/**
	 * Atualiza as informacoes de um outro usuario
	 * 
	 * @param matricula
	 *            Matricula do usuario
	 * @param atributo
	 *            Atributo a ser atualizado
	 * @param novoValor
	 *            Novo valor do atributo
	 */
	public void atualizaInfoFuncionario(String matricula, String atributo, String novoValor) {
		this.gerenciadorFuncionarios.atualizaInfoFuncionario(this.funcionarioLogado, matricula, atributo, novoValor);
	}

	/**
	 * Atualiza o proprio usuario
	 * 
	 * @param atributo
	 *            Atributo a ser atualizado
	 * @param novoValor
	 *            Novo valor do atributo
	 */
	public void atualizaInfoFuncionario(String atributo, String novoValor) {
		if (!estaLogado())
			throw new OperacaoInvalidaException("Voce deve estar logado para acessar o sistema.");
		this.gerenciadorFuncionarios.atualizaInfoFuncionario(funcionarioLogado, this.funcionarioLogado.getMatricula(),
				atributo, novoValor);
	}

	/**
	 * Atualiza senha do usuario
	 * 
	 * @param senhaAntiga
	 *            Senha antiga
	 * @param novaSenha
	 *            Nova senha
	 */
	public void atualizaSenha(String senhaAntiga, String novaSenha) {
		if (!estaLogado())
			throw new OperacaoInvalidaException("Voce deve estar logado para acessar o sistema.");
		this.gerenciadorFuncionarios.atualizaSenha(this.funcionarioLogado, senhaAntiga, novaSenha);
	}

	/**
	 * Pega informacao de um funcionario
	 * 
	 * @param matricula
	 *            Matricula do usuario
	 * @param atributo
	 *            Atributo a ser requisitado
	 * @return Atributo requisitado
	 */
	public String getInfoFuncionario(String matricula, String atributo) {
		return this.gerenciadorFuncionarios.getInfoFuncionario(matricula, atributo);
	}

	/**
	 * Faz o login no sistema
	 * 
	 * @param matricula
	 *            Matricula
	 * @param senha
	 *            Senha
	 */
	public void loginSistema(String matricula, String senha) {
		if (estaLogado())
			throw new OperacaoInvalidaException("Nao foi possivel realizar o login. Um funcionario ainda esta logado: "
					+ funcionarioLogado.getNome() + ".");
		this.funcionarioLogado = this.gerenciadorFuncionarios.acessaSistema(matricula, senha);
	}

	/**
	 * Verifica se ha funcionario logado.
	 * 
	 * @return true se um funcionario esta logado.
	 */
	private boolean estaLogado() {
		return this.funcionarioLogado != null;
	}

	/**
	 * Realiza o cadastro de um paciente
	 * 
	 * @param nome
	 *            Nome do paciente
	 * @param data
	 *            Data de nascimento do paciente
	 * @param peso
	 *            Peso do paciente
	 * @param sexo
	 *            Sexo biologico do paciente
	 * @param genero
	 *            Genero do paciente
	 * @param tipoSanguineo
	 *            Tipo sanguineo do paciente
	 * @return Id do paciente cadastrado
	 */
	public long cadastraPaciente(String nome, String data, double peso, String sexo, String genero,
			String tipoSanguineo) {
		if (!estaLogado()) {
			throw new OperacaoInvalidaException(
					"Voce precisa estar logado no sistema para realizar cadastros de pacientes.");
		} else
			ValidadorDeLogica.validaOperacao(MensagensDeErro.ERRO_PERMISSAO_CADASTRO_PACIENTE,
					Permissao.CADASTRAR_PACIENTES, funcionarioLogado);

		return this.gerenciadorDePaciente.cadastraPaciente(nome, data, peso, sexo, genero, tipoSanguineo);
	}

	/**
	 * Acessa uma informacao especifica sobre um paciente
	 * 
	 * @param idPaciente
	 *            Id do paciente
	 * @param atributo
	 *            Informacao a ser requisitada
	 * @return Informacao requisitada
	 */
	public Object getInfoPaciente(long idPaciente, String atributo) {
		if (!estaLogado())
			throw new OperacaoInvalidaException("Voce precisa estar logado no sistema para realizar cadastros.");
		return this.gerenciadorDePaciente.getInfoPaciente(idPaciente, atributo);
	}

	/**
	 * Pega o id de um prontuario
	 * 
	 * @param posicao
	 *            Posicao do prontuario
	 * @return Id
	 */
	public Long getProntuario(int posicao) {
		return this.gerenciadorDePaciente.getProntuario(posicao);
	}

	/**
	 * Metodo que cadastra um medicamento.
	 * 
	 * @param nome
	 *            Nome do medicamento.
	 * @param tipo
	 *            Tipo do medicamento.
	 * @param preco
	 *            Preco do medicamento.
	 * @param quantidade
	 *            Quantidade do medicamento.
	 * @param categoriasCategorias
	 *            do medicamento.
	 * @return Nome do medicamento.
	 */
	public String cadastraMedicamento(String nome, String tipo, Double preco,
			int quantidade, String categorias) {
		if (!estaLogado())
			throw new OperacaoInvalidaException("Voce precisa estar logado no sistema para realizar cadastros.");
		else
			ValidadorDeLogica.validaOperacao(MensagensDeErro.ERRO_PERMISSAO_CADASTRO_MEDICAMENTO,
					Permissao.CADASTRAR_MEDICAMENTO, funcionarioLogado);
		return gerenciadorDeMedicamento.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
	}

	/**
	 * Metodo que retorna um determinado atributo de um medicamento, passado o
	 * seu nome.
	 * 
	 * @param atributo
	 *            Atributo do medicamento.
	 * @param nomeMedicamento
	 *            Nome do medicamento.
	 * @return atributo do medicamento.
	 */
	public Object getInfoMedicamento(String atributo, String nome) {
		return gerenciadorDeMedicamento.getInfoMedicamento(atributo, nome);
	}

	/**
	 * Metodo que atualiza um atributo de um medicamento.
	 * 
	 * @param nomeMedicamento
	 *            Nome do medicamento.
	 * @param atributo
	 *            Atributo a ser atualizado.
	 * @param novoValor
	 *            Novo valor do atributo
	 */
	public void atualizaMedicamento(String nome, String atributo, String novo) {
		this.gerenciadorDeMedicamento.atualizaMedicamento(nome, atributo, novo);
	}

	/**
	 * Metodo que retorna uma lista em String de todos os medicamentos com
	 * determinada categoria.
	 * 
	 * @param categoria
	 *            Categoria do medicamento desejada.
	 * @return lista em String dos medicamentos.
	 */
	public String consultaMedCategoria(String categoria) {
		return this.gerenciadorDeMedicamento.consultaMedCategoria(categoria);
	}

	/**
	 * Metodo que retorna as caracteristicas de um medicamento.
	 * 
	 * @param nome
	 *            Nome do medicamento.
	 * @return Caracteristicas do medicamento.
	 */
	public String consultaMedNome(String nome) {
		return this.gerenciadorDeMedicamento.consultaMedNome(nome);
	}

	/**
	 * Metodo que retorna uma lista em String dos medicamentos a partir de uma
	 * ordem definida.
	 * 
	 * @param ordenacao
	 *            Ordenacao desejada.
	 * @return lista ordenada em String dos medicamentos.
	 */
	public String getEstoqueFarmacia(String ordenacao) {
		return this.gerenciadorDeMedicamento.getEstoqueFarmacia(ordenacao);
	}
}
