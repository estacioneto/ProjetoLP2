package projeto.hospital.gerencia.procedimento.procedimentos;

import projeto.hospital.gerencia.prontuario.Prontuario;
import projeto.hospital.gerencia.prontuario.paciente.Paciente;
import projeto.util.Constantes;

/**
 * Classe responsavel por abstrair a ideia do procedimento de consulta clinica
 * 
 * @author Eric
 */
public class ConsultaClinica extends Procedimento {
	/**
	 * Id gerado automaticamente
	 */
	private static final long serialVersionUID = -2861170665692991534L;

	private final String NOME_PROCEDIMENTO = "Consulta clinica";
	private final Double PRECO_CONSULTA = 350.0;
	private final Integer PONTUACAO_CONSULTA_CLINICA = 50;

	/**
	 * Construtor
	 * 
	 * @param dataRealizacao
	 *            Data em que o procedimento foi realizado
	 * @param nomeMedico
	 *            Nome do medico que realizou o procedimento
	 */
	public ConsultaClinica(String dataRealizacao, String nomeMedico) {
		super(dataRealizacao, nomeMedico);
	}

	@Override
	public void realizaProcedimento(Prontuario prontuario, Double valorMedicamentos) {
		prontuario.registraProcedimento(this);
		Paciente paciente = prontuario.getPaciente();

		this.registradorDeGastos(paciente, PRECO_CONSULTA, valorMedicamentos);
		paciente.registradorDePontos(PONTUACAO_CONSULTA_CLINICA);
	}

	@Override
	public String toString() {
		String saida = new String();
		saida += "--> " + NOME_PROCEDIMENTO + ":" + Constantes.QUEBRA_LINHA;
		saida += super.toString();
		return saida;
	}
}
