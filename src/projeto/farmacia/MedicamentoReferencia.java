package projeto.farmacia;

public class MedicamentoReferencia extends Medicamento{

	public MedicamentoReferencia(String nome, double preco, int quantidade, String categorias){
		super(nome, preco, quantidade, categorias);
	}

	public String getTipo(){
		return "de Referencia";
	}
	
	@Override
	public Double calculaPreco(){
		return super.getPreco();
	}
	
	@Override
	public String toString() {
		String formatacao = "Medicamento de Referencia:" + super.toString();
		return formatacao;
	}
}