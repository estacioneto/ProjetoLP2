package projeto.hospital.controller;

import java.io.Serializable;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.gerencia.ValidadorDeLogica;
import projeto.hospital.gerencia.bancodeorgaos.BancoDeOrgaos;
import projeto.hospital.gerencia.bancodeorgaos.Orgao;
import projeto.hospital.gerencia.farmacia.GerenciadorDeFarmacia;
import projeto.hospital.gerencia.funcionario.Funcionario;
import projeto.hospital.gerencia.funcionario.GerenciadorDeFuncionarios;
import projeto.hospital.gerencia.funcionario.cargo.Permissao;
import projeto.hospital.gerencia.procedimentos.GerenciadorProcedimento;
import projeto.hospital.gerencia.prontuario.GerenciadorDePacienteProntuario;
import projeto.hospital.gerencia.prontuario.Prontuario;
import projeto.hospital.gerencia.prontuario.paciente.Paciente;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.ValidadorDeDados;

/**
 * Classe controladora. Gerencia a logica de negocio do sistema delegando
 * responsabilidades e conectando a facade com a logica do sistema (Model).
 * 
 * @author Estacio Pereira
 * @author Eric
 * @author Thaynan
 */
public class Controller implements Serializable {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = -1215543804197231123L;

	private Funcionario funcionarioLogado;
	private GerenciadorDeFuncionarios gerenciadorFuncionarios;
	private GerenciadorDePacienteProntuario gerenciadorDePaciente;
	private GerenciadorDeFarmacia gerenciadorDeMedicamento;
	private BancoDeOrgaos bancoDeOrgaos;
	private GerenciadorProcedimento gerenciadorProcedimento;

	/**
	 * Construtor
	 */
	public Controller() {
		this.gerenciadorDePaciente = new GerenciadorDePacienteProntuario();
		this.gerenciadorDeMedicamento = new GerenciadorDeFarmacia();
		this.gerenciadorFuncionarios = new GerenciadorDeFuncionarios();
		this.bancoDeOrgaos = new BancoDeOrgaos();
		this.gerenciadorProcedimento = new GerenciadorProcedimento();
		this.funcionarioLogado = null;
	}

	// OPERACOES DO SISTEMA
	/**
	 * Fecha o sistema se nenhum usuario estiver mais logado
	 */
	public void fechaSistema() {
		if (estaLogado())
			throw new OperacaoInvalidaException("Nao foi possivel fechar o sistema. Um funcionario ainda esta logado: "
					+ funcionarioLogado.getNome() + ".");
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
	 * Desloga do sistema
	 */
	public void logout() {
		if (!estaLogado())
			throw new OperacaoInvalidaException("Nao foi possivel realizar o logout. Nao ha um funcionario logado.");
		funcionarioLogado = null;
	}

	/**
	 * Verifica se ha funcionario logado.
	 * 
	 * @return true se um funcionario esta logado.
	 */
	private boolean estaLogado() {
		return this.funcionarioLogado != null;
	}

	// OPERACOES DO SISTEMA
	// OPERACOES DE FUNCIONARIO
	/**
	 * Realiza o cadastro de um funcionario.
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
		}
		return this.gerenciadorFuncionarios.cadastraFuncionario(nome, cargo, dataNascimento, funcionarioLogado);
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
	 * Atualiza o proprio usuario.
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

	// CONSULTA DE FUNCIONARIO
	/**
	 * Pega informacao de um funcionario
	 * 
	 * @param matricula
	 *            Matricula do usuario
	 * @param atributo
	 *            Atributo a ser requisitado
	 * @return Atributo requisitado
	 */
	public Object getInfoFuncionario(String matricula, String atributo) {
		return this.gerenciadorFuncionarios.getInfoFuncionario(matricula, atributo);
	}
	// CONSULTA DE FUNCIONARIO
	// OPERACOES DE FUNCIONARIO
	// OPERACOES DE PACIENTE/PRONTUARIO

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
	public String cadastraPaciente(String nome, String data, double peso, String sexo, String genero,
			String tipoSanguineo) {
		if (!estaLogado()) {
			throw new OperacaoInvalidaException(
					"Voce precisa estar logado no sistema para realizar cadastros de pacientes.");
		}

		return this.gerenciadorDePaciente.cadastraPaciente(nome, data, peso, sexo, genero, tipoSanguineo,
				funcionarioLogado);
	}

	// CONSULTA DE PACIENTE/PRONTUARIO
	/**
	 * Acessa uma informacao especifica sobre um paciente
	 * 
	 * @param idPaciente
	 *            Id do paciente
	 * @param atributo
	 *            Informacao a ser requisitada
	 * @return Informacao requisitada
	 */
	public Object getInfoPaciente(String idPaciente, String atributo) {
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
	public String getProntuario(int posicao) {
		return this.gerenciadorDePaciente.getIdProntuarioPosicao(posicao);
	}

	// CONSULTA DE PACIENTE/PRONTUARIO
	// OPERACOES DE PACIENTE/PRONTUARIO
	// OPERACOES DE MEDICAMENTO/FARMACIA
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
	 * @param categorias
	 *            do medicamento.
	 * @return Nome do medicamento.
	 */
	public String cadastraMedicamento(String nome, String tipo, Double preco, int quantidade, String categorias) {
		if (!estaLogado())
			throw new OperacaoInvalidaException("Voce precisa estar logado no sistema para realizar cadastros.");
		return gerenciadorDeMedicamento.cadastraMedicamento(nome, tipo, preco, quantidade, categorias,
				funcionarioLogado);
	}

	/**
	 * Metodo que atualiza um atributo de um medicamento.
	 * 
	 * @param nome
	 *            Nome do medicamento.
	 * @param atributo
	 *            Atributo a ser atualizado.
	 * @param novo
	 *            Novo valor do atributo
	 */
	public void atualizaMedicamento(String nome, String atributo, String novo) {
		this.gerenciadorDeMedicamento.atualizaMedicamento(nome, atributo, novo);
	}

	// CONSULTA DE MEDICAMENTO/FARMACIA
	/**
	 * Metodo que retorna um determinado atributo de um medicamento, passado o
	 * seu nome.
	 * 
	 * @param atributo
	 *            Atributo do medicamento.
	 * @param nome
	 *            Nome do medicamento.
	 * @return atributo do medicamento.
	 */
	public Object getInfoMedicamento(String atributo, String nome) {
		return gerenciadorDeMedicamento.getInfoMedicamento(atributo, nome);
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

	// CONSULTA DE MEDICAMENTO/FARMACIA
	// OPERACOES DE MEDICAMENTO/FARMACIA
	// OPERACOES DE ORGAO
	public void cadastraOrgao(String nome, String tipoSanguineo) {
		this.bancoDeOrgaos.cadastraOrgao(nome, tipoSanguineo);
	}

	public void retiraOrgao(String nome, String tipoSanguineo) {
		this.bancoDeOrgaos.retiraOrgao(nome, tipoSanguineo);
	}

	public boolean buscaOrgao(String nome, String tipoSanguineo) {
		return this.bancoDeOrgaos.buscaOrgao(nome, tipoSanguineo);
	}

	public String buscaOrgPorNome(String nome) {
		return this.bancoDeOrgaos.buscaOrgPorNome(nome);
	}

	public String buscaOrgPorSangue(String tipoSanguineo) {
		return this.bancoDeOrgaos.buscaOrgPorSangue(tipoSanguineo);
	}

	public int qtdOrgaos(String nome) {
		return this.bancoDeOrgaos.qtdOrgaos(nome);
	}

	public int totalOrgaosDisponiveis() {
		return this.bancoDeOrgaos.totalOrgaosDisponiveis();
	}
	// OPERACOES DE ORGAO
	// OPERACOES DE PROCEDIMENTO

	public void realizaProcedimento(String procedimento, String idPaciente) {
		this.realizaProcedimento(procedimento, idPaciente, "");
	}
	
	/**
	 * Realiza um procedimento
	 * 
	 * @param nomeProcedimento
	 *            Nome do procedimento
	 * @param idPaciente
	 *            Id do paciente
	 * @param medicamentos
	 *            Medicamentos necessarios
	 */
	public void realizaProcedimento(String nomeProcedimento, String idPaciente, String medicamentos) {
		try {
			ValidadorDeDados.validaProcedimento(nomeProcedimento);
			ValidadorDeLogica.validaOperacao("O funcionario %s" + MensagensDeErro.ERRO_SEM_PERMISSAO_PROCEDIMENTO,
					Permissao.REALIZAR_PROCEDIMENTO, funcionarioLogado);
			Prontuario prontuario = this.gerenciadorDePaciente.getProntuarioPaciente(idPaciente);
			Double valorMedicamentos = this.gerenciadorDeMedicamento.getValorMedicamentos(medicamentos);

			this.gerenciadorProcedimento.realizaProcedimento(nomeProcedimento, prontuario, valorMedicamentos);

		} catch (DadoInvalidoException | OperacaoInvalidaException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_REALIZAR_PROCEDIMENTO + e.getMessage());
		}
	}

	/**
	 * Realiza um procedimento
	 * 
	 * @param nomeProcedimento
	 *            Nome do procedimento
	 * @param idPaciente
	 *            Id do paciente
	 * @param orgao
	 *            Orgao para o procedimento
	 * @param medicamentos
	 *            Medicamentos necessarios
	 */
	public void realizaProcedimento(String nomeProcedimento, String idPaciente, String orgao, String medicamentos) {
		try {
			ValidadorDeDados.validaProcedimento(nomeProcedimento);
			Prontuario prontuario = this.gerenciadorDePaciente.getProntuarioPaciente(idPaciente);
			ValidadorDeDados.validaString(Constantes.NOME + Constantes.DO_ORGAO, orgao);
			ValidadorDeLogica.validaOperacao("O funcionario %s" + MensagensDeErro.ERRO_SEM_PERMISSAO_PROCEDIMENTO,
					Permissao.REALIZAR_PROCEDIMENTO, funcionarioLogado);
			String sanguePaciente = prontuario.getPaciente().getTipoSanguineo();
			Orgao orgaoRecuperado = null;
			try {
				orgaoRecuperado = this.bancoDeOrgaos.getOrgao(orgao, sanguePaciente);
			} catch (OperacaoInvalidaException e) {
				throw new DadoInvalidoException("Banco nao possui o orgao especificado.");
			}
			Double valorMedicamento = this.gerenciadorDeMedicamento.getValorMedicamentos(medicamentos);

			this.gerenciadorProcedimento.realizaProcedimento(nomeProcedimento, prontuario, orgaoRecuperado,
					valorMedicamento);

		} catch (OperacaoInvalidaException | DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_REALIZAR_PROCEDIMENTO + e.getMessage());
		}
	}

	/**
	 * Pega o id de um paciente de acordo com seu nome
	 * 
	 * @param nome
	 *            Nome do paciente
	 * @return Id do paciente
	 */
	public String getPacienteId(String nome) {
		return this.gerenciadorDePaciente.getIdPaciente(nome);
	}

	/**
	 * Pega a quantidade de procedimentos realizados pelo paciente
	 * 
	 * @param idPaciente
	 *            Id do paciente
	 * @return Quantidade de procedimentos realizados
	 */
	public int getTotalProcedimento(String idPaciente) {
		return this.gerenciadorDePaciente.getTotalProcedimento(idPaciente);
	}

	public int getPontosFidelidade(String id) {
		try {
			Prontuario prontuario = this.gerenciadorDePaciente.getProntuarioPaciente(id);
			Paciente paciente = prontuario.getPaciente();
			return paciente.getPontuacao();
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTAR_PRONTUARIO + e.getMessage());
		}
	}

	public String getGastosPaciente(String id) {
		try {
			Prontuario prontuario = this.gerenciadorDePaciente.getProntuarioPaciente(id);
			Paciente paciente = prontuario.getPaciente();
			String gastoFormatado = String.format("%.2f", paciente.getGastosPaciente()).replace(",", ".");
			return gastoFormatado;
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTAR_PRONTUARIO + e.getMessage());
		}
	}
}
