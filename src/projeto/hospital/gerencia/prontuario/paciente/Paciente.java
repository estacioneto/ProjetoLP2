package projeto.hospital.gerencia.prontuario.paciente;

import java.io.Serializable;
import java.time.LocalDate;

import projeto.hospital.gerencia.prontuario.paciente.fidelidade.Fidelidade;
import projeto.hospital.gerencia.prontuario.paciente.fidelidade.FidelidadeMaster;
import projeto.hospital.gerencia.prontuario.paciente.fidelidade.FidelidadePadrao;
import projeto.hospital.gerencia.prontuario.paciente.fidelidade.FidelidadeVIP;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.Util;
import projeto.util.reflexao.ConstantesReflection;
import projeto.util.reflexao.MetodoAssociado;
import projeto.util.reflexao.Validacao;

/**
 * Classe para representar os pacientes
 * 
 * @author Eric
 */
public class Paciente implements Serializable, Comparable<Paciente> {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 1697453654897L;

	@Validacao(metodo = ConstantesReflection.VALIDA_STRING, erro = Constantes.NOME
			+ Constantes.DO_PACIENTE)
	@MetodoAssociado(get = ConstantesReflection.GET_NOME)
	private String nome;

	@Validacao(metodo = ConstantesReflection.VALIDA_DATA, erro = Constantes.DATA)
	@MetodoAssociado(get = ConstantesReflection.GET_DATA_NASCIMENTO)
	private String data;

	@MetodoAssociado(get = ConstantesReflection.GET_IDADE)
	private Integer idade;

	@Validacao(metodo = ConstantesReflection.VALIDA_POSITIVO, erro = Constantes.PESO
			+ Constantes.DO_PACIENTE)
	@MetodoAssociado(get = ConstantesReflection.GET_PESO)
	private Double peso;

	@Validacao(metodo = ConstantesReflection.VALIDA_TIPO_SANGUINEO, erro = MensagensDeErro.TIPO_SANGUINEO_INVALIDO)
	@MetodoAssociado(get = ConstantesReflection.GET_TIPO_SANGUINEO)
	private String tipoSanguineo;

	@Validacao(metodo = ConstantesReflection.VALIDA_SEXO, erro = Constantes.SEXO
			+ Constantes.DO_PACIENTE)
	@MetodoAssociado(get = ConstantesReflection.GET_SEXO)
	private String sexo;

	@Validacao(metodo = ConstantesReflection.VALIDA_STRING, erro = Constantes.GENERO
			+ Constantes.DO_PACIENTE)
	@MetodoAssociado(get = ConstantesReflection.GET_GENERO)
	private String genero;

	@MetodoAssociado(get = "getGastosPaciente")
	private Double gastos;

	@MetodoAssociado(get = "getPontuacao", set = "setPontuacao")
	private Integer pontuacao;

	private Fidelidade fidelidade;

	private String id;

	/**
	 * Construtor
	 * 
	 * @param nome
	 *            Nome
	 * @param dataNascimento
	 *            Data de nascimento
	 * @param peso
	 *            Peso
	 * @param tipoSanguineo
	 *            Tipo sanguineo
	 * @param sexoBiologico
	 *            Sexo biologico
	 * @param genero
	 *            Genero
	 */
	public Paciente(String nome, String dataNascimento, Double peso,
			String tipoSanguineo, String sexoBiologico, String genero) {
		this.nome = nome;
		this.data = dataNascimento;
		this.peso = peso;
		this.tipoSanguineo = tipoSanguineo;
		this.sexo = sexoBiologico;
		this.genero = genero;
		this.gastos = Double.parseDouble(Integer.toString(Constantes.ZERO));
		this.pontuacao = Constantes.ZERO;
		this.fidelidade = new FidelidadePadrao();
	}

	/**
	 * Set a pontuacao do usuario e verifica se o usuario pode mudar de status
	 * na fidelidade.
	 * 
	 * @param pontuacao
	 *            Pontuacao nova ganha pelo usuario
	 */
	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
		this.verificaMudancaStatus();
	}

	/**
	 * @return Nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Muda o nome do paciente
	 * 
	 * @param nome
	 *            Nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return Peso
	 */
	public Double getPeso() {
		return peso;
	}

	/**
	 * @param peso
	 *            Peso
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	/**
	 * @return Data de nascimento
	 */
	public String getDataNascimento() {
		return Util.transformaFormatoData(data);
	}

	/**
	 * @return Tipo sanguineo
	 */
	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	/**
	 * @return Sexo biologico
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @return Genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @return Id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Seta um novo id para o paciente
	 * 
	 * @param id
	 *            Novo id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retorna a pontuacao atual do usario
	 * 
	 * @return pontuacao do usuario
	 */
	public Integer getPontuacao() {
		return this.pontuacao;
	}

	/**
	 * Calcula a idade do paciente
	 * 
	 * @return Idade do paciente
	 */
	public Integer getIdade() {
		LocalDate hoje = LocalDate.now();

		String[] nascimento = data.split(Constantes.BARRA);
		int ano = Integer.parseInt(nascimento[Constantes.INDICE_ANO]), mes = Integer
				.parseInt(nascimento[Constantes.INDICE_MES]), dia = Integer
				.parseInt(nascimento[Constantes.INDICE_DIA]);

		int idade = hoje.getYear() - ano - 1;
		if (mes < hoje.getMonthValue() || mes == hoje.getMonthValue()
				&& dia <= hoje.getDayOfMonth())
			idade++;

		return idade;
	}

	// Metodo responsavel por mudar dinamicamente a fidelidade do usuario.
	private void verificaMudancaStatus() {
		if (this.fidelidade instanceof FidelidadePadrao) {
			if ((this.pontuacao >= Constantes.PONTUACAO_MINIMA_MASTER)
					&& (this.pontuacao <= Constantes.PONTUACAO_MAXIMA_MASTER)) {
				this.fidelidade = new FidelidadeMaster();
			} else if (this.pontuacao > Constantes.PONTUACAO_MAXIMA_MASTER) {
				this.fidelidade = new FidelidadeVIP();
			}
		} else if ((this.fidelidade instanceof FidelidadeMaster)
				&& (this.pontuacao > Constantes.PONTUACAO_MAXIMA_MASTER)) {
			this.fidelidade = new FidelidadeVIP();
		}
	}

	/**
	 * Registra gastos do paciente em procedimentos
	 * 
	 * @param gasto
	 */
	public void registraGasto(double gasto) {
		this.gastos += gasto;
	}

	/**
	 * Metodo temporario, provavelmente vai ser usado algo assim
	 * 
	 * @return Gastos do paciente
	 */
	public Double getGastosPaciente() {
		return this.gastos;
	}

	/**
	 * Muda o genero do paciente
	 */
	public void mudaGenero() {
		if (this.genero.equalsIgnoreCase(Constantes.MASCULINO))
			this.genero = Constantes.FEMININO;

		else
			this.genero = Constantes.MASCULINO;
	}

	// Metodo que calcular o bonus ganho na pontuacao a partir de sua
	// fidelidade.
	private Integer calculaBonusPontuacao(Integer pontuacao) {
		Integer bonus = (int) (this.fidelidade.getCreditoBonus() * pontuacao);
		return bonus;
	}

	/**
	 * Metodo responsavel por registrar os pontos ganhos pelo paciente
	 * 
	 * @param pontuacaoProcedimento
	 *            Pontuacao ganha pelo paciente no procedimento.
	 */
	public void registradorDePontos(Integer pontuacaoProcedimento) {
		pontuacao += pontuacaoProcedimento /*
											 * + this.calculaBonus(
											 * pontuacaoProcedimento)
											 */;
		this.verificaMudancaStatus();

	}

	/**
	 * Compara pacientes pelo nome
	 */
	@Override
	public int compareTo(Paciente paciente) {
		return nome.compareTo(paciente.nome);
	}

	@Override
	public String toString() {
		String saida = "Nome: " + nome + ". ID: " + id + ". Data Nascimento: "
				+ data;
		return saida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Compara a partir do nome do paciente
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Paciente))
			return false;

		Paciente paciente = (Paciente) obj;
		return paciente.nome.equals(this.nome);
	}

	public double calculaDesconto(double d) {
		double valor = d * fidelidade.getDescontoServico();
		return valor;
	}
}
