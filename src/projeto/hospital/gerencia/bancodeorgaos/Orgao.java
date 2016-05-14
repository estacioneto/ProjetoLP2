package projeto.hospital.gerencia.bancodeorgaos;

import java.io.Serializable;

import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.reflexao.ConstantesReflection;
import projeto.util.reflexao.MetodoAssociado;
import projeto.util.reflexao.Validacao;

/**
 * Entidade Orgao.
 * 
 * @author Estacio Pereira
 */
public class Orgao implements Serializable {
	/**
	 * Id gerado automaticamente
	 */
	private static final long serialVersionUID = -6346901598202920798L;

	@Validacao(metodo = ConstantesReflection.VALIDA_STRING, erro = Constantes.NOME
			+ Constantes.DO_ORGAO)
	@MetodoAssociado(get = ConstantesReflection.GET_NOME)
	private String nome;

	@Validacao(metodo = ConstantesReflection.VALIDA_TIPO_SANGUINEO, erro = MensagensDeErro.TIPO_SANGUINEO_INVALIDO)
	@MetodoAssociado(get = ConstantesReflection.GET_TIPO_SANGUINEO)
	private String tipoSanguineo;

	/**
	 * Construtor padrao.
	 * 
	 * @param nome
	 *            Nome do orgao.
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao.
	 */
	public Orgao(String nome, String tipoSanguineo) {
		this.nome = nome;
		this.setTipoSanguineo(tipoSanguineo);
	}

	/**
	 * Retorna o nome do orgao.
	 * 
	 * @return Nome do orgao.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Modifica o nome do orgao.
	 * 
	 * @param nome
	 *            Nome do orgao.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o tipo sanguineo do orgao.
	 * 
	 * @return Tipo sanguineo do orgao.
	 */
	public String getTipoSanguineo() {
		return tipoSanguineo.toString();
	}

	/**
	 * Modifica o tipo sanguineo do orgao.
	 * 
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao.
	 */
	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	/**
	 * Equals de orgao analisa o nome e o tipo sanguineo.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Orgao))
			return false;

		Orgao orgao = (Orgao) obj;
		if (this.nome.equals(orgao.nome)
				&& this.tipoSanguineo.equals(orgao.tipoSanguineo)) {
			return true;
		}
		return false;
	}
}
