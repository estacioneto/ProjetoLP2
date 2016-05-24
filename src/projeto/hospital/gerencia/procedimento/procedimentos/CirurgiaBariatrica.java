package projeto.hospital.gerencia.procedimento.procedimentos;

import projeto.hospital.gerencia.prontuario.Prontuario;
import projeto.hospital.gerencia.prontuario.paciente.Paciente;
import projeto.util.Constantes;

/**
 * Classe responsavel por abstrair a ideia do procedimento de cirurgia bariatrica
 * 
 * @author Eric
 */
public class CirurgiaBariatrica extends Procedimento {
	/**
	 * Id gerado automaticamente
	 */
	private static final long serialVersionUID = 1087531527341473653L;

	private final String NOME_PROCEDIMENTO = "Cirurgia Bariatrica";
	private final Double PRECO_CIRURGIA_BARIATRICA = 7600.00;
	private final Integer PONTUACAO_CIRURGIA_BARIATRICA = 100;
	private final Double REDUCAO_PESO_BARIATRICA = 10.0;

	/**
	 * Construtor
	 * 
	 * @param dataRealizacao
	 *            Data em que o procedimento foi realizado
	 * @param nomeMedico
	 *            Nome do medico que realizou o procedimento
	 */
	public CirurgiaBariatrica(String dataRealizacao, String nomeMedico) {
		super(dataRealizacao, nomeMedico);
	}

	@Override
	public void realizaProcedimento(Prontuario prontuario, Double valorMedicamentos) {
		prontuario.registraProcedimento(this);
		Paciente paciente = prontuario.getPaciente();

		this.registradorDeGastos(paciente, PRECO_CIRURGIA_BARIATRICA, valorMedicamentos);
		paciente.registradorDePontos(PONTUACAO_CIRURGIA_BARIATRICA);

		double pesoPaciente = paciente.getPeso();
		// Usado esse tipo de calculo para evitar erros de precisao
		double novoPeso = pesoPaciente - (pesoPaciente * REDUCAO_PESO_BARIATRICA / Constantes.PORCENTAGEM_TOTAL);
		paciente.setPeso(novoPeso);
	}

	@Override
	public String toString() {
		StringBuilder saida = new StringBuilder();
		saida.append("--> " + NOME_PROCEDIMENTO + ":" + Constantes.QUEBRA_LINHA);
		saida.append(super.toString());
		return saida.toString();
	}
}
