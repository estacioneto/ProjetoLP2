package projeto.farmacia;

public class MedicamentoGenerico extends Medicamento{

	public static final double DESCONTO_GENERICO = 0.6;
	
	public MedicamentoGenerico(String nome, double preco, int quantidade, String categorias) throws Exception {
		super(nome, preco, quantidade, categorias);
	}

	@Override
	public double calculaPreco(){
		return super.getPreco() * DESCONTO_GENERICO;
	}
	
	@Override
	public String toString() {
		String formatacao = super.toString() + " Tipo: Generico;";
		return formatacao;
	}
}