package projeto.hospital.gerencia.prontuario.paciente.fidelidade;

/**
 * Fidelidade Master.
 * 
 * @author Estacio Pereira
 *
 */
public class FidelidadeMaster implements Fidelidade {

	private final double DESCONTO = 15.0;
	private final double BONUS = 5.0;

	@Override
	public double getDescontoServico() {
		return this.DESCONTO;
	}

	@Override
	public double getCreditoBonus() {
		return this.BONUS;
	}

}
