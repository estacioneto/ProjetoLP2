package projeto.hospital.gerencia.funcionario;

import java.io.Serializable;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.gerencia.funcionario.cargo.Cargo;
import projeto.hospital.gerencia.funcionario.cargo.Permissao;
import projeto.util.ConstantesReflection;
import projeto.util.MetodoAssociado;
import projeto.util.Util;
import projeto.util.ValidadorDeDados;

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

	@MetodoAssociado(get = ConstantesReflection.GET_NOME, set = ConstantesReflection.SET_NOME)
	private String nome;
	@MetodoAssociado(get = ConstantesReflection.GET_MATRICULA)
	private String matricula;
	@MetodoAssociado(get = ConstantesReflection.GET_CARGO)
	private Cargo cargo;
	@MetodoAssociado(get = ConstantesReflection.GET_SENHA_PROTEGIDA)
	private String senha;
	@MetodoAssociado(get = ConstantesReflection.GET_DATA_NASCIMENTO, set = ConstantesReflection.SET_DATA)
	private String data;

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
	 */
	public Funcionario(String nome, Cargo cargo, String matricula,
			String senha, String dataNascimento) {
		// Nao eh necessario validar porque no Gerenciador ja eh feito isso
		// la no gerenciador de funcionarios
		this.nome = nome;
		this.cargo = cargo;
		this.matricula = matricula;
		this.senha = senha;
		this.data = dataNascimento;
	}

	// VERIFICACAO DE PERMISSAO
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
	// VERIFICACAO DE PERMISSAO
	// GETTERS E SETTERS
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
	public String getCargoNome() {
		return cargo.getNome();
	}

	/**
	 * Retorna o cargo do funcionario.
	 * 
	 * @return Cargo do funcionario.
	 */
	public Cargo getCargo() {
		return cargo;
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
	 * Metodo utilizado para consulta de senha.
	 *  
	 * @return {@code OperacaoInvalidaException}
	 */
	public String getSenhaProtegida() {
		throw new OperacaoInvalidaException("A senha do funcionario eh protegida.");
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
		return Util.transformaFormatoData(this.data);
	}

	/**
	 * Muda a data do nascimento do funcionario.
	 * 
	 * @param dataNascimento
	 *            Data de nascimento do funcionario.
	 * @throws DadoInvalidoException
	 *             Caso a data seja invalida.
	 */
	public void setData(String dataNascimento)
			throws DadoInvalidoException {
		ValidadorDeDados.validaData(dataNascimento);
		this.data = dataNascimento;
	}
	// GETTERS E SETTERS

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

}
