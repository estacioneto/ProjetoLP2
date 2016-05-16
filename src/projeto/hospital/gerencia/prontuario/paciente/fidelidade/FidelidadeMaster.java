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
	private static final long serialVersionUID = 5195811085847684506L;
	private final Double DESCONTO = 15.0;
	private final Integer BONUS = 5;

	@Override
	public Double getDescontoServico() {
		return this.DESCONTO;
	}

	@Override
	public Integer getCreditoBonus() {
		return this.BONUS;
	}

}
