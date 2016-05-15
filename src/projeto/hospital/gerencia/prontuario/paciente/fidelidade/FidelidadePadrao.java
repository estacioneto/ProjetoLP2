package projeto.hospital.gerencia.prontuario.paciente.fidelidade;

import java.io.Serializable;

import projeto.util.Constantes;

/**
 * Fidelidade padrao.
 * 
 * @author Estacio Pereira
 */
public class FidelidadePadrao implements Fidelidade, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2425017466808695066L;

	@Override
	public double getDescontoServico() {
		return Constantes.ZERO;
	}

	@Override
	public double getCreditoBonus() {
		return Constantes.ZERO;
	}

}
