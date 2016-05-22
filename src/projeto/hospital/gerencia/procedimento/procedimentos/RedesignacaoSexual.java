package projeto.hospital.gerencia.procedimento.procedimentos;

import projeto.hospital.gerencia.prontuario.Prontuario;
import projeto.hospital.gerencia.prontuario.paciente.Paciente;
import projeto.util.Constantes;

/**
 * Classe responsavel por abstrair a ideia do procedimento de redesignacao sexual
 * 
 * @author Eric
 */
public class RedesignacaoSexual extends Procedimento {
	/**
	 * Id gerado automaticamente
	 */
	private static final long serialVersionUID = 3300137079106088687L;

	private final String NOME_PROCEDIMENTO = "Redesignacao Sexual";
	private final Double PRECO_REDESIGNICAO_SEXUAL = 9300.00;
	private final Integer PONTUACAO_CIRURGIA_REDESIGNICAO_SEXUAL = 130;

	/**
	 * Construtor
	 * 
	 * @param dataRealizacao
	 *            Data em que o procedimento foi realizado
	 * @param nomeMedico
	 *            Nome do medico que realizou o procedimento
	 */
	public RedesignacaoSexual(String dataRealizacao, String nomeMedico) {
		super(dataRealizacao, nomeMedico);
	}

	@Override
	public void realizaProcedimento(Prontuario prontuario, Double valorMedicamentos) {
		prontuario.registraProcedimento(this);
		Paciente paciente = prontuario.getPaciente();

		this.registradorDeGastos(paciente, PRECO_REDESIGNICAO_SEXUAL, valorMedicamentos);
		paciente.registradorDePontos(PONTUACAO_CIRURGIA_REDESIGNICAO_SEXUAL);

		paciente.mudaGenero();
	}

	@Override
	public String toString() {
		String saida = new String();
		saida += "--> " + NOME_PROCEDIMENTO + ":" + Constantes.QUEBRA_LINHA;
		saida += super.toString();
		return saida;
	}
}
