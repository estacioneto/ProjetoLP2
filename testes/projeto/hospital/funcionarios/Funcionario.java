package projeto.hospital.funcionarios;

import java.time.LocalDate;

public class Funcionario {
	
	private String nome;
	private String cargo;
	private String matricula;
	private String senha;
	private LocalDate dataNascimento;
	
	public Funcionario(String nome, String cargo, String matricula, String senha, LocalDate dataNascimento) {
		this.nome = nome;
		this.cargo = cargo;
		this.matricula = matricula;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
