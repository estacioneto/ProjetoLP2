package projeto.hospital.gerencia.prontuario.paciente.fidelidade;

import java.io.Serializable;

/**
 * Fidelidade Master.
 * 
 * @author Estacio Pereira
 *
 */
public class FidelidadeMaster implements Fidelidade, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6401691713873953862L;
	private final double DESCONTO = 15;
	private final double BONUS = 5;
	
	@Override
	public Double getDescontoServico() {
		return this.DESCONTO;
	}

	@Override
	public Integer getCreditoBonus() {
		return (int) this.BONUS;
	}

}
