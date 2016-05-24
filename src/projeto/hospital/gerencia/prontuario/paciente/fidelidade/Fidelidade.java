package projeto.hospital.gerencia.prontuario.paciente.fidelidade;

import java.io.Serializable;

/**
 * Interface que define a relacao de fidelidade com o SOOS
 * 
 * @author Estacio Pereira
 * @author Thaynan
 */
public interface Fidelidade extends Serializable {
	/**
	 * Retorna o desconto dos pacientes nos servicos.
	 * 
	 * @return Porcentagem do desconto em servicos do SOOS.
	 */
	public double getDescontoServico();

	/**
	 * Retorna a porcentagem de bonus que um paciente recebe em relacao aos
	 * pontos que ganha no SOOS.
	 * 
	 * @return Porcentagem do bonus de pontos no SOOS.
	 */
	public double getCreditoBonus();
}
