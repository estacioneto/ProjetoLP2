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
	private static final long serialVersionUID = 5868172159104966668L;
	private final Double DESCONTO = 30.0;
	private final Integer BONUS = 10;
	
	@Override
	public Double getDescontoServico() {
		return this.DESCONTO;
	}

	@Override
	public Integer getCreditoBonus() {
		return this.BONUS;
	}

}
