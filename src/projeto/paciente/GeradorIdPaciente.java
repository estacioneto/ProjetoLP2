package projeto.paciente;

public class GeradorIdPaciente {
	private final static GeradorIdPaciente instancia = new GeradorIdPaciente();
	private long id;

	private GeradorIdPaciente() {
		id = 0;
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
	public long getProximoId() {
		return id++;
	}
}
