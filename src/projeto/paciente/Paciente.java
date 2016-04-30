package projeto.paciente;

import projeto.util.Constantes;
import projeto.util.Util;

public class Paciente {
	private String nome;
	private String dataNascimento;
	private double peso;
	private String tiposanguineo;
	private String sexoBiologico;
	private String genero;
	private long id;

	/**
	 * Construtor
	 *  
	 * @param nome Nome
	 * @param dataNascimento Data de nascimento
	 * @param peso Peso
	 * @param tipoSanguineo Tipo sanguineo
	 * @param sexoBiologico Sexo biologico
	 * @param genero Genero
	 * @param id Id
	 */
	public Paciente(String nome, String dataNascimento, double peso, String tipoSanguineo, String sexoBiologico,
			String genero, long id) {
		Util.validaString(Constantes.NOME, nome);
		Util.validaData(Constantes.DATA, dataNascimento);
		Util.validaPositivo(Constantes.PESO, peso);
		Util.validaTipoSanguineo(tipoSanguineo);
		Util.validaSexoBiologico(sexoBiologico);
		Util.validaString(Constantes.GENERO, genero);

		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.peso = peso;
		this.tiposanguineo = tipoSanguineo;
		this.sexoBiologico = sexoBiologico;
		this.genero = genero;
		this.id = id;
	}

	/**
	 * @return Nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Muda o nome do paciente
	 * @param nome Nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return Peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * @param peso Peso
	 */
	public void setPeso(double peso) {
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
	public long getId() {
		return id;
	}
}
