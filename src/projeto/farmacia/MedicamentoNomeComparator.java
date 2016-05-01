package projeto.farmacia;

import java.util.Comparator;

public class MedicamentoNomeComparator implements Comparator<Medicamento>{

	public int compare(Medicamento medicamento, Medicamento outroMedicamento) {
		return medicamento.getNome().compareTo(outroMedicamento.getNome());
	}

}