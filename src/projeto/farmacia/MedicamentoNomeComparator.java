package projeto.farmacia;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 
 * @author Thaynan
 *
 */
public class MedicamentoNomeComparator implements Comparator<Medicamento>,
		Serializable {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo que compara dois elementos pelo preco.
	 * 
	 * @return Um valor inteiro
	 */
	public int compare(Medicamento medicamento, Medicamento outroMedicamento) {
		return medicamento.getNome().compareTo(outroMedicamento.getNome());
	}

}