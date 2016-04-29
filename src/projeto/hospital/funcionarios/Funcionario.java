package projeto.hospital.funcionarios;

import projeto.util.Constantes;
import projeto.util.Util;

public class Funcionario {

	private String nome;
	private Cargo cargo;
	private String matricula;
	private String senha;
	private String dataNascimento;

	public Funcionario(String nome, Cargo cargo, String matricula,
			String senha, String dataNascimento) {
		Util.validaString(Constantes.NOME, nome);
		Util.validaString(Constantes.MATRICULA, matricula);
		Util.validaString(Constantes.SENHA, senha);
		Util.validaData(Constantes.DATA_NASCIMENTO, dataNascimento);

		this.nome = nome;
		this.cargo = cargo;
		this.matricula = matricula;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Funcionario))
			return false;
		Funcionario outro = (Funcionario) obj;

		if (this.matricula.equals(outro.matricula))
			return true;
		return false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo.getNome();
	}

	public void setCargo(Cargo cargo) {
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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
