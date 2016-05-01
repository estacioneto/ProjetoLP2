package projeto.farmacia;

public class MedicamentoReferencia extends Medicamento{

	public MedicamentoReferencia(String nome, double preco, int quantidade, String categorias) throws Exception {
		super(nome, preco, quantidade, categorias);
	}

	@Override
	public double calculaPreco(){
		return super.getPreco();
	}
	
	@Override
	public String toString() {
		String formatacao = super.toString() + " Tipo: Referencia;";
		return formatacao;
	}
}