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
	
	public Paciente(String nome, String dataNascimento, double peso, String tipoSanguineo, String sexoBiologico, String genero, long id){
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public String getTiposanguineo() {
		return tiposanguineo;
	}

	public String getSexoBiologico() {
		return sexoBiologico;
	}

	public String getGenero() {
		return genero;
	}

	public long getId() {
		return id;
	}
	
	

}
