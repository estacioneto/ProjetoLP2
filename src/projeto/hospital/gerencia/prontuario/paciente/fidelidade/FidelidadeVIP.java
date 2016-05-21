package projeto.hospital.gerencia.prontuario.paciente.fidelidade;

import java.io.Serializable;

/**
 * Fidelidade VIP.
 * 
 * @author Estacio Pereira
 * @author Thaynan Andrey
 */
public class FidelidadeVIP implements Fidelidade, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5868172159104966668L;
	private final double DESCONTO = 0.3;
	private final double BONUS = 0.1;
	
	@Override
	public double getDescontoServico() {
		return this.DESCONTO;
	}

	@Override
	public double getCreditoBonus() {
		return this.BONUS;
	}

}
