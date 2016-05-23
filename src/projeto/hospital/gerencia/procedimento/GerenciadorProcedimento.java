package projeto.hospital.gerencia.procedimento;

import java.io.Serializable;
import java.time.LocalDate;

import projeto.exceptions.dados.DadoInvalidoException;
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
	 * Construtor
	 */
	public GerenciadorProcedimento() {
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
	 * @param nomeMedico
	 *            Nome do medico que realizara o procedimento.
	 * @param argumentosExtras
	 *            Caso precise argumentos extras, como orgaos, por exemplo.
	 * @throws DadoInvalidoException
	 *             Procedimento invalido
	 */
	public void realizaProcedimento(String procedimento, Prontuario prontuario, String nomeMedico,
			Double valorMedicamentos, Object... argumentosExtras) throws DadoInvalidoException {
		// Necessidade da data de procedimento
		LocalDate data = LocalDate.now();

		Object[] argumentosConstrutor = null;
		if (argumentosExtras.length > Constantes.ZERO) {
			// Caso tenha orgao associado, sera o primeiro indice
			argumentosConstrutor = new Object[] { data.toString(), nomeMedico, argumentosExtras[Constantes.ZERO] };
		} else {
			argumentosConstrutor = new Object[] { data.toString(), nomeMedico };
		}
		Procedimento procedimentoRealizado = (Procedimento) Reflection.godFactory(
				Util.getNomeClasse(Procedimento.class, procedimento), MensagensDeErro.PROCEDIMENTO_INVALIDO,
				argumentosConstrutor);

		// Realiza o procedimento
		procedimentoRealizado.realizaProcedimento(prontuario, valorMedicamentos);
	}
}
