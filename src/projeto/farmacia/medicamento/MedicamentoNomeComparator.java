package projeto.farmacia.medicamento;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Classe responsavel por ordenar uma lista de medicamentos a partir do nome dos
 * mesmos.
 * 
 * @author Thaynan
 *
 */
public class MedicamentoNomeComparator implements Comparator<Medicamento>,
		Serializable {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 6335375792078771774L;

	/**
	 * Metodo que compara dois elementos pelo preco.
	 * 
	 * @return Um valor inteiro
	 */
	public int compare(Medicamento medicamento, Medicamento outroMedicamento) {
		return medicamento.getNome().compareTo(outroMedicamento.getNome());
	}

}