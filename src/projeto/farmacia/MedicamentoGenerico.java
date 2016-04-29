package projeto.farmacia;

public class MedicamentoGenerico extends Medicamento{

	public static double DESCONTO = 0.6;
	
	public MedicamentoGenerico(String nome, double preco, int quantidade) throws Exception {
		super(nome, preco, quantidade);
	}

	@Override
	public double calculaPreco(){
		return super.calculaPreco() * DESCONTO;
	}
}
