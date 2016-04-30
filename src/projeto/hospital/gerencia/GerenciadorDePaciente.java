package projeto.hospital.gerencia;

import java.util.HashMap;

import projeto.paciente.GeradorIdPaciente;
import projeto.paciente.Paciente;

public class GerenciadorDePaciente {
	private HashMap<Long, Paciente> pacientes;
	private GeradorIdPaciente geradorIdPaciente;

	public GerenciadorDePaciente() {
		geradorIdPaciente = GeradorIdPaciente.getInstancia();
	}

	public long cadastraPaciente(String nome, String data, double peso, String sexo, String genero,
			String tipoSanguineo) {
		Long novoId = geradorIdPaciente.getProximoId();
		pacientes.put(novoId, new Paciente(nome, data, peso, tipoSanguineo, sexo, genero, novoId));
		return novoId;
	}
}
