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
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.ValidadorDeDados;
import projeto.util.reflexao.Reflection;

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
	 * @param funcionarioLogado
	 *            Funcionario Logado.
	 * @return Id do paciente cadastrado
	 */
	public String cadastraPaciente(String nome, String data, double peso,
			String sexo, String genero, String tipoSanguineo,
			Funcionario funcionarioLogado) {
		try {
			// ValidadorDeDados.validaNome(Constantes.NOME +
			// Constantes.DO_PACIENTE, nome);
			// ValidadorDeDados.validaData(Constantes.DATA, data);
			// ValidadorDeDados.validaPositivo(Constantes.PESO +
			// Constantes.DO_PACIENTE, peso);
			// ValidadorDeDados.validaSexoBiologico(MensagensDeErro.SEXO_INVALIDO,
			// sexo);
			// ValidadorDeDados.validaString(Constantes.GENERO, genero);

			ValidadorDeLogica.validaOperacao(
					MensagensDeErro.ERRO_PERMISSAO_CADASTRO_PACIENTE,
					Permissao.CADASTRAR_PACIENTES, funcionarioLogado);
			Paciente paciente = (Paciente) Reflection.godFactory(
					Paciente.class, nome, data, peso, tipoSanguineo, sexo,
					genero);
			if (pacientes.containsKey(paciente))
				throw new DadoInvalidoException(
						MensagensDeErro.PACIENTE_JA_CADASTRADO);

			String novoId = geradorIdPaciente.getProximoId();
			paciente.setId(novoId);
			pacientes.put(paciente, new Prontuario(paciente));
			return novoId;
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_CADASTRO_PACIENTE + e.getMessage());
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
	public Object getInfoPaciente(String idPaciente, String atributo) {
		try {
			Paciente paciente = buscaPacientePorId(idPaciente);

			return Reflection.getInfo(paciente, atributo);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_CONSULTAR_PRONTUARIO + e.getMessage());
		}
	}

	/**
	 * Busca um paciente por seu ID
	 * 
	 * @param idPaciente
	 *            Id do paciente
	 * @return Paciente
	 */
	private Paciente buscaPacientePorId(String idPaciente) {
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
	public String getIdProntuarioPosicao(int posicao) {
		try {
			ValidadorDeDados.validaPositivo(MensagensDeErro.INDICE_PRONTUARIO,
					posicao);

			return getPacientePosicao(posicao).getId();
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_CONSULTAR_PRONTUARIO + e.getMessage());
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
	private Paciente getPacientePosicao(int posicao)
			throws DadoInvalidoException {
		int contadorPosicao = 0;
		for (Paciente paciente : pacientes.keySet()) {
			if (contadorPosicao == posicao)
				return paciente;
			contadorPosicao++;
		}
		throw new DadoInvalidoException(String.format(
				MensagensDeErro.ERRO_PRONTUARIOS_INSUFICIENTES,
				pacientes.size()));
	}

	/**
	 * Recupera o id de um paciente de acordo com seu nome
	 * 
	 * @param nome
	 *            Nome do paciente
	 * @return Id do paciente
	 */
	public String getIdPaciente(String nome) {
		try {
			for (Paciente paciente : this.pacientes.keySet())
				if (paciente.getNome().equals(nome))
					return paciente.getId();

			throw new DadoInvalidoException("Paciente nao encontrado.");
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_CONSULTAR_PRONTUARIO + e.getMessage());
		}
	}

	/**
	 * Recupera o prontuario de um paciente
	 * 
	 * @param idPaciente
	 *            Id do paciente
	 * @return Prontuario do paciente
	 * @throws DadoInvalidoException
	 *             Caso paciente nao seja encontrado
	 */
	public Prontuario getProntuarioPaciente(String idPaciente)
			throws DadoInvalidoException {
		ValidadorDeDados.validaString(Constantes.ID.toUpperCase()
				+ Constantes.DO_PACIENTE, idPaciente);

		for (Paciente paciente : this.pacientes.keySet())
			if (paciente.getId().equals(idPaciente))
				return this.pacientes.get(paciente);

		throw new OperacaoInvalidaException("Paciente nao encontrado.");
	}

	/**
	 * Pega a quantidade de procedimentos realizados pelo paciente
	 * 
	 * @param idPaciente
	 *            Id do paciente
	 * @return Quantidade de procedimentos realizados
	 * @throws OperacaoInvalidaException
	 *             Paciente nao existe
	 */
	public int getTotalProcedimento(String idPaciente) {
		try {
			Prontuario prontuario = getProntuarioPaciente(idPaciente);
			return prontuario.qtdProcedimentos();
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(e.getMessage());
		}
	}

	/**
	 * Gera uma ficha de um paciente e guarda
	 * 
	 * @param idPaciente
	 */
	public void exportaFichaPaciente(String idPaciente) {
		try {
			Prontuario prontuario = getProntuarioPaciente(idPaciente);
			prontuario.exportaFichaPaciente();
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException("Erro ao exportar paciente. " + e.getMessage());
		}		
	}
}
