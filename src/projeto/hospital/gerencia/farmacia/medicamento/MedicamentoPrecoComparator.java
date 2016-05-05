package projeto.hospital.gerencia.farmacia.medicamento;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Classe responsavel por ordenar uma lista de medicamentos a partir do preco
 * final.
 * 
 * @author Thaynan
 *
 */
public class MedicamentoPrecoComparator implements Comparator<Medicamento>,
		Serializable {
	
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 5428598739769016904L;

	/**
	 * Metodo que compara dois elementos pelo preco.
	 * 
	 * @return Um valor inteiro
	 */
	public int compare(Medicamento medicamento, Medicamento outroMedicamento) {
		if (medicamento.calculaPreco() > outroMedicamento.calculaPreco()) {
			return 1;
		} else if (medicamento.calculaPreco() < outroMedicamento.calculaPreco()) {
			return -1;
		}
		return 0;
	}

}