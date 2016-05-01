package projeto.farmacia;

import projeto.exceptions.dados.DadoInvalidoException;

public class MedicamentoFactory {

	private Medicamento criaMedicamentoGenerico(String nome, Double preco,
			int quantidade, String tipoMedicamento, String categorias) {
		Medicamento medicamento = new MedicamentoGenerico(nome, preco,
				quantidade, categorias);
		return medicamento;
	}

	private Medicamento criaMedicamentoReferencia(String nome, Double preco,
			int quantidade, String tipoMedicamento, String categorias) {
		Medicamento medicamento = new MedicamentoReferencia(nome, preco,
				quantidade, categorias);
		return medicamento;
	}

	public Medicamento criaMedicamento(String nome, String tipoMedicamento, Double preco,
			int quantidade, String categorias) {
		Medicamento medicamento;
		if (tipoMedicamento.equalsIgnoreCase("generico")) {
			medicamento = this.criaMedicamentoGenerico(nome, preco, quantidade,
					tipoMedicamento, categorias);
		} else if (tipoMedicamento.equalsIgnoreCase("referencia")) {
			medicamento = this.criaMedicamentoReferencia(nome, preco,
					quantidade, tipoMedicamento, categorias);
		} else {
			throw new DadoInvalidoException(
					"Nao existe este tipo de medicamento.");
		}
		return medicamento;
	}
}