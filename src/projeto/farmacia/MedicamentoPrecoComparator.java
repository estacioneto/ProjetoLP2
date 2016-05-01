package projeto.farmacia;

import java.util.Comparator;

public class MedicamentoPrecoComparator implements Comparator<Medicamento>{

	public int compare(Medicamento medicamento, Medicamento outroMedicamento) {
		if(medicamento.calculaPreco() > outroMedicamento.calculaPreco()){
			return 1;
		}
		else if(medicamento.calculaPreco() < outroMedicamento.calculaPreco()){
			return -1;
		}
		return 0;
	}

}