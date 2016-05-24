package projeto.hospital.gerencia.funcionario;

import java.io.Serializable;
import java.text.DecimalFormat;

import projeto.util.Constantes;
import projeto.util.Util;

/**
 * Gerenciador de dados de seguranca. O gerenciador trata da logica de seguranca
 * do sistema envolvendo, por exemplo, operacoes relacionadas a matricula e
 * senha.
 * 
 * @author Estacio Pereira
 */
public class GeradorDeDadosDeSeguranca implements Serializable {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = -787866569388135898L;

	private Integer cadastros;
	private DecimalFormat formatadorDecimal;
	private static GeradorDeDadosDeSeguranca instancia;

	/**
	 * Construtor padrao.
	 */
	private GeradorDeDadosDeSeguranca() {
		this.cadastros = 1;
		this.formatadorDecimal = new DecimalFormat(Constantes.FORMATO_TRES_NUMEROS);
	}
	
	/**
	 * @return Instancia do gerador
	 */
	public static GeradorDeDadosDeSeguranca getInstancia(){
		if(instancia == null){
			instancia = new GeradorDeDadosDeSeguranca();
		}
		return instancia;
	}

	/**
	 * Gera uma matricula automaticamente.
	 * 
	 * @param cargo
	 *            Cargo do funcionario.
	 * @param ano
	 *            Ano de nascimento do funcionario.
	 * @return Matricula gerada.
	 */
	public String geraMatricula(String cargo, String ano) {
		String matricula = getMatriculaCadastro(cargo, ano, Integer.toString(cadastros));
		this.novoCadastroEfetuado();
		return matricula;
	}

	/**
	 * Retorna a matricula de um determinado cadastro.
	 * 
	 * @param cargo
	 *            Cargo do funcionario.
	 * @param ano
	 *            Ano de nascimento do funcionario.
	 * @param cadastro
	 *            Cadastro desejado.
	 * @return Matricula gerada no determinado cadastro.
	 */
	public String getMatriculaCadastro(String cargo, String ano, String cadastro) {
		String codigo = Util.getCodigoPorCargo(cargo);
		String matricula = codigo + ano + formatadorDecimal.format(Integer.parseInt(cadastro));
		return matricula;
	}

	/**
	 * Registra que um novo cadastro foi efetuado.
	 */
	private void novoCadastroEfetuado() {
		this.cadastros++;
	}

	/**
	 * Gera uma senha automaticamente.
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 * @param ano
	 *            Ano de nascimento do funcionario.
	 * @return Senha gerada.
	 */
	public String geraSenha(String matricula, String ano) {
		return ano + Util.quatroCaracteresIniciais(matricula);
	}
}
