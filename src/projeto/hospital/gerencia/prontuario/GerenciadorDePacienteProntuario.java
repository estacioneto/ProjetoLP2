package projeto.hospital.gerencia.prontuario;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.gerencia.ValidadorDeLogica;
import projeto.hospital.gerencia.funcionario.Funcionario;
import projeto.hospital.gerencia.funcionario.cargo.Permissao;
import projeto.hospital.gerencia.prontuario.paciente.GeradorIdPaciente;
import projeto.hospital.gerencia.prontuario.paciente.Paciente;
import projeto.hospital.gerencia.tipo_sanguineo.TipoSanguineo;
import projeto.hospital.gerencia.tipo_sanguineo.TipoSanguineoFactory;
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
	private TipoSanguineoFactory tipoSanguineoFactory;

	/**
	 * Construtor
	 */
	public GerenciadorDePacienteProntuario() {
		geradorIdPaciente = new GeradorIdPaciente();
		pacientes = new TreeMap<>();
		tipoSanguineoFactory = TipoSanguineoFactory.getInstacia();		
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
	 * @param funcionarioLogado
	 *            Funcionario Logado.
	 * @return Id do paciente cadastrado
	 */
	public long cadastraPaciente(String nome, String data, double peso, String sexo, String genero,
			String tipoSanguineo, Funcionario funcionarioLogado) {
		try {
			ValidadorDeDados.validaNome(Constantes.DO_PACIENTE, nome);
			ValidadorDeDados.validaData(data);
			ValidadorDeDados.validaPositivo(Constantes.PESO + Constantes.DO_PACIENTE, peso);
			ValidadorDeDados.validaSexoBiologico(sexo);
			ValidadorDeDados.validaString(Constantes.GENERO, genero);
			
			TipoSanguineo sangue = tipoSanguineoFactory.criaTipo(tipoSanguineo);
			
			ValidadorDeLogica.validaOperacao(MensagensDeErro.ERRO_PERMISSAO_CADASTRO_PACIENTE,
					Permissao.CADASTRAR_PACIENTES, funcionarioLogado);
			Paciente novoPaciente = new Paciente(nome, data, peso, sangue, sexo, genero);
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
		try {
			Paciente paciente = buscaPacientePorId(idPaciente);

			return Util.getInfo(paciente, atributo);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTAR_PRONTUARIO + e.getMessage());
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
	public Long getIdProntuarioPosicao(int posicao) {
		try {
			ValidadorDeDados.validaPositivo(MensagensDeErro.INDICE_PRONTUARIO, posicao);

			return getPacientePosicao(posicao).getId();
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTAR_PRONTUARIO + e.getMessage());
		}
	}

	/**
	 * Pega um prontuario de acordo com sua posicao
	 * 
	 * @param posicao
	 *            Posicao do prontuario a ser recuperado
	 * @return Prontuario recuperado
	 */
	public Prontuario getProntuarioPosicao(int posicao) {
		try {
			Paciente paciente = getPacientePosicao(posicao);
			
			return this.pacientes.get(paciente);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException();
		}
	}

	/**
	 * Pega um paciente no set de acordo com sua posicao em ordem alfabetica
	 * 
	 * @param posicao
	 *            Posicao do paciente requerido
	 * @return Paciente recuperado
	 * @throws DadoInvalidoException
	 *             Caso nao haja a posicao requerida
	 */
	private Paciente getPacientePosicao(int posicao) throws DadoInvalidoException {
		int contadorPosicao = 0;
		for (Paciente paciente : pacientes.keySet()) {
			if (contadorPosicao == posicao)
				return paciente;
			contadorPosicao++;
		}
		throw new DadoInvalidoException(
				String.format(MensagensDeErro.ERRO_PRONTUARIOS_INSUFICIENTES, pacientes.size()));
	}
}
