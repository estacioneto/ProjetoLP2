package projeto.hospital.funcionarios;

import java.io.Serializable;

import projeto.exceptions.dados.ObjetoNuloException;
import projeto.exceptions.dados.StringVaziaException;
import projeto.util.Util;

/**
 * Entidade Funcionario. Trata-se da entidade generica do sistema que tera todos
 * os dados de um funcionario do SOOS.
 * 
 * @author Estacio Pereira
 *
 */
public class Funcionario implements Serializable {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 1948219698630791794L;

	private String nome;
	private Cargo cargo;
	private String matricula;
	private String senha;
	private String dataNascimento;

	/**
	 * Construtor padrao.
	 * 
	 * @param nome
	 *            Nome do funcionario.
	 * @param cargo
	 *            Cargo do funcionario.
	 * @param matricula
	 *            Matricula do funcionario.
	 * @param senha
	 *            Senha do funcionario.
	 * @param dataNascimento
	 *            Data de nascimento do funcionario.
	 * @throws ObjetoNuloException 
	 * @throws StringVaziaException 
	 */
	public Funcionario(String nome, String cargo, String matricula,
			String senha, String dataNascimento) {
		// Nao eh necessario validar porque no Gerenciador ja eh feito isso
		// la no gerenciador de funcionarios
		this.nome = nome;
		this.cargo = new Cargo(cargo);
		this.matricula = matricula;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Equals de funcionario, tendo em vista que a matricula identifica cada um.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Funcionario))
			return false;
		Funcionario outro = (Funcionario) obj;

		if (this.matricula.equals(outro.matricula))
			return true;
		return false;
	}

	/**
	 * Retorna o nome do funcionario.
	 * 
	 * @return Nome do funcionario.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Muda o nome do funcionario.
	 * 
	 * @param nome
	 *            Nome novo do funcionario.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o nome do cargo do funcionario.
	 * 
	 * @return Nome do cargo do funcionario.
	 */
	public String getCargo() {
		return cargo.getNome();
	}

	/**
	 * Muda o cargo do funcionario.
	 * 
	 * @param cargo
	 *            Cargo novo do funcionario.
	 */
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	/**
	 * Retorna a matricula do funcionario.
	 * 
	 * @return Matricula do funcionario.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Retorna a senha do funcionario.
	 * 
	 * @return Senha do funcionario.
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Muda a senha do funcionario.
	 * 
	 * @param senha
	 *            Senha nova do funcionario.
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Retorna a data de nascimento do funcionario.
	 * 
	 * @return Data de nascimento do funcionario.
	 */
	public String getDataNascimento() {
		return Util.transformaFormatoData(this.dataNascimento);
	}

	/**
	 * Muda a data do nascimento do funcionario.
	 * 
	 * @param dataNascimento
	 *            Data de nascimento do funcionario.
	 */
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Verifica se o funcionario possui determinada permissao.
	 * 
	 * @param permissao
	 *            Permissao a ser verificada.
	 * @return true se o funcionario possui a permissao.
	 */
	public boolean temPermissao(Permissao permissao) {
		return this.cargo.temPermissao(permissao);
	}

}
