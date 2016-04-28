package farmacia;

import java.util.HashSet;
import java.util.Set;

public class Medicamento implements Comparable<Medicamento>{

	private String nome;
	private double preco;
	private int quantidade;
	private Set<Categorias> categorias;
	
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
		this.categorias = new HashSet<>();
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

	public Set<Categorias> getCategorias() {
		Set<Categorias> copiaCategorias = new HashSet<>(this.categorias);
		return copiaCategorias;
	}
	
	public int getQutDeCategorias(){
		return this.categorias.size();
	}
	
	public double calculaPreco() {
		return preco;
	}

	public void ampliaQutMedicamentos(int quantidade)throws Exception{
		if(quantidade<0){
			throw new Exception("Nao existe quantidade negativa.");
		}
		this.quantidade += quantidade;
	}
	
	public void diminuiQutMedicamentos(int quantidade)throws Exception{
		if(quantidade < 0){
			throw new Exception("Nao existe quantidade negativa.");
		}
		if(quantidade > this.quantidade){
			throw new Exception("Nao ha medicamentos suficientes.");
		}
		this.quantidade -= quantidade;
	}
	
	public boolean addCategoria(Categorias categoria) throws Exception{
		if(categoria == null){
			throw new Exception("Nao eh permitido a adicao de uma categoria nula.");
		}
		boolean condicaoAdicaoCategoria = this.categorias.add(categoria);
		return condicaoAdicaoCategoria;
	}
	
	public boolean removeCategoria(Categorias categoria) throws Exception{
		if(categoria == null){
			throw new Exception("Nao eh permitido a remocao de uma categoria nula.");
		}
		boolean condicaoAdicaoCategoria = this.categorias.remove(categoria);
		return condicaoAdicaoCategoria;
	}
	
	public boolean contemCategoria(Categorias categoria){
		if(this.categorias.contains(categoria)){
			return true;
		}
		return false;
	}
	@Override
	public int compareTo(Medicamento outroMedicamento) {
		if(this.calculaPreco() > outroMedicamento.calculaPreco()){
			return 1;
		}
		else if(this.calculaPreco() < outroMedicamento.calculaPreco()){
			return -1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		String formatacao = String.format("Medicamento: %s; preco: %.2f; quantidade atual: %d.", this.getNome(), this.calculaPreco(), this.getQuantidade());
		return formatacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(preco) != Double
				.doubleToLongBits(other.preco))
			return false;
		return true;
	}	
}