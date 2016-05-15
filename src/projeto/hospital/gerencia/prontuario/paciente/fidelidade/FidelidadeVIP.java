package projeto.hospital.gerencia.prontuario.paciente.fidelidade;

import java.io.Serializable;

/**
 * Fidelidade VIP.
 * 
 * @author Estacio Pereira
 *
 */
public class FidelidadeVIP implements Fidelidade, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3376099012314637696L;
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
