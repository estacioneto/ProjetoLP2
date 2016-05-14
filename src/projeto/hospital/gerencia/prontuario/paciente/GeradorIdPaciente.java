package projeto.hospital.gerencia.prontuario.paciente;

import java.io.Serializable;

/**
 * Factory responsavel por gerar ids dos pacientes
 * 
 * @author Eric
 */
public class GeradorIdPaciente implements Serializable {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = -4340956236350344819L;

	private Long id;

	public GeradorIdPaciente() {
		id = new Long(1);
	}

	/**
	 * Gera o proximo ID
	 * 
	 * @return Proximo id
	 */
	public String getProximoId() {
		return Long.toString(id++);
	}
}
