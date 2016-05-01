package projeto.paciente;

public class Prontuario implements Comparable {
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
		System.out.println(paciente);
		return paciente.getId();
	}
	
	@Override
	public int compareTo(Object obj){
		Prontuario prontuario = (Prontuario) obj;
		
		return this.paciente.getNome().compareTo(prontuario.getPaciente().getNome());
	}
}
