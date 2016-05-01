package projeto.hospital.paciente;

import java.io.Serializable;

/**
 * Factory singleton responsavel por gerar ids dos pacientes
 * 
 * @author Eric
 */
public class GeradorIdPaciente implements Serializable {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = -4340956236350344819L;

	private final static GeradorIdPaciente instancia = new GeradorIdPaciente();
	private Long id;

	private GeradorIdPaciente() {
		id = new Long(1);
	}

	/**
	 * @return Instancia do gerador
	 */
	public static GeradorIdPaciente getInstancia() {
		return instancia;
	}

	/**
	 * Gera o proximo ID
	 * 
	 * @return Proximo id
	 */
	public Long getProximoId() {
		return id++;
	}
}
