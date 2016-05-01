package projeto.farmacia;

public class MedicamentoFactory {

	private Medicamento criaMedicamentoGenerico(String nome, double preco, int quantidade, String tipoMedicamento, String categorias) throws Exception{
		Medicamento medicamento = new MedicamentoGenerico(nome, preco, quantidade, categorias);
		return medicamento;
	}
	private Medicamento criaMedicamentoReferencia(String nome, double preco, int quantidade, String tipoMedicamento, String categorias) throws Exception{
		Medicamento medicamento = new MedicamentoReferencia(nome, preco, quantidade, categorias);
		return medicamento;
	}
	
	public Medicamento criaMedicamento(String nome, double preco, int quantidade, String categorias, String tipoMedicamento) throws Exception{
		Medicamento medicamento;
		if(tipoMedicamento.equalsIgnoreCase("generico")){
			medicamento = this.criaMedicamentoGenerico(nome, preco, quantidade, tipoMedicamento, categorias); 
		}
		else if(tipoMedicamento.equalsIgnoreCase("referencia")){
			medicamento = this.criaMedicamentoReferencia(nome, preco, quantidade, tipoMedicamento, categorias);
		}
		else{
			throw new Exception("Nao existe este tipo de medicamento.");
		}
		return medicamento;
	}
}