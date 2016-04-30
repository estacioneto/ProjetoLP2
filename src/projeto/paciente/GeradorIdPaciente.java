package projeto.paciente;

public class GeradorIdPaciente {
	private final static GeradorIdPaciente instancia = new GeradorIdPaciente();
	private long id;
	
	private GeradorIdPaciente() {
		id=0;
	}
	
	public static GeradorIdPaciente getInstancia(){
		return instancia;
	}
	
	public long getProximoId(){
		return id++;
	}
}
