package projeto.hospital.gerencia.tipo_sanguineo;

import java.io.Serializable;
import java.util.Set;

import projeto.exceptions.dados.DadoInvalidoException;

/**
 * Classe responsavel por representar os tipos sanguineos
 * 
 * @author Eric
 */
public class TipoSanguineo implements Serializable {
	/**
	 * Id gerado automaticamente
	 */
	private static final long serialVersionUID = 2900624940043063884L;

	private String tipo;
	private Set<String> tiposCompativeis;

	/**
	 * Construtor
	 * 
	 * @param tipo
	 *            Tipo do sangue
	 * @param tiposCompativeis
	 *            Tipos que ele pode receber
	 */
	public TipoSanguineo(String tipo, Set<String> tiposCompativeis) {
		this.tipo = tipo;
		this.tiposCompativeis = tiposCompativeis;
	}

	/**
	 * Valida se o tipo pode receber o outro tipo
	 * 
	 * @param outroTipo
	 *            Tipo a ser validado a doacao
	 * @throws DadoInvalidoException
	 *             Se os tipos nao forem compativeis
	 */
	public void recebeDe(String outroTipo) throws DadoInvalidoException {
		if (!this.tiposCompativeis.contains(outroTipo))
			throw new DadoInvalidoException("Tipos incompativeis.");
	}

	@Override
	public String toString() {
		return tipo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TipoSanguineo))
			return false;

		TipoSanguineo tipoSanguineo = (TipoSanguineo) obj;
		if (this.tipo.equals(tipoSanguineo.tipo)){
			return true;
		}
		return false;
	}
}
