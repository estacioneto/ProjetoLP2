package projeto.hospital.gerencia.prontuario;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.gerencia.prontuario.paciente.GeradorIdPaciente;
import projeto.hospital.gerencia.prontuario.paciente.Paciente;
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

	private Map<Paciente, Prontuario> pacientes;
	private GeradorIdPaciente geradorIdPaciente;

	/**
	 * Construtor
	 */
	public GerenciadorDePacienteProntuario() {
		geradorIdPaciente = new GeradorIdPaciente();
		pacientes = new TreeMap<>();
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
			if (pacientes.containsKey(novoPaciente))
				throw new DadoInvalidoException(MensagensDeErro.PACIENTE_JA_CADASTRADO);

			Long novoId = geradorIdPaciente.getProximoId();
			novoPaciente.setId(novoId);
			pacientes.put(novoPaciente, new Prontuario(novoPaciente));
			return novoId;
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CADASTRO_PACIENTE + e.getMessage());
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
		// ValidadorDeDados.validaPositivo(Constantes.ID, idPaciente);
		// ValidadorDeDados.validaString(Constantes.ATRIBUTO, atributo);

		Paciente paciente = buscaPacientePorId(idPaciente);

		return Util.getInfo(paciente, atributo, MensagensDeErro.ERRO_CONSULTAR_PRONTUARIO);
	}

	/**
	 * Busca um paciente por seu ID
	 * 
	 * @param idPaciente
	 *            Id do paciente
	 * @return Paciente
	 */
	private Paciente buscaPacientePorId(Long idPaciente) {
		for (Paciente paciente : this.pacientes.keySet())
			if (paciente.getId().equals(idPaciente))
				return paciente;

		throw new OperacaoInvalidaException("Paciente nao encontrado.");
	}

	/**
	 * Busca um id de um paciente de acordo com a posicao de seu prontuario
	 * 
	 * @param posicao
	 *            Posicao do prontuario
	 * @return Id do paciente
	 */
	public Long getIdProntuario(int posicao) {
		try {
			ValidadorDeDados.validaPositivo(MensagensDeErro.INDICE_PRONTUARIO, posicao);
			
			int contadorPosicao = 0;
			for (Paciente paciente : pacientes.keySet()){
				if (contadorPosicao == posicao)
					return pacientes.get(paciente).getId();
				contadorPosicao++;
			}

			throw new DadoInvalidoException(
					String.format(MensagensDeErro.ERRO_PRONTUARIOS_INSUFICIENTES, pacientes.size()));
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTAR_PRONTUARIO + e.getMessage());
		}
	}
}
