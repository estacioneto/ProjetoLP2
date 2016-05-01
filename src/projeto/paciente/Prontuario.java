package projeto.paciente;

import java.io.Serializable;

/**
 * @author Eric
 */
public class Prontuario implements Comparable<Prontuario>, Serializable {
	private static final long serialVersionUID = 21463754771L;
	
	Paciente paciente;

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

	@Override
	public int compareTo(Prontuario prontuario) {
		return this.paciente.getNome().compareTo(prontuario.getPaciente().getNome());
	}
}
