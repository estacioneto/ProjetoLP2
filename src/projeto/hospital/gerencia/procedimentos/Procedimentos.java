package projeto.hospital.gerencia.procedimentos;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.hospital.gerencia.bancodeorgaos.Orgao;
import projeto.hospital.gerencia.prontuario.Prontuario;
import projeto.hospital.gerencia.prontuario.paciente.Paciente;
import projeto.util.Util;

/**
 * Classe responsavel por realizar os procedimentos
 * 
 * @author Er1c
 */
public class Procedimentos {
	private final double PRECO_CONSULTA = 350.0;
	private final double PRECO_CIRURGIA_BARIATRICA = 7600.00;
	private final double PRECO_REDESIGNICAO_SEXUAL = 9300.00;
	private final double PRECO_TRANSPLANTE = 12500.00;

	private final double REDUCAO_PESO_BARIATRICA = 15;

	/**
	 * ~ Procedimento de consulta clinica
	 * 
	 * @param prontuarioPaciente
	 *            Prontuario do paciente a ser atendido
	 */
	public void consultaClinica(Prontuario prontuarioPaciente) {
		String procedimentoRealizado = "Consulta clinica";
		prontuarioPaciente.registraProcedimento(procedimentoRealizado);
		Paciente paciente = prontuarioPaciente.getPaciente();
		paciente.registraGasto(PRECO_CONSULTA);
	}

	/**
	 * Procedimento de cirugia bariatrica
	 * 
	 * @param prontuarioPaciente
	 *            Prontuario do paciente a ser atendido
	 */
	public void cirurgiaBariatrica(Prontuario prontuarioPaciente) {
		String procedimentoRealizado = "Cirurgia Bariatrica";
		prontuarioPaciente.registraProcedimento(procedimentoRealizado);
		Paciente paciente = prontuarioPaciente.getPaciente();
		paciente.registraGasto(PRECO_CIRURGIA_BARIATRICA);

		double pesoPaciente = paciente.getPeso();
		// Usado esse tipo de calculo para evitar erros de precisao
		double novoPeso = pesoPaciente * REDUCAO_PESO_BARIATRICA / 100.0;
		paciente.setPeso(novoPeso);
	}

	/**
	 * Procedimento de redesignacao sexual, onde o genero do paciente eh mudado
	 * 
	 * @param prontuarioPaciente
	 *            Prontuario do paciente a ser atendido
	 */
	public void redesignicaoSexual(Prontuario prontuarioPaciente) {
		String procedimentoRealizado = "Redesignacao Sexual";
		prontuarioPaciente.registraProcedimento(procedimentoRealizado);
		Paciente paciente = prontuarioPaciente.getPaciente();
		paciente.registraGasto(PRECO_REDESIGNICAO_SEXUAL);

		paciente.mudaGenero();
	}

	/**
	 * Realiza o procedimento de transplante de orgaos
	 * 
	 * @param prontuarioPaciente
	 *            Prontuario do paciente a ser atendido
	 * @param orgao
	 *            Orgao a ser transplantado
	 * @throws DadoInvalidoException
	 *             Caso nao haja compatibilidade entre os tipos sanguineos
	 */
	public void transplante(Prontuario prontuarioPaciente, Orgao orgao) throws DadoInvalidoException {
		String procedimentoRealizado = "Transplante de " + orgao.getNome();
		Paciente paciente = prontuarioPaciente.getPaciente();
		Util.validaCompatibilidadeTipoSanguineo(paciente.getTiposanguineo(), orgao.getTipoSanguineo());

		prontuarioPaciente.registraProcedimento(procedimentoRealizado);
		paciente.registraGasto(PRECO_TRANSPLANTE);

	}
}
