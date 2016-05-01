package projeto.hospital.paciente;

import java.io.Serializable;
import java.time.LocalDate;

import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.Validadores;

/**
 * @author Eric
 */
public class Paciente implements Serializable { 
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 1697453654897L;
	
	private String nome;
	private String dataNascimento;
	private Double peso;
	private String tiposanguineo;
	private String sexoBiologico;
	private String genero;
	private Long id;

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
	 * @param id
	 *            Id
	 */
	public Paciente(String nome, String dataNascimento, Double peso, String tipoSanguineo, String sexoBiologico,
			String genero) {
		Validadores.validaString(MensagensDeErro.ERRO_NOME_PACIENTE, nome);
		Validadores.validaData(MensagensDeErro.ERRO_DATA_PACIENTE, dataNascimento);
		Validadores.validaPositivo(MensagensDeErro.ERRO_PESO_PACIENTE, peso);
		Validadores.validaTipoSanguineo(tipoSanguineo);
		Validadores.validaSexoBiologico(sexoBiologico);
		Validadores.validaString(Constantes.GENERO, genero);

		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.peso = peso;
		this.tiposanguineo = tipoSanguineo;
		this.sexoBiologico = sexoBiologico;
		this.genero = genero;
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
		return dataNascimento;
	}

	/**
	 * @return Tipo sanguineo
	 */
	public String getTiposanguineo() {
		return tiposanguineo;
	}

	/**
	 * @return Sexo biologico
	 */
	public String getSexoBiologico() {
		return sexoBiologico;
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
	public Long getId() {
		return id;
	}

	/**
	 * Seta um novo id para o paciente
	 * 
	 * @param id
	 *            Novo id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Calcula a idade do paciente
	 * 
	 * @return Idade do paciente
	 */
	public Integer getIdade() {
		LocalDate hoje = LocalDate.now();

		String[] nascimento = this.dataNascimento.split("/");
		int ano = Integer.parseInt(nascimento[2]), 
			mes = Integer.parseInt(nascimento[1]),
			dia = Integer.parseInt(nascimento[0]);

		int idade = hoje.getYear() - ano - 1;
		if (mes < hoje.getMonthValue() || mes == hoje.getMonthValue() && dia <= hoje.getDayOfMonth())
			idade++;

		return idade;
	}

	@Override
	public String toString() {
		String saida = "Nome: " + nome + ". ID: " + id + ". Data Nascimento: " + dataNascimento;
		return saida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Paciente))
			return false;

		Paciente paciente = (Paciente) obj;
		return paciente.nome.equals(this.nome);
	}
}