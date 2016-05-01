package projeto.hospital.gerencia;

import java.util.HashSet;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.paciente.GeradorIdPaciente;
import projeto.paciente.Paciente;
import projeto.util.MensagensDeErro;

public class GerenciadorDePaciente {
	private HashSet<Paciente> pacientes;
	private GeradorIdPaciente geradorIdPaciente;

	public GerenciadorDePaciente() {
		geradorIdPaciente = GeradorIdPaciente.getInstancia();
		pacientes = new HashSet<Paciente>();
	}

	public long cadastraPaciente(String nome, String data, double peso, String sexo, String genero,
			String tipoSanguineo) {
		Paciente novoPaciente =  new Paciente(nome, data, peso, tipoSanguineo, sexo, genero);
		Long novoId = geradorIdPaciente.getProximoId();	
		novoPaciente.setId(novoId);
		if(!pacientes.add(novoPaciente))
			throw new DadoInvalidoException(MensagensDeErro.ERRO_PACIENTE_JA_CADASTRADO);
		return novoId;
	}
}
