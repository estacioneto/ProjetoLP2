package projeto.hospital.gerencia.procedimento;

import java.io.Serializable;
import java.time.LocalDate;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.hospital.gerencia.bancodeorgaos.Orgao;
import projeto.hospital.gerencia.procedimento.procedimentos.Procedimento;
import projeto.hospital.gerencia.prontuario.Prontuario;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.Util;
import projeto.util.reflexao.Reflection;

public class GerenciadorProcedimento implements Serializable {
	/**
	 * Id gerado automaticamente
	 */
	private static final long serialVersionUID = 588210554771048672L;

	/**
	 * Realiza procedimento.
	 * 
	 * @param procedimento
	 *            Nome do procedimento.
	 * @param prontuario
	 *            Prontuario do paciente.
	 * @param nomeMedico
	 *            Nome do medico.
	 * @param valorMedicamentos
	 *            Valor dos medicamentos necessarios.
	 * @throws DadoInvalidoException
	 *             Caso algum dos dados esteja incorreto.
	 */
	public void realizaProcedimento(String procedimento, Prontuario prontuario, String nomeMedico,
			Double valorMedicamentos) throws DadoInvalidoException {
		Procedimento procedimentoRealizado = getProcedimento(procedimento, nomeMedico);

		// Realiza o procedimento
		procedimentoRealizado.realizaProcedimento(prontuario, valorMedicamentos);
	}

	/**
	 * Realiza procedimento com orgao.
	 * 
	 * @param procedimento
	 *            Nome do procedimento.
	 * @param prontuario
	 *            Prontuario do paciente.
	 * @param nomeMedico
	 *            Nome do medico.
	 * @param valorMedicamentos
	 *            Valor dos medicamentos necessarios.
	 * @param orgaoRecuperado
	 *            Orgao a ser utilizado.
	 * @throws DadoInvalidoException
	 *             Caso algum dos dados esteja incorreto.
	 */
	public void realizaProcedimento(String procedimento, Prontuario prontuario, String nomeMedico,
			Double valorMedicamentos, Orgao orgaoRecuperado) throws DadoInvalidoException {
		// Necessidade da data de procedimento
		Procedimento procedimentoRealizado = getProcedimento(procedimento, nomeMedico, orgaoRecuperado);

		// Realiza o procedimento
		procedimentoRealizado.realizaProcedimento(prontuario, valorMedicamentos);
	}

	/**
	 * Recupera procedimento a ser realizado.
	 * 
	 * @param procedimento
	 *            Nome do procedimento.
	 * @param argumentos
	 *            Argumentos para criacao do procedimento.
	 * @return Procedimento a ser realizado.
	 * @throws DadoInvalidoException
	 *             Caso o procedimento nao exista.
	 */
	private Procedimento getProcedimento(String procedimento, Object... argumentos) throws DadoInvalidoException {
		LocalDate data = LocalDate.now();

		// Lista de argumentos precisa adicionar data ao inicio.
		Object[] argumentosConstrutor = montaArgumentos(data.toString(), argumentos);
		Procedimento procedimentoRealizado = (Procedimento) Reflection.godFactory(
				Util.getNomeClasse(Procedimento.class, procedimento), MensagensDeErro.PROCEDIMENTO_INVALIDO,
				argumentosConstrutor);
		return procedimentoRealizado;
	}

	/**
	 * Monta argumentos do construtor. Necessario para adicionar data aos
	 * argumentos necessarios.
	 * 
	 * @param data
	 *            Data de realizacao do procedimento.
	 * @param argumentosExtras
	 *            Argumentos extras do construtor de procedimentos.
	 * @return Lista de argumentos do construtor de medicamentos.
	 */
	private Object[] montaArgumentos(String data, Object... argumentosExtras) {
		Object[] argumentos = new Object[argumentosExtras.length + 1];
		argumentos[Constantes.ZERO] = data;
		for (int i = Constantes.UM; i <= argumentosExtras.length; i++) {
			argumentos[i] = argumentosExtras[i - Constantes.UM];
		}
		return argumentos;
	}
}
