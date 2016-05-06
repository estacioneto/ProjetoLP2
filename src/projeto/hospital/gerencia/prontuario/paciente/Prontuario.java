package projeto.hospital.gerencia.prontuario.paciente;

import java.io.Serializable;

/**
 * Classe para representar os prontuarios
 * 
 * @author Eric
 */
public class Prontuario implements Serializable {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 21463754771L;

	private Paciente paciente;

	/**
	 * Construtor
	 * 
	 * @param paciente
	 *            Paciente relacionado ao prontuario
	 */
	public Prontuario(Paciente paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return Paciente relacionado ao prontuario
	 */
	public Paciente getPaciente() {
		return this.paciente;
	}

	/**
	 * @return Id do paciente do prontuario
	 */
	public Long getId() {
		return paciente.getId();
	}
}
