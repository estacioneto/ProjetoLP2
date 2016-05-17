package projeto.hospital.gerencia.procedimentos;

import java.io.Serializable;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.hospital.gerencia.bancodeorgaos.Orgao;
import projeto.hospital.gerencia.prontuario.Prontuario;
import projeto.hospital.gerencia.prontuario.paciente.Paciente;
import projeto.util.Constantes;

/**
 * Classe responsavel por realizar os procedimentos
 * 
 * @author Er1c
 */
public class Procedimentos implements Serializable {
	/**
	 * Id gerado automaticamente
	 */
	private static final long serialVersionUID = 6731797048997438464L;

	private final double PRECO_CONSULTA = 350.0;
	private final double PRECO_CIRURGIA_BARIATRICA = 7600.00;
	private final double PRECO_REDESIGNICAO_SEXUAL = 9300.00;
	private final double PRECO_TRANSPLANTE = 12500.00;

	private final Integer PONTUACAO_CONSULTA_CLINICA = 50;
	private final Integer PONTUACAO_CIRURGIA_BARIATRICA = 100;
	private final Integer PONTUACAO_CIRURGIA_REDESIGNICAO_SEXUAL = 130;
	private final Integer PONTUACAO_TRANSPLANTE_ORGAOS = 160;

	private final double REDUCAO_PESO_BARIATRICA = 10;

	/**
	 * Construtor
	 */
	public Procedimentos() {
	}

	/**
	 * ~ Procedimento de consulta clinica
	 * 
	 * @param prontuarioPaciente
	 *            Prontuario do paciente a ser atendido
	 * @param valorMedicamentos
	 *            Valor dos medicamentos utilizados no procedimento.
	 */
	public void consultaClinica(Prontuario prontuarioPaciente,
			Double valorMedicamentos) {
		String procedimentoRealizado = "Consulta clinica";
		prontuarioPaciente.registraProcedimento(procedimentoRealizado);
		Paciente paciente = prontuarioPaciente.getPaciente();

		this.registradorDeGastos(paciente, PRECO_CONSULTA, valorMedicamentos);
		paciente.registradorDePontos(PONTUACAO_CONSULTA_CLINICA);
	}

	/**
	 * Procedimento de cirugia bariatrica
	 * 
	 * @param prontuarioPaciente
	 *            Prontuario do paciente a ser atendido
	 * @param valorMedicamentos
	 *            Valor do medicamento necessario.
	 */
	public void cirurgiaBariatrica(Prontuario prontuarioPaciente,
			Double valorMedicamentos) {
		String procedimentoRealizado = "Cirurgia Bariatrica";
		prontuarioPaciente.registraProcedimento(procedimentoRealizado);
		Paciente paciente = prontuarioPaciente.getPaciente();

		this.registradorDeGastos(paciente, PRECO_CIRURGIA_BARIATRICA,
				valorMedicamentos);
		paciente.registradorDePontos(PONTUACAO_CIRURGIA_BARIATRICA);

		double pesoPaciente = paciente.getPeso();
		// Usado esse tipo de calculo para evitar erros de precisao
		double novoPeso = pesoPaciente
				- (pesoPaciente * REDUCAO_PESO_BARIATRICA / Constantes.PORCENTAGEM_TOTAL);
		paciente.setPeso(novoPeso);
	}

	/**
	 * Procedimento de redesignacao sexual, onde o genero do paciente eh mudado
	 * 
	 * @param prontuarioPaciente
	 *            Prontuario do paciente a ser atendido
	 * @param valorMedicamentos
	 *            Valor dos medicamentos necessarios
	 */
	public void redesignacaoSexual(Prontuario prontuarioPaciente,
			Double valorMedicamentos) {
		String procedimentoRealizado = "Redesignacao Sexual";
		prontuarioPaciente.registraProcedimento(procedimentoRealizado);
		Paciente paciente = prontuarioPaciente.getPaciente();

		this.registradorDeGastos(paciente, PRECO_REDESIGNICAO_SEXUAL,
				valorMedicamentos);
		paciente.registradorDePontos(PONTUACAO_CIRURGIA_REDESIGNICAO_SEXUAL);

		paciente.mudaGenero();
	}

	/**
	 * Realiza o procedimento de transplante de orgaos
	 * 
	 * @param prontuarioPaciente
	 *            Prontuario do paciente a ser atendido
	 * @param orgao
	 *            Orgao a ser transplantado
	 * @param valorMedicamentos
	 *            Valor dos medicamentos necessarios
	 * @throws DadoInvalidoException
	 *             Caso nao haja compatibilidade entre os tipos sanguineos
	 */
	public void transplanteDeOrgaos(Prontuario prontuarioPaciente, Orgao orgao,
			Double valorMedicamentos) throws DadoInvalidoException {
		String procedimentoRealizado = "Transplante de " + orgao.getNome();
		Paciente paciente = prontuarioPaciente.getPaciente();
		prontuarioPaciente.registraProcedimento(procedimentoRealizado);
		this.registradorDeGastos(paciente, PRECO_TRANSPLANTE, valorMedicamentos);
		paciente.registradorDePontos(PONTUACAO_TRANSPLANTE_ORGAOS);
	}

	// Metodo responsavel por calcular o gasto do paciente e repassar para ser
	// registrado no Paciente.
	private void registradorDeGastos(Paciente paciente,
			Double valorProcedimento, Double valorMedicamentos) {
		Double gastoComDesconto = (valorProcedimento + valorMedicamentos)
				- (paciente.calculaDesconto(valorProcedimento
						+ valorMedicamentos));
		paciente.registraGasto(gastoComDesconto);
	}
}
