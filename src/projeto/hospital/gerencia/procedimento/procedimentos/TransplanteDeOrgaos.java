package projeto.hospital.gerencia.procedimento.procedimentos;

import projeto.hospital.gerencia.bancodeorgaos.Orgao;
import projeto.hospital.gerencia.prontuario.Prontuario;
import projeto.hospital.gerencia.prontuario.paciente.Paciente;
import projeto.util.Constantes;

/**
 * Classe responsavel por abstrair a ideia do procedimento de transplante de orgao
 * 
 * @author Eric
 */
public class TransplanteDeOrgaos extends Procedimento {
	/**
	 * Id gerado automaticamente
	 */
	private static final long serialVersionUID = 5084788845867487596L;

	private Orgao orgao;

	private final String NOME_PROCEDIMENTO = "Transplante de Orgaos";
	private final Double PRECO_TRANSPLANTE = 12500.00;
	private final Integer PONTUACAO_TRANSPLANTE_ORGAOS = 160;

	/**
	 * Construtor
	 * 
	 * @param dataRealizacao
	 *            Data em que o procedimento foi realizado
	 * @param nomeMedico
	 *            Nome do medico que realizou o procedimento
	 * @param orgao
	 *            Orgao utilizado no procedimento
	 */
	public TransplanteDeOrgaos(String dataRealizacao, String nomeMedico, Orgao orgao) {
		super(dataRealizacao, nomeMedico);
		this.orgao = orgao;
	}

	@Override
	public void realizaProcedimento(Prontuario prontuario, Double valorMedicamentos) {
		Paciente paciente = prontuario.getPaciente();
		prontuario.registraProcedimento(this);
		this.registradorDeGastos(paciente, PRECO_TRANSPLANTE, valorMedicamentos);
		paciente.registradorDePontos(PONTUACAO_TRANSPLANTE_ORGAOS);
	}

	@Override
	public String toString() {
		String saida = new String();
		saida += "--> " + NOME_PROCEDIMENTO + ":" + Constantes.QUEBRA_LINHA;
		saida += super.toString() + Constantes.QUEBRA_LINHA;
		saida += "....... Orgao transplantado: " + this.orgao.getNome(); 
		return saida;
	}
}
