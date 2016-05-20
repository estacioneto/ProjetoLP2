package projeto.hospital.gerencia.prontuario.paciente.fidelidade;

import java.io.Serializable;

/**
 * Fidelidade Master.
 * 
 * @author Estacio Pereira
 * @author Thaynan Andrey
 */
public class FidelidadeMaster implements Fidelidade, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5195811085847684506L;
	private final double DESCONTO = 0.15;
	private final double BONUS = 0.05;

	@Override
	public double getDescontoServico() {
		return this.DESCONTO;
	}

	@Override
	public double getCreditoBonus() {
		return this.BONUS;
	}

}
