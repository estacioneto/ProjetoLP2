package projeto.paciente;

public class GeradorIdPaciente {
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
		id++;
		return id;
	}
}
