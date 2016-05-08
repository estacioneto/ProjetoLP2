package projeto.hospital.gerencia.prontuario.paciente.fidelidade;

/**
 * Fidelidade VIP.
 * 
 * @author Estacio Pereira
 *
 */
public class FidelidadeVIP implements Fidelidade {

	private final double DESCONTO = 30;
	private final double BONUS = 10;
	
	@Override
	public double getDescontoServico() {
		return this.DESCONTO;
	}

	@Override
	public double getCreditoBonus() {
		return this.BONUS;
	}

}
