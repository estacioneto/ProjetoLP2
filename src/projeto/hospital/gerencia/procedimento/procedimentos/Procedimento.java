package projeto.hospital.gerencia.procedimento.procedimentos;

import java.io.Serializable;

import projeto.hospital.gerencia.prontuario.Prontuario;
import projeto.hospital.gerencia.prontuario.paciente.Paciente;

/**
 * Classe responsavel por abstrair a ideia de procedimento
 * 
 * @author Eric
 */
public abstract class Procedimento implements Serializable {
	/**
	 * Id gerado automaticamente
	 */
	protected static final long serialVersionUID = 5826036344611544945L;

	// Nao eh necessario validar os atributos pois so usa informacoes
	// de outros objetos do programa, que ja sao validados
	private String dataRealizacao;
	private String nomeMedico;

	/**
	 * Construtor
	 * 
	 * @param dataRealizacao
	 *            Data em que o procedimento foi realizado
	 * @param nomeMedico
	 *            Nome do medico que realizou o procedimento
	 */
	public Procedimento(String dataRealizacao, String nomeMedico) {
		this.dataRealizacao = dataRealizacao;
		this.nomeMedico = nomeMedico;
	}

	/**
	 * Realiza um procedimento
	 * 
	 * @param prontuario
	 *            Prontuario do paciente a ser submetido ao procedimento
	 * @param valorMedicamentos
	 *            Valor dos medicamentos necessarios
	 */
	public abstract void realizaProcedimento(Prontuario prontuario, Double valorMedicamentos);

	/**
	 * @return Data da realizacao do procedimento, formato DD-MM-YYYY
	 */
	public String getData() {
		return this.dataRealizacao;
	}

	/**
	 * @return Nome do medico que realizou o procedimento
	 */
	public String getMedico() {
		return this.nomeMedico;
	}

	/**
	 * Metodo responsavel por calcular o gasto do paciente e repassar para ser
	 * registrado no Paciente.
	 * 
	 * @param paciente
	 *            Paciente
	 * @param valorProcedimento
	 *            Valor do procedimento
	 * @param valorMedicamentos
	 *            Valor dos medicamentos
	 */
	protected void registradorDeGastos(Paciente paciente, Double valorProcedimento, Double valorMedicamentos) {
		Double gastoComDesconto = (valorProcedimento + valorMedicamentos)
				- (paciente.calculaDesconto(valorProcedimento + valorMedicamentos));
		paciente.registraGasto(gastoComDesconto);
	}

	@Override
	public String toString() {
		StringBuilder saida = new StringBuilder();
		saida.append("....... Data: " + getData() + " Medico: " + getMedico());
		return saida.toString();
	}
}
