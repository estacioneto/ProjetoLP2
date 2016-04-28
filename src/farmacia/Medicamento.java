package farmacia;

public class Medicamento {

	private String nome;
	private double preco;
	private int quantidade;
	
	public Medicamento(String nome, double preco, int quantidade) throws Exception{
		if(nome == null || nome.trim().isEmpty()){
			throw new Exception("Nome de medicamento nao pode ser nulo ou vazio.");
		}
		if(preco < 0){
			throw new Exception("Nao ha medicamento com valor negativo.");
		}
		if(quantidade <= 0){
			throw new Exception("Nao ha quantidade nula ou negativa de medicamento.");
		}
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
}
