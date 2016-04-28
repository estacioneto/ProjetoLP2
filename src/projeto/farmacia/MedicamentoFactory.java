package projeto.farmacia;

public class MedicamentoFactory {

	private Medicamento criaMedicamentoGenerico(String nome, double preco, int quantidade, String tipoMedicamento) throws Exception{
		Medicamento medicamento = new MedicamentoGenerico(nome, preco, quantidade);
		return medicamento;
	}
	private Medicamento criaMedicamentoReferencia(String nome, double preco, int quantidade, String tipoMedicamento) throws Exception{
		Medicamento medicamento = new MedicamentoReferencia(nome, preco, quantidade);
		return medicamento;
	}
	
	public Medicamento criaMedicamento(String nome, double preco, int quantidade, String tipoMedicamento) throws Exception{
		Medicamento medicamento;
		if(tipoMedicamento.equalsIgnoreCase("generico")){
			medicamento = this.criaMedicamentoGenerico(nome, preco, quantidade, tipoMedicamento); 
		}
		else if(tipoMedicamento.equalsIgnoreCase("referencia")){
			medicamento = this.criaMedicamentoReferencia(nome, preco, quantidade, tipoMedicamento);
		}
		else{
			throw new Exception("Nao existe este tipo de medicamento.");
		}
		return medicamento;
	}
}
