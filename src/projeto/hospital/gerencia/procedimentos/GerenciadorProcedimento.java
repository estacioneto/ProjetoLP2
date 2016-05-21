package projeto.hospital.gerencia.procedimentos;

import java.io.Serializable;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.gerencia.bancodeorgaos.Orgao;
import projeto.hospital.gerencia.prontuario.Prontuario;
import projeto.util.Util;
import projeto.util.reflexao.Reflection;

public class GerenciadorProcedimento implements Serializable {
	/**
	 * Id gerado automaticamente
	 */
	private static final long serialVersionUID = 588210554771048672L;

	private Procedimentos procedimentos;

	/**
	 * Construtor
	 */
	public GerenciadorProcedimento() {
		this.procedimentos = new Procedimentos();
	}

	/**
	 * Vai realizar um procedimento
	 * 
	 * @param procedimento
	 *            Procedimento a ser realizado
	 * @param prontuario
	 *            Prontuario do paciente
	 * @param orgao
	 *            Orgao a ser utilizado no procedimento
	 * @param valorMedicamentos
	 *            Valor dos medicamentos usados
	 */
	public void realizaProcedimento(String procedimento, Prontuario prontuario, Orgao orgao, Double valorMedicamentos) {
		try {
			String nomeProcedimento = Util.getNomeMetodo(procedimento);
			Class<?>[] classesArgumentos = { Prontuario.class, Orgao.class, Double.class };
			Object[] argumentos = { prontuario, orgao, valorMedicamentos };
			Reflection.executaMetodo(nomeProcedimento, this.procedimentos, classesArgumentos, argumentos);

		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(e.getMessage());
		}
	}

	/**
	 * Vai realizar um procedimento
	 * 
	 * @param procedimento
	 *            Procedimento a ser realizado
	 * @param prontuario
	 *            Prontuario do paciente
	 * @param valorMedicamentos
	 *            Valor dos medicamentos usados
	 */
	public void realizaProcedimento(String procedimento, Prontuario prontuario, Double valorMedicamentos) {
		try {
			String nomeProcedimento = Util.getNomeMetodo(procedimento);
			Class<?>[] classesArgumentos = { Prontuario.class, Double.class };
			Object[] argumentos = { prontuario, valorMedicamentos };
			Reflection.executaMetodo(nomeProcedimento, this.procedimentos, classesArgumentos, argumentos);

		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(e.getMessage());
		}
	}
}
