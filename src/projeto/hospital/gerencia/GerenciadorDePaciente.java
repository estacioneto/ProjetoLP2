package projeto.hospital.gerencia;

import java.util.HashSet;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.paciente.GeradorIdPaciente;
import projeto.paciente.Paciente;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.Util;;

public class GerenciadorDePaciente {
	private HashSet<Paciente> pacientes;
	private GeradorIdPaciente geradorIdPaciente;
	/**
	 * Construtor
	 */
	public GerenciadorDePaciente() {
		geradorIdPaciente = GeradorIdPaciente.getInstancia();
		pacientes = new HashSet<Paciente>();
	}

	/**
	 * Realiza o cadastro de um paciente
	 * 
	 * @param nome
	 *            Nome do paciente
	 * @param data
	 *            Data de nascimento do paciente
	 * @param peso
	 *            Peso do paciente
	 * @param sexo
	 *            Sexo biologico do paciente
	 * @param genero
	 *            Genero do paciente
	 * @param tipoSanguineo
	 *            Tipo sanguineo do paciente
	 * @return Id do paciente cadastrado
	 */
	public long cadastraPaciente(String nome, String data, double peso, String sexo, String genero,
			String tipoSanguineo) {
		Paciente novoPaciente = new Paciente(nome, data, peso, tipoSanguineo, sexo, genero);
		Long novoId = geradorIdPaciente.getProximoId();
		novoPaciente.setId(novoId);
		if (!pacientes.add(novoPaciente))
			throw new DadoInvalidoException(MensagensDeErro.ERRO_PACIENTE_JA_CADASTRADO);
		return novoId;
	}
	
	/**
	 * Acessa uma informacao especifica sobre um paciente
	 * 
	 * @param idPaciente
	 *            Id do paciente
	 * @param atributo
	 *            Informacao a ser requisitada
	 * @return Informacao requisitada
	 */
	public Object getInfoPaciente(Long idPaciente, String atributo){
		Util.validaPositivo(Constantes.ID, idPaciente);
		Util.validaString(Constantes.ATRIBUTO, atributo);
		
		Paciente paciente = buscaPaciente(idPaciente);
		
		switch(atributo){
		case Constantes.NOME:
			return paciente.getNome();
		case Constantes.DATA:
			return Util.transformaFormatoData(paciente.getDataNascimento());
		case Constantes.SEXO:
			return paciente.getSexoBiologico();
		case Constantes.GENERO:
			return paciente.getGenero();
		case Constantes.TIPOS_SANGUINEO:
			return paciente.getTiposanguineo();
		case Constantes.PESO:
			return paciente.getPeso();
		default:
			throw new DadoInvalidoException();
		}
	}
	
	private Paciente buscaPaciente(Long idPaciente){
		for(Paciente paciente : this.pacientes)
			if(paciente.getId().equals(idPaciente))
				return paciente;
		
		throw new DadoInvalidoException("Paciente nao encontrado.");
	}
}
