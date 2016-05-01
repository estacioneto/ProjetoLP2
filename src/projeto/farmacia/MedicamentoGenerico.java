package projeto.farmacia;

public class MedicamentoGenerico extends Medicamento{

	public static final double DESCONTO_GENERICO = 60;
	public static final double DESCONTO_GENERICO_PORCENTAGEM = 100;
	
	public MedicamentoGenerico(String nome, Double preco, int quantidade, String categorias){
		super(nome, preco, quantidade, categorias);
	}

	public String getTipo(){
		return "Generico";
	}
	
	@Override
	public Double calculaPreco(){
		return super.getPreco() * DESCONTO_GENERICO / DESCONTO_GENERICO_PORCENTAGEM;
	}
	
	@Override
	public String toString() {
		String formatacao = "Medicamento Generico:" + super.toString();
		return formatacao;
	}
}