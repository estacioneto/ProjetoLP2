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
	private static final long serialVersionUID = -8170936533271144437L;

	@Override
	public Double getDescontoServico() {
		Double zero = 0.0; 
		return zero;
	}

	@Override
	public Integer getCreditoBonus() {
		return Constantes.ZERO;
	}

}
