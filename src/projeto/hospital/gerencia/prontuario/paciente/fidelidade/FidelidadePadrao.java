package projeto.hospital.gerencia.prontuario.paciente.fidelidade;

import projeto.util.Constantes;

/**
 * Fidelidade padrao.
 * 
 * @author Estacio Pereira
 */
public class FidelidadePadrao implements Fidelidade {

	@Override
	public double getDescontoServico() {
		return Constantes.ZERO;
	}

	@Override
	public double getCreditoBonus() {
		return Constantes.ZERO;
	}

}
