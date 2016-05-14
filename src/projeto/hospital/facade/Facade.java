package projeto.hospital.facade;

import projeto.exceptions.logica.AcessoBloqueadoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.controller.Controller;
import projeto.util.Constantes;
import projeto.util.Util;

/**
 * Classe de fachada. A fachada do sistema cuida da interacao do usuario com o
 * sistema.
 * 
 * @author Estacio Pereira
 * @author Eric
 * @author Thaynan
 */
public class Facade {

	private final String CHAVE_DESBLOQUEIO = "c041ebf8";
	private Controller controller;
	private boolean sistemaJaLiberado;

	/**
	 * Construtor padrao.
	 */
	public Facade() {
		this.controller = null;
		this.sistemaJaLiberado = false;
	}

	// OPERACOES DO SISTEMA

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
	 * Inicia o sistema.
	 */
	public void iniciaSistema() {
		if (this.controller != null)
			throw new OperacaoInvalidaException("O sistema ja foi iniciado.");

		try {
			this.controller = (Controller) Util.getObjeto(Constantes.ARQUIVO_CONTROLLER);
		} catch (Exception excecao) {
			this.controller = new Controller();
			Util.criaArquivo(Constantes.ARQUIVO_CONTROLLER);
		}
	}

	/**
	 * Fecha o sistema.
	 */
	public void fechaSistema() {
		this.controller.fechaSistema();
		Util.setObjeto(Constantes.ARQUIVO_CONTROLLER, this.controller);
		this.controller = null;
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
		this.controller.loginSistema(matricula, senha);
	}

	/**
	 * Usuario sai da sua conta.
	 */
	public void logout() {
		this.controller.logout();
	}

	// OPERACOES DO SISTEMA
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

	/**
	 * Atualiza determinado atributo do usuario (funcionario logado).
	 * 
	 * @param atributo
	 *            Atributo do usuario.
	 * @param novoValor
	 *            Novo valor do atributo.
	 */
	public void atualizaInfoFuncionario(String atributo, String novoValor) {
		this.controller.atualizaInfoFuncionario(atributo, novoValor);
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

	// CONSULTA DE FUNCIONARIO
	/**
	 * Pega determinada informacao do funcionario desejado.
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 * @param atributo
	 *            Atributo do funcionario.
	 * @return Informacao do funcionario.
	 */
	public Object getInfoFuncionario(String matricula, String atributo) {
		return this.controller.getInfoFuncionario(matricula, atributo);
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
		return this.controller.cadastraPaciente(nome, data, peso, sexo, genero, tipoSanguineo);
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
		return this.controller.getInfoPaciente(idPaciente, atributo);
	}

	/**
	 * Pega o id de um prontuario
	 * 
	 * @param posicao
	 *            Posicao do prontuario
	 * @return Id
	 */
	public String getProntuario(int posicao) {
		return this.controller.getProntuario(posicao);
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
		return this.controller.cadastraMedicamento(nome, tipo, preco, quantidade, categorias);
	}

	/**
	 * Metodo que atualiza um atributo de um medicamento.
	 * 
	 * @param nome
	 *            Nome do medicamento.
	 * @param atributo
	 *            Atributo a ser atualizado.
	 * @param novoValor
	 *            Novo valor do atributo
	 */
	public void atualizaMedicamento(String nome, String atributo, String novoValor) {
		this.controller.atualizaMedicamento(nome, atributo, novoValor);
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
		return this.controller.getInfoMedicamento(atributo, nome);
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
		return this.controller.consultaMedCategoria(categoria);
	}

	/**
	 * Metodo que retorna as caracteristicas de um medicamento.
	 * 
	 * @param nome
	 *            Nome do medicamento.
	 * @return Caracteristicas do medicamento.
	 */
	public String consultaMedNome(String nome) {
		return this.controller.consultaMedNome(nome);
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
		return this.controller.getEstoqueFarmacia(ordenacao);
	}

	/**
	 * Pega o id de um paciente de acordo com seu nome
	 * 
	 * @param nome
	 *            Nome do paciente
	 * @return Id do paciente
	 */
	public String getPacienteID(String nome) {
		return this.controller.getPacienteId(nome);
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
		this.controller.realizaProcedimento(nomeProcedimento, idPaciente, medicamentos);
	}

	/**
	 * Realiza um procedimento
	 * 
	 * @param nomeProcedimento
	 *            Nome do procedimento
	 * @param idPaciente
	 *            Id do paciente
	 * @param orgao
	 *            Orgao a ser usado no procedimento
	 * @param medicamentos
	 *            Medicamentos necessarios
	 */
	public void realizaProcedimento(String nomeProcedimento, String idPaciente, String orgao, String medicamentos) {
		this.controller.realizaProcedimento(nomeProcedimento, idPaciente, orgao, medicamentos);
	}

	public void realizaProcedimento(String procedimento, String idPaciente){
		this.controller.realizaProcedimento(procedimento, idPaciente);
	}
	
	/**
	 * Pega a quantidade de procedimentos realizados pelo paciente
	 * 
	 * @param idPaciente
	 *            Id do paciente
	 * @return Quantidade de procedimentos realizados
	 */
	public int getTotalProcedimento(String idPaciente) {
		return this.controller.getTotalProcedimento(idPaciente);
	}

	// CONSULTA DE MEDICAMENTO/FARMACIA
	// OPERACOES DE MEDICAMENTO/FARMACIA
	// OPERACOES DE ORGAO
	public void cadastraOrgao(String nome, String tipoSanguineo) {
		this.controller.cadastraOrgao(nome, tipoSanguineo);
	}

	public void retiraOrgao(String nome, String tipoSanguineo) {
		this.controller.retiraOrgao(nome, tipoSanguineo);
	}

	public String buscaOrgPorNome(String nome) {
		return this.controller.buscaOrgPorNome(nome);
	}

	public String buscaOrgPorSangue(String tipoSanguineo) {
		return this.controller.buscaOrgPorSangue(tipoSanguineo);
	}

	public boolean buscaOrgao(String nome, String tipoSanguineo) {
		return this.controller.buscaOrgao(nome, tipoSanguineo);
	}

	public int qtdOrgaos(String nome) {
		return this.controller.qtdOrgaos(nome);
	}

	public int totalOrgaosDisponiveis() {
		return this.controller.totalOrgaosDisponiveis();
	}
	// OPERACOES DE ORGAO
	
	public int getPontosFidelidade(String id){
		return this.controller.getPontosFidelidade(id);
	}
	
	public Double getGastosPaciente(String id){
		return this.controller.getGastosPaciente(id);
	}
}
