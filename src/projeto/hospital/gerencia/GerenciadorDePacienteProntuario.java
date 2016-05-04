package projeto.hospital.gerencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.hospital.paciente.GeradorIdPaciente;
import projeto.hospital.paciente.Paciente;
import projeto.hospital.paciente.Prontuario;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.Util;
import projeto.util.ValidadorDeDados;

/**
 * Gerencia os pacientes e prontuarios
 * 
 * @author Eric
 */
public class GerenciadorDePacienteProntuario implements Serializable {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 6385782587198577722L;

	private Set<Paciente> pacientes;
	private List<Prontuario> prontuarios;
	private GeradorIdPaciente geradorIdPaciente;

	/**
	 * Construtor
	 */
	public GerenciadorDePacienteProntuario() {
		geradorIdPaciente = new GeradorIdPaciente();
		pacientes = new HashSet<Paciente>();
		prontuarios = new ArrayList<Prontuario>();
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
		try {
			ValidadorDeDados.validaNome(Constantes.DO_PACIENTE, nome);
			ValidadorDeDados.validaData(data);
			ValidadorDeDados.validaPositivo(Constantes.PESO + Constantes.DO_PACIENTE, peso);
			ValidadorDeDados.validaSexoBiologico(sexo);
			ValidadorDeDados.validaString(Constantes.GENERO, genero);
			ValidadorDeDados.validaTipoSanguineo(tipoSanguineo);
			
			Paciente novoPaciente = new Paciente(nome, data, peso, tipoSanguineo, sexo, genero);
			if (!pacientes.add(novoPaciente))
				throw new DadoInvalidoException(MensagensDeErro.PACIENTE_JA_CADASTRADO);

			Long novoId = geradorIdPaciente.getProximoId();
			novoPaciente.setId(novoId);
			prontuarios.add(new Prontuario(novoPaciente));
			return novoId;
		} catch (DadoInvalidoException e) {
			throw new DadoInvalidoException(MensagensDeErro.ERRO_CADASTRO_PACIENTE + e.getMessage());
		}
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
	public Object getInfoPaciente(Long idPaciente, String atributo) {
		ValidadorDeDados.validaPositivo(Constantes.ID, idPaciente);
		ValidadorDeDados.validaString(Constantes.ATRIBUTO, atributo);

		Paciente paciente = buscaPacientePorId(idPaciente);

		switch (atributo) {
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
		case Constantes.IDADE:
			return paciente.getIdade();
		default:
			throw new DadoInvalidoException();
		}
	}

	/**
	 * Busca um paciente por seu ID
	 * 
	 * @param idPaciente
	 *            Id do paciente
	 * @return Paciente
	 */
	private Paciente buscaPacientePorId(Long idPaciente) {
		for (Paciente paciente : this.pacientes)
			if (paciente.getId().equals(idPaciente))
				return paciente;

		throw new DadoInvalidoException("Paciente nao encontrado.");
	}

	/**
	 * Busca um id de um paciente de acordo com a posicao de seu prontuario
	 * 
	 * @param posicao
	 *            Posicao do prontuario
	 * @return Id do paciente
	 */
	public Long getProntuario(int posicao) {
		try {
			ValidadorDeDados.validaPositivo(MensagensDeErro.INDICE_PRONTUARIO, posicao);
			if (posicao >= prontuarios.size())
				throw new DadoInvalidoException(
						String.format(MensagensDeErro.ERRO_PRONTUARIOS_INSUFICIENTES, prontuarios.size()));

			Collections.sort(prontuarios);
			return prontuarios.get(posicao).getId();
		} catch (DadoInvalidoException e) {
			throw new DadoInvalidoException(MensagensDeErro.ERRO_CONSULTAR_PRONTUARIO + e.getMessage());
		}
	}
}
